package ai;

import game.Card;
import game.Game;
import game.Land;
import game.Mana;
import game.ManaCost;
import game.Player;

import java.util.Iterator;

public class AIPlayer {
    public static void MainPhase(Player p) {
        Iterator<Card> i = p.GetHand().iterator();
        while(i.hasNext()) {
            Card c = i.next();
            if (c.Is(Card.Type.Land) && c.CanPlay()) {
                Game.Get().PlayCard(c);
            }
            else if (AIDecision.Decide() && CanPlay(p, c)) {
                PayForCard(p, c);
                Game.Get().PlayCard(c);
            }
        }
    }

    public static boolean CanPlay(Player p, Card card) {
        Mana available = new Mana();
        for (Card c : p.GetPermanents())
            if (c.Is(Card.Type.Land))
                available.Add(((Land) c).ColorProduced());
        return available.IsEnoughFor(card.GetCost());
    }

    public static void PayForCard(Player p, Card c) {
        ManaCost remaining = c.GetCost().GetManaCost();

        for (Mana.Color color : Mana.Color.values())
            if (remaining.Has(color))
                for (Card card : p.GetPermanents())
                    if (card.Is(Card.Type.Land) && !card.IsTapped() && ((Land)card).ColorProduced() == color)
                        card.Tap();
        for (int i = 0; i < p.GetPermanents().size() && remaining.GetGeneric() > 0; i++) {
            Card card = p.GetPermanents().get(i);
            if (card.Is(Card.Type.Land) && !card.IsTapped())
                card.Tap();
        }
    }
}
