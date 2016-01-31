package com.jbrown.image2avi.core;

import java.io.*;
import javax.imageio.stream.ImageOutputStream;

public class FilterImageOutputStream extends FilterOutputStream {
  private ImageOutputStream imgOut;

  public FilterImageOutputStream(ImageOutputStream iOut) {
    super(null);
    this.imgOut = iOut;
  }

  @Override
  public void write(int b) throws IOException {
    imgOut.write(b);
  }

  @Override
  public void write(byte b[], int off, int len) throws IOException {
    imgOut.write(b, off, len);
  }

  @Override
  public void flush() throws IOException {
    // System.err.println(this+" discarded flush");
    // imgOut.flush();
  }

  @Override
  public void close() throws IOException {
    try {
      flush();
    } catch (IOException ignored) {
    }
    imgOut.close();
  }
}