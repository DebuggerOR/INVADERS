package sprites;

import animation.GameLevel;
import biuoop.DrawSurface;
import general.GameEnvironment;
import general.Utils;
import listeners.HitListener;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The type Alien collection.
 */
public class AlienCollection implements Sprite {
    private List<Alien> aliens;
    private Directions direction;
    private boolean isDown;
    private int dy;
    private double speed;
    private int bottomYPos;
    private long lastShoot;

    /**
     * Instantiates a new Alien collection.
     *
     * @param xPos        the x pos
     * @param yPos        the y pos
     * @param image       the image
     * @param environment the environment
     * @param gameLevel   the game level
     */
    public AlienCollection(int xPos, int yPos, Image image, GameEnvironment environment, GameLevel gameLevel) {
        this.aliens = new ArrayList<>();
        for (int c = 0; c < Utils.ALIENS_COLOUMNS; c++) {
            for (int r = 0; r < Utils.ALIENS_ROWS; r++) {
                this.aliens.add(new Alien(xPos + (Utils.ALIENS_WIDTH + 10) * c,
                        yPos + (Utils.ALIENS_HEIGHT + 10) * r, image, environment, gameLevel, c));
            }
        }
        this.direction = Directions.RIGHT;
        this.isDown = false;
        this.speed = Utils.getAlienSpeed();
        this.bottomYPos = 0;
    }

    /**
     * Sets speed.
     *
     * @param newSpeed the speed
     */
    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    /**
     * Reset position and speed.
     */
    public void resetPositionAndSpeed() {
        this.isDown = false;
        this.direction = Directions.RIGHT;
        this.bottomYPos = 0;
        this.speed = Utils.getAlienSpeed();
        for (Alien alien : this.aliens) {
            alien.resetPosition();
        }
    }

    /**
     * Adds to game.
     *
     * @param gameLevel the game level to add the sprite to.
     */
    public void addToGame(GameLevel gameLevel) {
        for (Alien alien : this.aliens) {
            alien.addToGame(gameLevel);
        }
        gameLevel.addSprite(this);
    }

    /**
     * Removes from game.
     *
     * @param gameLevel the game level.
     */
    public void removeFromGame(GameLevel gameLevel) {
        for (Alien alien : this.aliens) {
            alien.removeFromGame(gameLevel);
        }
    }

    /**
     * Remove alien.
     *
     * @param alien the alien
     */
    public void removeAlien(Sprite alien) {
        this.aliens.remove(alien);
    }

    /**
     * Add listener.
     *
     * @param listener the listener
     */
    public void addListener(HitListener listener) {
        for (Alien alien : this.aliens) {
            alien.addHitListener(listener);
        }
    }

    @Override
    public void drawOn(DrawSurface surface) {
    }

    @Override
    public void timePassed(double dt) {
        // move down
        if (this.isDown) {
            this.dy++;
            for (Alien alien : this.aliens) {
                alien.advanceY(dt * Utils.ALIENS_INIT_SPEED);
            }
            // until moving 20 pixels down
            if (this.dy >= 20) {
                this.isDown = false;
            }
        } else {
            for (Alien alien : this.aliens) {
                // close to right border
                if (alien.xPos() >= Utils.WINDOW_WIDTH - Utils.ALIENS_WIDTH) {
                    this.isDown = true;
                    this.dy = 0;
                    this.direction = Directions.LEFT;
                    this.speed *= 1.1;
                    break;
                    // close to left border
                } else if (alien.xPos() <= 0) {
                    this.isDown = true;
                    this.dy = 0;
                    this.direction = Directions.RIGHT;
                    this.speed *= 1.1;
                    break;
                }
            }

            // move right
            if (this.direction == Directions.RIGHT) {
                for (Alien alien : this.aliens) {
                    alien.advanceX(dt * this.speed);
                }
                // move left
            } else if (this.direction == Directions.LEFT) {
                for (Alien alien : this.aliens) {
                    alien.advanceNegX(dt * this.speed);
                }
            }
        }
        // update bottom y position
        for (Alien alien : this.aliens) {
            if (alien.yPos() + Utils.ALIENS_HEIGHT > this.bottomYPos) {
                this.bottomYPos = (int) alien.yPos() + Utils.ALIENS_HEIGHT;
            }
        }
        // shoot every 0.5 second
        this.shoot();
    }

    /**
     * Shoot.
     */
    public void shoot() {
      // shoot every 0.5 second from random column
      if (this.lastShoot == -1 || (System.currentTimeMillis() - this.lastShoot > 500)) {
          Random random = new Random();
          int randCol;
          // try columns until the column exits
          do {
              randCol = random.nextInt(Utils.ALIENS_COLOUMNS);
          } while (this.alienBottomOfColumn(randCol) == null);
          // shoot from the lowest alien in column
          this.alienBottomOfColumn(randCol).shoot();
          // time of this shoot to keep 500 ms between
          this.lastShoot = System.currentTimeMillis();
      }
    }

    /**
     * Alien bottom of column alien.
     *
     * @param column the column
     * @return the alien
     */
    private Alien alienBottomOfColumn(int column) {
        int yFromBottom = Utils.WINDOW_HEIGHT;
        Alien retAlien = null;
        for (Alien alien : this.aliens) {
            if (alien.coloumn() == column && alien.fromBottom() < yFromBottom) {
                yFromBottom = alien.fromBottom();
                retAlien = alien;
            }
        }
        return retAlien;
    }

    /**
     * Is reach shields boolean.
     *
     * @return the boolean
     */
    public boolean isReachShields() {
        return this.bottomYPos >= Utils.SHIELDS_YPOS;
    }

    /**
     * The enum Directions.
     */
    public enum Directions {
        /**
         * Up directions.
         */
        UP,
        /**
         * Right directions.
         */
        RIGHT,
        /**
         * Down directions.
         */
        DOWN,
        /**
         * Left directions.
         */
        LEFT
    }
}
