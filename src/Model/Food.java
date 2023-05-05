package Model;

import View.RectangleConst;

import java.awt.*;

public abstract class Food {
    protected Color color;
    protected int points;
    protected Field field;
    protected Board board;

    public Food() {
        points = 1;
        color = new Color(248, 168, 153);
        board = Board.GetInstance();
    }

    public abstract void HitBy(Snake s);

    public void SetField(Field f) {
        field = f;
    }

    public void Draw(Graphics g){
        g.setColor(color);
        g.fillRect(field.GetX(), field.GetY(), RectangleConst.RECT_WIDTH, RectangleConst.RECT_HEIGHT);
    }
}
