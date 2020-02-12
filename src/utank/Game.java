package utank;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

enum RoundState {
    Player1Won, Player2Won, WasDraw, FirstRound
}

public class Game extends JFrame {
    private final static int WIDTH = 500, HEIGHT = 500;
    private final static double MINE_FREQUENCY = 0.0028;
    private int map;
    private List<PowerUp> powerUpsInTheAir = new ArrayList<>();
    private List<Thing> everyThing = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private List<Shot> shotsInTheAir = new ArrayList<>();
    private int winPoint;
    private int timeToNextMine;
    PowerUp powerUpCoords = new PowerUp(0,0);

    public Game(Player player1, Player player2, int winPoint, int map) {
        this.map = map;
        this.winPoint = winPoint;
        this.setSize(Game.WIDTH, Game.HEIGHT);
        this.setTitle("UTANK GAME");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);

        newRoundHandler(player1, player2, RoundState.FirstRound);
    }


    private void newRoundHandler(Player player1, Player player2, RoundState roundState) {
        timeToNextMine = (int) Math.round(Math.log(1 - Math.random()) / (-MINE_FREQUENCY));

        if (roundState == RoundState.FirstRound) {
            Map mapWalls = new Map(this.map);
            this.everyThing.addAll(mapWalls.defaultWalls);
            this.walls.addAll(mapWalls.defaultWalls);
            this.everyThing.addAll(mapWalls.mapWalls);
            this.walls.addAll(mapWalls.mapWalls);
        }
        if (roundState != RoundState.FirstRound) {
            new BombSound();
            shotsInTheAir.clear();
            powerUpsInTheAir.clear();
            Tank p1Tank = (Tank) player1.getTank();
            Tank p2Tank = (Tank) player2.getTank();
            this.everyThing.remove(p2Tank);
            this.everyThing.remove(p1Tank);

            GameActionListener listener = (GameActionListener) this.getKeyListeners()[0];
            listener.resetVariables();
        }

        int player1AddedPoint = 0, player2AddedPoint = 0;
        if (roundState == RoundState.Player1Won)
            player1AddedPoint++;
        if (roundState == RoundState.Player2Won)
            player2AddedPoint++;

//        int[] coordinatesP1 = player1.getNewTank(everyThing, WIDTH, HEIGHT);
        player1.newRound(player1AddedPoint, player1.getNewTank(everyThing, WIDTH, HEIGHT));
        this.everyThing.add(player1.getTank());
//        int[] coordinatesP2 = player2.getNewTank(everyThing, WIDTH, HEIGHT);
        player2.newRound(player2AddedPoint, player2.getNewTank(everyThing, WIDTH, HEIGHT));
        this.everyThing.add(player2.getTank());

        if (roundState == RoundState.WasDraw) {
            JOptionPane.showMessageDialog(this, " Equal !");
        }
        else if (player1.getPoints() == winPoint) {
            JOptionPane.showMessageDialog(this, "Player1 : " + player1.getName() + " won!  Player1 shots : " + ((Tank)player1.getTank()).getShotCounter() + " player2 shots : " + ((Tank)player2.getTank()).getShotCounter());
            this.dispose();
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            new FirstFrame();
        } else if (player2.getPoints() == winPoint) {
            JOptionPane.showMessageDialog(this, "Player2 : " + player2.getName() + " won!");
            this.dispose();
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            new FirstFrame();

        } else if (roundState == RoundState.Player1Won || roundState == RoundState.Player2Won){
            JOptionPane.showMessageDialog(this, "New Round! Player1 : " + player1.getName() + " " + player1.getPoints() + " Player2 : " + player2.getName() + " " + player2.getPoints());
        }

    }

    public void updateState(Player player1, Player player2) {
        timeToNextMine--;
        if (timeToNextMine == 0) {
            int[] powerUpCoordinates = powerUpCoords.getCoordinates(everyThing, WIDTH, HEIGHT);
            PowerUp test = new PowerUp(powerUpCoordinates[0], powerUpCoordinates[1]);
            this.powerUpsInTheAir.add(test);

            timeToNextMine = (int) Math.round(Math.log(1 - Math.random()) / (-MINE_FREQUENCY));
        }

        Tank p1Tank = (Tank) player1.getTank();
        Tank p2Tank = (Tank) player2.getTank();

        if (p1Tank.shotTimer != 0) {
            p1Tank.shotTimer -= 1;
        }
        if (p2Tank.shotTimer != 0) {
            p2Tank.shotTimer -= 1;
        }

        for (PowerUp powerUp : this.powerUpsInTheAir) {
            boolean breakFromLoop = false;
            switch (powerUp.powerUpStatus) {
                case UnPicked:
                    if (powerUp.contacts(p1Tank)) {
                        powerUp.setPicked(p1Tank);
                        break;
                    }
                    else if (powerUp.contacts(p2Tank)) {
                        powerUp.setPicked(p2Tank);
                        break;
                    }
                    break;
                case Landed:
                    if (powerUp.contacts(p1Tank) && !powerUp.carrier.equals(p1Tank)) {
                        this.newRoundHandler(player1, player2, RoundState.Player2Won);
                        breakFromLoop = true;
                        break;
                    }
                    else if (powerUp.contacts(p2Tank) && !powerUp.carrier.equals(p2Tank)) {
                        this.newRoundHandler(player1, player2, RoundState.Player1Won);
                        breakFromLoop = true;
                        break;
                    }
            }
            if (breakFromLoop)
                break;
        }
        this.powerUpsInTheAir.forEach(PowerUp::growOld);
        this.powerUpsInTheAir.removeIf(PowerUp::isDead);


        for (Shot shot : this.shotsInTheAir) {
            shot.calculateVelocity();
            for (Wall wall : this.walls) {
                if (wall.contacts(shot)) {
                    shot.bounceAgainst(wall);
                }
            }
            shot.step();
            if (p1Tank.contacts(shot)) {
                this.newRoundHandler(player1, player2, RoundState.Player2Won);
                break;
            }
            if (p2Tank.contacts(shot)) {
                this.newRoundHandler(player1, player2, RoundState.Player1Won);
                break;
            }

        }
        if (p2Tank.shotCounter == 0 && p1Tank.shotCounter == 0 && shotsInTheAir.isEmpty()) {
            this.newRoundHandler(player1, player2, RoundState.WasDraw);
        }
        this.shotsInTheAir.forEach(Shot::growOld);
        this.shotsInTheAir.removeIf(Shot::isDead);


        GameActionListener listener = (GameActionListener) this.getKeyListeners()[0];
        if (listener.p1Move) {
            p1Tank.calculateVelocity();
            if (p1Tank.contacts(p2Tank)) {
                p1Tank.blockedBy(p2Tank);
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
        if (listener.p1Fire && p1Tank.shotTimer == 0) {
            boolean isMineLanded = false;
            for (PowerUp powerUp : powerUpsInTheAir) {
                if (powerUp.powerUpStatus == PowerUpStatus.Picked && powerUp.carrier.equals(p1Tank)) {
                    powerUp.setLanded();
                    isMineLanded = true;
                    p1Tank.shotTimer = 50;
                    break;
                }
            }
            if (!isMineLanded && p1Tank.shotCounter != 0) {
                Shot shotP1 = new Shot(p1Tank.getGunX(), p1Tank.getGunY(), (float) p1Tank.getDirection());
                this.shotsInTheAir.add(shotP1);
                p1Tank.shotCounter -= 1;
                p1Tank.shotTimer = 50;
            }
        }
        if (listener.p2Move) {
            p2Tank.calculateVelocity();
            if (p1Tank.contacts(p2Tank)) {
                p2Tank.blockedBy(p1Tank);
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
        if (listener.p2Fire && p2Tank.shotTimer == 0) {
            boolean isMineLanded = false;
            for (PowerUp powerUp : powerUpsInTheAir) {
                if (powerUp.powerUpStatus == PowerUpStatus.Picked && powerUp.carrier.equals(p2Tank)) {
                    powerUp.setLanded();
                    isMineLanded = true;
                    p2Tank.shotTimer = 50;
                    break;
                }
            }
            if (!isMineLanded && p2Tank.shotCounter != 0) {
                Shot shotP2 = new Shot(p2Tank.getGunX(), p2Tank.getGunY(), (float) p2Tank.getDirection());
                this.shotsInTheAir.add(shotP2);
                p2Tank.shotCounter -= 1;
                p2Tank.shotTimer = 50;
            }
        }
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.everyThing.forEach(thing -> thing.draw(graphics));
        this.shotsInTheAir.forEach(shot -> shot.draw(graphics));
        this.powerUpsInTheAir.forEach(powerUp -> powerUp.draw(graphics));
        Toolkit.getDefaultToolkit().sync();
    }
}