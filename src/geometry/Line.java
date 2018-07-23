package geometry;

import biuoop.DrawSurface;
import general.Utils;

import java.awt.Color;
import java.util.List;

/**
 * A line class.
 *
 * @author Ori.
 */
public class Line implements Shape {
    private Point start;
    private Point end;
    private Color color;

    /**
     * Construct a line given two points.
     *
     * @param start first point.
     * @param end   second point.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * d.
     *
     * @param start d
     * @param end   d
     * @param color d
     */
    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    /**
     * Construct a line given coordinates.
     *
     * @param x1 first point's x coordinate.
     * @param y1 first point's y coordinate.
     * @param x2 second point's x coordinate.
     * @param y2 second point's y coordinate.
     */
    public Line(double x1, double y1, double x2, double y2) {
        // using other constructor
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Gives the length of the line.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Gives the middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        // calculate mid x and y and create middle point
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Gives the start point of the line.
     *
     * @return the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * Gives the end point of the line.
     *
     * @return the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if two lines are equal.
     * Consider line and reversed line are equal.
     *
     * @param other line to check if equal to.
     * @return true if lines equal, else false.
     */
    public boolean equals(Line other) {
        // is this start equal to other start
        boolean isStartsEqual = this.end.equals(other.end());
        // is this end equal to other end
        boolean isEndsEqual = this.start.equals(other.start());
        // is this start equal to other end
        boolean isStartEndEqual = this.start.equals(other.end());
        // is this end equal to other start
        boolean isEndStartEqual = this.end.equals(other.start());

        // equal if starts and ends equal or the opposite (one line is reversed)
        return (isStartsEqual && isEndsEqual)
                || (isStartEndEqual && isEndStartEqual);
    }

    /**
     * Checks if slope exists.
     *
     * @return true if slope exists, else false.
     */
    public boolean isSlopeExists() {
        Utils f = new Utils();
        // not vertical
        return !(f.doublesEqual(this.start.getX(), this.end.getX()));
    }

    /**
     * Gives the slope of the line if exists.
     *
     * @return the slope of the line or null.
     */
    public double slope() {
        /*
        case no slope return max int shape
        the purpose of this is to prevent div by 0 anyway.
         */
        if (!isSlopeExists()) {
            return Integer.MAX_VALUE;
        }

        // calculate dx and dy and return the slope
        double dx = this.end.getX() - this.start.getX();
        double dy = this.end.getY() - this.start.getY();

        return dy / dx;
    }

    /**
     * Gives the line's data as string.
     *
     * @return line's data as string.
     */
    public String toString() {
        return this.start.toString() + " -> " + this.end.toString();
    }

    /**
     * Checks if this line intersecting with other line, using the formula
     * below:
     * <p>
     * Two segments (p1,q1) and (p2,q2) intersect if and only if one of the
     * following two conditions is verified:
     * 1. General Case:
     * (p1, q1, p2) and (p1, q1, q2) have different orientations and
     * (p2, q2, p1) and (p2, q2, q1) have different orientations.
     * 2. Special Case
     * (p1, q1, p2), (p1, q1, q2), (p2, q2, p1), and (p2, q2, q1) are
     * all collinear and the x-projections of (p1, q1) and (p2, q2)
     * intersect the y-projections of (p1, q1) and (p2, q2) intersect.
     *
     * @param other line to check intersection with.
     * @return true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        // convert lines to points
        Point p1 = this.start();
        Point q1 = this.end();
        Point p2 = other.start();
        Point q2 = other.end();

        // find orientations needed for general and special cases
        int o1 = p1.orientation(q1, p2);
        int o2 = p1.orientation(q1, q2);
        int o3 = p2.orientation(q2, p1);
        int o4 = p2.orientation(q2, q1);

        // general case according to the formula
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        // p1, q1 and p2 are collinear and p2 lies on segment p1q1
        if (o1 == 0 && p2.onSegment(p1, q1)) {
            return true;
        }
        // p1, q1 and q2 are collinear and q2 lies on segment p1q1
        if (o2 == 0 && q2.onSegment(p1, q1)) {
            return true;
        }
        // p2, q2 and p1 are collinear and p1 lies on segment p2q2
        if (o3 == 0 && p1.onSegment(p2, q2)) {
            return true;
        }
        // p2, q2 and q1 are collinear and q1 lies on segment p2q2
        if (o4 == 0 && q1.onSegment(p2, q2)) {
            return true;
        }
        // if didn't match any case return false
        return false;
    }

    /**
     * Gives the intersection point if exits.
     * Using simple formulas and algebra.
     * The equation of a line is: y = m(x - x1) + y1 while m is the slope
     * and (x1, y1) is a point on the line.
     *
     * @param other line to find the intersection point with.
     * @return the point of intersection if exists, else null.
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            // points' coordinates and slopes
            double x1 = this.start.getX();
            double y1 = this.start.getY();
            double x2 = other.start().getX();
            double y2 = other.start().getY();
            double m1, m2;

            // case this is vertical
            if (!this.isSlopeExists()) {
                m2 = other.slope();
                // remember that other: y = m2(x - x2) + y2
                return new Point(x1, m2 * (x1 - x2) + y2);
            }
            // case other is vertical
            if (!other.isSlopeExists()) {
                m1 = this.slope();
                // remember that this:  y = m1(x - x1) + y1
                return new Point(x2, m1 * (x2 - x1) + y1);
            }
            //case two slopes exist
            m1 = this.slope();
            m2 = other.slope();

            // interX * (m1 - m2) = y2 - y1 + m1 * x1 - x2 * y2
            double interX = (y2 - y1 + m1 * x1 - m2 * x2) / (m1 - m2);
            // interY = m1(interX - x1) + y1
            double interY = m1 * (interX - x1) + y1;

            // return inter point
            return new Point(interX, interY);
        } else {
            //if intersection point doesn't exist
            return null;
        }
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rectangle rectangle to find closest intersection with.
     * @return the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rectangle) {
        // minDistance initialised to max value
        double minDistance = Double.MAX_VALUE;
        // closestPoint and intersections points
        Point closestPoint = null;
        List<Point> intersections = rectangle.intersectionPoints(this);

        // pass over the intersection points and find closest
        for (Point intersection : intersections) {
            if (intersection.distance(this.start) < minDistance) {
                closestPoint = intersection;
                minDistance = closestPoint.distance(this.start);
            }
        }

        // return the closest point
        return closestPoint;
    }

    /**
     * Draws the line on given surface.
     *
     * @param surface the surface that the line drawn on.
     */
    public void drawOn(DrawSurface surface) {
        // line coordinates
        int xStart = (int) start.getX();
        int yStart = (int) start.getY();
        int xEnd = (int) end.getX();
        int yEnd = (int) end.getY();
        // set color and draw line
        surface.setColor(this.color);
        surface.drawLine(xStart, yStart, xEnd, yEnd);
    }

    @Override
    public Color color() {
        return this.color;
    }
}
