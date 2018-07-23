package animation;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import general.Utils;

/**
 * The type Animation runner.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;

    /**
     * Instantiates a new Animation runner.
     *
     * @param sleeper the sleeper
     * @param gui     the gui
     */
    public AnimationRunner(Sleeper sleeper, GUI gui) {
        this.gui = gui;
        this.sleeper = sleeper;
        this.framesPerSecond = Utils.FRAMES_PER_SECOND;
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        long milliSecondsPerTick = 1000 / this.framesPerSecond;
        while (true) {
            long start = System.currentTimeMillis();

            DrawSurface ds = this.gui.getDrawSurface();
            animation.doOneFrame(ds, milliSecondsPerTick / 1000.0D);
            if (animation.shouldStop()) {
                break;
            }
            this.gui.show(ds);

            long tickMilliSeconds = System.currentTimeMillis() - start;
            long milliSecondLeftToSleep = milliSecondsPerTick - tickMilliSeconds;
            if (milliSecondLeftToSleep > 0L) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}