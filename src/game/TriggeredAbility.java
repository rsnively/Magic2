package game;

public abstract class TriggeredAbility extends Ability {

    private Card source;
    private String description;
    private Trigger trigger;

    public TriggeredAbility(Card source, String description, Trigger trigger) {
        this.source = source;
        this.description = description;
        this.trigger = trigger;
    }

    public Trigger.Type GetTriggerType() { return trigger.GetType(); }
    public Card GetSource() { return source; }

    @Override
    public boolean IsTriggeredAbility() { return true; }

    @Override
    public String GetEffectDescription() { return description; }

    @Override
    public String GetEffectImageFile() { return source.GetEffectImageFile(); }

    @Override
    public boolean UsesStack() { return true; }
}
