package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.*;


public class Board {
    private Field[][] fields;
    private final List<Steppable> steppables;
    // static variable b of type Singleton
    private static Board b = null;
    private boolean singleGame;

    //private constructor of the Singleton class that restricted to this class itself
    private Board() {
        fields = new Field[40][40];
        steppables = new ArrayList<>();
    }

    public void DeleteBoard() {
        b = null;
    }

    //static method to create an instance of the Singleton class
    // we can also create a method with the same name as the class name
    public static Board GetInstance() {
        if (b== null)
            b = new Board();
        return b;
    }

    public void SetGameType(boolean b) {singleGame = b;}

    public void Create() {
        //Create fields
        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 40; x++) {
                fields[x][y] = new Field();
                fields[x][y].SetX(x * 20);
                fields[x][y].SetY(y * 20);

                if (x == 0 || x == 39 || y == 0 || y == 39) {
                    Wall tmp = new Wall();
                    fields[x][y].AddWall(tmp);
                    tmp.SetField(fields[x][y]);
                }
            }
        }

        //Set Neighbors
        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 40; x++) {
                if (y >= 1) {
                    //So basically everything, but the first row,has a neigbhor
                    fields[x][y].SetNeighbor(Direction.UP, fields[x][y-1]);
                }
                if(y < 39){
                    fields[x][y].SetNeighbor(Direction.DOWN, fields[x][y+1]);
                }
                if(x >= 1) {
                    fields[x][y].SetNeighbor(Direction.LEFT, fields[x-1][y]);
                }
                if(x < 39) {
                    fields[x][y].SetNeighbor(Direction.RIGHT, fields[x+1][y]);
                }
            }
        }
//        TEST
//        fields[19][17].AddFood(new NormalFood());
//        fields[19][17].GetFood().SetField(fields[19][17]);
        GenFood(0);
    }

    public List<Snake> GenSnake(int column, Color c){
        List<Snake> snake = new ArrayList<>();
        //Create a 4FieldLong Snake
        for (int y = 19; y < 23; y++) {
            Snake part;
                part = new Snake(Direction.UP, c, fields[column+1][y]);
                steppables.add(part);
                snake.add(part);
                fields[column][y].Accept(part);
            }
        return snake;
    }

    public void AddSteppable(Steppable s) {
        steppables.add(s);
    }

    public void RemoveSteppable(Steppable s) {steppables.remove(s);}

    public boolean SteppableContains(Steppable s) {
        return steppables.contains(s);
    }

    public void GenFood(int i) {
        //How random works: nextInt(X) give a number from 0 to X (without X)
        //I needed something between 1 - 38, so nextInt(37) gives something between 0 - 37, then +1
        Random rnd = new Random();
        int x = rnd.nextInt(37) + 1;
        int y = rnd.nextInt(37) + 1;
        while (!fields[x][y].Empty()) {
            x = rnd.nextInt(37) + 1;
            y = rnd.nextInt(37) + 1;
        }

        //Delete previous special one
        for (int j = 0; j < 40; j++){
            for(int k = 0; k < 40; k++){
                if(!fields[k][j].Empty()){
                    fields[k][j].RemoveFood();
                }
            }
        }

        switch (i) {
            case 0 -> {
                //1to4 that we generate a special food
                if(singleGame) {
                    int special = rnd.nextInt(5);
//                TEST
//                int special = 0;
                    if (special == 0) {
                        //Then we draw it's type
                        int type = rnd.nextInt(3) + 1;
//                    TEST
//                    type = 3;
                        GenFood(type);
                    }
                }
                fields[x][y].AddFood(new NormalFood());
            }
            case 1 -> fields[x][y].AddFood(new DecreaseFood());
            case 2 -> fields[x][y].AddFood(new DoubleFood());
            case 3 -> fields[x][y].AddFood(new ImmortalFood());
        }
        fields[x][y].GetFood().SetField(fields[x][y]);
    }

    public Field GetField (int x, int y) {
        return fields[x][y];
    }

    //We need to start from the head: if we wouldn't, he would bite himself by the first step
    public void Step() {
        for (int i = 0; i < steppables.size(); i++) {
            steppables.get(i).Step();
        }
    }

}
