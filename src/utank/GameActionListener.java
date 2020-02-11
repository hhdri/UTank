package utank;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameActionListener extends KeyAdapter {
    boolean p1Move, p1Left, p1Right, p1Fire,
            p2Move, p2Left, p2Right, p2Fire,
            escape;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    public GameActionListener(int number) {
        super();
        this.number = number;
    }

    public void keyPressed(KeyEvent e) {
        switch (this.getNumber()) {
            case 1:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_M:
                        FirstFrame firstFrame = new FirstFrame();

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
                    //case KeyEvent escape
                }
                e.consume();
                break;
            case 2:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_M:
                        FirstFrame firstFrame = new FirstFrame();

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
                    case KeyEvent.VK_Z:
                        this.p2Left = true;
                        break;
                    case KeyEvent.VK_C:
                        this.p2Right = true;
                        break;
                    case KeyEvent.VK_S:
                        this.p2Move = true;
                        break;
                    case KeyEvent.VK_X:
                        this.p2Fire = true;
                        break;
                }
                e.consume();
                break;
            case 3:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_M:
                        FirstFrame firstFrame = new FirstFrame();

                    case KeyEvent.VK_J:
                        this.p1Left = true;
                        break;
                    case KeyEvent.VK_L:
                        this.p1Right = true;
                        break;
                    case KeyEvent.VK_I:
                        this.p1Move = true;
                        break;
                    case KeyEvent.VK_K:
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
                break;
            case 4:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_M:
                        FirstFrame firstFrame = new FirstFrame();

                    case KeyEvent.VK_J:
                        this.p1Left = true;
                        break;
                    case KeyEvent.VK_L:
                        this.p1Right = true;
                        break;
                    case KeyEvent.VK_I:
                        this.p1Move = true;
                        break;
                    case KeyEvent.VK_K:
                        this.p1Fire = true;
                        break;
                    case KeyEvent.VK_Z:
                        this.p2Left = true;
                        break;
                    case KeyEvent.VK_C:
                        this.p2Right = true;
                        break;
                    case KeyEvent.VK_S:
                        this.p2Move = true;
                        break;
                    case KeyEvent.VK_X:
                        this.p2Fire = true;
                        break;
                }
                e.consume();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (this.getNumber()) {
            case 1:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_M:
                        FirstFrame firstFrame = new FirstFrame();

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
                    case KeyEvent.VK_W:
                        this.p2Move = false;
                        break;
                    case KeyEvent.VK_S:
                        this.p2Fire = false;
                        break;
                }
                e.consume();
                break;
            case 2:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_M:
                        FirstFrame firstFrame = new FirstFrame();

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
                    case KeyEvent.VK_Z:
                        this.p2Left = false;
                        break;
                    case KeyEvent.VK_C:
                        this.p2Right = false;
                        break;
                    case KeyEvent.VK_S:
                        this.p2Move = false;
                        break;
                    case KeyEvent.VK_X:
                        this.p2Fire = false;
                        break;
                }
                e.consume();
                break;
            case 3:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_M:
                        FirstFrame firstFrame = new FirstFrame();

                    case KeyEvent.VK_J:
                        this.p1Left = false;
                        break;
                    case KeyEvent.VK_L:
                        this.p1Right = false;
                        break;
                    case KeyEvent.VK_I:
                        this.p1Move = false;
                        break;
                    case KeyEvent.VK_K:
                        this.p1Fire = false;
                        break;
                    case KeyEvent.VK_A:
                        this.p2Left = false;
                        break;
                    case KeyEvent.VK_D:
                        this.p2Right = false;
                        break;
                    case KeyEvent.VK_W:
                        this.p2Move = false;
                        break;
                    case KeyEvent.VK_S:
                        this.p2Fire = false;
                        break;
                }
                e.consume();
                break;
            case 4:
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_M:
                        FirstFrame firstFrame = new FirstFrame();
                    case KeyEvent.VK_J:
                        this.p1Left = false;
                        break;
                    case KeyEvent.VK_L:
                        this.p1Right = false;
                        break;
                    case KeyEvent.VK_I:
                        this.p1Move = false;
                        break;
                    case KeyEvent.VK_K:
                        this.p1Fire = false;
                        break;
                    case KeyEvent.VK_Z:
                        this.p2Left = false;
                        break;
                    case KeyEvent.VK_C:
                        this.p2Right = false;
                        break;
                    case KeyEvent.VK_S:
                        this.p2Move = false;
                        break;
                    case KeyEvent.VK_X:
                        this.p2Fire = false;
                        break;
                }
                e.consume();
                break;
        }
    }
}
