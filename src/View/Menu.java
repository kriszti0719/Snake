package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu extends GamePanel {
    private JButton bSingle;
    private JButton bMulti;
    private JButton bTop;
    private JButton bExit;
    private JLabel title;
    private final GridBagConstraints gridBag = new GridBagConstraints();
    private final ActionListener actionListener;

    public Menu(ActionListener actionListener) {
        super();
        this.setLayout(new GridBagLayout());
        this.actionListener = actionListener;
    }

    public void Init() {
        this.setVisible(true);
        ButtonCustomize();
        TitleCustomize();

        this.setPreferredSize(new Dimension(800, 800));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setVisible(true);

        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.insets = new Insets(20, 0, 20,0);

        this.add(bSingle, gridBag);
        gridBag.gridy = 2;
        this.add(bMulti, gridBag);
        gridBag.gridy = 3;
        this.add(bTop, gridBag);
        gridBag.gridy = 4;
        this.add(bExit, gridBag);

        gridBag.insets = new Insets(0, 35, 80, 0);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.CENTER;
        this.add(title, gridBag);
    }

    public void ButtonCustomize() {
        bSingle = new JButton("Single player");
        bMulti = new JButton("Multi player");
        bTop = new JButton("Top 10");
        bExit = new JButton("Exit");

        bSingle.setFont(buttonFont);
        bMulti.setFont(buttonFont);
        bTop.setFont(buttonFont);
        bExit.setFont(buttonFont);


        bSingle.setBackground(Color.BLACK);
        bMulti.setBackground(Color.BLACK);
        bTop.setBackground(Color.BLACK);
        bExit.setBackground(Color.BLACK);

        bSingle.setForeground(Color.white);
        bMulti.setForeground(Color.white);
        bTop.setForeground(Color.white);
        bExit.setForeground(Color.white);

        bSingle.addActionListener(actionListener);
        bMulti.addActionListener(actionListener);
        bTop.addActionListener(actionListener);
        bExit.addActionListener(actionListener);
    }

    private void TitleCustomize(){
        title = new JLabel("Snake");
        title.setFont(mainTitleFont.deriveFont(Font.PLAIN, 100));
        title.setBackground(Color.white);
        title.setForeground(Color.white);
    }
}
