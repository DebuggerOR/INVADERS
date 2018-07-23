package geometry;

import java.util.List;

/**
 * A Point class.
 *
 * @author Ori.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructs a point given x and y coordinates.
     *
     * @param x the x coordinate.
     * @param y the y coordinate.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between given point to this.
     *
     * @param other point to calculate the distance from.
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        // coordinates of both points
        double x1 = this.x;
        double y1 = this.y;
        double x2 = other.getX();
        double y2 = other.getY();

        // calc distance according to the simple  formula
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }

    /**
     * Checks if given point is equal to this (small mistake is ok).
     *
     * @param other point to check if equals to this point.
     * @return true is the points are equal, false otherwise.
     */
    public boolean equals(Point other) {
        // determine the precision of the comparison
        double cmpMistake = 0.1;
        // equal iff the coordinates equal (small mistake is ok)
        boolean isXClose = (Math.abs(this.x - other.getX()) <= cmpMistake);
        boolean isYClose = (Math.abs(this.x - other.getX()) <= cmpMistake);
        return isXClose && isYClose;
    }

    /**
     * Gives the x value of this point.
     *
     * @return x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gives the y value of this point.
     *
     * @return y value of this point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets the x position of this point.
     *
     * @param newX the new x position.
     */
    public void setX(int newX) {
        this.x = newX;
    }

    /**
     * Sets the y position of this point.
     *
     * @param newY the new y position.
     */
    public void setY(int newY) {
        this.y = newY;
    }

    /**
     * Gives the point's data as string.
     *
     * @return point's data as string.
     */
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * Consider that three points, this and two given are collinear,
     * checks if this is on the segment p1p2.
     *
     * @param p1 collinear point with p2 and this.
     * @param p2 collinear point with p1 and this.
     * @return true if this point on segment, else false.
     */
    public boolean onSegment(Point p1, Point p2) {
        // if x, y of this are between x, y of p1 and p2 on segment
        boolean isBetweenX = (this.x <= Math.max(p1.getX(), p2.getX())
                && this.x >= Math.min(p1.getX(), p2.getX()));
        boolean isBetweenY = (this.y <= Math.max(p1.getY(), p2.getY())
                && this.y >= Math.min(p1.getY(), p2.getY()));

        return isBetweenX && isBetweenY;
    }

    /**
     * Finds the orientation of this and two points (ordered) using formula.
     * The delta of the slopes can help to determine the orientation.
     * If the delta is zero so the points might be on a line.
     * Positive or negative can point that the middle point is "above" or
     * "under" the line that the other points create.
     * To sum up:
     * First, doing some slopes calculation and substruction.
     * If it equals to 0 than they are collinear (ret val 0).
     * If positive than they are ordered clockwise (ret val 1).
     * If negative than they are ordered counterclockwise (ret val 2).
     *
     * @param p1 other point to check orientation with.
     * @param p2 other point to check orientation with.
     * @return orientations : 0 for collinear, 1 clockwise & 2 the opposite.
     */
    public int orientation(Point p1, Point p2) {
        // coordinates of the points
        double x1 = p1.getX();
        double y1 = p1.getY();
        double x2 = p2.getX();
        double y2 = p2.getY();

        // checks orientation by calculating slopes' substruction
        double sDelta = (x1 - this.x) * (y2 - y1) - (y1 - this.y) * (x2 - x1);

        // delta equals to 0 means collinear (slopes are the same)
        if (sDelta == 0) {
            return 0;
        }

        // pos delta says clockwise orientation (1) and neg counterclockwise (2)
        return (sDelta > 0) ? 1 : 2;
    }

    /**
     * Finds the closest point to this from a given list.
     *
     * @param points a list of points to find the closest from.
     * @return the closest point to this from points list.
     */
    public Point closestPoint(List<Point> points) {
        // closest point and min distance
        Point closestPoint = null;
        double minDistance = Double.MAX_VALUE;

        // put the closest point in closestPoint
        for (Point point : points) {
            if (this.distance(point) < minDistance) {
                closestPoint = point;
                minDistance = this.distance(closestPoint);
            }
        }
        return closestPoint;
    }
}
