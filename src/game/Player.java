package game;

import java.util.ArrayList;
import java.util.Collections;

public class Player {

    private boolean playedFirst;

    private int life;
    private int landsPlayedThisTurn;

    private ArrayList<Card> hand;
    private ArrayList<Card> library;
    private ArrayList<Card> graveyard;
    private ArrayList<Card> permanents;

    private Mana manaPool;

    public Player(boolean playedFirst) {
        this.playedFirst = playedFirst;
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
    public int GetLifeTotal() { return life; }
    public int LandsPlayedThisTurn() { return landsPlayedThisTurn; }
    public void IncrementLandsPlayed() { landsPlayedThisTurn++; }
    public void ResetLandsPlayed() { landsPlayedThisTurn = 0;}

    public boolean CanAfford(Card c) {
        return manaPool.Covers(c.GetCost().GetMana());
    }

    public void ShuffleLibrary() { Collections.shuffle(this.library); }

    public void AddMana(Mana m) {
        manaPool.Add(m);
    }

    public void RemoveMana(Mana m) {
        manaPool.Remove(m);
    }

    public void EmptyManaPool() {
        manaPool = new Mana();
    }

    public void DrawCards(int amount) {
        for( ; amount > 0 && library.size() > 0; amount--)
            hand.add(library.remove(0));

        if (library.size() == 0 && amount > 0)
            ; //todo MILL
    }

    public void UntapPermanents() {
        for (Card c : permanents)
            c.Untap();
    }
}
