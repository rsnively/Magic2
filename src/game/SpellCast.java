package game;

import java.util.ArrayList;

public class SpellCast {

    private Card card;
    private Player caster;
    private Cost costRemaining;
    private ArrayList<Card> cardsTapped;
    private Mana manaTakenFromPool;

    public SpellCast(Card c, Player p) {
        card = c;
        caster = p;
        costRemaining = new Cost(c.GetCost());
        cardsTapped = new ArrayList<>();
        manaTakenFromPool = new Mana();
    }

    public Card GetCard() { return card; }
    public Cost GetCostRemaining() { return costRemaining; }
    public boolean CouldUse(Mana m) { return costRemaining.GetManaCost().CouldUse(m); }

    public void PayFromSource(Mana m, Card source) {
        costRemaining.Pay(m);
        cardsTapped.add(source);
    }

    public void PayFromPool(Mana m) {
        costRemaining.Pay(m);
        manaTakenFromPool.Add(m);
    }

    public boolean Paid() { return costRemaining.Paid(); }

    public void Undo() {
        for (Card c : cardsTapped)
            c.Untap();
        caster.GetManaPool().Add(manaTakenFromPool);
    }

}
