package sprites;

import animation.GameLevel;
import general.Utils;
import listeners.HitListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Shield collection.
 */
public class ShieldCollection {
    private List<Shield> shields;

    /**
     * Instantiates a new Shield collection.
     *
     * @param xPos the x pos
     */
    public ShieldCollection(int xPos) {
        this.shields = new ArrayList<>();
        for (int c = 0; c < Utils.SHIELDS_COLOUMN; c++) {
            for (int r = 0; r < Utils.SHIELDS_ROW; r++) {
                this.shields.add(new Shield(xPos + c * Utils.SHIELD_WIDTH,
                        Utils.SHIELDS_YPOS + r * Utils.SHIELD_HEIGHT));
            }
        }
    }

    /**
     * Add to game.
     *
     * @param gameLevel the game level
     */
    public void addToGame(GameLevel gameLevel) {
        for (Shield shield : this.shields) {
            shield.addToGame(gameLevel);
        }
    }

    /**
     * Remove from game.
     *
     * @param gameLevel the game level
     */
    public void removeFromGame(GameLevel gameLevel) {
        for (Shield shield : this.shields) {
            shield.removeFromGame(gameLevel);
        }
    }

    /**
     * Add hit listener.
     *
     * @param listener the listener
     */
    public void addHitListener(HitListener listener) {
        for (Shield shield : this.shields) {
            shield.addHitListener(listener);
        }
    }
}
