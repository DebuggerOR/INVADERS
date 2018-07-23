package listeners;

import animation.GameLevel;
import general.Counter;
import geometry.Ball;
import sprites.Sprite;

/**
 * Score tracking listener class.
 */
public class LivesTrackingListener implements HitListener {
    private Counter currentLives;
    private GameLevel gameLevel;

    /**
     * Constructs a LivesTrackingListener.
     *
     * @param gameLevel    the game level
     * @param livesCounter the init count of lives.
     */
    public LivesTrackingListener(GameLevel gameLevel, Counter livesCounter) {
        this.gameLevel = gameLevel;
        this.currentLives = livesCounter;
    }

    /**
     * No use.
     *  @param beingHit the hit block.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Sprite beingHit, Ball hitter) {
    }

    /**
     * Gives the number of the lives.
     *
     * @return the number of the lives.
     */
    public Counter getCurrentLives() {
        return this.currentLives;
    }

    @Override
    public void reachShieldEvent() {
        this.currentLives.decrease(1);
        gameLevel.setRunning(false);
    }
}