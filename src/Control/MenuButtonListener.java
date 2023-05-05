package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MenuButtonListener implements ActionListener {
    private final GameFrame frame;
    public MenuButtonListener(GameFrame frame) {
        this.frame = frame;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case("Single player")-> frame.NameInput();
            case("Multi player") -> frame.MultiGame();
            case("Top 10")->frame.top10();
            case("Exit")->System.exit(0);
            default -> throw new IllegalStateException("Unexpected value: " + e.getActionCommand());
        }
    }
}
