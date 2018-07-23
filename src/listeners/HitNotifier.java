package listeners;

/**
 * HitNotifier interface.
 */
public interface HitNotifier {

    /**
     * Adds hit listener.
     *
     * @param listener hit listener to add.
     */
    void addHitListener(HitListener listener);

    /**
     * Removes hit listener.
     *
     * @param listener hit listener to add.
     */
    void removeHitListener(HitListener listener);
}