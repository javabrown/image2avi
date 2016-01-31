package image2avi;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import com.jbrown.image2avi.core.AVIOutputStream;
import com.jbrown.image2avi.core.VideoFormat;

public class Main {
  public static void main(String[] args) {
      try {
          test(new File("avi-jpg.avi"),
              VideoFormat.JPG, 1f);
          test(new File("avi-png.avi"),
              VideoFormat.PNG, 1f);
          test(new File("avi-raw.avi"),
              VideoFormat.RAW, 1f);

      } catch (IOException ex) {
          ex.printStackTrace();
      }
  }

  private static void test(File file, VideoFormat format, float quality) throws IOException {
      AVIOutputStream out = null;
      Graphics2D g = null;
      try {
          out = new AVIOutputStream(file, format);
          out.setVideoCompressionQuality(quality);

          out.setTimeScale(1);
          out.setFrameRate(30);

          Random r = new Random(0); // use seed 0 to get reproducable output
          BufferedImage img = new BufferedImage(320, 160, BufferedImage.TYPE_INT_RGB);
          g = img.createGraphics();
          g.setBackground(Color.WHITE);
          g.clearRect(0, 0, img.getWidth(), img.getHeight());

          for (int i = 0; i < 100; i++) {
              g.setColor(new Color(r.nextInt()));
              g.fillRect(r.nextInt(img.getWidth() - 30), r.nextInt(img.getHeight() - 30), 30, 30);
              out.writeFrame(img);
          }

          System.out.println("Done!!");

      } finally {
          if (g != null) {
              g.dispose();
          }
          if (out != null) {
              out.close();
          }
      }
  }
}