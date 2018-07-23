
import general.GameFlow;
import menu.Menu;
import animation.MenuAnimation;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import animation.AnimationRunner;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import scores.HighScoresTable;
import menu.Task;
import general.Utils;

/**
 * The type Ass 7 game.
 */
public class Ass7Game {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // init some basics
        GUI gui = new GUI("Invaders", Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        Sleeper sleeper = new Sleeper();
        AnimationRunner runner = new AnimationRunner(sleeper, gui);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        DialogManager dialog = gui.getDialogManager();
        // manage main menu
        Menu<Task<Void>> mainMenu = new MenuAnimation<>("Main Menu", keyboard, runner);
        // add start game option
        mainMenu.addSelection("s", "Start Game", new Task() {
            public Void run() {
                GameFlow gameFlow = new GameFlow(runner, keyboard, dialog);
                gameFlow.runLevels();
                return null;
            }
        });
        // add high scores option
        mainMenu.addSelection("h", "High Scores", new Task() {
            public Void run() {
                HighScoresTable scoresTable = Utils.scoresTable();
                runner.run(new KeyPressStoppableAnimation(keyboard, "space", new HighScoresAnimation(scoresTable)));
                return null;
            }
        });
        // add exit option
        mainMenu.addSelection("e", "Exit", new Task() {
            public Void run() {
                gui.close();
                return null;
            }
        });
        // run menu
        while (true) {
            runner.run(mainMenu);
            mainMenu.getStatus().run();
            mainMenu.reset();
        }
    }
}