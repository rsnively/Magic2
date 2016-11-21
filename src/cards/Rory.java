package cards;

import game.Cost;
import game.Spell;
import game.Player;

// Test card to be whatever the hell i want
public class Rory extends Spell {

    public Rory(Player owner) {
        super(owner, "Rory");
    }

    @Override
    public void Resolve() {
        System.out.println("Rory resolves");
    }

    @Override
    public Cost GetCost() { return new Cost(); }
}
