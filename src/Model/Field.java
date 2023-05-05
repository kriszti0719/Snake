package Model;

import View.RectangleConst;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {
    private Map<Direction, Field> neighbor = new HashMap();
    private List<Snake> snakes;
    private Food food;
    private Wall wall;
    private int x;
    private int y;
    private Board board = Board.GetInstance();
    Color color = new Color(60, 63, 65);

    public Field() {
        wall = null;
        food = null;
        snakes = new ArrayList<Snake>();
    }

    public void SetX(int _x) {
        x = _x;
    }

    public int GetX() {
        return x;
    }

    public void SetY(int _y) {y = _y;}

    public int GetY() {
        return y;
    }

    public void Accept(Snake s) {
        if(food != null){
            food.HitBy(s);
            RemoveFood();
        } else if (!snakes.isEmpty() && s.GetPlayer().GetSnake().get(0) == s){
            s.HitBy(snakes.get(0));
        } else if(wall != null){
            s.HitBy();
        }

        snakes.add(s);
        s.SetField(this);
    }

    public boolean Empty() {
        if(snakes.isEmpty() && food == null){
            return true;
        }   else {
            return false;
        }
    }

    public void MoveOn(Snake s) {
        snakes.remove(s);
        Direction d = s.GetDirection();
        neighbor.get(d).Accept(s);
    }

    public void Remove(Snake s){
        snakes.remove(s);
    }

    public void AddFood(Food f) {
        food = f;
    }

    public Food GetFood() {
        return food;
    }

    public void RemoveFood() {
        food = null;
    }

    public void AddWall(Wall w){
        wall = w;
    }

    public Field GetNeighbor(Direction d){ return neighbor.get(d); }

    public void SetNeighbor(Direction d, Field f) {
        neighbor.put(d, f);
    }

    public void Draw(Graphics g) {
        if(snakes.isEmpty()!=true){
            snakes.get(0).Draw(g);
        }
        else if(food!=null){
            food.Draw(g);
        }

        else if (wall!=null) {
                wall.Draw(g);
            }
        else {
            g.setColor(color);
            g.fillRect(x, y, RectangleConst.RECT_WIDTH, RectangleConst.RECT_HEIGHT);
        }
    }
}
