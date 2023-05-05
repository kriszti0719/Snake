package View;

import Control.BackButtonListener;
import Control.InputHandler;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Endings extends GamePanel{
    private InputHandler keyHandler;
    private Player winner;
    private Player winner2;
    private JLabel title;
    private JLabel name;
    private JLabel score;
    private BackButtonListener buttonListener;
    private JButton menu;
    private final GridBagConstraints gridBag = new GridBagConstraints();
//    private final ActionListener actionListener;

    public Endings(BackButtonListener buttonListener) {
        super();
        this.grabFocus();
        this.setLayout(new GridBagLayout());
        this.buttonListener = buttonListener;

    }

    public void init() {
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.setFocusable(true);
        this.requestFocusInWindow();
        ButtonCustomize();
    }

    public void ShowScore(Player w) {
//        winner = w;
        TitleCustomize("Congrats on your score");
        TextCustomize(w);
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(0, 35, 80, 0);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.fill = GridBagConstraints.CENTER;
//        this.add(title, gridBag);

        gridBag.insets = new Insets(20, 0, 20,0);
        gridBag.gridy = 1;
        this.add(name, gridBag);
        gridBag.gridy = 2;
        this.add(score, gridBag);
        gridBag.gridy = 3;
        this.add(menu, gridBag);
    }

    public void ShowWinner(Player p1, Player p2) {
        ButtonCustomize();
        winner = p1;
        winner2 = p2;
        if(p1.Won() && p2.Won()){
            TitleCustomize("It's a tie! Maybe another game?");
        } else if(p1.Won()){
            TitleCustomize("Congrats "+p1.GetName()+" you won!");
        } else {
            TitleCustomize("Congrats "+p2.GetName()+" you won!");
        }
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.insets = new Insets(0, 35, 80, 0);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.fill = GridBagConstraints.CENTER;
        this.add(title, gridBag);
        gridBag.gridy = 1;
        this.add(menu, gridBag);
    }

    private void TitleCustomize(String s){
        title = new JLabel(s);
        title.setFont(titleFont);
        title.setBackground(Color.white);
        title.setForeground(Color.white);
    }

    private void TextCustomize(Player p){
        name  = new JLabel(p.GetName());
        Integer points = p.GetPoints();
        score = new JLabel(points.toString());
        name.setFont(textFont);
        name.setBackground(Color.white);
        name.setForeground(Color.white);
        score.setFont(textFont);
        score.setBackground(Color.white);
        score.setForeground(Color.white);
    }

    private void ButtonCustomize() {
        menu = new JButton("Go back to menu");
        menu.setFont(buttonFont);
        menu.setBackground(Color.BLACK);
        menu.setForeground(Color.white);
        menu.addActionListener(buttonListener);
        menu.setActionCommand("Back");
//        menu.setPreferredSize(new Dimension(200, 60));
    }

    public void Remove() {
        if(name != null){
            this.remove(name);
        }
        if(score != null){
            this.remove(score);
        }
        if(title != null){
            this.remove(title);
        }
        if(menu != null){
            this.remove(menu);
        }
    }
}
