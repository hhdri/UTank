package utank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstFrame {
    int map;

    public FirstFrame() {
        JFrame jFrame = new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        jFrame.setBounds(0, 0, 520, 500);
        jFrame.setResizable(false);
        JPanel jPanel = new JPanel();
        JLabel welcome = new JLabel("Welcome to UTank. ", SwingConstants.CENTER);
        welcome.setBounds(200, 200, 100, 30);

        JLabel playerOneLabel = new JLabel("Player 1 : ");
        JLabel playerTwoLabel = new JLabel("Player 2 : ");
        JLabel winningPointLabel = new JLabel("Winning Point : ", SwingConstants.CENTER);
        JLabel shotsLabel = new JLabel("Shots : ");
        JTextField playerOneTextField = new JTextField(5);
        JTextField playerTwoTextField = new JTextField(5);
        JTextField winningTextField = new JTextField(2);
        JTextField shotsTextField = new JTextField(2);
        ButtonGroup playerOneButtonGroup = new ButtonGroup();
        ButtonGroup playerTwoButtonGroup = new ButtonGroup();


        JLabel playerOneKeys = new JLabel("Player1 plays with : ");
        JLabel playerTwoKeys = new JLabel("Player2 plays with : ");
        JRadioButton jRadioButton1 = new JRadioButton("A W S D");
        JRadioButton jRadioButton2 = new JRadioButton(" Z S X C");
        JRadioButton jRadioButton3 = new JRadioButton("S E D F");
        JRadioButton jRadioButton4 = new JRadioButton("J I K L");
        playerOneButtonGroup.add(jRadioButton1);
        playerOneButtonGroup.add(jRadioButton2);
        playerTwoButtonGroup.add(jRadioButton3);
        playerTwoButtonGroup.add(jRadioButton4);
        String[] mapsArray = {" Map 1 ", " Map 2 ", " Map 3 ", " Map 4 "};
        JComboBox mapsComboBox = new JComboBox(mapsArray);
        JButton enterGameButton = new JButton("Enter Game");
        JButton howToPlayButton = new JButton("How to play");
        JButton settingButton = new JButton("Setting");

        mapsComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mapString = mapsComboBox.getSelectedItem().toString();
                if (mapString.equals(" Map 1 "))
                    map = 1;
                else if (mapString.equals(" Map 2 "))
                    map = 2;
                else if (mapString.equals(" Map 3 "))
                    map = 3;
                else if (mapString.equals(" Map 4 "))
                    map = 4;
                else
                    map = 0;
            }
        });


        enterGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                jFrame.dispose();
                Player player1 = new Player(playerOneTextField.getText(), Color.gray, Integer.parseInt(shotsTextField.getText()));
                Player player2 = new Player(playerTwoTextField.getText(), Color.red, Integer.parseInt(shotsTextField.getText()));
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
                Setting setting = new Setting();
            }
        });


        jPanel.add(welcome);
        jPanel.add(playerOneLabel);
        jPanel.add(playerOneTextField);
        jPanel.add(playerTwoLabel);
        jPanel.add(playerTwoTextField);
        jPanel.add(winningPointLabel);
        jPanel.add(winningTextField);
        jPanel.add(shotsLabel);
        jPanel.add(shotsTextField);
        jPanel.add(mapsComboBox);
        jPanel.add(playerOneKeys);

        jPanel.add(jRadioButton1);
        jPanel.add(jRadioButton2);
        jPanel.add(playerTwoKeys);
        jPanel.add(jRadioButton3);
        jPanel.add(jRadioButton4);
        jPanel.add(enterGameButton);
        jPanel.add(settingButton);
        jPanel.add(howToPlayButton);
        jFrame.add(jPanel);

        jFrame.setVisible(true);

    }
}
