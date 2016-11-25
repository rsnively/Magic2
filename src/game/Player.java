package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Player {

    private boolean playedFirst;
    private boolean lost;
    private Optional<Card> cardBeingCast;
    private Optional<Cost> costRemaining;

    private int life;
    private int landsPlayedThisTurn;

    private ArrayList<Card> hand;
    private ArrayList<Card> library;
    private ArrayList<Card> graveyard;
    private ArrayList<Card> permanents;

    private Mana manaPool;

    public Player(boolean playedFirst) {
        this.playedFirst = playedFirst;
        this.lost = false;
        this.cardBeingCast = Optional.empty();
        this.costRemaining = Optional.empty();
        this.life = 20;
        this.landsPlayedThisTurn = 0;

        library = new ArrayList<>();
        hand = new ArrayList<>();
        graveyard = new ArrayList<>();
        permanents = new ArrayList<>();

        manaPool = new Mana();
    }

    public void SetDeck(ArrayList<Card> deck) {
        library = deck;
        ShuffleLibrary();
    }

    public ArrayList<Card> GetHand() { return hand; }
    public ArrayList<Card> GetLibrary() { return library; }
    public ArrayList<Card> GetPermanents() { return permanents; }
    public ArrayList<Card> GetGraveyard() { return graveyard; }
    public Mana GetManaPool() { return manaPool; }

    public boolean IsActivePlayer() {
        return (playedFirst && Game.Get().GetPhase().GetActivePlayer()) || (!playedFirst && !Game.Get().GetPhase().GetActivePlayer());
    }

    public boolean PlayedFirst() { return playedFirst; }
    public boolean LostGame() { return lost; }
    public void DeclareLoser() { lost = true; }
    public int GetLifeTotal() { return life; }
    public int LandsPlayedThisTurn() { return landsPlayedThisTurn; }
    public void IncrementLandsPlayed() { landsPlayedThisTurn++; }
    public void ResetLandsPlayed() { landsPlayedThisTurn = 0;}

    public void ShuffleLibrary() { Collections.shuffle(this.library); }

    public void DealDamage(int damage) {
        life -= damage;
        if (life <= 0)
            DeclareLoser();
    }

    public void AddMana(Mana m) {
        if (cardBeingCast.isPresent() && costRemaining.get().GetManaCost().CouldUse(m)) {
            costRemaining.get().Pay(m);
            if (costRemaining.get().Paid()) {
                Game.Get().PlayCard(cardBeingCast.get());
                cardBeingCast = Optional.empty();
                costRemaining = Optional.empty();
            }
        }
        else
            manaPool.Add(m);
    }

    public boolean IsPayingCosts() { return cardBeingCast.isPresent(); }
    public Cost GetCostBeingPaid() { return cardBeingCast.isPresent() && costRemaining.isPresent() ? costRemaining.get() : new Cost(); }

    public void PayFor(Card c) {
        cardBeingCast = Optional.of(c);
        costRemaining = Optional.of(new Cost(c.GetCost()));
    }

    public void EmptyManaPool() {
        manaPool = new Mana();
    }

    public void DrawCards(int amount) {
        for( ; amount > 0 && library.size() > 0; amount--)
            hand.add(library.remove(0));

        if (library.size() == 0 && amount > 0)
            DeclareLoser();
    }

    public void Mill(int amount) {
        for ( ; amount > 0 && library.size() > 0; amount--)
            graveyard.add(library.remove(0));
    }

    public void UntapPermanents() {
        for (Card c : permanents)
            c.Untap();
    }

    public void TapAttackers() {
        for (Card c : permanents)
            if (c.IsAttacking())
                c.Tap();
    }

    public void ResetAttackers() {
        for (Card c : permanents)
            c.SetAttacking(false);
    }

    public int GetAttackerDamage() {
        int damage = 0;
        for (Card c: permanents)
            if (c.IsAttacking())
                damage += c.GetPower();
        return damage;
    }

    public void RemoveSummoningSickness() {
        for (Card c : permanents)
            c.RemoveSummoningSickness();
    }
}
