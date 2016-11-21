package game;

import java.util.ArrayList;

public abstract class Card implements Effect {
    public enum Type { Creature, Land }
    public enum Supertype { Basic }
    public enum Subtype { Swamp, Zombie }

    private String set;
    public String GetFullCardImageFile() { return "res/card/" + set + "/full/" + name + ".full.jpg"; }
    public String GetCardArtImageFile() { return "res/card/" + set + "/art/" + name + ".art.jpg"; }

    private Player owner;
    private String name;

    private ArrayList<Type> types;
    private ArrayList<Supertype> supertypes;
    private ArrayList<Subtype> subtypes;
    private ArrayList<Ability> abilities;

    private boolean tapped;
    private boolean attacking;

    public Card(String set, Player owner, String name) {
        this.set = set;
        this.owner = owner;
        this.name = name;
        types = new ArrayList<>();
        supertypes = new ArrayList<>();
        subtypes = new ArrayList<>();
        abilities = new ArrayList<>();

        this.tapped = false;
        this.attacking = false;
    }

    public Player GetOwner() { return owner; }
    public String GetName() { return name; }
    public int GetPower() { return 0; }
    public int GetToughness() { return 0; }

    public boolean IsInHand() { return owner.GetHand().contains(this); }
    public boolean IsInLibrary() { return owner.GetLibrary().contains(this); }
    public boolean IsOnBattlefield() { return owner.GetPermanents().contains(this); }
    public boolean IsInGraveyard() { return owner.GetGraveyard().contains(this); }

    public void Tap() { tapped = true; }
    public void Untap() { tapped = false; }
    public boolean IsTapped() { return tapped; }

    public String GetEffectDescription() { return name; }
    public String GetEffectImageFile() { return GetCardArtImageFile(); }

    public void AddAbility(Ability ability) { abilities.add(ability); }
    public void AddType(Type type) { types.add(type); }
    public void AddSupertype(Supertype supertype) { supertypes.add(supertype); }
    public void AddSubtype(Subtype subtype) { subtypes.add(subtype); }

    public boolean Is(Type t) { return types.contains(t); }
    public boolean Is(Supertype t) { return supertypes.contains(t); }
    public boolean Is(Subtype t) { return subtypes.contains(t); }
    public boolean IsBasicLand() { return Is(Supertype.Basic) && Is(Type.Land); }
    public boolean IsCreature() { return Is(Type.Creature); }
    public boolean IsLand() { return Is(Type.Land); }

    public boolean HasSummoningSickness() { return false; }
    public void RemoveSummoningSickness() {}

    public boolean CanActivate() {
        // todo multiple abilities
        for (Ability a : abilities)
            if (IsOnBattlefield() && a.CanActivate()) // todo abilities from gy/exile/hand?
                return true;
        return false;
    }

    public void ActivateAbility(int i) {
        abilities.get(i).Activate();
        Game.Get().AbilityActivated(abilities.get(i));
    }

    public abstract Cost GetCost();
    public abstract boolean UsesStack();
    public boolean CanPlay() { return false; }
    public boolean CanAttack() { return false; }
    public boolean IsAttacking() { return attacking; }
    public void SetAttacking(boolean a) { attacking = a; }
    public void Resolve() {}
}
