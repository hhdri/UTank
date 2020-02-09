package utank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameActionListener extends KeyAdapter {
    boolean p1Move, p1Left, p1Right, p1Fire,
            p2Move, p2Left, p2Right, p2Fire,
            escape;

    public GameActionListener() {
        super();
//        p1Move = false;
//        p1Left = false;
//        p1Right = false;
//        p1Fire = false;
//        p2Move = false;
//        p2Left = false;
//        p2Right = false;
//        p2Fire = false;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.p1Left = true;
                break;
            case KeyEvent.VK_RIGHT:
                this.p1Right = true;
                break;
            case KeyEvent.VK_UP:
                this.p1Move = true;
                break;
            case KeyEvent.VK_DOWN:
                this.p1Fire = true;
                break;
            case KeyEvent.VK_A:
                this.p2Left = true;
                break;
            case KeyEvent.VK_D:
                this.p2Right = true;
                break;
            case KeyEvent.VK_W:
                this.p2Move = true;
                break;
            case KeyEvent.VK_S:
                this.p2Fire = true;
                break;
        }
        e.consume();
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                this.p1Left = false;
                break;
            case KeyEvent.VK_RIGHT:
                this.p1Right = false;
                break;
            case KeyEvent.VK_UP:
                this.p1Move = false;
                break;
            case KeyEvent.VK_DOWN:
                this.p1Fire = false;
                break;
            case KeyEvent.VK_A:
                this.p2Left = false;
                break;
            case KeyEvent.VK_D:
                this.p2Right = false;
                break;
            case KeyEvent.VK_S:
                this.p2Fire = false;
                break;
            case KeyEvent.VK_W:
                this.p2Move = false;
                break;
        }
        e.consume();
    }
}
