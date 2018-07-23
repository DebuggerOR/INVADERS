package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import collisions.Collidable;
import general.GameEnvironment;
import general.Utils;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Alien.
 */
public class Alien implements Sprite, Collidable, HitNotifier, Shooter {
    private double xPos;
    private double yPos;
    private double firstXPos;
    private double firstYPos;
    private Image image;
    private GameLevel gameLevel;
    private List<HitListener> hitListeners;
    private GameEnvironment environment;
    private int column;

    /**
     * Instantiates a new Alien.
     *
     * @param xPos        the x pos
     * @param yPos        the y pos
     * @param image       the image
     * @param environment the environment
     * @param gameLevel   the game level
     * @param column      the coloumn
     */
    public Alien(int xPos, int yPos, Image image, GameEnvironment environment, GameLevel gameLevel, int column) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.firstXPos = xPos;
        this.firstYPos = yPos;
        this.image = image;
        this.hitListeners = new ArrayList<>();
        this.environment = environment;
        this.gameLevel = gameLevel;
        this.column = column;
    }

    /**
     * Add to game.
     */
    public void addToGame() {
        this.gameLevel.addCollidable(this);
        this.gameLevel.addSprite(this);
    }

    /**
     * Removes from game.
     *
     * @param newGameLevel the game level
     */
    public void removeFromGame(GameLevel newGameLevel) {
        newGameLevel.removeCollidable(this);
        newGameLevel.removeSprite(this);
    }

    /**
     * Remove from game.
     */
    public void removeFromGame() {
        this.gameLevel.removeCollidable(this);
        this.gameLevel.removeSprite(this);
    }

    /**
     * Coloumn int.
     *
     * @return the int
     */
    public int coloumn() {
        return this.column;
    }

    /**
     * No use.
     *
     * @param dt keeps the speed to be according to seconds.
     */
    public void timePassed(double dt) {
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.drawImage((int) this.xPos, (int) this.yPos, this.image);
    }

    @Override
    public void addToGame(GameLevel newGameLevel) {
        newGameLevel.addCollidable(this);
        newGameLevel.addSprite(this);
    }

    /**
     * Sets game level.
     *
     * @param newGameLevel the game level
     */
    public void setGameLevel(GameLevel newGameLevel) {
        this.gameLevel = newGameLevel;
    }

    /**
     * Adds hit listener.
     *
     * @param hl the added hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener listener) {
        this.hitListeners.remove(listener);
    }

    @Override
    public void hit(Ball hitter) {
        // do something if the ball isn't aliens ball
        if (!hitter.isAlienBall()) {
            this.notifyHit(hitter);
        }
    }

    /**
     * Notifies hit.
     *
     * @param hitter the hitter.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return new Rectangle(new Point(xPos, yPos), Utils.ALIENS_WIDTH, Utils.ALIENS_HEIGHT);
    }

    /**
     * X pos double.
     *
     * @return the double
     */
    public double xPos() {
        return this.xPos;
    }

    /**
     * Y pos double.
     *
     * @return the double
     */
    public double yPos() {
        return this.yPos;
    }

    /**
     * Advance x.
     *
     * @param delta the delta
     */
    public void advanceX(double delta) {
        this.xPos += delta;
    }

    /**
     * Advance neg x.
     *
     * @param delta the delta
     */
    public void advanceNegX(double delta) {
        this.xPos -= delta;
    }

    /**
     * Advance y.
     *
     * @param delta the delta
     */
    public void advanceY(double delta) {
        this.yPos += delta;
    }

    /**
     * Sets pos.
     *
     * @param newXPos the x pos
     */
    public void setxPos(int newXPos) {
        this.xPos = newXPos;
    }

    /**
     * Distance from bottom.
     *
     * @return distance from bottom.
     */
    public int fromBottom() {
        return (int) (Utils.WINDOW_HEIGHT - (this.yPos + Utils.ALIENS_HEIGHT));
    }

    @Override
    public boolean isInRangeX(int x) {
        return (x >= this.xPos) && (x <= this.xPos + Utils.ALIENS_WIDTH);
    }

    /**
     * Reset position.
     */
    public void resetPosition() {
        this.xPos = this.firstXPos;
        this.yPos = this.firstYPos;
    }

    @Override
    public void shoot() {
        int xPosMid = (int) ((this.xPos + Utils.ALIENS_WIDTH / 2));
        Point point = new Point(xPosMid, this.yPos + Utils.ALIENS_HEIGHT);
        this.gameLevel.addSprite(new Ball(point, Utils.ALIEN_BALL_SIZE,
                Utils.ALIEN_BALL_VELOCITY, Utils.ALIEN_BALL_COLOR, environment, gameLevel));
    }
}
