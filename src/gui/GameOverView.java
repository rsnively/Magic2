package gui;

import java.awt.Color;
import java.awt.Graphics2D;

public class GameOverView extends View {

    private boolean won;

    public GameOverView(Rect r, boolean won) {
        super(r);
        this.won = won;
    }

    @Override
    protected void Render(Graphics2D g) {
        if (won) {
            SetBackground(g, Color.GREEN);
            g.setColor(Color.BLACK);
            DrawStringCentered(g, "You Won");
        }
        else {
            SetBackground(g, Color.RED);
            g.setColor(Color.BLACK);
            DrawStringCentered(g, "You Lost");
        }
    }
}
