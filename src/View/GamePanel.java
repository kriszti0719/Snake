package View;

import Model.Board;
import Model.Field;
import Model.Wall;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import static java.awt.Font.TRUETYPE_FONT;

public class GamePanel extends JPanel {
    private Board board;
    protected Font buttonFont = new Font("Stg", TRUETYPE_FONT, 30);
    protected Font textFont = new Font("Stg", TRUETYPE_FONT, 45);
    protected Font scoreFont = new Font("Stg", TRUETYPE_FONT, 20);
    protected Font titleFont = new Font("Stg", TRUETYPE_FONT, 55);
    protected Font mainTitleFont;
//    private ArrayList<Drawable> drawables;

    public GamePanel() {
        InputStream fileStream = getClass().getResourceAsStream("/Font/Snake-Regular.ttf");

        try {
            mainTitleFont = Font.createFont(Font.TRUETYPE_FONT, fileStream);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(800, 800));
        this.setFocusable(true);
        this.setLayout(null);
        this.requestFocusInWindow();
    }
    public static Font loadFont(String path, float size){
        try {
            InputStream fileStream = Font.class.getResourceAsStream(path);
            Font myFont = Font.createFont(TRUETYPE_FONT, fileStream);
            return myFont.deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
