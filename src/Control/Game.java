package Control;

import Model.*;
import View.GamePanel;

import java.awt.*;

public class Game extends GamePanel {
    private Player player1;
    private Player player2;
    private GameFrame frame;
    private Board board;
    private Stopwatch stopwatch;
    private InputHandler keyHandler;

    public Game(GameFrame frame) {
        super();
        board = Board.GetInstance();
        this.frame = frame;
        this.grabFocus();
    }

    public GameFrame GetGameFrame() {return frame;}

    //For TESTS
    public Player GetPlayer() {return player1;}

    public Board GetBoard() {return board;}

    public void StartSingleGame(String name) {
        this.grabFocus();
        board = Board.GetInstance();
        board.SetGameType(true);
        board.Create();
        player1 = new Player(name);
        player1.SetSnake(board.GenSnake(38/2, new Color(161, 204, 233)));
        keyHandler = new InputHandler(this);
        stopwatch = new Stopwatch(this, "Single");
        stopwatch.Start();
    }

    public void StartMultiGame() {
        this.grabFocus();
        board = Board.GetInstance();
        board.SetGameType(false);
        board.Create();
        player1 = new Player("Blue");
        player1.SetSnake(board.GenSnake(((38/3)+1)*2, new Color(161, 204, 233)));
        player2 = new Player("Purple");
        player2.SetSnake(board.GenSnake((38/3)+1, new Color(187, 147, 205)));
        keyHandler = new InputHandler(this);
        stopwatch = new Stopwatch(this, "Multi");
        stopwatch.Start();
    }

    public void StepSingle() {
        player1.Move();
        KeyHandling();
        board.Step();
        if(player1.isAlive())
            this.repaint();
        else
            EndGameSingle();
    }
    public void StepMulti() {
        player1.Move();
        player2.Move();
        KeyHandling();
        board.Step();
        if(player1.isAlive() && player2.isAlive())
            this.repaint();
        else
            EndGameMulti();
    }

    public void KeyHandling(){
        if (keyHandler.isDOWN() && !(player1.GetSnake().get(0).GetDirection()==Direction.UP)) {
            player1.NewDirection(Direction.DOWN);
        }
        if (keyHandler.isUP() && !(player1.GetSnake().get(0).GetDirection()==Direction.DOWN)) {
            player1.NewDirection(Direction.UP);
        }
        if (keyHandler.isLEFT() && !(player1.GetSnake().get(0).GetDirection()==Direction.RIGHT)) {
            player1.NewDirection(Direction.LEFT);
        }
        if (keyHandler.isRIGHT() && !(player1.GetSnake().get(0).GetDirection()==Direction.LEFT)) {
            player1.NewDirection(Direction.RIGHT);
        }

        if(player2 != null) {
            if (keyHandler.isS() && !(player2.GetSnake().get(0).GetDirection()==Direction.UP)) {
                player2.NewDirection(Direction.DOWN);
            }
            if (keyHandler.isW() && !(player2.GetSnake().get(0).GetDirection()==Direction.DOWN)) {
                player2.NewDirection(Direction.UP);
            }
            if (keyHandler.isA() && !(player2.GetSnake().get(0).GetDirection()==Direction.RIGHT)) {
                player2.NewDirection(Direction.LEFT);
            }
            if (keyHandler.isD() && !(player2.GetSnake().get(0).GetDirection()==Direction.LEFT)) {
                player2.NewDirection(Direction.RIGHT);
            }
        }
    }

    public void EndGameSingle() {
        stopwatch.Stop();
        board.DeleteBoard();
        frame.Endings(player1, player2);
    }

    public void EndGameMulti() {
        stopwatch.Stop();
        board.DeleteBoard();
        frame.Endings(player1, player2);
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 40; j++) {
                if(!(board.GetField(j, i) == null)){
                    board.GetField(j, i).Draw(g);
                }
            }
        }
    }


}
