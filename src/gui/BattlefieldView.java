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
        double cardHeight = GetRect().GetHeight() * 0.95 / 2;
        double cardWidth = cardHeight * CardView.WHRatio;
        Size s = new Size(c.IsTapped() ? cardHeight : cardWidth, c.IsTapped() ? cardWidth : cardHeight);

        double x = GetRect().GetLeft() + i * cardWidth * 1.05;
        double y = GetRect().GetBottom() - (GetRect().GetHeight() / 2 - cardHeight) / 2;
        Point bottomLeft = new Point(x, y);

        return Rect.RectBottomLeft(bottomLeft, s);
    }

    private Rect GetTopPermanentRect(int i, Card c) {
        double cardHeight = GetRect().GetHeight() * 0.95 / 2;
        double cardWidth = cardHeight * CardView.WHRatio;
        Size s = new Size(c.IsTapped() ? cardHeight : cardWidth, c.IsTapped() ? cardWidth : cardHeight);

        double x = GetRect().GetLeft() + i * cardWidth * 1.05;
        double y = GetRect().GetTop() + (GetRect().GetHeight() / 2 - cardHeight) / 2;
        Point topLeft = new Point(x, y);

        return Rect.RectTopLeft(topLeft, s);
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
