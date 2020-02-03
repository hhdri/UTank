package utank;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstFrame {
    public FirstFrame() {
        JFrame jFrame = new JFrame();

        jFrame.setBounds(0,0,500,500);
        JPanel jPanel = new JPanel();
        JLabel welcome = new JLabel("Welcome to UTank. ",SwingConstants.CENTER);
        welcome.setBounds(200,200,100,30);
        JLabel winningPointLabel = new JLabel("Winning Point : ",SwingConstants.CENTER);
        JLabel playerOneLabel = new JLabel("Player 1 : ");
        JLabel playerTwoLabel = new JLabel("");
        JTextField winningTextField = new JTextField(1);

        JButton enterGameButton = new JButton("Enter Game");
        JButton howToPlayButton = new JButton("How to play");

        enterGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                jFrame.dispose();
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
        });

        howToPlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
                Rules rules = new Rules();
            }
        });


        jPanel.add(welcome);
        jPanel.add(winningPointLabel);
        jPanel.add(winningTextField);
        jPanel.add(enterGameButton);
        jPanel.add(howToPlayButton);
        jFrame.add(jPanel);
        jFrame.setVisible(true);

    }
}
