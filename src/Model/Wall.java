package Model;

import View.RectangleConst;

import java.awt.*;

public class Wall {
    private Field field;
    private Color color = new Color(43, 43, 43);

    public void Draw(Graphics g) {
        g.setColor(color);
        g.fillRect(field.GetX(), field.GetY(), RectangleConst.RECT_WIDTH, RectangleConst.RECT_HEIGHT);
    }

    public void SetField(Field f) {
        this.field = f;
    }
}
