package utank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Game extends JFrame {
    final static int WIDTH = 500, HEIGHT = 500;

    List<Thing> everyThing = new ArrayList<>();
    List<Wall> walls = new ArrayList<>();
    Player player1 = new Player();
    Player player2 = new Player();
    List<Shot> shotsInTheAir = new ArrayList<>();
    static int winPoint = 3;

    public Game() {
        this.setSize(Game.WIDTH, Game.HEIGHT);

        Wall leftEdge = new Wall(20, 20, Game.HEIGHT, true);
        this.everyThing.add(leftEdge);
        this.walls.add(leftEdge);
//         Wall topEdge = new Wall(20, 20, Game.WIDTH, false);
//         this.everyThing.add(topEdge);
//         this.walls.add(topEdge);
//         // other walls
//        Wall leftEdge = new Wall(0, 0, Game.HEIGHT, true);
//        this.everyThing.add(leftEdge);
//        this.walls.add(leftEdge);
//        Wall topEdge = new Wall(0, 20, Game.WIDTH, false);
//        this.everyThing.add(topEdge);
//        this.walls.add(topEdge);
//        Wall rightEdge = new Wall(600, 0, Game.HEIGHT, true);
//        this.everyThing.add(rightEdge);
//        this.walls.add(rightEdge);
//        Wall bottomEdge = new Wall(0, 600, Game.WIDTH, false);
//        this.everyThing.add(bottomEdge);
//        this.walls.add(bottomEdge);
        this.player1.newRound(false, (int) Math.round(Math.random() * Game.WIDTH), (int) Math.round(Math.random() * Game.HEIGHT));
        this.player2.newRound(false, (int) Math.round(Math.random() * Game.WIDTH), (int) Math.round(Math.random() * Game.HEIGHT));
        this.everyThing.add(player1.getTank());
        this.everyThing.add(player2.getTank());
    }

    public void newRoundHandler(Player player1, Player player2) {
        if (player1.getPoints() == winPoint) {
            JOptionPane.showMessageDialog(this, "Player1 won!");
            this.dispose();
        } else if (player2.getPoints() == winPoint) {
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
                this.player1.newRound(false, (int) Math.round(Math.random() * Game.WIDTH), (int) Math.round(Math.random() * Game.HEIGHT));
                this.player2.newRound(true, (int) Math.round(Math.random() * Game.WIDTH), (int) Math.round(Math.random() * Game.HEIGHT));
                this.everyThing.add(player1.getTank());
                this.everyThing.add(player2.getTank());
                this.newRoundHandler(player1, player2);
                shotsInTheAir.clear();  // this mutates the iterator
                break;  // so can't use this loop anymore (and we don't need to)
            }
            if (p2Tank.contacts(shot)) {
                this.everyThing.remove(p2Tank);
                this.everyThing.remove(p1Tank);
                this.player1.newRound(true, (int) Math.round(Math.random() * Game.WIDTH), (int) Math.round(Math.random() * Game.HEIGHT));
                this.player2.newRound(false, (int) Math.round(Math.random() * Game.WIDTH), (int) Math.round(Math.random() * Game.HEIGHT));
                this.everyThing.add(player1.getTank());
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
            for (Wall wall : walls)
                if (wall.contacts(p1Tank)) {
                    p1Tank.blockedBy(wall);
                    break;
                }
            p1Tank.step();
        }
        if (listener.p1Left)
            p1Tank.turnLeft();
        if (listener.p1Right)
            p1Tank.turnRight();
        if (listener.p1Fire) {
            Shot shotP1 = new Shot(p1Tank.getGunX(), p1Tank.getGunY(), (float) p1Tank.getDirection());
            this.shotsInTheAir.add(shotP1);
            listener.p1Fire = false;
        }
        if (listener.p2Move) {
            for (Wall wall : walls)
                if (wall.contacts(p2Tank)) {
                    p2Tank.blockedBy(wall);
                    break;
                }
            p2Tank.step();
        }
        if (listener.p2Left)
            p2Tank.turnLeft();
        if (listener.p2Right)
            p2Tank.turnRight();
        if (listener.p2Fire) {
            Shot shotP2 = new Shot(p2Tank.getGunX(), p2Tank.getGunY(), (float) p2Tank.getDirection());
            this.shotsInTheAir.add(shotP2);
            listener.p2Fire = false;
        }
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.everyThing.forEach(thing -> thing.draw(graphics));
        this.shotsInTheAir.forEach(shot -> shot.draw(graphics));
        Toolkit.getDefaultToolkit().sync();
    }
}
