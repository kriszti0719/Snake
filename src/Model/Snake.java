package Model;

import Control.Game;
import View.GamePanel;
import View.RectangleConst;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake implements Steppable{
    private Direction direction;
    private Color color;
    private List<Effect> effects;
    private Field field;
    private Player player;
    private Board board;

    public Snake (Direction d, Color c, Field f) {
        direction = d;
        color = c;
        effects = new ArrayList<>();
        field = f;
        board = Board.GetInstance();
        player = null;
    }
    public void SetDirection(Direction direction) { this.direction = direction; }

    public Direction GetDirection() {return direction;}

    public Color GetColor() {return color;}

    public void SetField(Field f) {field = f;}

    public Field GetField() {return field;}

    public void SetPlayer(Player p) {player = p;}

    public Player GetPlayer() {return player;}

    public void SetEffect(Effect e) {
        for(int i = 0; i < effects.size(); i++){
            if(e.GetName().equals(effects.get(i).GetName())){
                effects.get(i).Restart();
                return;
            }
        }
        effects.add(e);
    }

    public void HitBy(Snake s) {
        if(s.player == player) {
            for (int i = 0; i < effects.size(); i++) {
                if (effects.get(i).GetName().equals("Immortal")) {
                    return;
                }
            }
            s.player.Lose();
        }
        else
            player.Lose();
        Die();
    }

    public void HitBy() {
        player.Lose();
        Die();
    }

    public void Grow() {
        //Last Element:
        Snake end = player.GetSnake().get(player.GetSnake().size()-1);

        //The field, at the end of the Snake's bottom
        Field newField;
        switch (end.GetDirection()){
            //NOT HERE
            case UP -> newField = board.GetField(end.GetField().GetX()/20, end.GetField().GetY()/20 + 1);
            case DOWN -> newField = board.GetField(end.GetField().GetX()/20, end.GetField().GetY()/20 - 1);
            case LEFT -> newField = board.GetField(end.GetField().GetX()/20 + 1, end.GetField().GetY()/20);
            case RIGHT -> newField = board.GetField(end.GetField().GetX()/20 - 1, end.GetField().GetY()/20);
            default -> newField = board.GetField(end.GetField().GetX()/20, end.GetField().GetY()/20 - 1);
        }

        //The new part of the Snake
        Snake newSnake = new Snake(end.GetDirection(), end.GetColor(), newField);

        //Field gets the Snake
        newSnake.GetField().Accept(newSnake);

        //Snake gets the new part, so as the player
        newSnake.player = end.player;
        end.GetPlayer().GetSnake().add(newSnake);

        //And so it becomes Steppable
        board.AddSteppable(newSnake);
    }

    public void Shrink() {
        List<Snake> snake = player.GetSnake();
        int s = snake.size();
        if(s > 9){
            for(int i = s-1; i >= s/3*2; i--){
                snake.get(i).GetField().Remove(snake.get(i));
                board.RemoveSteppable(snake.get(i));
                player.GetSnake().remove(i);
                System.out.println(snake.size()/3*2);
            }
        }
    }

    public void Die() {
//        System.out.println("Dead");
//        for(int i = 0; i < player.GetSnake().size(); i++) {
//            System.out.println(i);
//            System.out.println("    " + player.GetSnake().get(i).GetField().GetX());
//            System.out.println("    " + player.GetSnake().get(i).GetField().GetY());
//        } TESZT
        player.SetPlay(false);
    }

    public void Draw(Graphics g) {
        g.setColor(color);
        g.fillRect(field.GetX(), field.GetY(), RectangleConst.RECT_WIDTH, RectangleConst.RECT_HEIGHT);
    }

    //A mező a MoveOn-ban lekéri az irányát és átadja a szomszédjának a megfelelő irányba
    //A szomszédon meghívódik az Accept, hozzáadja magához a kígyót, ahogy a kígyóhoz is Önmagát.
    //Vagyis a kígyónak itt nincs más dolga, mint jelezni, hogy lépne.
    public void Step(){
        if(!effects.isEmpty()) {
            for (int i = 0; i < effects.size(); i++) {
                effects.get(i).Effect(player);
                effects.get(i).Aging();
                if (effects.get(i).GetTTL() == 0) {
                    effects.get(i).Remove(player);
                    effects.remove(i);
                }
            }
        }
        field.MoveOn(this);
    }
}
