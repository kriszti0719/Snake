package Control;

import Model.Wall;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener {
        private boolean UP;
        private boolean DOWN;
        private boolean LEFT;
        private boolean RIGHT;

        private boolean W;
        private boolean S;
        private boolean A;
        private boolean D;

        public InputHandler(JPanel panel){
            panel.addKeyListener(this);
            UP = true;
            DOWN = false;
            LEFT = false;
            RIGHT = false;

            W = true;
            S = false;
            A = false;
            D = false;
        }

        public boolean isUP() {
            return UP;
        }

        public boolean isDOWN() {
            return DOWN;
        }

        public boolean isLEFT() {
            return LEFT;
        }

        public boolean isRIGHT() {
            return RIGHT;
        }

        public boolean isW() { return W; }

        public boolean isS() { return S;}

        public boolean isA() { return A; }

        public boolean isD() { return D; }

        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();

            if (key == KeyEvent.VK_RIGHT) {
                RIGHT=true;
                UP=false;
                DOWN=false;
            }
            if (key == KeyEvent.VK_LEFT) {
                LEFT=true;
                UP=false;
                DOWN=false;
            }
            if (key == KeyEvent.VK_UP) {
                UP=true;
                LEFT=false;
                RIGHT=false;
            }
            if (key == KeyEvent.VK_DOWN) {
                DOWN=true;
                LEFT=false;
                RIGHT=false;
            }

            if (key == KeyEvent.VK_D) {
                D=true;
                W=false;
                S=false;
            }
            if (key == KeyEvent.VK_A) {
                A=true;
                W=false;
                S=false;
            }
            if (key == KeyEvent.VK_W) {
                W=true;
                A=false;
                D=false;
            }
            if (key == KeyEvent.VK_S) {
                S=true;
                A=false;
                D=false;
            }
        }
        public void keyTyped(KeyEvent e) { }

        public void keyReleased(KeyEvent e) { }
}
