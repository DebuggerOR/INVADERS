package geometry;

import animation.GameLevel;
import biuoop.DrawSurface;
import collisions.CollisionInfo;
import general.GameEnvironment;
import general.Utils;
import sprites.Sprite;

import java.awt.Color;

/**
 * Ball class.
 *
 * @author Ori.
 */
public class Ball implements Sprite {
    private Color color;
    private Circle circle;
    private Velocity velocity;
    private GameEnvironment environment;
    private GameLevel gameLevel;
    private boolean isAlienBall;

    /**
     * Instantiates a new Ball.
     *
     * @param center      the center
     * @param radius      the radius
     * @param velocity    the velocity
     * @param color       the color
     * @param environment the environment
     * @param gameLevel   the game level
     */
    public Ball(Point center, int radius, Velocity velocity, Color color, GameEnvironment environment,
                GameLevel gameLevel) {
        this.circle = new Circle(center, radius);
        this.velocity = new Velocity(velocity);
        this.color = color;
        this.environment = environment;
        this.gameLevel = gameLevel;
        this.isAlienBall = (this.velocity.getDy() > 0);
    }

    /**
     * Is alien ball boolean.
     *
     * @return the boolean
     */
    public boolean isAlienBall() {
        return isAlienBall;
    }

    /**
     * Draws this ball using given draw surface.
     *
     * @param surface use this to draw.
     */
    public void drawOn(DrawSurface surface) {
        // fill circle with color
        surface.setColor(this.color);
        surface.fillCircle((int) circle.getCenter().getX(), (int) circle.getCenter().getY(), circle.getRadius());
    }

    /**
     * Adds this ball to given game.
     *
     * @param newGameLevel the GameLevel that this added to.
     */
    public void addToGame(GameLevel newGameLevel) {
        newGameLevel.addSprite(this);
    }

    /**
     * Handles balls behavior when time passed.
     *
     * @param dt keeps the speed to be according seconds.
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }

    /**
     * Check out side window boolean.
     *
     * @return the boolean
     */
    public boolean checkOutSideWindow() {
        int yPos = (int) this.circle.getCenter().getY();
        if (yPos < 0 || yPos > Utils.WINDOW_HEIGHT) {
            this.removeFromGame();
            return true;
        }
        return false;
    }

    /**
     * Handles the movement of the ball.
     *
     * @param dt keeps the speed to be according to seconds.
     */
    private void moveOneStep(double dt) {
        if (this.checkOutSideWindow()) {
            return;
        }
        // ball trajectory
        Line trajectory = new Line(circle.getCenter(), velocity.applyToPoint(circle.getCenter(), dt));
        // collision information
        CollisionInfo collisionInfo = this.environment.getClosestCollision(trajectory);

        // case no collision move to end of trajectory
        if (collisionInfo == null) {
            this.circle.setCenter(trajectory.end());
            // case collision
        } else {
            // move very close the collision point
            double xMove = (collisionInfo.collisionPoint().getX() - this.circle.getCenter().getX()) * 0.9999D;
            double yMove = (collisionInfo.collisionPoint().getY() - this.circle.getCenter().getY()) * 0.9999D;
            this.circle.setCenter(new Point(circle.getCenter().getX() + xMove, circle.getCenter().getY() + yMove));
            collisionInfo.collisionObject().hit(this);
        }
    }

    /**
     * Removes from game.
     */
    public void removeFromGame() {
        this.gameLevel.removeSprite(this);
    }

    /**
     * Sets game level.
     *
     * @param newGameLevel the game level
     */
    public void setGameLevel(GameLevel newGameLevel) {
        this.gameLevel = newGameLevel;
    }

    @Override
    public void removeFromGame(GameLevel newGameLevel) {
        newGameLevel.removeSprite(this);
    }
}