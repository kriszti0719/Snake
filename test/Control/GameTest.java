package Control;

import Model.Direction;
import Model.Snake;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameTest {
    GameFrame frame;
    Game game;

    @Before
    public void SetUp(){
        frame = new GameFrame();
        game = new Game(frame);
    }

    @Test
    public void GameCtrTest() {
        Assert.assertEquals(game.GetGameFrame(), frame);
    }

    @Test
    public void StepTest(){
        game.StartSingleGame("Example");
        List <Snake> s = game.GetPlayer().GetSnake();
        Snake tmp = new Snake(s.get(s.size()-2).GetDirection(), s.get(s.size()-2).GetColor(), s.get(s.size()-2).GetField());
        game.StepSingle();
        Assert.assertEquals(tmp.GetField(), s.get(s.size()-1).GetField());
    }
}
