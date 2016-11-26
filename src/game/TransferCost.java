package game;

import java.util.Optional;

public class TransferCost {

    private CardZone from;
    private CardZone to;
    private int amount;
    private Optional<Card.Type> cardType;

    public TransferCost(CardZone from, CardZone to, int amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
        cardType = Optional.empty();
    }

    public TransferCost(CardZone from, CardZone to, int amount, Card.Type type) {
        this(from, to, amount);
        cardType = Optional.of(type);
    }

    private String CardString() {
        return Integer.toString(amount) + " " + (cardType.isPresent() ? cardType.get().toString() : "") + " card" + (amount > 1 ? "s" : "");
    }

    public String GetString() {
        if (to == CardZone.Exile)
            return "Exile " + CardString() + " from your " + from.toString();
        return "Move " + CardString() + " from your " + from.toString() + " to your " + to.toString();
    }

    public CardZone GetTo() { return to; }
    public CardZone GetFrom() { return from; }

    public boolean Paid() { return amount == 0; }
    public boolean CouldPay(Card c) {
        return c.GetZone() == from
                && (cardType.isPresent() ? c.Is(cardType.get()) : true);
    }
    public void Pay(Card c) {
        assert(CouldPay(c));
        amount--;
    }
}
