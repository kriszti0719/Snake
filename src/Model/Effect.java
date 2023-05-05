package Model;

public abstract class Effect {
    //Identical
    protected String name;
    // Fix
    protected int LifeTime;
    //Will be decreased from sec to sec
    protected int TTL;

    public Effect() {}

    public int GetTTL() {
        return TTL;
    }

    public String GetName() {return name;}

    public void Aging() {
        TTL -= 1;
    }

    public void Restart() {
        TTL = LifeTime;
    }

    public abstract void Effect(Player p);

    public abstract void Remove(Player p);
}
