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
        int x = (GameView.CardWidth + 5) * i;
        if (hand.size() > 0)
            x = Math.min(x, GetRect().GetWidth() / hand.size() * i);
        x += GetRect().GetLeft();
        int y = GetRect().GetTop() + 3;
        Point topLeft = new Point(x, y);
        Size size = new Size(GameView.CardWidth, GameView.CardHeight);
        return Rect.RectTopLeft(topLeft, size);
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
