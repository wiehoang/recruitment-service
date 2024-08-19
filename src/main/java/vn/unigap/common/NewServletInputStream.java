package vn.unigap.common;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public class NewServletInputStream extends ServletInputStream {

    private InputStream inputStream;

    public NewServletInputStream(byte[] data) {
        this.inputStream = new ByteArrayInputStream(data);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int read() throws IOException {
        return this.inputStream.read();
    }
}