package geometry;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Circle class.
 */
public class Circle implements Shape {
    private Point center;
    private int radius;
    private Color color;

    /**
     * Constructs a circle.
     *
     * @param center the center.
     * @param radius the radius.
     */
    public Circle(Point center, int radius) {
        this.center = center;
        this.radius = radius;
    }

    /**
     * Gives the center.
     *
     * @return the center.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Gives the radius.
     *
     * @return the radius.
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Sets the center.
     *
     * @param newCenter the new center.
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * Sets the radius.
     *
     * @param newRadius the new radius.
     */
    public void setRadius(int newRadius) {
        this.radius = newRadius;
    }

    /**
     * Draws on the circle.
     *
     * @param surface the surface it is drawn on it.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle((int) this.getCenter().getX(), (int) this.getCenter().getY(), this.getRadius());
    }

    /**
     * Gives the color of the circle.
     *
     * @return the color of the circle.
     */
    @Override
    public Color color() {
        return this.color;
    }
}
