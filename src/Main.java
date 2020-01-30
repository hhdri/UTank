import utank.Game;
import utank.GameActionListener;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.addKeyListener(new GameActionListener());
        game.setVisible(true);
        game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        new Timer(
                10,
                e -> {
                    game.updateState();
                    game.repaint();
                }
        ).start();
    }
}
