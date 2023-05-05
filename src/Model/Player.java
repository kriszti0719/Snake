package Model;

import java.util.List;

public class Player {
    private String name;
    private int points;
    private int multiplier;
    private List<Snake> snake;
    private boolean plays = false;
    private boolean winner = true;
    private Board board = Board.GetInstance();

    public Player(String n) {
        name = n;
        points = 0;
        multiplier = 1;
        plays = true;
    }

    public void AddPoints(int p) {
        points += p * multiplier ;
    }

    public int GetPoints() {return points;}

    public void SetPoints(int i) {points = i;}

    public String GetName() {return name;}

    public void SetName(String n) {name = n;}

    public void SetSnake(List<Snake> s){
        snake = s;
        for(int i = 0; i < s.size(); i++){
            s.get(i).SetPlayer(this);
            if(!board.SteppableContains(s.get(i)))
                board.AddSteppable(s.get(i));
        }
    }

    public void SetMultiplier(int m) {
        multiplier = m;
    }

    public void Lose() {
        winner = false;
    }

    public void SetPlay(boolean b) {
        plays = b;
    }

    public boolean isAlive() {
        if (plays)
            return true;
        return false;
    }

    public boolean Won() { return winner;}

    public List<Snake> GetSnake() {
        return snake;
    }

    public void NewDirection(Direction d) {
        snake.get(0).SetDirection(d);
    }

    public void Move(){
        for (int i = snake.size() - 1; i > 0; i--) {
            snake.get(i).SetDirection(snake.get(i - 1).GetDirection());
        }
    }
}
