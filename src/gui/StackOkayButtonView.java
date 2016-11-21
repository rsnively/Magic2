package gui;

import game.Game;

import java.awt.Color;
import java.awt.Graphics2D;

public class StackOkayButtonView extends View {

    public StackOkayButtonView(Rect r) {
        super(r);
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.BLACK);
        DrawBorder(g, Color.DARK_GRAY);

        g.setColor(Color.GRAY);
        g.drawString("OK", GetRect().GetLeft(), GetRect().GetBottom());
    }

    @Override
    protected void Clicked(Click click) {
        Game.Get().GetStack().Resolve();
        MakeDirty();
    }
}
