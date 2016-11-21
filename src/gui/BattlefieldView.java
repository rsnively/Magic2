package gui;

import game.Card;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class BattlefieldView extends View {

    ArrayList<CardView> bottom, top;

    public BattlefieldView(Rect r, ArrayList<Card> p1Permanents, ArrayList<Card> p2Permanents) {
        super(r);

        bottom = new ArrayList<>();
        top = new ArrayList<>();
        for (int i = 0; i < p1Permanents.size(); i++)
            bottom.add(new CardView(GetBottomPermanentRect(i, p1Permanents.get(i)), p1Permanents.get(i)));
        for (int i = 0; i < p2Permanents.size(); i++)
            top.add(new CardView(GetTopPermanentRect(i, p2Permanents.get(i)), p2Permanents.get(i)));
    }

    private Rect GetBottomPermanentRect(int i, Card c) {
        //todo spacing, positioning
        int x = GetRect().GetLeft() + ((int) ((float) (GameView.CardWidth) * (((float) (i)) + 0.5)));
        int y = GetRect().GetHeight() / 2 + GameView.CardHeight / 2;
        Size s = c.IsTapped() ? GameView.TappedCardSize : GameView.CardSize;
        return Rect.RectCenter(new Point(x, y), s);
    }

    private Rect GetTopPermanentRect(int i, Card c) {
        //todo spacing, positioning
        int x = GetRect().GetLeft() + ((int) ((float) (GameView.CardWidth) * (((float) (i)) + 0.5)));
        int y = GetRect().GetTop() + GameView.CardHeight / 2;
        Size s = c.IsTapped() ? GameView.TappedCardSize : GameView.CardSize;
        return Rect.RectCenter(new Point(x, y), s);
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.BLACK);

        g.setColor(Color.WHITE);
        g.drawLine(GetRect().GetLeft(), GetRect().GetCenterY(), GetRect().GetRight(), GetRect().GetCenterY());

        super.Render(g);
    }

    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        for (CardView c : bottom)
            views.add(c);
        for (CardView c : top)
            views.add(c);
        return views;
    }
}
