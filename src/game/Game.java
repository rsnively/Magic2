package game;

import cards.*;

import gui.MainView;

import java.util.ArrayList;

public class Game {
    private final static Game instance = new Game();
    public static Game Get() { return instance; }

    private Player p1, p2;
    private Stack stack;
    private Phase phase;
    private PhaseStops phaseStops;

    private Game() {
        p1 = new Player(true);
        ArrayList<Card> deck1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            deck1.add(new ISD_Swamp(p1));
            deck1.add(new ISD_Island(p1));
        }
        for (int i = 0; i < 4; i++) {
            deck1.add(new ISD_ArmoredSkaab(p1));
            deck1.add(new ISD_MakeshiftMauler(p1));
            deck1.add(new ISD_WalkingCorpse(p1));
        }
        p1.SetDeck(deck1);

        p2 = new Player(false);
        ArrayList<Card> deck2 = new ArrayList<>();
        for (int i = 0; i < 60; i++) {
            deck2.add(new ISD_WalkingCorpse(p2));
        }
        p2.SetDeck(deck2);

        stack = new Stack();
        phase = new Phase(true);
        phaseStops = new PhaseStops();

        p1.DrawCards(7);
        p2.DrawCards(7);

        AdvancePhase();
    }

    public Phase GetPhase() { return phase; }
    public boolean HasPhaseStop(Phase.Name phaseName, boolean activePlayer) { return phaseStops.Get(phaseName, activePlayer); }
    public boolean HasPhaseStop(Phase p) { return HasPhaseStop(p.GetName(), p.GetActivePlayer()); }

    public Player GetPlayer1() { return p1; }
    public Player GetPlayer2() { return p2; }
    public Player GetActivePlayer() { return phase.GetActivePlayer() ? p1 : p2; }
    public Player GetNonActivePlayer() { return phase.GetActivePlayer() ? p2 : p1;}

    public boolean CostsBeingPaid() { return p1.IsPayingCosts() || p2.IsPayingCosts(); }
    public Cost GetCostBeingPaid() {
        if (p1.IsPayingCosts())
            return p1.GetCostBeingPaid();
        if (p2.IsPayingCosts())
            return p2.GetCostBeingPaid();
        return new Cost();
    }

    public Stack GetStack() { return stack; }

    public boolean IsOver() { return p1.LostGame() || p2.LostGame(); }

    public void AdvancePhase() {
        do {
            if (phase.GetName() == Phase.Name.Untap) {
                GetActivePlayer().UntapPermanents();
                GetActivePlayer().RemoveSummoningSickness();
            }

            else if (phase.GetName() == Phase.Name.Draw) {
                if (!(GetActivePlayer().PlayedFirst() && phase.GetTurnNumber() == 1))
                    GetActivePlayer().DrawCards(1);
            }

            else if (phase.GetName() == Phase.Name.Attack) {
                GetActivePlayer().TapAttackers();
            }

            else if (phase.GetName() == Phase.Name.Damage) {
                GetNonActivePlayer().DealDamage(GetActivePlayer().GetAttackerDamage());
            }

            else if (phase.GetName() == Phase.Name.EndCombat) {
                GetActivePlayer().ResetAttackers();
            }

            else if (phase.GetName() == Phase.Name.End) {
                // todo is this true or only on your/their turn? (ie GetActivePlayer().ResetLandsPlayed())
                p1.ResetLandsPlayed();
                p2.ResetLandsPlayed();
            }

            p1.EmptyManaPool();
            p2.EmptyManaPool();
            phase.NextPhase();
        } while (!HasPhaseStop(phase));
        MainView.Update();
    }

    public void CastSpell(Card card) {
        if (card.GetCost().GetManaCost().Convert() == 0)
            PlayCard(card);
        else {
            card.GetOwner().PayFor(card);
            MainView.Update();
        }
    }

    public void Uncast() {
        assert(CostsBeingPaid());
        p1.StopCasting();
        MainView.Update();
    }

    public void PlayCard(Card card) {
        card.GetOwner().GetHand().remove(card);
        if (card.UsesStack()) {
            stack.AddEffect(card);
            card.SetZone(CardZone.Stack);
        }
        else
            card.Resolve();

        MainView.Update();
    }

    public void AbilityActivated(Ability ability) {
        if (ability.UsesStack())
            stack.AddEffect(ability);
        else
            ability.Resolve();
        MainView.Update();
    }

    public void AbilityTriggered(Ability ability) {
        stack.AddEffect(ability);
        MainView.Update();
    }
}
