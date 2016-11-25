package cards;

import game.Cost;
import game.Player;
import game.Creature;

public class ISD_WalkingCorpse extends Creature {

    public ISD_WalkingCorpse(Player owner) {
        super("ISD", owner, "Walking Corpse", new Cost("1B"), 2, 2);
        AddSubtype(Subtype.Zombie);
    }
}
