package game;

import java.util.ArrayList;

public class Stack {

    public ArrayList<Effect> effects;

    public Stack() {
        effects = new ArrayList<>();
    }

    public ArrayList<Effect> GetEffects() { return effects; }

    public void AddEffect(Effect e) {
        effects.add(e);
    }

    public void Resolve() {
        effects.remove(effects.size() - 1).Resolve();
    }

    public boolean HasEffects() { return effects.size() > 0; }

}
