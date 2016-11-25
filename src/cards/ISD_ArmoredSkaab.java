package cards;

import game.Cost;
import game.Creature;
import game.Player;
import game.Trigger;
import game.TriggeredAbility;

public class ISD_ArmoredSkaab extends Creature {

    public ISD_ArmoredSkaab(Player owner) {
        super("ISD", owner, "Armored Skaab", new Cost("2U"), 1, 4);
        AddSubtype(Subtype.Zombie);
        AddSubtype(Subtype.Warrior);

        AddAbility(new TriggeredAbility(this,
                                        "Put the top four cards of your library into your graveyard.",
                                        new Trigger(Trigger.Type.EntersBattlefield)) {
                                        @Override
                                        public void Resolve() {
                                            GetOwner().Mill(4);
                                        }
        });
    }
}
