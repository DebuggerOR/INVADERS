package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import general.Utils;

import java.awt.Color;

/**
 * Score indicator class.
 */
public class ScoreIndicator extends Border {
    private GameLevel gameLevel;

    /**
     * Constructs a scoreIndicator.
     *
     * @param gameLevel the game level.
     */
    public ScoreIndicator(GameLevel gameLevel) {
        super(300, 0, 200, 0, Utils.INDICATOR_COLOR);
        super.setHeight(Utils.BORDER_SIZE);
        this.gameLevel = gameLevel;
    }

    /**
     * Draws on the surface.
     *
     * @param surface use this to draw.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        super.drawOn(surface);

        // draw text in black
        surface.setColor(Color.BLACK);
        surface.drawText(350, 18, "Score: " + gameLevel.score(), 18);
    }

    /**
     * Adds this to game.
     */
    public void addToGame() {
        super.addToGame(this.gameLevel);
    }
}