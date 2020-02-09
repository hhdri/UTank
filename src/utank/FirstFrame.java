package utank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstFrame {
    int map;

    public FirstFrame() {
        JFrame jFrame = new JFrame();

        jFrame.setBounds(0, 0, 500, 500);
        JPanel jPanel = new JPanel();
        JLabel welcome = new JLabel("Welcome to UTank. ", SwingConstants.CENTER);
        welcome.setBounds(200, 200, 100, 30);
        JLabel winningPointLabel = new JLabel("Winning Point : ", SwingConstants.CENTER);
        JLabel playerOneLabel = new JLabel("Player 1 : ");
        JLabel playerTwoLabel = new JLabel("Player 2 : ");
        JTextField playerOneTextField = new JTextField(5);
        JTextField playerTwoTextField = new JTextField(5);
        JTextField winningTextField = new JTextField(2);
        JLabel playerOneKeys = new JLabel("Player1 plays with : ");
        JLabel playerTwoKeys = new JLabel("Player2 plays with : ");
        String mapsArray[] = {" Map 1 ", " Map 2 ", " Map 3 " , " Map 4 "};
        JComboBox mapsComboBox = new JComboBox(mapsArray);


        JButton enterGameButton = new JButton("Enter Game");
        JButton howToPlayButton = new JButton("How to play");
        JButton settingButton = new JButton("Setting");

        mapsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mapString =  mapsComboBox.getSelectedItem().toString();
                if ( mapString == " Map 1 ")
                    map = 1;
                else if (mapString == " Map 2 ")
                    map = 2;
                else if (mapString == " Map 3 ")
                    map = 3;
                else if (mapString == " Map 4 ")
                    map = 4;
                else
                    map = 0;



            }
        });


        enterGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                jFrame.dispose();
                Player player1 = new Player(playerOneTextField.getText(), Color.gray);
                Player player2 = new Player(playerTwoTextField.getText(), Color.red);
                Game game = new Game(player1, player2, Integer.parseInt(winningTextField.getText()), map);


                game.addKeyListener(new GameActionListener());
                game.setVisible(true);
                game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                new Timer(
                        10,
                        e -> {
                            game.updateState(player1, player2, Integer.parseInt(winningTextField.getText()));
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
        settingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        jPanel.add(welcome);
        jPanel.add(playerOneLabel);
        jPanel.add(playerOneTextField);
        jPanel.add(playerTwoLabel);
        jPanel.add(playerTwoTextField);
        jPanel.add(winningPointLabel);
        jPanel.add(winningTextField);
        jPanel.add(mapsComboBox);
        jPanel.add(enterGameButton);
        jPanel.add(howToPlayButton);
        jFrame.add(jPanel);
        jFrame.setVisible(true);

    }
}
