package general;

import geometry.Velocity;
import scores.HighScoresTable;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Utils class.
 */
public class Utils {
    /**
     * The constant WINDOW_WIDTH.
     */
    public static final int WINDOW_WIDTH = 800;
    /**
     * The constant WINDOW_HEIGHT.
     */
    public static final int WINDOW_HEIGHT = 600;
    /**
     * The constant BORDER_SIZE.
     */
    public static final int BORDER_SIZE = 25;
    /**
     * The constant BALL_SIZE.
     */
    public static final int SPACESHIP_BALL_SIZE = 3;
    /**
     * The constant SPACESHIP_BALL_COLOR.
     */
    public static final Color SPACESHIP_BALL_COLOR = Color.WHITE;
    /**
     * The constant SPACESHIP_BALL_VELOCITY.
     */
    public static final Velocity SPACESHIP_BALL_VELOCITY = new Velocity(0, -400);
    /**
     * The constant SPACESHIP_SPEED.
     */
    public static final int SPACESHIP_SPEED = 400;
    /**
     * The constant SPACESHIP_WIDTH.
     */
    public static final int SPACESHIP_WIDTH = 80;
    /**
     * The constant SPACESHIP_HEIGHT.
     */
    public static final int SPACESHIP_HEIGHT = 20;
    /**
     * The constant SPACESHIP_COLOR.
     */
    public static final Color SPACESHIP_COLOR = Color.ORANGE;
    /**
     * The constant SPACESHIP_YPOS.
     */
    public static final int SPACESHIP_YPOS = 550;
    /**
     * The constant ALIEN_BALL_SIZE.
     */
    public static final int ALIEN_BALL_SIZE = 4;
    /**
     * The constant ALIEN_BALL_COLOR.
     */
    public static final Color ALIEN_BALL_COLOR = Color.RED;
    /**
     * The constant ALIEN_BALL_VELOCITY.
     */
    public static final Velocity ALIEN_BALL_VELOCITY = new Velocity(0, 400);
    /**
     * The constant ALIENS_NUM.
     */
    public static final int ALIENS_NUM = 50;
    /**
     * The constant ALIENS_ROWS.
     */
    public static final int ALIENS_ROWS = 5;
    /**
     * The constant ALIENS_COLOUMNS.
     */
    public static final int ALIENS_COLOUMNS = 10;
    /**
     * The constant ALIENS_WIDTH.
     */
    public static final int ALIENS_WIDTH = 40;
    /**
     * The constant ALIENS_HEIGHT.
     */
    public static final int ALIENS_HEIGHT = 30;
    /**
     * The constant ALIENS_INIT_SPEED.
     */
    public static final double ALIENS_INIT_SPEED = 100;
    /**
     * The constant ALIENS_XPOS.
     */
    public static final int ALIENS_XPOS = 150;
    /**
     * The constant ALIENS_YPOS.
     */
    public static final int ALIENS_YPOS = 50;

    /**
     * The constant SHIELDS_XPOS.
     */
    public static final int SHIELDS_XPOS = 125;
    /**
     * The constant SHIELDS_YPOS.
     */
    public static final int SHIELDS_YPOS = 500;
    /**
     * The constant SHIELDS_COLOR.
     */
    public static final Color SHIELDS_COLOR = Color.CYAN;
    /**
     * The constant SHIELDS_ROW.
     */
    public static final int SHIELDS_ROW = 3;
    /**
     * The constant SHIELDS_COLOUMN.
     */
    public static final int SHIELDS_COLOUMN = 20;
    /**
     * The constant SHIELD_WIDTH.
     */
    public static final int SHIELD_WIDTH = 5;
    /**
     * The constant SHIELD_HEIGHT.
     */
    public static final int SHIELD_HEIGHT = 5;
    /**
     * The constant SHIELDS_NUM.
     */
    public static final int SHIELDS_NUM = 4;
    /**
     * The constant BETWEEN_SHIELDS.
     */
    public static final int BETWEEN_SHIELDS = 150;
    /**
     * The constant LIVES.
     */
    public static final int LIVES = 3;
    /**
     * The constant FRAMES_PER_SECOND.
     */
    public static final int FRAMES_PER_SECOND = 60;
    /**
     * The constant SCORES_TABLE_SIZE.
     */
    public static final int SCORES_TABLE_SIZE = 5;
    /**
     * The constant INDICATOR_COLOR.
     */
    public static final Color INDICATOR_COLOR = new Color(230, 230, 230);
    /**
     * The Scorestable path.
     */
    static final String SCORESTABLE_PATH = "highscores.ser";
    private static final String LOSE_IMAGE_PATH = "lose.jpg";
    private static final String PAUSE_IMAGE_PATH = "pause.png";
    private static final String ALIEN_IMAGE_PATH = "alien.png";

    private static double alienSpeed = Utils.ALIENS_INIT_SPEED;

    /**
     * Sets alien speed.
     *
     * @param newAlienSpeed the alien speed
     */
    public static void setAlienSpeed(double newAlienSpeed) {
        Utils.alienSpeed = newAlienSpeed;
    }

    /**
     * Gets alien speed.
     *
     * @return the alien speed
     */
    public static double getAlienSpeed() {
        return Utils.alienSpeed;
    }

    /**
     * Alien image image.
     *
     * @return the image
     */
    public static Image alienImage() {
        BufferedImage image;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(ALIEN_IMAGE_PATH);
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("failed loading alien image");
        }
        return image;
    }

    /**
     * Gives the winning image.
     *
     * @return the winning image.
     */
    public static Image loseImage() {
        BufferedImage image;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(LOSE_IMAGE_PATH);
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("failed loading losing image");
        }
        return image;
    }


    /**
     * Pause image image.
     *
     * @return the image
     */
    public static Image pauseImage() {
        BufferedImage image;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(PAUSE_IMAGE_PATH);
            image = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("failed loading pause image");
        }
        return image;
    }

    /**
     * Gives the scores table.
     *
     * @return the scores table.
     */
    public static HighScoresTable scoresTable() {
        return HighScoresTable.loadFromFile(new File(SCORESTABLE_PATH));
    }

    /**
     * Checks if given numbers are equal.
     * Allows small mistake in comparison.
     *
     * @param num1 num to check if equal to num2.
     * @param num2 num to check if equal to num1.
     * @return true if numbers are equal, else false.
     */
    public static boolean doublesEqual(double num1, double num2) {
        double mistake = 0.001;
        return Math.abs(num1 - num2) < mistake;
    }
}
