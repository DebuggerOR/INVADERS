package menu;

/**
 * Selection class.
 *
 * @param <T> the type parameter
 */
public class Selection<T> {
    private String key;
    private String message;
    private T retValue;

    /**
     * Constructs a selection.
     *
     * @param key      the key to press.
     * @param message  the message to display.
     * @param retValue the value of the selection.
     */
    public Selection(String key, String message, T retValue) {
        this.key = key;
        this.message = message;
        this.retValue = retValue;
    }

    /**
     * Gives the key.
     *
     * @return the message.
     */
    public String getKey() {
        return this.key;
    }

    /**
     * Gives the message.
     *
     * @return the message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Gives the returned value.
     *
     * @return the returned value.
     */
    public T getRetValue() {
        return this.retValue;
    }
}
