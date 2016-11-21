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
        if (mana.GetWhite() > 0) manaViews.add(new ManaPoolManaView(GetManaViewRect(manaViews.size()), Mana.Color.White, mana.GetWhite()));
        if (mana.GetBlue() > 0) manaViews.add(new ManaPoolManaView(GetManaViewRect(manaViews.size()), Mana.Color.Blue, mana.GetBlue()));
        if (mana.GetBlack() > 0) manaViews.add(new ManaPoolManaView(GetManaViewRect(manaViews.size()), Mana.Color.Black, mana.GetBlack()));
        if (mana.GetRed() > 0) manaViews.add(new ManaPoolManaView(GetManaViewRect(manaViews.size()), Mana.Color.Red, mana.GetRed()));
        if (mana.GetGreen() > 0) manaViews.add(new ManaPoolManaView(GetManaViewRect(manaViews.size()), Mana.Color.Green, mana.GetGreen()));
        if (mana.GetColorless() > 0) manaViews.add(new ManaPoolManaView(GetManaViewRect(manaViews.size()), Mana.Color.Colorless, mana.GetColorless()));
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
