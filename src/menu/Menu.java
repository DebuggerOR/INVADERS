package menu;

import animation.Animation;

/**
 * Menu interface.
 *
 * @param <T> the type parameter
 */
public interface Menu<T> extends Animation {

    /**
     * Adds a selection to the menu.
     *
     * @param key       the key to choose the option.
     * @param message   the message to display besides the key.
     * @param returnVal the return value for the option.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Gets the status of the menu.
     *
     * @return the status of the menu.
     */
    T getStatus();

    /**
     * Adds a sub menu.
     *
     * @param key     the key to reach the sub menu.
     * @param message the displayed title besides the key.
     * @param subMenu the subMenu itself.
     */
    void addSubMenu(String key, String message, Menu<T> subMenu);

    /**
     * Resets menu.
     */
    void reset();
}