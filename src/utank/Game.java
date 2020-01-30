package utank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {
    final static int WIDTH = 500, HEIGHT = 500;

    List<Thing> everyThing = new ArrayList<>();
    List<Wall> walls = new ArrayList<>();
    Player player1 = new Player();
    Player player2 = new Player();
    List<Shot> shotsInTheAir = new ArrayList<>();

    public Game() {
        this.setSize(Game.WIDTH, Game.HEIGHT);

        Wall leftEdge = new Wall(20, 20, Game.HEIGHT, true);
        this.everyThing.add(leftEdge);
        this.walls.add(leftEdge);
        Wall topEdge = new Wall(20, 20, Game.WIDTH, false);
        this.everyThing.add(topEdge);
        this.walls.add(topEdge);
        // other walls
        this.player1.newRound(false, (int) Math.round(Math.random() * Game.WIDTH), (int) Math.round(Math.random() * Game.HEIGHT));
        this.player2.newRound(false, (int) Math.round(Math.random() * Game.WIDTH), (int) Math.round(Math.random() * Game.HEIGHT));
        this.everyThing.add(player1.getTank());
        this.everyThing.add(player2.getTank());
    }

    public void updateState() {
        Tank p1Tank = (Tank) this.player1.getTank();
        Tank p2Tank = (Tank) this.player2.getTank();
        for (Shot shot : this.shotsInTheAir) {
            for (Wall wall : this.walls) {
                if (wall.contacts(shot))
                    shot.bounceAgainst(wall);
                else
                    shot.step();
            }
            if (p1Tank.contacts(shot)) {
                this.everyThing.remove(p1Tank);
                // ... code to handle new round
            }
            // ... same for player2
        }
        this.shotsInTheAir.forEach(Shot::growOld);
        this.shotsInTheAir.removeIf(Shot::isDead);

        GameActionListener listener = (GameActionListener) this.getKeyListeners()[0];
        if (listener.p1Move && this.walls.stream().noneMatch(wall -> wall.contacts(p1Tank))) {
            p1Tank.step();
        }
        if (listener.p1Left)
            p1Tank.turnLeft();
        if (listener.p1Right)
            p1Tank.turnRight();
        if (listener.p1Fire) {
            this.shotsInTheAir.add(new Shot(p1Tank.getGunX(), p1Tank.getGunY(), (float) p1Tank.getDirection()));
        }
        if (listener.p2Move && this.walls.stream().noneMatch(wall -> wall.contacts(p2Tank))) {
            p2Tank.step();
        }
        if (listener.p2Left)
            p2Tank.turnLeft();
        if (listener.p2Right)
            p2Tank.turnRight();
        if (listener.p2Fire) {
            this.shotsInTheAir.add(new Shot(p2Tank.getGunX(), p2Tank.getGunY(), (float) p2Tank.getDirection()));
        }
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.everyThing.forEach(thing -> thing.draw(graphics));
        Toolkit.getDefaultToolkit().sync();
    }
}
