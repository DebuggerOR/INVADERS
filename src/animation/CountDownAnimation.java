package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * The type Count down animation.
 */
public class CountDownAnimation implements Animation {
    private boolean stop;
    private boolean isStart;
    private int countFrom;
    private double numSeconds;
    private SpriteCollection gameScreen;

    /**
     * Instantiates a new Count down animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountDownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.isStart = true;
    }

    @Override
    public void doOneFrame(DrawSurface surface, double dt) {
        Sleeper sleeper = new Sleeper();
        // sleep for numSeconds at each start of one frame
        if (!this.isStart) {
            sleeper.sleepFor((long) this.numSeconds);
        }
        this.isStart = false;

        // draw regular screen
        this.gameScreen.drawAllOn(surface);

        // draw oval
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillOval(325 - 2, 240 - 2, 150 + 4, 100 + 4);
        surface.setColor(Color.WHITE);
        surface.fillOval(325, 240, 150, 100);

        if (this.countFrom > 0) {
            // case > 0 display countdown
            surface.setColor(Color.BLACK);
            surface.drawText(385 - 2, 305 - 2, "" + countFrom, 50);
            surface.setColor(Color.LIGHT_GRAY);
            surface.drawText(385, 305, "" + countFrom--, 50);

        } else if (this.countFrom == 0) {
            // case 0 display go
            this.countFrom--;

            surface.setColor(Color.BLACK);
            surface.drawText(350 - 2, 305 - 2, "GO!!", 50);
            surface.setColor(Color.RED);
            surface.drawText(350, 305, "GO!!", 50);

        } else {
            // case < 0 stop countdown
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}