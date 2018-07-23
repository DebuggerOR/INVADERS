package listeners;

import animation.GameLevel;
import geometry.Ball;
import sprites.Sprite;

/**
 * Block remover class.
 */
public class ShieldRemover implements HitListener {
    private GameLevel gameLevel;

    /**
     * Constructs a BlockRemover.
     *
     * @param gameLevel the gameLevel.
     */
    public ShieldRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    /**
     * Removes zero hit points blocks.
     *
     * @param beingHit the hit block.
     * @param hitter   the hitter ball.
     */
    public void hitEvent(Sprite beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
    }

    @Override
    public void reachShieldEvent() {
    }
}
