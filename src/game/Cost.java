package game;

import java.util.ArrayList;

public class Cost {
    private ManaCost manaCost;
    private ArrayList<Card> tapCost;
    private ArrayList<TransferCost> transferCosts;

    public Cost(ManaCost m, ArrayList<Card> cards) {
        this.manaCost = m;
        this.tapCost = cards;
        this.transferCosts = new ArrayList<>();
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
        this.transferCosts = new ArrayList<>(other.transferCosts);
    }

    public void AddTransferCost(TransferCost tc) { transferCosts.add(tc); }

    public String GetString() {
        if (!manaCost.Paid())
            return manaCost.ToString();
        if (!tapCost.isEmpty())
            return "todo tap costs";
        if (!transferCosts.isEmpty())
            return transferCosts.get(0).GetString();
        return "";
    }

    public ManaCost GetManaCost() { return manaCost; }

    public boolean CouldPay() {
        for (Card c : tapCost)
            if (c.IsTapped())
                return false;
        return true;
    }

    public boolean CouldPayTransferCost(Card c) {
        if (!manaCost.Paid()) return false;
        for (TransferCost transferCost : transferCosts)
            if (transferCost.CouldPay(c))
                return true;
        return false;
    }

    public void Pay() {
        for (Card c : tapCost)
            c.Tap();
    }

    public void Pay(Mana m) {
        manaCost.Pay(m);
    }

    public TransferCost GetTransferCost(Card c) {
        for (TransferCost transferCost : transferCosts)
            if (transferCost.CouldPay(c))
                return transferCost;
        assert(false);
        return transferCosts.get(0);
    }

    public boolean Paid() {
        return manaCost.Paid() && tapCost.isEmpty() && TransferCostsPaid();
    }

    public boolean TransferCostsPaid() {
        for (int i = 0; i < transferCosts.size(); i++) {
            if (transferCosts.get(i).Paid()) {
                transferCosts.remove(i);
                i--;
            }
        }
        return transferCosts.isEmpty();
    }
}
