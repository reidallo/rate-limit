
# Rate Limiting Middleware
Background:

You're tasked with implementing a rate limiting middleware for a RESTful API built using Spring Boot. The rate limiter should restrict the number of requests from a client within a specified time window.
## Requirements:
1. Implement a Spring Boot middleware component that applies rate limiting based on the client's IP address.
2. The middleware should allow a maximum of **N** requests per minute (**N** is configurable).
3. If a client exceeds the request limit within a minute, subsequent requests from that client should receive a "429 Too Many Requests" HTTP response.
4. The rate limiting configuration should be adjustable without requiring application restart.
## Additional Details:
- You can use any appropriate data structure or mechanism for tracking request counts and timestamps.
- Consider thread safety and performance implications.
- Ensure proper error handling and logging.
- Write unit tests to validate the functionality.
