package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.Collidable;
import general.GameEnvironment;
import general.Utils;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * A SpaceShip class.
 *
 * @author Ori.
 */
public class SpaceShip implements Sprite, Collidable, Shooter {
    private Rectangle shape;
    private int speed;
    private Color color;
    private GameEnvironment environment;
    private GameLevel gameLevel;
    private KeyboardSensor keyboard;
    private long startShoot;
    private boolean isHit;

    /**
     * Instantiates a new Space ship.
     *
     * @param width       the width
     * @param height      the height
     * @param speed       the speed
     * @param environment the environment
     * @param gameLevel   the game level
     * @param keyboard    the keyboard
     */
    public SpaceShip(int width, int height, int speed, GameEnvironment environment, GameLevel gameLevel,
                     KeyboardSensor keyboard) {
        this.color = Utils.SPACESHIP_COLOR;
        this.speed = speed;
        this.environment = environment;
        this.gameLevel = gameLevel;
        this.keyboard = keyboard;
        this.setShape(width, height);
        this.startShoot = -1;
        this.isHit = false;
    }

    /**
     * Instantiates a new Space ship.
     *
     * @param environment the environment
     * @param gameLevel   the game level
     * @param keyboard    the keyboard
     */
    public SpaceShip(GameEnvironment environment, GameLevel gameLevel, KeyboardSensor keyboard) {
        this.color = Utils.SPACESHIP_COLOR;
        this.speed = Utils.SPACESHIP_SPEED;
        this.environment = environment;
        this.gameLevel = gameLevel;
        this.keyboard = keyboard;
        this.setShape(Utils.SPACESHIP_WIDTH, Utils.SPACESHIP_HEIGHT);
        this.startShoot = -1;
        this.isHit = false;
    }

    /**
     * Moves the paddle left.
     *
     * @param dt keeps the speed to be according to seconds.
     */
    private void moveLeft(double dt) {
        // the new xPos of the paddle
        int newX = (int) (this.shape.getUpperLeft().getX() - dt * this.speed);
        // change to new xPos if not pass the borders
        if (newX >= Utils.BORDER_SIZE) {
            this.shape.getUpperLeft().setX(newX);
        }
    }

    /**
     * Moves the paddle right.
     *
     * @param dt keeps the speed to be according to seconds.
     */
    private void moveRight(double dt) {
        // paddle width and new xPos of the paddle
        int width = (int) this.shape.getWidth();
        int newX = (int) (this.shape.getUpperLeft().getX() + dt * this.speed);
        // change to new xPos if not pass the borders
        if (newX + width <=  Utils.WINDOW_WIDTH - Utils.BORDER_SIZE) {
            this.shape.getUpperLeft().setX(newX);
        }
    }

    /**
     * Sets the shape of the paddle by given width and height.
     *
     * @param width  of the shape.
     * @param height of the shape.
     */
    private void setShape(int width, int height) {
        double xPos = (Utils.WINDOW_WIDTH - width) / 2;
        this.shape = new Rectangle(new Point(xPos, Utils.SPACESHIP_YPOS), width, height);
    }

    /**
     * Gets the shape of the paddle.
     *
     * @return the shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Handles movement of paddle according to pressed keys.
     *
     * @param dt keeps the speed to be according to seconds.
     */
    @Override
    public void timePassed(double dt) {
        // case left key is pressed move left
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft(dt);
        }
        // case right key is pressed move right
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight(dt);
        }
        // case space is pressed shoot
        if (keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.shoot();
        }
    }

    /**
     * Draws the paddle.
     *
     * @param surface the surface that paddle drawn on.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // width and height of paddle
        int height = (int) shape.getHeight();
        int width = (int) shape.getWidth();

        // position of the top left point of paddle
        int startX = (int) getCollisionRectangle().getUpperLeft().getX();
        int startY = (int) getCollisionRectangle().getUpperLeft().getY();

        // draw paddles circle in color
        surface.setColor(color);
        surface.fillRectangle(startX, startY, width, height);

        // draw paddled frame in black
        surface.setColor(Color.BLACK);
        surface.drawRectangle(startX, startY, width, height);
    }

    /**
     * Adds this paddle to the game.
     *
     * @param newGameLevel the GameLevel that the paddle added to.
     */
    public void addToGame(GameLevel newGameLevel) {
        newGameLevel.addSprite(this);
        newGameLevel.addCollidable(this);
    }

    /**
     * Updates is hit.
     *
     * @param hitter the hitter.
     */
    public void hit(Ball hitter) {
        this.isHit = true;
    }

    /**
     * Sets hit.
     *
     * @param hit the hit
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }

    /**
     * Is hit boolean.
     *
     * @return the boolean
     */
    public boolean isHit() {
        return this.isHit;
    }

    /**
     * Removes paddle from game.
     */
    public void removeFromGame() {
        this.gameLevel.removeCollidable(this);
        this.gameLevel.removeSprite(this);
    }

    @Override
    public void removeFromGame(GameLevel newGameLevel) {
        newGameLevel.removeCollidable(this);
        newGameLevel.removeSprite(this);
    }

    @Override
    public void shoot() {
        if (this.startShoot == -1 || (System.currentTimeMillis() - this.startShoot >  350)) {
            int xPosMid = (int) (this.shape.getUpperLeft().getX() + this.shape.getWidth() / 2);
            int yPos = (int) (this.shape.getUpperLeft().getY());
            this.gameLevel.addSprite(new Ball(new Point(xPosMid, yPos - 5), Utils.SPACESHIP_BALL_SIZE,
                    Utils.SPACESHIP_BALL_VELOCITY, Utils.SPACESHIP_BALL_COLOR, this.environment, this.gameLevel));
            this.startShoot = System.currentTimeMillis();
        }
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
     * No use.
     *
     * @return no use.
     */
    public int fromBottom() {
        return 0;
    }

    @Override
    public boolean isInRangeX(int xPos) {
        int xShip = (int) shape.getUpperLeft().getX();
        return (xPos >= xShip) && (xPos <= xShip + Utils.SPACESHIP_WIDTH);
    }
}