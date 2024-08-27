package vn.unigap.config.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import vn.unigap.common.Common;
import vn.unigap.common.RepeatableContentCachingRequestWrapper;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


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
        StringBuilder build = new StringBuilder();

        build.append("REQUEST ");
        build.append("method=[").append(requestWrapper.getMethod()).append("], ");
        build.append("path=[").append(requestWrapper.getRequestURI()).append("], ");
        build.append("parameters=[").append(getParameterMap(requestWrapper)).append("], ");
        build.append("headers=[").append(getHeaderMap(requestWrapper)).append("], ");
        if (body != null) {build.append("body=[").append(body).append("]");}

        log.info(build.toString());
    }

    private void logResponse(ContentCachingResponseWrapper responseWrapper) throws IOException {

        String body = new String(responseWrapper.getContentAsByteArray());

        String build = "RESPONSE " +
                "responseAt=" + Common.currentDateTime() + ", " +
                "statusCode=" + responseWrapper.getStatus() + ", " +
                "header=[" + getHeaderMap(responseWrapper) + "], " +
                "body=[" + body + "]";

        log.info(build);

        responseWrapper.copyBodyToResponse();
    }

    private Map<String, String> getParameterMap(RepeatableContentCachingRequestWrapper requestWrapper) {

        Map<String, String> parameterMap = new HashMap<>();
        Enumeration<String> parameterNames = requestWrapper.getParameterNames();

        while (parameterNames.hasMoreElements()) {
            String key = parameterNames.nextElement();
            String value = requestWrapper.getParameter(key);
            parameterMap.put(key, value);
        }

        return parameterMap;
    }

    private Map<String, String> getHeaderMap(RepeatableContentCachingRequestWrapper requestWrapper) {

        Map<String, String> headerMap = new HashMap<>();
        Enumeration<String> headerNames = requestWrapper.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = requestWrapper.getHeader(key);
            headerMap.put(key, value);
        }

        return headerMap;
    }

    private Map<String, String> getHeaderMap(ContentCachingResponseWrapper responseWrapper) {

        Map<String, String> headerMap = new HashMap<>();
        Collection<String> headerNames = responseWrapper.getHeaderNames();

        for (String headerName : headerNames) {
            String value = responseWrapper.getHeader(headerName);
            headerMap.put(headerName, value);
        }

        return headerMap;
    }
}
