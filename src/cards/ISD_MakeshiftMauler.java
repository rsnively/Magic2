package cards;

import game.Card;
import game.CardZone;
import game.Cost;
import game.Creature;
import game.Player;
import game.TransferCost;

public class ISD_MakeshiftMauler extends Creature {

    public ISD_MakeshiftMauler(Player owner) {
        super("ISD", owner, "Makeshift Mauler", new Cost("3U"), 4, 5);
        AddSubtype(Subtype.Zombie);
        AddSubtype(Subtype.Horror);
        cost.AddTransferCost(new TransferCost(CardZone.Graveyard, CardZone.Exile, 1, Card.Type.Creature));
    }
}
