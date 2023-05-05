package View;

import Control.BackButtonListener;
import Control.InputHandler;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreBoard extends GamePanel{
    private BackButtonListener buttonListener;
    private JLabel title;
    private JButton menu;
    private final GridBagConstraints gridBag = new GridBagConstraints();
    private List<Player> top10;

    public ScoreBoard(BackButtonListener buttonListener) {
        super();
        this.grabFocus();
        this.setLayout(new GridBagLayout());
        this.buttonListener = buttonListener;
    }

    public void Init(List<Player> p) {
        top10 = p;
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.setFocusable(true);
        this.requestFocusInWindow();
        ButtonCustomize();
        TitleCustomize();

        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(0, 0, 60, 0);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.fill = GridBagConstraints.CENTER;
        this.add(title, gridBag);

        gridBag.insets = new Insets(10, 0, 10, 0);
        for(int i = 0; i < 10; i++) {
            gridBag.anchor = GridBagConstraints.WEST;
            gridBag.fill = GridBagConstraints.WEST;
            gridBag.gridy++;
            JLabel tmpName = new JLabel(top10.get(i).GetName());
            TextCustomize(tmpName);
            this.add(tmpName, gridBag);

            gridBag.anchor = GridBagConstraints.EAST;
            gridBag.fill = GridBagConstraints.EAST;
            Integer tmpInt = top10.get(i).GetPoints();
            JLabel tmpScore = new JLabel(tmpInt.toString());
            TextCustomize(tmpScore);
            this.add(tmpScore, gridBag);
        }
        gridBag.insets = new Insets(40, 0, 20, 0);
        gridBag.gridy = 11;
        this.add(menu, gridBag);
    }

    private void ButtonCustomize() {
        menu = new JButton("Go back to menu");
        menu.setFont(buttonFont);
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.white);
        menu.addActionListener(buttonListener);
        menu.setActionCommand("Back");
    }

    private void TitleCustomize(){
        title = new JLabel("Top 10");
        title.setFont(titleFont);
        title.setBackground(Color.white);
        title.setForeground(Color.white);
    }

    private void TextCustomize(JLabel JL){
        JL.setFont(scoreFont);
        JL.setBackground(Color.white);
        JL.setForeground(Color.white);
    }

    public void Remove() {
        this.removeAll();
    }



}
