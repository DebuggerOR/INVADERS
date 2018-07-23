package general;

/**
 * Counter class.
 */
public class Counter {
    private int value;

    /**
     * Constructs a counter.
     */
    public Counter() {
        this.value = 0;
    }

    /**
     * Constructs a counter.
     *
     * @param value the init value of the counter.
     */
    public Counter(int value) {
        this.value = value;
    }

    /**
     * Increases the counter by given value.
     *
     * @param val the value to add.
     */
    public void increase(int val) {
        this.value += val;
    }

    /**
     * Decreases the counter by given value.
     *
     * @param val the value to sub.
     */
    public void decrease(int val) {
        this.value -= val;
    }

    /**
     * Gives the value of the counter.
     *
     * @return the value of the counter.
     */
    public int getValue() {
        return this.value;
    }
}