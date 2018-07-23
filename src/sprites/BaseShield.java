package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import collisions.Collidable;
import general.Utils;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;

/**
 * BaseBlock class.
 */
public abstract class BaseShield implements Collidable, Sprite {
    private Rectangle shape;

    /**
     * Constructs a base block.
     *
     * @param xPos   x position of the block.
     * @param yPos   y position of the block.
     * @param width  width of the block.
     * @param height height of the block.
     */
    public BaseShield(int xPos, int yPos, int width, int height) {
        this.shape = new Rectangle(new Point(xPos, yPos), width, height);
    }

    /**
     * Sets blocks height.
     *
     * @param newHeight the new height.
     */
    public void setHeight(int newHeight) {
        this.shape.setHeight(newHeight);
    }

    /**
     * Gives the shape.
     *
     * @return the shape.
     */
    Rectangle getShape() {
        return this.shape;
    }

    /**
     * Gives the collision shape of the object.
     *
     * @return the collision shape of the object.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Gives the block's data as string.
     *
     * @return block's data as string.
     */
    public String toString() {
        return this.shape.toString();
    }

    /**
     * Hit.
     */
    public abstract void hit();

    /**
     * Handles hit.
     *
     * @param hitter the hitter.
     */
    public abstract void hit(Ball hitter);

    /**
     * Draws this ball using given draw surface.
     *
     * @param surface use this to draw.
     */
    public abstract void drawOn(DrawSurface surface);

    /**
     * Adds this to sprites and collidables.
     *
     * @param gameLevel the GameLevel that
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        gameLevel.addCollidable(this);
    }

    /**
     * Removes from game.
     *
     * @param gameLevel the gameLevel to remove from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * No use.
     *
     * @param dt keeps the speed to be according to seconds.
     */
    public void timePassed(double dt) {
    }

    @Override
    public int fromBottom() {
        return (int) (Utils.WINDOW_HEIGHT - (this.shape.getUpperLeft().getY() + this.shape.getHeight()));
    }

    /**
     * Is xPos in range of the x of the shield.
     *
     * @param xPos the x pos.
     * @return is in x range.
     */
    public abstract boolean isInRangeX(int xPos);
}