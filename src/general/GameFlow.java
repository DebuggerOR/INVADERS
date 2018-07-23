package general;

import animation.AnimationRunner;
import animation.EndScreen;
import animation.GameLevel;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;
import scores.HighScoresTable;
import scores.ScoreInfo;

import java.io.File;

/**
 * The type Game flow.
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private DialogManager dialogManager;
    private AnimationRunner runner;
    private int score;
    private int lives;
    private int numLevel;

    /**
     * Instantiates a new Game flow.
     *
     * @param runner         the runner
     * @param keyboardSensor the keyboard sensor
     * @param dialogManager  the dialog manager
     */
    public GameFlow(AnimationRunner runner, KeyboardSensor keyboardSensor, DialogManager dialogManager) {
        this.runner = runner;
        this.keyboardSensor = keyboardSensor;
        this.dialogManager = dialogManager;
        this.score = 0;
        this.lives = Utils.LIVES;
        this.numLevel = 1;
    }

    /**
     * Run levels.
     */
    public void runLevels() {
        while (true) {
            // create level
            GameLevel level = new GameLevel(keyboardSensor, runner, score, lives, numLevel);
            level.initialize();
            // play turns while there are lives and aliens
            while (level.numAliens() > 0 && this.lives > 0) {
                level.playOneTurn();
                // update score and lives
                this.lives = level.numLives();
                this.score = level.score();
            }
            // if no more aliens
            if (level.numAliens() < 1) {
                Utils.setAlienSpeed(Utils.getAlienSpeed() * 2);
                this.numLevel++;
                continue;
            }
            // if no more lives
            if (this.lives < 1) {
                break;
            }
        }
        // run end screen animation
        this.runner.run(new KeyPressStoppableAnimation(keyboardSensor, "space", new EndScreen(score)));
        // manage high scores issue
        this.manageHighScore();
    }

    /**
     * Manages highscores.
     */
    private void manageHighScore() {
        HighScoresTable scoresTable = Utils.scoresTable();
        int curRank = scoresTable.getRank(this.score);
        // if score is one of the highest scores get and save player details
        if (curRank < scoresTable.size()) {
            String name = this.dialogManager.showQuestionDialog("Name", "What is your name?", "");
            scoresTable.add(new ScoreInfo(name, this.score));
            scoresTable.save(new File(Utils.SCORESTABLE_PATH));
        }
        // run scores table animation
        this.runner.run(new KeyPressStoppableAnimation(keyboardSensor, "space",
                new HighScoresAnimation(scoresTable, curRank)));
    }
}
