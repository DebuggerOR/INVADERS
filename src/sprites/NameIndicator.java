package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import general.Utils;

import java.awt.Color;

/**
 * Score indicator class.
 */
public class NameIndicator extends Border {
    private GameLevel gameLevel;

    /**
     * Constructs a nameIndicator.
     *
     * @param gameLevel the gameLevel to add this to.
     */
    public NameIndicator(GameLevel gameLevel) {
        super(500, 0, 300, Utils.BORDER_SIZE, Utils.INDICATOR_COLOR);
        this.gameLevel = gameLevel;
    }

    /**
     * Draws this ball using given draw surface.
     *
     * @param surface use this to draw.
     */
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);
        // draw text in black
        String name = this.gameLevel.getLevelName();
        surface.setColor(Color.BLACK);
        surface.drawText(600 - 5 * name.length(), 18, "Level Name: " + name, 18);
    }

    /**
     * Adds this to game.
     */
    public void addToGame() {
        this.gameLevel.addSprite(this);
    }
}