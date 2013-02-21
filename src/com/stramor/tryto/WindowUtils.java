package com.stramor.tryto;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Window;

import com.google.common.base.Preconditions;

public class WindowUtils {
  private WindowUtils() {
    throw new AssertionError();
  }

  /**
   * Pack the window, center it on the screen, and set the window visible.
   *
   * @param window the window to center and show.
   */
  public static void centerOnScreenAndSetVisible(Window window) {
    window.pack();
    centerOnScreen(window);
    window.setVisible(true);
  }

  /**
   * Take the window and center it on the screen.
   * <p/>
   * This works around a bug in setLocationRelativeTo(...): it currently does
   * not take multiple monitors into accounts on all operating systems.
   *
   * @param window the window to center
   */
  public static void centerOnScreen(Window window) {
    Preconditions.checkNotNull(window, "window cannot be null");
    // This works around a bug in setLocationRelativeTo(...): it currently
    // does not take multiple monitors into accounts on all operating
    // systems.
    try {
      // Note that if this is running on a JVM prior to 1.4, then an
      // exception will be thrown and we will fall back to
      // setLocationRelativeTo(...).
      final Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

      final Dimension windowSize = window.getSize();
      final int x = screenBounds.x + ((screenBounds.width - windowSize.width) / 2);
      final int y = screenBounds.y + ((screenBounds.height - windowSize.height) / 2);
      window.setLocation(x, y);
    }
    catch (Throwable t) {
      window.setLocationRelativeTo(window);
    }
  }
}