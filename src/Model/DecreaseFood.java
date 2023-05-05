package Model;

import java.awt.*;

public class DecreaseFood extends Food{
    public DecreaseFood(){
        points = 3;
        color = new Color(101, 201, 145);
    }

    @Override
    public void HitBy(Snake s) {
        s.GetPlayer().AddPoints(points);
        s.Shrink();
    }
}
