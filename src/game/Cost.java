package game;

import java.util.ArrayList;

public class Cost {
    public Mana manaCost;
    public ArrayList<Card> tapCost;

    public Cost(Mana m, ArrayList<Card> cards) {
        this.manaCost = m;
        this.tapCost = cards;
    }

    public Cost() {
        this(new Mana(), new ArrayList<>());
    }

    public Cost(String mana) {
        this(new Mana(mana), new ArrayList<>());
    }

    public Cost(Card c) {
        this(new Mana(), new ArrayList<>());
        this.tapCost.add(c);
    }

    public Mana GetMana() { return manaCost; }

    public boolean CouldPay() {
        for (Card c : tapCost)
            if (c.IsTapped())
                return false;
        return true;
    }

    public void Pay() {
        for (Card c : tapCost)
            c.Tap();
    }

    public void Pay(Mana m) {
        manaCost.Remove(m);
    }

    public boolean Paid() { return manaCost.Convert() == 0 && tapCost.isEmpty(); }
}
