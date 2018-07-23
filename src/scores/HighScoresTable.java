package scores;

import general.Utils;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HighScoreTable class.
 */
public final class HighScoresTable implements Serializable {
    private List<ScoreInfo> scores;
    private int size;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size the size of the scores table.
     */
    private HighScoresTable(int size) {
        this.size = size;
        this.scores = new ArrayList<>();
    }

    /**
     * Adds a high score.
     *
     * @param score the high score.
     */
    public void add(ScoreInfo score) {
        this.scores.add(this.getRank(score.getScore()) - 1, score);

        if (this.getRank(score.getScore()) > this.size) {
            List<ScoreInfo> newScores = new ArrayList<>();
            for (int i = 0; i < this.size; i++) {
                newScores.add(scores.get(i));
            }
            scores = newScores;
        }
    }

    /**
     * Gives the size of the scores table.
     *
     * @return the size of the scores table.
     */
    public int size() {
        return this.size;
    }

    /**
     * Return the current high scores.
     * The list is sorted such that the highest
     * scores come first.
     *
     * @return the scores.
     */
    public List<ScoreInfo> getHighScores() {
        return this.scores;
    }


    /**
     * return the rank of the current score: where will it
     * be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not
     * be added to the list.
     *
     * @param score the score
     * @return the place of the score if added to scores.
     */
    public int getRank(int score) {
        int rank = 1;
        for (ScoreInfo score1 : this.scores) {
            if (score1.getScore() > score) {
                rank++;
            }
        }
        return rank;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.scores.clear();
    }

    /**
     * Loads table data from file.
     * Current table data is clear.
     *
     * @param fileName the file.
     */
    public void load(File fileName) {
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            HighScoresTable table = (HighScoresTable) objectInputStream.readObject();
            this.size = table.size();
            Collections.copy(this.scores, table.getHighScores());

        } catch (FileNotFoundException e) {
            System.out.println("unable to find file: " + fileName);

        } catch (ClassNotFoundException e) {
            System.out.println("unable find class for object in file");

        } catch (IOException e) {
            System.out.println("failed reading object");

        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("failed closing file: " + fileName);
            }
        }
    }

    /**
     * Saves table data to the specified file.
     *
     * @param fileName the file.
     */
    public void save(File fileName) {
        ObjectOutputStream objectOutputStream = null;

        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            objectOutputStream.writeObject(this);

        } catch (IOException e) {
            System.out.println("failed saving object");

        } finally {
            try {
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
            } catch (IOException e) {
                System.out.println("failed closing file: " + fileName);
            }
        }
    }

    /**
     * Read a table from file and return it.
     * If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param fileName the file.
     * @return HighScoresTable. high scores table
     */
    public static HighScoresTable loadFromFile(File fileName) {
        ObjectInputStream objectInputStream = null;

        try {
            objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
            return (HighScoresTable) objectInputStream.readObject();

        } catch (FileNotFoundException | ClassNotFoundException e) {
            return new HighScoresTable(Utils.SCORES_TABLE_SIZE);

        } catch (IOException e) {
            System.out.println("failed reading object");
            return new HighScoresTable(Utils.SCORES_TABLE_SIZE);

        } finally {
            try {
                if (objectInputStream != null) {
                    objectInputStream.close();
                }
            } catch (IOException e) {
                System.out.println("failed closing file: " + fileName);
            }
        }
    }
}