package Control;

import Model.Player;
import View.Endings;
import View.GamePanel;
import View.Menu;
import View.ScoreBoard;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class GameFrame extends JFrame {
    private Game game;
    private final MenuButtonListener menuListener = new MenuButtonListener(this);
    private Menu menu = new Menu(menuListener);
    private final BackButtonListener backListener = new BackButtonListener(this);
    private Endings end = new Endings(backListener);
    private ScoreBoard scoreBoard = new ScoreBoard(backListener);
    private List<Player> top10;
    private FileHandling fileHandling = new FileHandling();

    public GameFrame() {
        super("Snake");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
//        this.setUndecorated(true);
        this.setVisible(true);
        top10 = fileHandling.readXML();
        MainMenu();
    }

    public void MainMenu() {
        scoreBoard.Remove();
        end.Remove();
        menu.Init();
        this.setContentPane(menu);
        this.pack();
    }

    public void SingleGame(String name) {
        game = new Game(this);
        this.setContentPane(game);
        this.pack();
        game.StartSingleGame(name);
    }

    public void MultiGame() {
        game = new Game(this);
        this.setContentPane(game);
        this.pack();
        game.StartMultiGame();
    }

    public void Endings(Player p1, Player p2) {
        end.init();
        if(p2 == null){
            fileHandling.AddPlayer(p1);
            end.ShowScore(p1);
        } else {
            end.ShowWinner(p1, p2);
        }

        this.setContentPane(end);
        this.pack();
    }

    public void top10() {
        scoreBoard.Init(top10);
        this.setContentPane(scoreBoard);
        this.pack();
    }

    public void NameInput() {
        NameInputListener nameInputListener = new NameInputListener(this);
        NameInput nameInput = new NameInput(nameInputListener);
        nameInput.init();
        this.setContentPane(nameInput);
        this.pack();
    }
}
