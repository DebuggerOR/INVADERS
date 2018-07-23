package animation;

import biuoop.DrawSurface;
import scores.HighScoresTable;
import general.Utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private boolean stop;
    private HighScoresTable scoresTable;
    private int curRank;


    /**
     * Instantiates a new High scores animation.
     *
     * @param scoresTable the scores table
     * @param curRank     the cur rank
     */
    public HighScoresAnimation(HighScoresTable scoresTable, int curRank) {
        this.stop = false;
        this.scoresTable = scoresTable;
        this.curRank = curRank;
    }


    /**
     * Instantiates a new High scores animation.
     *
     * @param scoresTable the scores table
     */
    public HighScoresAnimation(HighScoresTable scoresTable) {
        this.stop = false;
        this.scoresTable = scoresTable;
        this.curRank = -1;
    }


    @Override
    public void doOneFrame(DrawSurface surface, double dt) {
        // blue frame
        surface.setColor(new Color(61, 61, 153));
        surface.fillRectangle(0, 0, Utils.WINDOW_WIDTH, Utils.WINDOW_HEIGHT);
        surface.setColor(Color.WHITE);
        surface.fillRectangle(20, 20, Utils.WINDOW_WIDTH - 40, Utils.WINDOW_HEIGHT - 40);

        // gray rectangle
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle(150, 80, 500, 440);
        surface.setColor(Color.BLACK);
        surface.drawRectangle(150, 80, 500, 440);

        // colors for five scores
        List<Color> colors = new ArrayList<>();
        colors.add(Color.RED);
        colors.add(Color.ORANGE);
        colors.add(Color.YELLOW);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);

        // coordinates for scores display
        int xPos = 200;
        int yPos = 240;

        // headline
        surface.setColor(Color.BLACK);
        surface.drawText(xPos - 2, 180 - 2, "HIGHEST SCORES", 45);
        surface.setColor(Color.MAGENTA);
        surface.drawText(xPos, 180, "HIGHEST SCORES", 45);

        for (int i = 0; i < Math.min(this.scoresTable.getHighScores().size(), this.scoresTable.size()); i++) {
            // highlight cur rank
            if (i == this.curRank - 1) {
                surface.setColor(new Color(238, 232, 170));
                surface.fillRectangle(xPos - 10, yPos + 52 * i - 30, 420, 40);
            }

            // scores in black
            surface.setColor(Color.BLACK);
            surface.drawText(xPos - 2, yPos + 52 * i - 2, i + 1 + ".", 35);
            surface.drawText(xPos + 50 - 2, yPos + 52 * i - 2,
                    scoresTable.getHighScores().get(i).getName(), 35);
            surface.drawText(xPos + 300 - 2, yPos + 52 * i - 2,
                    scoresTable.getHighScores().get(i).getScore() + "", 35);
            // scores in changing color
            surface.setColor(colors.get(i));
            surface.drawText(xPos, yPos + 52 * i, i + 1 + ".", 35);
            surface.drawText(xPos + 50, yPos + 52 * i,
                    scoresTable.getHighScores().get(i).getName(), 35);
            surface.drawText(xPos + 300, yPos + 52 * i,
                    scoresTable.getHighScores().get(i).getScore() + "", 35);
        }
    }


    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}