package utank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstFrame {
    int map;
    int keySelection;

    public FirstFrame() {
        JFrame jFrame = new JFrame();
        JFrame.setDefaultLookAndFeelDecorated(true);
        jFrame.setBounds(0, 0, 520, 500);
        jFrame.setResizable(false);
        JPanel jPanel = new JPanel();
        JLabel welcome = new JLabel("Welcome to UTANK. ");
        welcome.setBounds(200, 200, 100, 30);

        JLabel playerOneLabel = new JLabel("Player 1 : ");
        playerOneLabel.setForeground(Color.GRAY);
        JLabel playerTwoLabel = new JLabel("Player 2 : ");
        playerTwoLabel.setForeground(Color.RED);
        JLabel winningPointLabel = new JLabel("Winning Point : ");
        JLabel shotsLabel = new JLabel("Shots : ");
        JTextField playerOneTextField = new JTextField(5);
        JTextField playerTwoTextField = new JTextField(5);
        JTextField winningTextField = new JTextField(2);
        winningTextField.setText("5");
        JTextField shotsTextField = new JTextField(2);
        shotsTextField.setText("10");
        ButtonGroup playerOneButtonGroup = new ButtonGroup();
        ButtonGroup playerTwoButtonGroup = new ButtonGroup();


        JLabel playerOneKeys = new JLabel("Player1 Keys : ");
        JLabel playerTwoKeys = new JLabel("Player2 Keys : ");
        JRadioButton jRadioButton1 = new JRadioButton("Arrow Keys  ");
        jRadioButton1.setSelected(true);
        JRadioButton jRadioButton2 = new JRadioButton("J L I K ");
        JRadioButton jRadioButton3 = new JRadioButton("A D W S");
        jRadioButton3.setSelected(true);
        JRadioButton jRadioButton4 = new JRadioButton("Z C S X");
        playerOneButtonGroup.add(jRadioButton1);
        playerOneButtonGroup.add(jRadioButton2);
        playerTwoButtonGroup.add(jRadioButton3);
        playerTwoButtonGroup.add(jRadioButton4);
        String[] mapsArray = {" Map 1 ", " Map 2 ", " Map 3 ", " Map 4 "};
        JComboBox mapsComboBox = new JComboBox(mapsArray);
        JButton enterGameButton = new JButton("Enter Game");
        JButton howToPlayButton = new JButton("How to play");

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
                if (!shotsTextField.getText().matches("\\d+") || !winningTextField.getText().matches("\\d+")) {
                    JOptionPane.showMessageDialog(jFrame, "Insert correct numbers please.");
                    return;
                }

                jFrame.dispose();
                Player player1 = new Player(playerOneTextField.getText(), Color.gray, Integer.parseInt(shotsTextField.getText()));
                Player player2 = new Player(playerTwoTextField.getText(), Color.red, Integer.parseInt(shotsTextField.getText()));
                Game game = new Game(player1, player2, Integer.parseInt(winningTextField.getText()), map);
                if (jRadioButton1.isSelected() && jRadioButton4.isSelected())
                    keySelection = 2;
                else if (jRadioButton2.isSelected() && jRadioButton3.isSelected())
                    keySelection = 3;
                else if (jRadioButton2.isSelected() && jRadioButton4.isSelected())
                    keySelection = 4;
                else
                    keySelection = 1;

                game.addKeyListener(new GameActionListener(keySelection));
                game.setVisible(true);
                game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                new Timer(
                        30,
                        e -> {
                            game.updateState(player1, player2);
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
        jPanel.add(howToPlayButton);
        jFrame.add(jPanel);

        jFrame.setVisible(true);

    }
}
