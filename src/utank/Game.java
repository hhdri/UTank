package utank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {
    private final static int WIDTH = 500, HEIGHT = 500;
    private static int WIN_POINT = 3;

    private List<Thing> everyThing = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private Player player1 = new Player();
    private Player player2 = new Player();
    private List<Shot> shotsInTheAir = new ArrayList<>();

    public Game() {
        this.setSize(Game.WIDTH, Game.HEIGHT);

        Wall leftEdge = new Wall(20, 45, Game.HEIGHT - 65, true);
        this.everyThing.add(leftEdge);
        this.walls.add(leftEdge);
        Wall topEdge = new Wall(20, 45, Game.WIDTH - 40, false);
        this.everyThing.add(topEdge);
        this.walls.add(topEdge);
        Wall rightEdge = new Wall(Game.WIDTH - 20, 45, Game.HEIGHT - 65, true);
        this.everyThing.add(rightEdge);
        this.walls.add(rightEdge);
        Wall bottomEdge = new Wall(20, Game.HEIGHT - 20, Game.WIDTH - 39, false);
        this.everyThing.add(bottomEdge);
        this.walls.add(bottomEdge);

        int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
        this.player1.newRound(false, coordinatesP1[0], coordinatesP1[1]);
        this.everyThing.add(player1.getTank());
        int[] coordinatesP2 = player2.getCoordinates(everyThing, WIDTH, HEIGHT);
        this.player2.newRound(false, coordinatesP2[0], coordinatesP2[1]);
        this.everyThing.add(player2.getTank());
    }

    private void newRoundHandler(Player player1, Player player2) {
        if (player1.getPoints() == WIN_POINT) {
            JOptionPane.showMessageDialog(this, "Player1 won!");
            this.dispose();
        } else if (player2.getPoints() == WIN_POINT) {
            JButton newGameButton = new JButton("New Game");
            this.add(newGameButton);
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game newGame = new Game();
                    newGame.setVisible(true);
                }
            });
            JOptionPane.showMessageDialog(this, "Player2 won!");
            this.dispose();

        } else
            JOptionPane.showMessageDialog(this, "New Round! Player1 : " + player1.getPoints() + " Player 2 : " + player2.getPoints());


    }

    public void updateState() {
        Tank p1Tank = (Tank) this.player1.getTank();
        Tank p2Tank = (Tank) this.player2.getTank();

        if(p1Tank.shotTimer != 0){
            p1Tank.shotTimer -= 1;
        }
        if(p2Tank.shotTimer != 0){
            p2Tank.shotTimer -= 1;
        }

        for (Shot shot : this.shotsInTheAir) {
            for (Wall wall : this.walls) {
                if (wall.contacts(shot)) {
                    shot.bounceAgainst(wall);
                } else
                    shot.step();
            }
            if (p1Tank.contacts(shot)) {
                this.everyThing.remove(p1Tank);
                this.everyThing.remove(p2Tank);
                int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                this.player1.newRound(false, coordinatesP1[0], coordinatesP1[1]);
                this.everyThing.add(player1.getTank());
                int[] coordinatesP2 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                this.player2.newRound(true, coordinatesP2[0], coordinatesP2[1]);
                this.everyThing.add(player2.getTank());
                this.newRoundHandler(player1, player2);
                shotsInTheAir.clear();  // this mutates the iterator
                break;  // so can't use this loop anymore (and we don't need to)
            }
            if (p2Tank.contacts(shot)) {
                this.everyThing.remove(p2Tank);
                this.everyThing.remove(p1Tank);
                int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                this.player1.newRound(true, coordinatesP1[0], coordinatesP1[1]);
                this.everyThing.add(player1.getTank());
                int[] coordinatesP2 = player2.getCoordinates(everyThing, WIDTH, HEIGHT);
                this.player2.newRound(false, coordinatesP2[0], coordinatesP2[1]);
                this.everyThing.add(player2.getTank());
                this.newRoundHandler(player1, player2);
                shotsInTheAir.clear();
                break;
            }
        }

        this.shotsInTheAir.forEach(Shot::growOld);
        this.shotsInTheAir.removeIf(Shot::isDead);


        GameActionListener listener = (GameActionListener) this.getKeyListeners()[0];
        if (listener.p1Move) {
            if (p1Tank.contacts(p2Tank)) {
                p1Tank.blockedBy(p2Tank);
                p2Tank.blockedBy(p1Tank);
                p1Tank.step();
            }
            for (Wall wall : walls)
                if (wall.contacts(p1Tank)) {
                    p1Tank.blockedBy(wall);
                }
            p1Tank.step();
        }
        if (listener.p1Left)
            p1Tank.turnLeft();
        if (listener.p1Right)
            p1Tank.turnRight();
        if (listener.p1Fire && p1Tank.shotTimer == 0 && p1Tank.shotCounter != 0) {
            Shot shotP1 = new Shot(p1Tank.getGunX(), p1Tank.getGunY(), (float) p1Tank.getDirection());
            this.shotsInTheAir.add(shotP1);
            listener.p1Fire = false;
            p1Tank.shotCounter -= 1;
            p1Tank.shotTimer = 50;
        }
        if (listener.p2Move) {
            if (p1Tank.contacts(p2Tank)) {
                p1Tank.blockedBy(p2Tank);
                p2Tank.blockedBy(p1Tank);
                p2Tank.step();
            }
            for (Wall wall : walls)
                if (wall.contacts(p2Tank)) {
                    p2Tank.blockedBy(wall);
                }
            p2Tank.step();
        }
        if (listener.p2Left)
            p2Tank.turnLeft();
        if (listener.p2Right)
            p2Tank.turnRight();
        if (listener.p2Fire && p2Tank.shotTimer == 0 && p2Tank.shotCounter != 0) {
            Shot shotP2 = new Shot(p2Tank.getGunX(), p2Tank.getGunY(), (float) p2Tank.getDirection());
            this.shotsInTheAir.add(shotP2);
            listener.p2Fire = false;
            p2Tank.shotCounter -= 1;
            p2Tank.shotTimer = 50;
        }
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.everyThing.forEach(thing -> thing.draw(graphics));
        this.shotsInTheAir.forEach(shot -> shot.draw(graphics));
        Toolkit.getDefaultToolkit().sync();
    }
}
