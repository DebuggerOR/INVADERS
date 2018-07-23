package listeners;

import geometry.Ball;
import sprites.Sprite;

/**
 * HitListener interface.
 */
public interface HitListener {

    /**
     * Handles hit event.
     *
     * @param beingHit the hit block.
     * @param hitter   the hitter ball.
     */
    void hitEvent(Sprite beingHit, Ball hitter);

    /**
     * Reach shield event.
     */
    void reachShieldEvent();
}