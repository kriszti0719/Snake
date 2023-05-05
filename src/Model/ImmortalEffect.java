package Model;

public class ImmortalEffect extends Effect{
    public ImmortalEffect() {
        name =  "Immortal";
        LifeTime = 30 * 10;     // ~30 seconds
        TTL = LifeTime;
    }

    @Override
    public void Effect(Player p) {}

    @Override
    public void Remove(Player p) {}
}
