package geometry;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Shape interface.
 */
public interface Shape {

    /**
     * Draws the shape on the surface.
     *
     * @param surface the surface it is drawn on it.
     */
    void drawOn(DrawSurface surface);

    /**
     * Gives the color of the shape.
     *
     * @return the color of the shape.
     */
    Color color();
}
