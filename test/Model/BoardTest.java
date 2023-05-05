package Model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {
    Board board;

    @Before
    public void SetUp() {
        board = Board.GetInstance();
        board.Create();
    }

    @Test
    public void TestCoordinates() {
        for (int x = 0; x < 40; x++) {
            for (int y = 0; y < 40; y++){
                Assert.assertEquals(board.GetField(x, y).GetX()/20, x);
                Assert.assertEquals(board.GetField(x, y).GetY()/20, y);
            }
        }
    }

    @Test
    public void TestNeighbors() {
        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 40; x++) {
                if (y >= 1) {
                    Assert.assertEquals(board.GetField(x, y).GetNeighbor(Direction.UP), board.GetField(x, y - 1));
                }
                if(y < 39){
                    Assert.assertEquals(board.GetField(x, y).GetNeighbor(Direction.DOWN), board.GetField(x, y + 1));
                }
                if(x >= 1) {
                    Assert.assertEquals(board.GetField(x, y).GetNeighbor(Direction.LEFT), board.GetField(x - 1, y));
                }
                if(x < 39) {
                    Assert.assertEquals(board.GetField(x, y).GetNeighbor(Direction.RIGHT), board.GetField(x + 1, y));
                }
            }
        }
    }
}
