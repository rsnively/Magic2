package gui;

import game.Mana;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class ManaPoolView extends View {

    private ArrayList<ManaPoolManaView> manaViews;

    public ManaPoolView(Rect r, Mana mana) {
        super(r);

        manaViews = new ArrayList<>();
        for (Mana.Color c : Mana.Color.values())
            if (mana.Has(c))
                manaViews.add(new ManaPoolManaView(GetManaViewRect(manaViews.size()), c, mana.Get(c)));
    }

    private Rect GetManaViewRect(int i) {
        Size s = new Size(GetRect().GetWidth(), 30);
        Point bottomRight = new Point(GetRect().GetRight(), GetRect().GetBottom() - i * s.GetHeight());
        return Rect.RectBottomRight(bottomRight, s);
    }

    @Override
    protected ArrayList<View> GetSubviews() {
        ArrayList<View> views = new ArrayList<>();
        for (ManaPoolManaView v : manaViews)
            views.add(v);
        return views;
    }

    @Override
    protected boolean OwnsClick(Click c) {
        for (ManaPoolManaView v : manaViews)
            if (v.OwnsClick(c))
                return true;
        return false;
    }
}
