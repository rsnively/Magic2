package cards;

import game.Cost;
import game.Player;
import game.Spell;

public class INN_WalkingCorpse extends Spell {

    public INN_WalkingCorpse(Player owner) {
        super(owner, "Walking Corpse");
        AddType(Type.Creature);
        AddSubtype(Subtype.Zombie);
    }

    @Override
    public Cost GetCost() { return new Cost("1B"); }

    @Override
    public void Resolve() {
        GetOwner().GetPermanents().add(this);
    }
}
