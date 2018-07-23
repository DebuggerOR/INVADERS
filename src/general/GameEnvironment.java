package general;

import collisions.Collidable;
import collisions.CollisionInfo;
import geometry.Line;
import geometry.Point;
import sprites.Alien;
import sprites.SpaceShip;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }


    /**
     * Add collidable.
     *
     * @param collidable the collidable
     */
    public void addCollidable(Collidable collidable) {
        this.collidables.add(collidable);
    }


    /**
     * Remove collidable.
     *
     * @param collidable the collidable
     */
    public void removeCollidable(Collidable collidable) {
        this.collidables.remove(collidable);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // greater y means lower
        boolean advanceUp = (trajectory.end().getY() < trajectory.start().getY());
        int xPos = (int) trajectory.start().getX();
        List<Collidable> collideCollidables = new ArrayList<>();

        // case balls go up - from spaceship
        if (advanceUp) {
            for (Collidable collidable1 : this.collidables) {
                // case not spaceship and in x rang and in y range
                if (!(collidable1 instanceof SpaceShip) && collidable1.isInRangeX(xPos) && collidable1.fromBottom()
                        <= (Utils.WINDOW_HEIGHT - trajectory.end().getY())) {
                    collideCollidables.add(collidable1);
                }
            }
            // case balls go down - from aliens
        } else {
            for (Collidable collidable1 : this.collidables) {
                // case not alien and in x range and in y range
                if (!(collidable1 instanceof Alien) && collidable1.isInRangeX(xPos)
                        && collidable1.getCollisionRectangle().getUpperLeft().getY() <= trajectory.end().getY()) {
                    collideCollidables.add(collidable1);
                }
            }
        }
        // case no potentials collidables return null
        if (collideCollidables.size() == 0) {
            return null;
        }
        // find the closest collidables from potentials
        Collidable closestCollidable = collideCollidables.get(0);
        // case go up
        if (advanceUp) {
            for (Collidable collideCollidable : collideCollidables) {
                // take the lowest
                if (closestCollidable.fromBottom() > collideCollidable.fromBottom()) {
                    closestCollidable = collideCollidable;
                }
            }
            // case go down
        } else {
            for (Collidable collideCollidable : collideCollidables) {
                // take the highest
                if (closestCollidable.getCollisionRectangle().getUpperLeft().getY()
                        >= collideCollidable.getCollisionRectangle().getUpperLeft().getY()) {
                    closestCollidable = collideCollidable;
                }
            }
        }

        Point collosionPoint;
        // case go up
        if (advanceUp) {
            collosionPoint = new Point(xPos, closestCollidable.fromBottom());
            // case go down
        } else {
            collosionPoint = new Point(xPos, closestCollidable.getCollisionRectangle().getUpperLeft().getY());
        }
        return new CollisionInfo(collosionPoint, closestCollidable);
    }
}
