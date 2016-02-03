package com.jbrown.image2avi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.jbrown.image2avi.core.AVIOutputStream;
import com.jbrown.image2avi.core.VideoFormat;

public class Image2AVI {
  private BufferedImage[] _bufferedImages;
  private VideoFormat _videoFormat;
  private float _frameQuality;

  public Image2AVI(String[] imageNameWithPath) {
    this(new BufferedImage[0], VideoFormat.RAW, 1f);
  }

  public Image2AVI(BufferedImage[] bufferedImages, VideoFormat videoFormat,
      float frameQuality) {
    _bufferedImages = bufferedImages;
    _videoFormat = videoFormat;
    _frameQuality = frameQuality;
  }

  public File getTransformedVideo() throws IOException {
    File tempOutputFile = new File("temp.avi");

    AVIOutputStream out = null;
    Graphics2D g = null;
    try {
        out = new AVIOutputStream(tempOutputFile, _videoFormat);
        out.setVideoCompressionQuality(_frameQuality);

        out.setTimeScale(1);
        out.setFrameRate(30);

        for (BufferedImage image : _bufferedImages) {
            out.writeFrame(image);
        }


    } finally {
        if (g != null) {
            g.dispose();
        }
        if (out != null) {
            out.close();
        }
    }

    return null;
  }

  public BufferedImage[] getBufferedImages() {
    return _bufferedImages;
  }

  public VideoFormat getVideoFormat() {
    return _videoFormat;
  }

  public float getFrameRate() {
    return _frameQuality;
  }
}
