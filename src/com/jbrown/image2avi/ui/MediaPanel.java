package com.jbrown.image2avi.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.net.URL;
import javax.media.CannotRealizeException;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.swing.JPanel;

public class MediaPanel extends JPanel {
  public MediaPanel(URL mediaURL) {
    setLayout(new BorderLayout());

    // Use lightweight components for Swing compatibility
    Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, true);

    try {
      Player mediaPlayer = Manager.createRealizedPlayer(mediaURL);
      Component video = mediaPlayer.getVisualComponent();
      Component controls = mediaPlayer.getControlPanelComponent();

      if (video != null)
        add(video, BorderLayout.CENTER); // add video component

      if (controls != null)
        add(controls, BorderLayout.SOUTH); // add controls

      mediaPlayer.start(); // start playing the media clip
    } // end try
    catch (NoPlayerException noPlayerException) {
      System.err.println("No media player found");
    } catch (CannotRealizeException cannotRealizeException) {
      System.err.println("Could not realize media player");
    } catch (IOException iOException) {
      System.err.println("Error reading from the source");
    }
  }
}