package Model;

public class DoublePointEffect extends Effect{
    public DoublePointEffect() {
        name =  "DoublePoint";
        LifeTime = 45 * 10;     // ~45 seconds
        TTL = LifeTime;
    }

    @Override
    public void Effect(Player p) {
        p.SetMultiplier(2);
    }

    @Override
    public void Remove(Player p) {
        p.SetMultiplier(1);
    }
}
