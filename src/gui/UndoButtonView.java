package gui;

import game.Game;

import java.awt.Color;
import java.awt.Graphics2D;

public class UndoButtonView extends View {

    public UndoButtonView(Rect r) { super(r); }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.BLACK);
        g.setColor(Color.WHITE);
        DrawStringCentered(g, "UNDO");
    }

    @Override
    protected void Clicked(Click c) {
        Game.Get().Uncast();
    }
}
