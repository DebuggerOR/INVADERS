package scores;

import java.io.Serializable;

/**
 * ScoreInfo class.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Constructs a score info.
     *
     * @param name  the name.
     * @param score the score.
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gives the name.
     *
     * @return the name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gives the score.
     *
     * @return the score.
     */
    public int getScore() {
        return this.score;
    }
}
