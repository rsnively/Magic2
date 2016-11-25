package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Player {

    private boolean playedFirst;
    private boolean lost;
    private Optional<SpellCast> spellCast;

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
        this.spellCast = Optional.empty();
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

    public void AddManaFromPool(Mana mana) {
        spellCast.get().PayFromPool(mana);
        CheckIfCanCast();
    }

    public void AddMana(Mana mana, Card source) {
        if (spellCast.isPresent() && spellCast.get().CouldUse(mana)) {
            spellCast.get().PayFromSource(mana, source);
            CheckIfCanCast();
        }
        else
            manaPool.Add(mana);
    }

    private void CheckIfCanCast() {
        if (spellCast.get().Paid()) {
            Game.Get().PlayCard(spellCast.get().GetCard());
            spellCast = Optional.empty();
        }
    }

    public void StopCasting() {
        spellCast.get().Undo();
        spellCast = Optional.empty();
    }

    public boolean IsPayingCosts() { return spellCast.isPresent(); }
    public Cost GetCostBeingPaid() { return spellCast.isPresent() ? spellCast.get().GetCostRemaining() : new Cost(); }
    public void PayFor(Card c) { spellCast = Optional.of(new SpellCast(c, this)); }

    public void RemoveManaFromPool(Mana m) {
        manaPool.Remove(m);
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
