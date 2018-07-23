package sprites;

import biuoop.DrawSurface;
import geometry.Ball;

import java.awt.Color;

/**
 * Border class.
 */
public class Border extends BaseShield {
    private Color color;

    /**
     * Constructs a border.
     *
     * @param xPos   x position of the border.
     * @param yPos   y position of the border.
     * @param width  width of the border.
     * @param height height of the border.
     * @param color  color of the border.
     */
    public Border(int xPos, int yPos, int width, int height, Color color) {
        super(xPos, yPos, width, height);
        this.color = color;
    }

    /**
     * Gives the color of the border.
     *
     * @return the color of the border.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of the border.
     *
     * @param newColor the new color.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Draws the border on the surface.
     *
     * @param surface use this to draw.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        // block coordinates sizes
        int startX = (int) super.getShape().getUpperLeft().getX();
        int startY = (int) super.getShape().getUpperLeft().getY();
        int height = (int) super.getShape().getHeight();
        int width = (int) super.getShape().getWidth();

        // draw rectangle with color
        surface.setColor(this.color);
        surface.fillRectangle(startX, startY, width, height);
    }

    @Override
    public void hit(Ball hitter) {

    }

    @Override
    public void hit() {

    }

    @Override
    public boolean isInRangeX(int xPos) {
        return false;
    }
}