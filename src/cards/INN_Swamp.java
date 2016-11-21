package cards;

import game.Land;
import game.Mana;
import game.Player;

public class INN_Swamp extends Land {

    public INN_Swamp(Player owner) {
        super(owner, "Swamp");
        AddSupertype(Supertype.Basic);
        AddSubtype(Subtype.Swamp);
    }

    @Override
    public Mana.Color ColorProduced() { return Mana.Color.Black; }
}
