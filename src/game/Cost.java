package game;

import java.util.ArrayList;

public class Cost {
    public ManaCost manaCost;
    public ArrayList<Card> tapCost;

    public Cost(ManaCost m, ArrayList<Card> cards) {
        this.manaCost = m;
        this.tapCost = cards;
    }

    public Cost() {
        this(new ManaCost(), new ArrayList<>());
    }

    public Cost(String mana) {
        this(new ManaCost(mana), new ArrayList<>());
    }

    public Cost(Card c) {
        this(new ManaCost(), new ArrayList<>());
        this.tapCost.add(c);
    }

    public Cost(Cost other) {
        this(new ManaCost(other.manaCost), new ArrayList<>(other.tapCost));
    }

    public ManaCost GetManaCost() { return manaCost; }

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
        manaCost.Pay(m);
    }

    public boolean Paid() { return manaCost.Paid() && tapCost.isEmpty(); }
}
