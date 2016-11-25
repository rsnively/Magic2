package cards;

import game.Cost;
import game.Land;
import game.Mana;
import game.Player;

public class ISD_Swamp extends Land {

    public ISD_Swamp(Player owner) {
        super("ISD", owner, "Swamp", new Cost());
        AddSupertype(Supertype.Basic);
        AddSubtype(Subtype.Swamp);
    }

    @Override
    public Mana.Color ColorProduced() { return Mana.Color.Black; }
}
