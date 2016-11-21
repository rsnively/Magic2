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
        int x = GetRect().GetRight() - (int) (GameView.CardWidth * i * 0.75);
        int y = GetRect().GetTop() + 10;
        Point topRight = new Point(x, y);
        Size size = new Size(GameView.CardWidth, GameView.CardHeight);
        return Rect.RectTopRight(topRight, size);
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
