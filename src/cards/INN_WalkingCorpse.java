package cards;

import game.Cost;
import game.Player;
import game.Creature;

public class INN_WalkingCorpse extends Creature {

    public INN_WalkingCorpse(Player owner) {
        super(owner, "Walking Corpse", 2, 2);
        AddSubtype(Subtype.Zombie);
    }

    @Override
    public Cost GetCost() { return new Cost("1B"); }
}
