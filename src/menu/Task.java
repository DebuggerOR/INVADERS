package menu;

/**
 * Tasl interface.
 *
 * @param <T> the type parameter
 */
public interface Task<T> {

    /**
     * Runs the task.
     *
     * @return return value for running.
     */
    T run();
}
