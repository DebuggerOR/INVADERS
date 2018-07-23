package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 */
public interface Animation {

    /**
     * Do one frame.
     *
     * @param surface the surface
     * @param dt      the dt
     */
    void doOneFrame(DrawSurface surface, double dt);

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    boolean shouldStop();
}