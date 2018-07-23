package collisions;

import geometry.Ball;
import geometry.Rectangle;

/**
 * Collidable interface.
 *
 * @author Ori.
 */
public interface Collidable {

    /**
     * Gets the shape of the object.
     *
     * @return the collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Hit.
     *
     * @param hitter the hitter
     */
    void hit(Ball hitter);

    /**
     * From bottom int.
     *
     * @return the int
     */
    int fromBottom();

    /**
     * Is in range x boolean.
     *
     * @param xPos the x pos
     * @return the boolean
     */
    boolean isInRangeX(int xPos);
}
