package cards;

import game.Cost;
import game.Spell;
import game.Player;

// Test card to be whatever the hell i want
public class Rory extends Spell {

    public Rory(Player owner) {
        super("NONE", owner, "Rory", new Cost());
    }

    @Override
    public void Resolve() {
        System.out.println("Rory resolves");
    }
}
