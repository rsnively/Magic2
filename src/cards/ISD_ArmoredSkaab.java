package cards;

import game.Cost;
import game.Creature;
import game.Player;

public class ISD_ArmoredSkaab extends Creature {

    public ISD_ArmoredSkaab(Player owner) {
        super("ISD", owner, "Armored Skaab", 1, 4);
        AddSubtype(Subtype.Zombie);
        AddSubtype(Subtype.Warrior);
    }

    @Override
    public Cost GetCost() { return new Cost("2U"); }

    //todo triggered ability
}
