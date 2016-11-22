package cards;

import game.Cost;
import game.Creature;
import game.Player;
import game.Trigger;
import game.TriggeredAbility;

public class ISD_ArmoredSkaab extends Creature {

    public ISD_ArmoredSkaab(Player owner) {
        super("ISD", owner, "Armored Skaab", 1, 4);
        AddSubtype(Subtype.Zombie);
        AddSubtype(Subtype.Warrior);

        AddAbility(new TriggeredAbility(this,
                                        "Put the top four cards of your library into your graveyard.",
                                        new Trigger(Trigger.Type.EntersBattlefield)) {
                                        @Override
                                        public void Resolve() {
                                            GetOwner().DrawCards(1);
                                        }
        });
    }

    @Override
    public Cost GetCost() { return new Cost("2U"); }

    //todo triggered ability
}
