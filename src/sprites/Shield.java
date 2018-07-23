package sprites;

import biuoop.DrawSurface;
import general.Utils;
import geometry.Ball;
import listeners.HitListener;
import listeners.HitNotifier;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Shield.
 */
public class Shield extends BaseShield implements HitNotifier {
    private List<HitListener> hitListeners;

    /**
     * Instantiates a new Shield.
     *
     * @param xPos the x pos
     * @param yPos the y pos
     */
    public Shield(int xPos, int yPos) {
        super(xPos, yPos, Utils.SHIELD_WIDTH, Utils.SHIELD_HEIGHT);
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public void drawOn(DrawSurface surface) {
        // block coordinates sizes
        int startX = (int) super.getShape().getUpperLeft().getX();
        int startY = (int) super.getShape().getUpperLeft().getY();
        int height = (int) super.getShape().getHeight();
        int width = (int) super.getShape().getWidth();

        surface.setColor(Utils.SHIELDS_COLOR);
        surface.fillRectangle(startX, startY, width, height);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Adds hit listener.
     *
     * @param hl the hit listener to be added.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void hit(Ball hitter) {
        this.notifyHit(hitter);
    }

    /**
     * Notifies hit.
     *
     * @param hitter the hitter.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void hit() {
    }

    @Override
    public boolean isInRangeX(int xPos) {
        int xShield = (int) super.getShape().getUpperLeft().getX();
        return (xPos >= xShield) && (xPos <= xShield + Utils.SHIELD_WIDTH);
    }
}
