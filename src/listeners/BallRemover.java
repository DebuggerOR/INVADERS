package listeners;

import animation.GameLevel;
import geometry.Ball;
import sprites.Sprite;

/**
 * Ball remover class.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel the game level
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    @Override
    public void hitEvent(Sprite beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
    }

    @Override
    public void reachShieldEvent() {

    }
}
