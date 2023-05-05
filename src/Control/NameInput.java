package Control;

import View.GamePanel;

import javax.swing.*;
import java.awt.*;

public class NameInput extends GamePanel {
    private JLabel text;
    private JTextField nameInput;
    private JButton playButton;
    private final NameInputListener actionListener;
    private final GridBagConstraints gridBag = new GridBagConstraints();

    public NameInput(NameInputListener actionListener){
        super();
        this.actionListener = actionListener;
        this.setLayout(new GridBagLayout());
    }

    public void init() {
        this.setVisible(true);
        this.setPreferredSize(new Dimension(800, 800));
        this.setFocusable(true);
        this.requestFocusInWindow();

        text = new JLabel("Name:");
        text.setFont(textFont);
        text.setBackground(Color.BLACK);
        text.setForeground(Color.white);

        nameInput = new JTextField(20);
        nameInput.setFont(textFont);
        nameInput.setBackground(Color.BLACK);
        nameInput.setForeground(Color.white);
        actionListener.setT(nameInput);

        playButton = new JButton("Let's play!");
        playButton.setFont(buttonFont);
        playButton.setBackground(Color.BLACK);
        playButton.setForeground(Color.white);
        playButton.setActionCommand("Play");
        playButton.addActionListener(actionListener);

        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.fill = GridBagConstraints.CENTER;
        gridBag.insets = new Insets(0, 0, 40, 0);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        this.add(text, gridBag);
        gridBag.gridy = 1;
        this.add(nameInput, gridBag);
        gridBag.gridy = 2;
        this.add(playButton, gridBag);
    }
}
