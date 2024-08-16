package vn.unigap.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import vn.unigap.common.RepeatableContentCachingRequestWrapper;
import java.io.IOException;


@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        RepeatableContentCachingRequestWrapper requestWrapper = new RepeatableContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        logRequest(requestWrapper);
        chain.doFilter(requestWrapper, responseWrapper);
        logResponse(responseWrapper);
    }

    private void logRequest(RepeatableContentCachingRequestWrapper requestWrapper) throws IOException {
        String body = requestWrapper.readAndDuplicate();
        log.info("Request {}", body);
    }

    private void logResponse(ContentCachingResponseWrapper responseWrapper) throws IOException {
        log.info("Response {}", new String(responseWrapper.getContentAsByteArray()));
        responseWrapper.copyBodyToResponse();
    }
}
