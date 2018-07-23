package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import general.Utils;

import java.awt.Color;

/**
 * KeyPressStoppableAnimation class.
 */
public class KeyPressStoppableAnimation implements Animation {
    private boolean stop;
    private String key;
    private Animation animation;
    private KeyboardSensor keyboard;
    private Utils utils;
    private boolean isIgnore;
    private boolean isFirstFrame;

    /**
     * Constructs a KeyPressStoppableAnimation.
     *
     * @param keyboard  the keyBoardSensor.
     * @param key       the key to stop.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.utils = new Utils();
        this.stop = false;
        this.keyboard = keyboard;
        this.key = key;
        this.animation = animation;
        this.isFirstFrame = true;
        this.isIgnore = false;
    }

    /**
     * Handles one frame of animation.
     *
     * @param surface drawSurface to do the frame with.
     * @param dt      keeps the speed according to seconds.
     */
    @Override
    public void doOneFrame(DrawSurface surface, double dt) {
        // ignore not first press in a row
        if (this.isFirstFrame) {
            this.isIgnore = this.keyboard.isPressed(this.key);
            this.isFirstFrame = false;
        }

        // do the origin animation
        this.animation.doOneFrame(surface, dt);

        // display press the key to close at down left
        surface.setColor(Color.BLACK);
        surface.drawText(25, Utils.WINDOW_HEIGHT - 25, "Press " + this.key + " to close..", 18);

        // if pressed key (if key = "space" check KeyboardSensor.SPACE_KEY)
        if (keyboard.isPressed(key) || (key.equals("space") && keyboard.isPressed(KeyboardSensor.SPACE_KEY))) {
            // ignore this press
            if (!this.isIgnore) {
                this.stop = true;
            }
        } else {
            // case not pressed
            this.isIgnore = false;
        }
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