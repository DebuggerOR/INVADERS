package geometry;

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A Rectangle class.
 *
 * @author Ori.
 */
public class Rectangle implements Shape {
    private Point topLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Construct a rectangle given top left point, width and height.
     *
     * @param topLeft the top left point of the rectangle.
     * @param width   the width of the rectangle.
     * @param height  the height of the rectangle.
     */
    public Rectangle(Point topLeft, double width, double height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a rectangle.
     *
     * @param topLeft the top left point of the rectangle.
     * @param width   the width of the rectangle.
     * @param height  the height of the rectangle.
     * @param color   the color of the rectangle.
     */
    public Rectangle(Point topLeft, double width, double height, Color color) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    /**
     * Constructs a rectangle.
     *
     * @param xPos   the x position of the rectangle.
     * @param yPos   the y position of the rectangle.
     * @param width  the width of the rectangle.
     * @param height the height of the rectangle.
     */
    public Rectangle(double xPos, double yPos, double width, double height) {
        this.topLeft = new Point(xPos, yPos);
        this.width = width;
        this.height = height;
    }

    /**
     * Gives the intersection points of the line and rectangle.
     *
     * @param line line to find the intersection points with.
     * @return the intersection points of the line and rectangle.
     */
    public List<Point> intersectionPoints(Line line) {
        // list of intersections and list of border lines
        List<Point> intersections = new ArrayList<>();
        List<Line> borders = this.getLines();

        for (Line border : borders) {
            if (line.isIntersecting(border)) {
                intersections.add(border.intersectionWith(line));
            }
        }
        return intersections;
    }

    /**
     * Gives the rectangle's data as string.
     *
     * @return rectangle's data as string.
     */
    public String toString() {
        return this.getPoints().toString();
    }

    /**
     * Gives the width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gives the height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gives the top left point of the rectangle.
     *
     * @return the top left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.topLeft;
    }

    /**
     * Gives the top left point of the rectangle.
     *
     * @return the top left point of the rectangle.
     */
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();

        // generate the other corner points (already have top left)
        Point topRight = new Point(this.topLeft.getX() + this.width,
                this.topLeft.getY());
        Point bottomRight = new Point(this.topLeft.getX() + this.width,
                this.topLeft.getY() + this.height);
        Point bottomLeft = new Point(this.topLeft.getX(),
                this.topLeft.getY() + this.height);

        // store clockwise
        points.add(this.topLeft);
        points.add(topRight);
        points.add(bottomRight);
        points.add(bottomLeft);

        return points;
    }

    /**
     * Gives the top left point of the rectangle.
     *
     * @return the top left point of the rectangle.
     */
    public List<Line> getLines() {
        List<Line> lines = new ArrayList<>();

        // generate the lines of the rectangle
        Line top = new Line(this.topLeft, this.getPoints().get(1));
        Line bottom = new Line(this.getPoints().get(3), this.getPoints().get(2));
        Line left = new Line(this.topLeft, this.getPoints().get(3));
        Line right = new Line(this.getPoints().get(1), this.getPoints().get(2));

        // store clockwise
        lines.add(top);
        lines.add(right);
        lines.add(bottom);
        lines.add(left);

        return lines;
    }

    /**
     * Checks if this is equal to other rectangle.
     *
     * @param other rectangle to check if equal to this.
     * @return true if this is equal to other, else false.
     */
    public boolean equals(Rectangle other) {
        List<Point> thisPoints = this.getPoints();
        List<Point> otherPoints = other.getPoints();
        for (int i = 0; i < thisPoints.size(); i++) {
            // if a corner point doesn't fit return false
            if (!thisPoints.get(0).equals(otherPoints.get(0))) {
                return false;
            }
        }
        // if all corner points fit return true
        return true;
    }

    /**
     * Checks if a given point is on the frame of the rectangle.
     *
     * @param point to check if is on frame.
     * @return true if the point is on frame, else false.
     */
    public boolean isPointOnFrame(Point point) {
        Point start, end;
        for (int i = 0; i < getLines().size(); i++) {
            start = getLines().get(i).start();
            end = getLines().get(i).end();
            // if on one segment of the frame return true
            if (point.onSegment(start, end)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if point is inside this rectangle.
     *
     * @param point to check if is inside rectangle.
     * @return true if point is inside rectangle, else false.
     */
    public boolean isPointInside(Point point) {
        double xPos = point.getX();
        double yPos = point.getY();

        // bools if x and y are in bound
        boolean xInBound = (topLeft.getX() <= xPos
                && xPos <= topLeft.getX() + width);
        boolean yInBound = (topLeft.getY() <= yPos
                && yPos <= topLeft.getY() + height);

        return xInBound && yInBound;
    }

    /**
     * Checks if given point is on top border.
     *
     * @param point to check if is on top border.
     * @return true if point is on top, else false.
     */
    public boolean isOnTop(Point point) {
        Point start = getLines().get(0).start();
        Point end = getLines().get(0).end();
        return point.onSegment(start, end);
    }

    /**
     * Checks if given point is on right border.
     *
     * @param point to check if is on right border.
     * @return true if point is on right, else false.
     */
    public boolean isOnRight(Point point) {
        Point start = getLines().get(1).start();
        Point end = getLines().get(1).end();
        return point.onSegment(start, end);
    }

    /**
     * Checks if given point is on bottom border.
     *
     * @param point to check if is on bottom border.
     * @return true if point is on bottom, else false.
     */
    public boolean isOnBottom(Point point) {
        Point start = getLines().get(2).start();
        Point end = getLines().get(2).end();
        return point.onSegment(start, end);
    }

    /**
     * Checks if given point is on left border.
     *
     * @param point to check if is on left border.
     * @return true if point is on left, else false.
     */
    public boolean isOnLeft(Point point) {
        Point start = getLines().get(3).start();
        Point end = getLines().get(3).end();
        return point.onSegment(start, end);
    }

    /**
     * Draws the rectangle on the surface.
     *
     * @param surface the surface it is drawn on it.
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) width, (int) height);
    }

    /**
     * Gives the color of the rectangle.
     *
     * @return the color of the rectangle.
     */
    @Override
    public Color color() {
        return this.color;
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param newWidth the new width of the rectangle.
     */
    public void setWidth(double newWidth) {
        this.width = newWidth;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param newHeight the new height of the rectangle.
     */
    public void setHeight(double newHeight) {
        this.height = newHeight;
    }
}