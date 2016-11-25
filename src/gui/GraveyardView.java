package gui;

import game.Card;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GraveyardView extends View {

    private ArrayList<CardView> cardViews;

    public GraveyardView(Rect r, ArrayList<Card> cards) {
        super(r);
        this.cardViews = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++)
            cardViews.add(new CardView(GetCardRect(i), cards.get(i)));
    }

    private Rect GetCardRect(int i) {
        double width = GetRect().GetWidth() * 0.95;
        double height = width / CardView.WHRatio;
        Size s = new Size(width, height);

        double x = GetRect().GetCenterX();
        double y = GetRect().GetTop() + i * height * 0.1;
        Point topCenter = new Point(x, y);

        return Rect.RectTopCenter(topCenter, s);
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.GRAY);
        super.Render(g);
    }

    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        for (CardView cv : cardViews)
            views.add(cv);
        return views;
    }

}
