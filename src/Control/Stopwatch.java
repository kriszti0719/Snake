package Control;

import java.awt.event.ActionListener;


public class Stopwatch {
    private final javax.swing.Timer timer;
//    private int elapsedTime = 0;
//    private JFrame frame;
    private Game game;

    public Stopwatch(Game g, String s){
        game = g;

        ActionListener timeListener = e -> {
//            elapsedTime += 1000;
//            elapsedTime = 0;
            Tick(s);
        };
        timer = new javax.swing.Timer(100, timeListener);
    }

    public void Tick(String s){
        if(s.equals("Single"))
            game.StepSingle();
        else if(s.equals("Multi"))
            game.StepMulti();
    }

    public void Start(){
        timer.start();
    }

    public void Stop(){
        timer.stop();
    }

//    public void Restart(){
//        elapsedTime = 0;
//        timer.restart();
//    }
}
