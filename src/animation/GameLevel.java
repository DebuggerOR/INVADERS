package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.Collidable;
import general.Counter;
import general.GameEnvironment;
import general.Utils;
import listeners.AlienRemover;
import listeners.BallRemover;
import listeners.LivesTrackingListener;
import listeners.ScoreTrackingListener;
import listeners.ShieldRemover;
import sprites.AlienCollection;
import sprites.ColorBackground;
import sprites.LivesIndicator;
import sprites.NameIndicator;
import sprites.ScoreIndicator;
import sprites.ShieldCollection;
import sprites.SpaceShip;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * Game level class.
 */
public class GameLevel implements Animation {
    private int numLevel;
    private boolean running;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private SpaceShip spaceShip;
    private AlienCollection alienCollection;
    private AlienRemover alienRemover;
    private ShieldRemover shieldRemover;
    private BallRemover ballRemover;
    private ScoreTrackingListener scoreUpdater;
    private LivesTrackingListener livesUpdater;

    /**
     * Instantiates a new Game level.
     *
     * @param keyboard the keyboard
     * @param runner   the runner
     * @param score    the score
     * @param lives    the lives
     * @param numLevel the num level
     */
    public GameLevel(KeyboardSensor keyboard, AnimationRunner runner, int score, int lives, int numLevel) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.keyboard = keyboard;
        this.runner = runner;
        this.spaceShip = new SpaceShip(Utils.SPACESHIP_WIDTH, Utils.SPACESHIP_HEIGHT,
               Utils.SPACESHIP_SPEED, environment, this, keyboard);
        this.shieldRemover = new ShieldRemover(this);
        this.alienRemover = new AlienRemover(this, new Counter(Utils.ALIENS_NUM));
        this.ballRemover = new BallRemover(this);
        this.scoreUpdater = new ScoreTrackingListener(new Counter(score));
        this.livesUpdater = new LivesTrackingListener(this, new Counter(lives));
        this.numLevel = numLevel;
        this.alienCollection = new AlienCollection(0, 0, null, null, null);
    }

    /**
     * Initialize.
     */
    public void initialize() {
        new ColorBackground(Color.BLACK).addToGame(this);

        // score indicator, lives and name at the top
        new ScoreIndicator(this).addToGame(this);
        new LivesIndicator(this).addToGame();
        new NameIndicator(this).addToGame();

        for (int i = 0; i < Utils.SHIELDS_NUM; i++) {
            ShieldCollection shieldCollection = new ShieldCollection(Utils.SHIELDS_XPOS + Utils.BETWEEN_SHIELDS * i);
            shieldCollection.addHitListener(this.shieldRemover);
            shieldCollection.addToGame(this);
            shieldCollection.addHitListener(this.ballRemover);
        }
    }

    /**
     * Handles one frame of animation.
     *
     * @param surface drawSurface to do the frame with.
     * @param dt      keeps the speed according to seconds.
     */
    @Override
    public void doOneFrame(DrawSurface surface, double dt) {
        // run pause if press p
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
        // draw sprites and notify time passed
        this.sprites.drawAllOn(surface);
        this.sprites.notifyAllTimePassed(dt);
    }

    /**
     * Tells if animation should stop.
     *
     * @return if animation should stop.
     */
    @Override
    public boolean shouldStop() {
        if (this.isReachShields() || this.isHitShip()) {
            this.spaceShip.setHit(false);
            this.createSpaceShip();
            this.resetAliensPositionAndSpeed();
            this.sprites.removeBalls();
            this.livesUpdater.getCurrentLives().decrease(1);
            if (this.livesUpdater.getCurrentLives().getValue() < 1) {
                return true;
            }
            this.runner.run(new CountDownAnimation(500, 3, this.sprites));
        }
        // stop if no lives
        if (this.livesUpdater.getCurrentLives().getValue() < 1) {
            return true;
        }
        // stop if no aliens
        if (this.alienRemover.getRemainingAliens().getValue() < 1) {
            return true;
        }

        return !this.running;
    }

    /**
     * Is hit ship boolean.
     *
     * @return the boolean
     */
    private boolean isHitShip() {
        return this.spaceShip.isHit();
    }

    /**
     * Plays one turn.
     */
    public void playOneTurn() {
        // first create spaceShip and balls
        this.createSpaceShip();
        this.createAliens();
        this.sprites.removeBalls();
        // run countdown from 3 (3, 2, 1 and GO)
        this.runner.run(new CountDownAnimation(500, 3, this.sprites));
        // is running
        this.running = true;
        // run this
        this.runner.run(this);
    }

    /**
     * Creates spaceships.
     */
    private void createSpaceShip() {
        this.spaceShip.removeFromGame();
        this.spaceShip = new SpaceShip(this.environment, this, this.keyboard);
        this.spaceShip.addToGame(this);
    }

    /**
     * Reset aliens position and speed.
     */
    private void resetAliensPositionAndSpeed() {
        this.alienCollection.resetPositionAndSpeed();
    }

    /**
     * Creates aliens.
     */
    private void createAliens() {
        this.alienCollection.removeFromGame(this);
        this.alienCollection = new AlienCollection(Utils.ALIENS_XPOS,
                Utils.ALIENS_YPOS, Utils.alienImage(), environment, this);
        this.alienCollection.addListener(this.alienRemover);
        this.alienCollection.addListener(this.scoreUpdater);
        this.alienCollection.addListener(this.livesUpdater);
        this.alienCollection.addToGame(this);
        this.alienCollection.setSpeed(Utils.getAlienSpeed());
        this.alienRemover.setAlienCollection(this.alienCollection);
    }

    /**
     * Is reach shields boolean.
     *
     * @return the boolean
     */
    private boolean isReachShields() {
        return this.alienCollection.isReachShields();
    }

    /**
     * Adds a collidable object.
     *
     * @param collidable a collidable object.
     */
    public void addCollidable(Collidable collidable) {
        this.environment.addCollidable(collidable);
    }

    /**
     * Adds a sprite object.
     *
     * @param sprite a sprite object.
     */
    public void addSprite(Sprite sprite) {
        this.sprites.addSprite(sprite);
    }

    /**
     * Removes collidable.
     *
     * @param collidable the collidable to remove.
     */
    public void removeCollidable(Collidable collidable) {
        this.environment.removeCollidable(collidable);
    }

    /**
     * Removes sprite.
     *
     * @param sprite the sprite to remove.
     */
    public void removeSprite(Sprite sprite) {
        this.sprites.removeSprite(sprite);
    }

    /**
     * Gives the num of the lives.
     *
     * @return the num of the lives.
     */
    public int numLives() {
        return this.livesUpdater.getCurrentLives().getValue();
    }

    /**
     * Num aliens int.
     *
     * @return the int
     */
    public int numAliens() {
        return this.alienRemover.getRemainingAliens().getValue();
    }

    /**
     * Gives the score.
     *
     * @return the score.
     */
    public int score() {
        return this.scoreUpdater.getCurrentScore().getValue();
    }

    /**
     * Gets level name.
     *
     * @return the level name
     */
    public String getLevelName() {
        return "Level" + this.numLevel;
    }

    /**
     * Gets lives updater.
     *
     * @return the lives updater
     */
    public LivesTrackingListener getLivesUpdater() {
        return this.livesUpdater;
    }

    /**
     * Sets running.
     *
     * @param isRunning the running
     */
    public void setRunning(boolean isRunning) {
        this.running = isRunning;
    }
}