package listeners;

import animation.GameLevel;
import general.Counter;
import geometry.Ball;
import sprites.AlienCollection;
import sprites.Sprite;

/**
 * The type Alien remover.
 */
public class AlienRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingAliens;
    private AlienCollection alienCollection;

    /**
     * Constructs a block remover.
     *
     * @param gameLevel the gameLevel.
     * @param remaining the count of the remaining blocks.
     */
    public AlienRemover(GameLevel gameLevel, Counter remaining) {
        this.gameLevel = gameLevel;
        this.remainingAliens = new Counter(remaining.getValue());
    }

    /**
     * Removes zero hit points blocks.
     *  @param beingHit the hit block.
     * @param hitter the hitter ball.
     */
    public void hitEvent(Sprite beingHit, Ball hitter) {
        hitter.removeFromGame();
        beingHit.removeFromGame(this.gameLevel);
        this.alienCollection.removeAlien(beingHit);
        this.remainingAliens.decrease(1);
    }

    /**
     * Gives the remaining blocks.
     *
     * @return the remaining blocks.
     */
    public Counter getRemainingAliens() {
        return this.remainingAliens;
    }

    @Override
    public void reachShieldEvent() {

    }

    /**
     * Sets alien collection.
     *
     * @param newAlienCollection the alien collection
     */
    public void setAlienCollection(AlienCollection newAlienCollection) {
        this.alienCollection = newAlienCollection;
    }
}
