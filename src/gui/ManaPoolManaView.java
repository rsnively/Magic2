package gui;

import game.Game;
import game.Mana;

import java.awt.Color;
import java.awt.Graphics2D;

public class ManaPoolManaView extends View {
    private Mana.Color color;
    private int amount;

    public ManaPoolManaView(Rect r, Mana.Color color, int amount) {
        super(r);
        this.color = color;
        this.amount = amount;
    }

    @Override
    protected void Render(Graphics2D g) {
        switch (color) {
            case White:
                SetBackground(g, Color.WHITE);
                DrawBorder(g, Color.BLACK);
                break;
            case Blue:
                SetBackground(g, Color.CYAN.darker());
                DrawBorder(g, Color.BLACK);
                break;
            case Black:
                SetBackground(g, Color.GRAY);
                DrawBorder(g, Color.WHITE);
                break;
            case Red:
                SetBackground(g, Color.RED);
                DrawBorder(g, Color.BLACK);
                break;
            case Green:
                SetBackground(g, Color.GREEN);
                DrawBorder(g, Color.BLACK);
                break;
        }

        DrawStringCentered(g, Integer.toString(amount));
    }

    @Override
    protected void Clicked(Click click) {
        if (Game.Get().GetCostBeingPaid().GetManaCost().CouldUse(new Mana(color))) {
            Game.Get().GetPlayer1().RemoveManaFromPool(new Mana(color));
            Game.Get().GetPlayer1().AddManaFromPool(new Mana(color));
            MakeDirty();
        }
    }
}
