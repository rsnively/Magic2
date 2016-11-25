package cards;

import game.Cost;
import game.Land;
import game.Mana;
import game.Player;

public class ISD_Island extends Land {

    public ISD_Island(Player owner) {
        super("ISD", owner, "Island", new Cost());
        AddSupertype(Supertype.Basic);
        AddSubtype(Subtype.Island);
    }

    @Override
    public Mana.Color ColorProduced() { return Mana.Color.Blue; }
}
