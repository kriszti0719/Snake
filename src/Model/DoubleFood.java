package Model;

import java.awt.*;

public class DoubleFood extends Food{
    DoublePointEffect effect;

    public DoubleFood() {
        points = 3;
        color = new Color(226, 207, 132);
    }
    @Override
    public void HitBy(Snake s) {
        effect = new DoublePointEffect();
        s.GetPlayer().AddPoints(points);
        s.SetEffect(effect);
    }
}
