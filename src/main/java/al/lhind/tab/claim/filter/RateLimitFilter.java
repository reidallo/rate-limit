package al.lhind.tab.claim.filter;

import al.lhind.tab.claim.exception.RateLimitException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Slf4j
public class RateLimitFilter extends OncePerRequestFilter {

    // Using ConcurrentHashMap and ConcurrentLinkedQueue for thread safety
    private static final ConcurrentHashMap<String, ConcurrentLinkedQueue<Instant>> rateLimitMap = new ConcurrentHashMap<>();
    private static final int RATE_LIMIT = 10;
    private static final int TIME_WINDOW_SECONDS = 60;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ipAddress = getIpAddress(request);

        if (ipAddress == null || ipAddress.isEmpty()) {
            log.info("IP address not found");
            filterChain.doFilter(request, response);
            return;
        }

        ConcurrentLinkedQueue<Instant> requestTimestamps = rateLimitMap.computeIfAbsent(ipAddress, k -> new ConcurrentLinkedQueue<>());

        Instant now = Instant.now();
        Instant timeWindowStart = now.minusSeconds(TIME_WINDOW_SECONDS);

        // Remove timestamps older than the time window (LinkedQueue contains elements in FIFO order)
        while (!requestTimestamps.isEmpty() && requestTimestamps.peek().isBefore(timeWindowStart)) {
            requestTimestamps.poll();
        }

        // Check if the rate limit is exceeded
        if (requestTimestamps.size() >= RATE_LIMIT) {
            log.error("Rate limit exceeded for IP address: {}, {}", ipAddress, requestTimestamps.size());
            response.sendError(new RateLimitException("Too many requests").getStatus().value());
            return;
        }

        // Record the current request's timestamp
        requestTimestamps.add(now);

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    private String getIpAddress(HttpServletRequest request) {
        return request.getHeader("X-FORWARDED-FOR") != null
                ? request.getHeader("X-FORWARDED-FOR")
                : request.getRemoteAddr();
    }
}
