package gui;

import game.Card;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class PlayerHandView extends View {

    private ArrayList<CardView> hand;

    public PlayerHandView(Rect r, ArrayList<Card> cards) {
        super(r);

        this.hand = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++)
            hand.add(new CardView(GetCardRect(i), cards.get(i)));
    }

    private Rect GetCardRect(int i) {
        int cardHeight = GetRect().GetHeight() * 19 / 20;
        int cardWidth = cardHeight * 5 / 7;
        Size s = new Size(cardWidth, cardHeight);

        int x = GetRect().GetLeft() + i * cardWidth * 21 / 20;
        int y = GetRect().GetTop() + (GetRect().GetHeight() - cardHeight) / 2;
        Point topLeft = new Point(x, y);

        return Rect.RectTopLeft(topLeft, s);
    }

    @Override
    protected void Render(Graphics2D g) {
        SetBackground(g, Color.GREEN.darker().darker());
        super.Render(g);
    }

    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        for (CardView cv : hand)
            views.add(cv);
        return views;
    }
}
