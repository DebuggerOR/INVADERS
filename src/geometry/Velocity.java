package geometry;

/**
 * Velocity class.
 * <p>
 * Velocity specifies the change in position
 * on the `x` and the `y` axes.
 * Consists also some important functions that used to
 * "move" the balls.
 *
 * @author Ori.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructs a velocity given dx and dy.
     *
     * @param dx the delta of the movement on the x scale.
     * @param dy the delta of the movement on the y scale.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a velociity.
     *
     * @param newVelocity the newVelocity.
     */
    public Velocity(Velocity newVelocity) {
        this.dx = newVelocity.getDx();
        this.dy = newVelocity.getDy();
    }

    /**
     * Helps to construct a velocity by angle and speed.
     * The angle is given in degrees, than changed to radians in order
     * to use sin and cos.
     *
     * @param angle represents the wanted direction.
     * @param speed represents the wanted speed.
     * @return a new velocity with dx and dy that fits the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // change to rad because sin/cos work with rad
        angle = Math.toRadians(angle);
        // dx sin and dy -cos because up is zero instead of right
        double dx = speed * Math.sin(angle);
        double dy = -1 * speed * Math.cos(angle);
        return new Velocity(dx, dy);
    }

    /**
     * Gets the speed of this velocity.
     *
     * @return the speed of this velocity.
     */
    public double getSpeed() {
        return Math.sqrt(this.dx * this.dx + this.dy * this.dy);
    }

    /**
     * Gives the dx of this velocity.
     *
     * @return the dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gives the dy of this velocity.
     *
     * @return the dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Sets the dy of the velocity by given dy.
     *
     * @param newDy the new dy.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Sets the dx of the velocity by given dx.
     *
     * @param newDx the new dx.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * Changes a point according to this velocity
     * (in fact, it creates a new point).
     *
     * @param point point to add dx and dy to it's position.
     * @param dt    keeps the speed to be according to seconds.
     * @return a new point with position (x + dx, y + dy).
     */
    public Point applyToPoint(Point point, double dt) {
        return new Point(point.getX() + dt * this.dx, point.getY() + dt * this.dy);
    }
}
