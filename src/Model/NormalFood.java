package Model;

import java.awt.*;

public class NormalFood extends Food{
    public NormalFood() {
        points = 1;
        color = new Color(248, 168, 153);
    }

    @Override
    public void HitBy(Snake s) {
        s.GetPlayer().AddPoints(points);
        s.Grow();
        board.GenFood(0);
    }
}
