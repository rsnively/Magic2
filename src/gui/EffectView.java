package gui;

import game.Effect;

import java.awt.Color;
import java.awt.Graphics2D;

public class EffectView extends View {

    private Effect effect;

    public EffectView(Rect r, Effect e) {
        super(r);
        this.effect = e;
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.MAGENTA);
        DrawBorder(g, Color.BLACK);

        g.setColor(Color.BLACK);
        g.drawString(effect.GetEffectDescription(), GetRect().GetLeft(), GetRect().GetTop()+12);
    }
}
