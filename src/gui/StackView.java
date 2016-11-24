package gui;

import game.Effect;
import game.Stack;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class StackView extends View {

    private Stack stack;
    private ArrayList<EffectView> effects;
    private StackOkayButtonView okayButton;

    public StackView(Rect r, Stack s) {
        super(r);
        this.stack = s;

        effects = new ArrayList<>();
        for (int i = 0; i < stack.GetEffects().size(); i++)
            effects.add(new EffectView(GetEffectRect(i), stack.GetEffects().get(i)));

        okayButton = new StackOkayButtonView(GetOkayButtonRect());
    }

    private Rect GetEffectRect(int i) {
        double cardHeight = GetRect().GetHeight() * 0.95;
        double cardWidth = cardHeight * CardView.WHRatio;
        Size s = new Size(cardWidth, cardHeight);

        double x = GetRect().GetRight() - cardWidth * i * 0.75;
        double y = GetRect().GetTop() + (GetRect().GetHeight() - cardHeight) / 2;
        Point topRight = new Point(x, y);

        return Rect.RectTopRight(topRight, s);
    }

    private Rect GetOkayButtonRect() {
        Point bottomCenter = GetRect().GetBottomCenter();
        Size s = new Size(100, 20);
        return Rect.RectBottomCenter(bottomCenter, s);
    }

    @Override
    protected void Render(Graphics2D g) {
        if (!stack.HasEffects())
            return;

        SetBackground(g, Color.RED);
        super.Render(g);
    }

    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        for (EffectView ev : effects)
            views.add(ev);
        views.add(okayButton);
        return views;
    }
}
