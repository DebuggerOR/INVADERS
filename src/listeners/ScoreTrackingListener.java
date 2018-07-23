package listeners;

import general.Counter;
import geometry.Ball;
import sprites.Sprite;

/**
 * Score tracking listener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a ScoreTrackingListener by given score.
     *
     * @param scoreCounter the given score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Gives the current score.
     *
     * @return the current score.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    @Override
    public void hitEvent(Sprite beingHit, Ball hitter) {
        this.currentScore.increase(100);
    }

    @Override
    public void reachShieldEvent() {
    }
}