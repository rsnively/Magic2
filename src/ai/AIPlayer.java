package ai;

import game.Card;
import game.CardZone;
import game.Game;
import game.Land;
import game.Mana;
import game.ManaCost;
import game.Player;

import java.util.Iterator;

public class AIPlayer {
    public static void MainPhase(Player p) {
        for (Iterator<Card> it = p.GetHand().iterator(); it.hasNext(); ) {
            Card c = it.next();
            if (c.Is(Card.Type.Land) && c.CanPlay()) {
                it.remove();
                c.Resolve();
            }
            else if (AIDecision.Decide() && CanPlay(p, c)) {
                it.remove();
                PayForCard(p, c);
                if (c.UsesStack()) {
                    Game.Get().GetStack().AddEffect(c);
                    c.SetZone(CardZone.Stack);
                }
                else
                    c.Resolve();
            }
        }
    }

    public static boolean CanPlay(Player p, Card card) {
        if (!card.CanPlay())
            return false;
        Mana available = new Mana();
        for (Iterator<Card> it = p.GetPermanents().iterator(); it.hasNext(); ) {
            Card c = it.next();
            if (c.Is(Card.Type.Land) && !c.IsTapped())
                available.Add(((Land) c).ColorProduced());
        }
        return available.IsEnoughFor(card.GetCost());
    }

    public static void PayForCard(Player p, Card c) {
        ManaCost remaining = c.GetCost().GetManaCost();

        for (Mana.Color color : Mana.Color.values()) {
            if (remaining.Has(color)) {
                for (Iterator<Card> it = p.GetPermanents().iterator(); it.hasNext(); ) {
                    Card card = it.next();
                    if (card.Is(Card.Type.Land) && !card.IsTapped() && ((Land) card).ColorProduced() == color && !remaining.Paid())
                        card.Tap();
                }
            }
        }
        for (int i = 0; i < p.GetPermanents().size() && remaining.GetGeneric() > 0; i++) {
            Card card = p.GetPermanents().get(i);
            if (card.Is(Card.Type.Land) && !card.IsTapped())
                card.Tap();
        }
    }
}
