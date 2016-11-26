package game;

import java.util.ArrayList;

public abstract class Card implements Effect {
    public enum Type { Creature, Land }
    public enum Supertype { Basic }
    public enum Subtype { Forest, Horror, Island, Mountain, Plains, Swamp, Warrior, Zombie }

    private String set;
    public String GetFullCardImageFile() { return "res/card/" + set + "/full/" + name + ".full.jpg"; }
    public String GetCardArtImageFile() { return "res/card/" + set + "/art/" + name + ".art.jpg"; }

    private Player owner;
    private CardZone zone;
    private String name;
    protected Cost cost;

    private ArrayList<Type> types;
    private ArrayList<Supertype> supertypes;
    private ArrayList<Subtype> subtypes;
    private ArrayList<Ability> abilities;

    private boolean tapped;
    private boolean attacking;

    public Card(String set, Player owner, String name, Cost cost) {
        this.set = set;
        this.owner = owner;
        this.zone = CardZone.Library;
        this.name = name;
        this.cost = cost;
        types = new ArrayList<>();
        supertypes = new ArrayList<>();
        subtypes = new ArrayList<>();
        abilities = new ArrayList<>();

        this.tapped = false;
        this.attacking = false;
    }

    public Player GetOwner() { return owner; }
    public String GetName() { return name; }
    public Cost GetCost() { return cost; }

    public ArrayList<Supertype> GetSupertypes() { return supertypes; }
    public ArrayList<Type> GetTypes() { return types; }
    public ArrayList<Subtype> GetSubtypes() { return subtypes; }

    public int GetPower() { return 0; }
    public int GetToughness() { return 0; }

    public CardZone GetZone() { return zone; }
    public void SetZone(CardZone zone ) { this.zone = zone; }

    public void Tap() { tapped = true; }
    public void Untap() { tapped = false; }
    public boolean IsTapped() { return tapped; }

    public String GetEffectDescription() { return name; }
    public String GetEffectImageFile() { return GetCardArtImageFile(); }

    public void AddAbility(Ability ability) { abilities.add(ability); }
    public ArrayList<Ability> GetAbilities() { return abilities; }
    public ArrayList<TriggeredAbility> GetTriggeredAbilities() { ArrayList<TriggeredAbility> as = new ArrayList<>(); for (Ability a : abilities) if (a.IsTriggeredAbility()) as.add((TriggeredAbility)a); return as; }
    public void AddType(Type type) { types.add(type); }
    public void AddSupertype(Supertype supertype) { supertypes.add(supertype); }
    public void AddSubtype(Subtype subtype) { subtypes.add(subtype); }

    public boolean Is(Type t) { return types.contains(t); }
    public boolean Is(Supertype t) { return supertypes.contains(t); }
    public boolean Is(Subtype t) { return subtypes.contains(t); }
    public boolean Is(Mana.Color c) { return false; }
    public boolean IsBasicLand() { return Is(Supertype.Basic) && Is(Type.Land); }
    public boolean IsCreature() { return Is(Type.Creature); }
    public boolean IsLand() { return Is(Type.Land); }

    public boolean HasSummoningSickness() { return false; }
    public void RemoveSummoningSickness() {}

    public boolean CanActivate() {
        // todo multiple abilities
        for (Ability a : abilities)
            if (GetZone() == CardZone.Battlefield && a.CanActivate()) // todo abilities from gy/exile/hand?
                return true;
        return false;
    }

    public void ActivateAbility(int i) {
        abilities.get(i).Activate();
        Game.Get().AbilityActivated(abilities.get(i));
    }

    public abstract boolean UsesStack();
    public boolean CanPlay() { return false; }
    public boolean CanAttack() { return false; }
    public boolean IsAttacking() { return attacking; }
    public void SetAttacking(boolean a) { attacking = a; }
    public void Resolve() {}
}
