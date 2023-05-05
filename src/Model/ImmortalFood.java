package Model;

import java.awt.*;

public class ImmortalFood extends Food{
    ImmortalEffect effect;
    public ImmortalFood() {
        color = new Color(187, 147, 205);
        points = 3;
    }

    @Override
    public void HitBy(Snake s) {
        effect = new ImmortalEffect();
        s.GetPlayer().AddPoints(points);
        s.SetEffect(effect);
    }
}
