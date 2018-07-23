package animation;

import biuoop.DrawSurface;
import general.Utils;

import java.awt.Color;

/**
 * EndScreen class.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private int score;

    /**
     * Constructs an end screen.
     *
     * @param score the score.
     */
    public EndScreen(int score) {
        this.stop = false;
        this.score = score;
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
        surface.setColor(new Color(153, 0, 0));
        surface.fillRectangle(0, 0, Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        surface.setColor(Color.WHITE);
        surface.fillRectangle(20, 20, Utils.WINDOW_WIDTH - 40, Utils.WINDOW_HEIGHT - 40);

        // draw image
        surface.drawImage(280, 108, Utils.loseImage());

        // draw oval
        surface.setColor(new Color(153, 0, 0));
        surface.fillOval(270 - 2, 260 - 2, 260 + 4, 100 + 4);
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillOval(270, 260, 260, 100);

        // game over two colors
        surface.setColor(Color.BLACK);
        surface.drawText(289 - 2, 324 - 2, "Game over", 46);
        surface.setColor(new Color(153, 0, 0));
        surface.drawText(289, 324, "Game over", 46);

        // displays score down left
        surface.setColor(Color.BLACK);
        surface.drawText(25, 555, "Your score: " + score, 18);
    }

    /**
     * Tells if animation should stop.
     *
     * @return if animation should stop..
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
