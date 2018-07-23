package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ColorBackground class.
 */
public class ColorBackground implements Sprite {
    private Color color;

    /**
     * Constructs a ColorBackground.
     *
     * @param color the color.
     */
    public ColorBackground(Color color) {
        this.color = color;
    }

    /**
     * Draws the background on the surface.
     *
     * @param surface the surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
    }

    /**
     * No use.
     *
     * @param dt keeps the speed to be according to seconds.
     */
    @Override
    public void timePassed(double dt) {
    }

    /**
     * Adds this to sprites.
     *
     * @param gameLevel the GameLevel that
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
