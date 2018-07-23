package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;

/**
 * A Sprite interface.
 */
public interface Sprite {

    /**
     * Draws the sprite to the screen.
     *
     * @param surface the surface to draw on.
     */
    void drawOn(DrawSurface surface);

    /**
     * Notifies the sprite that time has passed.
     *
     * @param dt keeps the speed to be according to seconds.
     */
    void timePassed(double dt);

    /**
     * Adds the sprite to the game level.
     *
     * @param gameLevel the game level to add the sprite to.
     */
    void addToGame(GameLevel gameLevel);

    /**
     * Remove from game.
     *
     * @param gameLevel the game level
     */
    void removeFromGame(GameLevel gameLevel);
}