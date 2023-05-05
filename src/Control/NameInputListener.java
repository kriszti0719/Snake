package Control;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameInputListener implements ActionListener {
    private final GameFrame frame;
    private JTextField t;

    public void setT(JTextField t) {
        this.t = t;
    }

    public NameInputListener(GameFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Play")){
            frame.SingleGame(t.getText());
        }
    }
}
