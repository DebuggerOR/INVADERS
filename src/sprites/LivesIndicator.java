package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import general.Utils;

import java.awt.Color;

/**
 * Lives indicator class.
 */
public class LivesIndicator extends Border {
    private GameLevel gameLevel;

    /**
     * Constructs a livesIndicator.
     *
     * @param gameLevel the gameLevel to add this to.
     */
    public LivesIndicator(GameLevel gameLevel) {
        super(0, 0, 300, Utils.BORDER_SIZE, Utils.INDICATOR_COLOR);
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
        surface.setColor(Color.BLACK);
        surface.drawText(50, 18, "Lives: " + gameLevel.getLivesUpdater().getCurrentLives().getValue(), 18);

        // draw small icons that represent num of lives
        for (int i = 0; i < this.gameLevel.getLivesUpdater().getCurrentLives().getValue(); i++) {
            surface.setColor(Color.YELLOW);
            surface.fillCircle(130 + 20 * i, 13, 10);
            surface.setColor(Color.BLACK);
            surface.fillCircle(130 + 20 * i, 13, 6);
            surface.setColor(Color.YELLOW);
            surface.fillCircle(130 + 20 * i, 10, 6);
            surface.setColor(Color.BLACK);
            surface.fillCircle(126 + 20 * i, 8, 2);
            surface.fillCircle(134 + 20 * i, 8, 2);
        }
    }

    /**
     * Adds this to game.
     */
    public void addToGame() {
        this.gameLevel.addSprite(this);
    }
}