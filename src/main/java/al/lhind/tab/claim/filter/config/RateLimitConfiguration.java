package al.lhind.tab.claim.filter.config;

import al.lhind.tab.claim.filter.RateLimitFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RateLimitConfiguration {

    @Bean
    public FilterRegistrationBean<RateLimitFilter> rateLimitFilter() {
        FilterRegistrationBean<RateLimitFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitFilter());
        // specify which URLs the filter should be applied to
        registrationBean.addUrlPatterns("/claims/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
