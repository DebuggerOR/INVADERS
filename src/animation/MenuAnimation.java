package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import general.Utils;
import menu.Menu;
import menu.Selection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * MenuAnimation class.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private T status;
    private List<Selection<T>> selections;
    private List<Boolean> isSubMenu;
    private List<Menu<T>> subMenus;

    /**
     * Constructs a MenuAnimation.
     *
     * @param title    the title of the menu.
     * @param keyboard the sensorKeyBoard.
     * @param runner   the runner to run the animation.
     */
    public MenuAnimation(String title, KeyboardSensor keyboard, AnimationRunner runner) {
        this.title = title;
        this.keyboard = keyboard;
        this.runner = runner;
        this.selections = new ArrayList<>();
        this.isSubMenu = new ArrayList<>();
        this.subMenus = new ArrayList<>();
        this.status = null;
    }

    /**
     * Adds option to the menu.
     *
     * @param key       the key to choose the option.
     * @param message   the message to display besides the key.
     * @param retValue the return value for the option.
     */
    @Override
    public void addSelection(String key, String message, T retValue) {
        this.selections.add(new Selection(key, message, retValue));
        this.isSubMenu.add(false);
        this.subMenus.add(null);
    }

    /**
     * Gives the status of the menu.
     *
     * @return the status of the menu.
     */
    @Override
    public T getStatus() {
        return this.status;
    }

    /**
     * Handles one frame of animation.
     *
     * @param surface drawSurface to do the frame with.
     * @param dt      keeps the speed according to seconds.
     */
    @Override
    public void doOneFrame(DrawSurface surface, double dt) {
        // green frame
        surface.setColor(new Color(0, 102, 0));
        surface.fillRectangle(0, 0, Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        surface.setColor(Color.WHITE);
        surface.fillRectangle(20, 20, Utils.WINDOW_WIDTH - 40, Utils.WINDOW_HEIGHT - 40);

        // gray rectangle.
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle(150, 80, 500, 440);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(150, 80, 500, 440);

        // colors of the options
        List<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);

        // coordinates to display the options
        int xPos = 200;
        int yPos = 240;

        // title
        surface.setColor(Color.BLACK);
        surface.drawText(xPos - 2, 180 - 2, this.title, 45);
        surface.setColor(Color.MAGENTA);
        surface.drawText(xPos, 180, this.title, 45);

        for (int i = 0; i < this.selections.size(); i++) {
            // names and keys in black
            surface.setColor(Color.BLACK);
            surface.drawText(xPos - 2, yPos + 52 * i - 2, i + 1 + ".", 35);
            surface.drawText(xPos + 50 - 2, yPos + 52 * i - 2, selections.get(i).getMessage(), 35);
            surface.drawText(xPos + 300 - 2, yPos + 52 * i - 2, "(" + selections.get(i).getKey() + ")", 35);

            // names and keys in changing color
            surface.setColor(colors.get(i));
            surface.drawText(xPos, yPos + 52 * i, i + 1 + ".", 35);
            surface.drawText(xPos + 50, yPos + 52 * i, this.selections.get(i).getMessage(), 35);
            surface.drawText(xPos + 300, yPos + 52 * i, "(" + this.selections.get(i).getKey() + ")", 35);
        }

        // display use english keyboard at down left
        surface.setColor(Color.BLACK);
        surface.drawText(25, Utils.WINDOW_HEIGHT - 25, "Use english keyboard", 18);

        // check if one of the options pressed
        for (int i = 0; i < this.selections.size(); i++) {
            if (this.keyboard.isPressed(this.selections.get(i).getKey())) {
                // if the chosen option isn't subMenu return its return value
                if (!this.isSubMenu.get(i)) {
                    this.status = this.selections.get(i).getRetValue();
                    break;
                }
                // if the chosen is subMenu run it
                Menu<T> subMenu = this.subMenus.get(i);
                this.runner.run(subMenu);
                this.status = subMenu.getStatus();
                subMenu.reset();
                break;
            }
        }
    }

    /**
     * Adds a subMenu to the menu.
     *
     * @param key     the key to reach the sub menu.
     * @param message the displayed title besides the key.
     * @param subMenu the subMenu itself.
     */
    @Override
    public void addSubMenu(String key, String message, Menu subMenu) {
        this.selections.add(new Selection<>(key, message, null));
        this.isSubMenu.add(true);
        this.subMenus.add(subMenu);
    }

    /**
     * Tells if animation should stop.
     *
     * @return if animation should stop..
     */
    @Override
    public boolean shouldStop() {
        return this.status != null;
    }

    /**
     * Resets menu.
     */
    @Override
    public void reset() {
        this.status = null;
    }
}