package vn.unigap.common;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.util.ContentCachingRequestWrapper;
import java.io.IOException;


public class RepeatableContentCachingRequestWrapper extends ContentCachingRequestWrapper {

    private NewServletInputStream inputStream;

    public RepeatableContentCachingRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public ServletInputStream getInputStream() {
        return this.inputStream;
    }

    public String readAndDuplicate() throws IOException {

        if (inputStream == null) {
            byte[] body = super.getInputStream().readAllBytes();
            this.inputStream = new NewServletInputStream(body);
        }

        return new String(super.getContentAsByteArray());
    }

}
