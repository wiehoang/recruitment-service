package vn.unigap.common;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.web.util.ContentCachingRequestWrapper;


/** Extended class used to duplicate request byte data after streaming process. */
public class RepeatableContentCachingRequestWrapper extends ContentCachingRequestWrapper {

  private NewServletInputStream inputStream;

  public RepeatableContentCachingRequestWrapper(HttpServletRequest request) {
    super(request);
  }

  @Override
  public ServletInputStream getInputStream() {
    return this.inputStream;
  }

  /** A method handles duplicating process. */
  public String readAndDuplicate() throws IOException {

    if (inputStream == null) {
      byte[] body = super.getInputStream().readAllBytes();
      this.inputStream = new NewServletInputStream(body);
    }

    return new String(super.getContentAsByteArray());

  }

}
