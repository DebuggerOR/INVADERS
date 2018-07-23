package animation;

import biuoop.DrawSurface;
import general.Utils;

import java.awt.Color;

/**
 * Pause screen class.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Constructs a pause screen.
     */
    PauseScreen() {
        this.stop = false;
    }

    /**
     * Handles one frame of animation.
     *
     * @param surface drawSurface to do the frame with.
     * @param dt      keeps the speed according to seconds.
     */
    @Override
    public void doOneFrame(DrawSurface surface, double dt) {
        // draw frame
        surface.setColor(Color.YELLOW);
        surface.fillRectangle(0, 0, Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        surface.setColor(Color.WHITE);
        surface.fillRectangle(20, 20, Utils.WINDOW_WIDTH - 40, Utils.WINDOW_HEIGHT - 40);

        // draw image
        surface.drawImage(300, 110, Utils.pauseImage());

        // draw oval
        surface.setColor(Color.YELLOW);
        surface.fillOval(270 - 2, 260 - 2, 260 + 4, 100 + 4);
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillOval(270, 260, 260, 100);

        // you win two colors
        surface.setColor(Color.BLACK);
        surface.drawText(320 - 2, 324 - 2, "Paused", 46);
        surface.setColor(Color.YELLOW);
        surface.drawText(320, 324, "Paused", 46);
    }

    /**
     * Checks if animation should stop.
     *
     * @return if animation should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}