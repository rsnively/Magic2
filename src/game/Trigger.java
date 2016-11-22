package game;

import java.util.ArrayList;

public class Trigger {

    public enum Type { EntersBattlefield }

    private Type type;

    public Trigger(Type type) {
        this.type = type;
    }

    public Type GetType() { return type; }

    private static ArrayList<TriggeredAbility> GetTriggeredAbilities(Type type) {
        ArrayList<TriggeredAbility> abilities = new ArrayList<>();
        for (Card c : Game.Get().GetActivePlayer().GetPermanents())
            for (TriggeredAbility ta : c.GetTriggeredAbilities())
                if (ta.GetTriggerType() == type)
                    abilities.add(ta);
        for (Card c : Game.Get().GetNonActivePlayer().GetPermanents())
            for (TriggeredAbility ta : c.GetTriggeredAbilities())
                if (ta.GetTriggerType() == type)
                    abilities.add(ta);
        return abilities;
    }

    public static void EntersTheBattlefield(Card c) {
        for (TriggeredAbility ta : GetTriggeredAbilities(Type.EntersBattlefield))
            if (c == ta.GetSource())
                Game.Get().AbilityTriggered(ta);
    }
}
