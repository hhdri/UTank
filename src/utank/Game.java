package utank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Game extends JFrame {
    private final static int WIDTH = 500, HEIGHT = 500;
    //private static int WIN_POINT = 3;
    private int map;
    private List<PowerUp> powerUpsInTheAir = new ArrayList<>();
    private List<Tank> tanks = new ArrayList<>();
    private List<Thing> everyThing = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private List<Shot> shotsInTheAir = new ArrayList<>();

    public Game(Player player1, Player player2, int winPoint, int map) {
        this.setSize(Game.WIDTH, Game.HEIGHT);
        this.setTitle("UTANK GAME");
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

        PowerUp test = new PowerUp(50, 50, PowerUpType.MINE);
        this.everyThing.add(test);
        this.powerUpsInTheAir.add(test);
        //JPanel jPanel = new JPanel();
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu("Menu");
        JMenuItem jMenuItem = new JMenuItem("Go to menu");


        jMenu.add(jMenuItem);
        jMenuBar.add(jMenu);
        //jPanel.add(jMenuBar);
        this.setJMenuBar(jMenuBar);
        this.setVisible(true);
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                FirstFrame frame = new FirstFrame();
            }
        });

        switch (map) {
            case 0:
                break;
            case 1:
                Wall obstacle11 = new Wall(190, 100, 110, true);
                Wall obstacle12 = new Wall(80, 210, 110, false);
                Wall obstacle13 = new Wall(310, 100, 110, true);
                Wall obstacle14 = new Wall(310, 210, 110, false);
                Wall obstacle15 = new Wall(80, 300, 110, false);
                Wall obstacle16 = new Wall(190, 300, 110, true);
                Wall obstacle17 = new Wall(310, 300, 100, false);
                Wall obstacle18 = new Wall(310, 300, 110, true);
                this.everyThing.add(obstacle11);
                this.walls.add(obstacle11);
                this.everyThing.add(obstacle12);
                this.walls.add(obstacle12);
                this.everyThing.add(obstacle13);
                this.walls.add(obstacle13);
                this.everyThing.add(obstacle14);
                this.walls.add(obstacle14);
                this.everyThing.add(obstacle15);
                this.walls.add(obstacle15);
                this.everyThing.add(obstacle16);
                this.walls.add(obstacle16);
                this.everyThing.add(obstacle17);
                this.walls.add(obstacle17);
                this.everyThing.add(obstacle18);
                this.walls.add(obstacle18);
                break;
            case 2:
                Wall obstacle21 = new Wall(100, 100, 300, false);
                Wall obstacle22 = new Wall(400, 100, 200, true);
                Wall obstacle23 = new Wall(150, 200, 200, true);
                Wall obstacle24 = new Wall(150, 400, 250, false);
                Wall obstacle25 = new Wall(250, 230, 50, false);
                Wall obstacle26 = new Wall(300, 230, 50, true);
                Wall obstacle27 = new Wall(250, 230, 50, true);
                Wall obstacle28 = new Wall(250, 280, 50, false);
                this.everyThing.add(obstacle21);
                this.walls.add(obstacle21);
                this.everyThing.add(obstacle22);
                this.walls.add(obstacle22);
                this.everyThing.add(obstacle23);
                this.walls.add(obstacle23);
                this.everyThing.add(obstacle24);
                this.walls.add(obstacle24);
                this.everyThing.add(obstacle25);
                this.walls.add(obstacle25);
                this.everyThing.add(obstacle26);
                this.walls.add(obstacle26);
                this.everyThing.add(obstacle27);
                this.walls.add(obstacle27);
                this.everyThing.add(obstacle28);
                this.walls.add(obstacle28);
                break;
            case 3:
                Wall obstacle31 = new Wall(100, 100, 80, false);
                Wall obstacle32 = new Wall(180, 100, 80, true);
                Wall obstacle33 = new Wall(100, 100, 80, true);
                Wall obstacle34 = new Wall(100, 180, 80, false);
                Wall obstacle35 = new Wall(330, 100, 80, false);
                Wall obstacle36 = new Wall(410, 100, 80, true);
                Wall obstacle37 = new Wall(330, 100, 80, true);
                Wall obstacle38 = new Wall(330, 180, 80, false);
                Wall obstacle39 = new Wall(100, 330, 80, false);
                Wall obstacle310 = new Wall(180, 330, 80, true);
                Wall obstacle311 = new Wall(100, 330, 80, true);
                Wall obstacle312 = new Wall(100, 410, 80, false);
                Wall obstacle313 = new Wall(330, 330, 80, false);
                Wall obstacle314 = new Wall(410, 330, 80, true);
                Wall obstacle315 = new Wall(330, 330, 80, true);
                Wall obstacle316 = new Wall(330, 410, 80, false);
                Wall obstacle317 = new Wall(235, 235, 40, false);
                Wall obstacle318 = new Wall(235, 235, 40, true);
                Wall obstacle319 = new Wall(275, 235, 40, true);
                Wall obstacle320 = new Wall(235, 275, 40, false);
                this.everyThing.add(obstacle31);
                this.walls.add(obstacle31);
                this.everyThing.add(obstacle32);
                this.walls.add(obstacle32);
                this.everyThing.add(obstacle33);
                this.walls.add(obstacle33);
                this.everyThing.add(obstacle34);
                this.walls.add(obstacle34);
                this.everyThing.add(obstacle35);
                this.walls.add(obstacle35);
                this.everyThing.add(obstacle36);
                this.walls.add(obstacle36);
                this.everyThing.add(obstacle37);
                this.walls.add(obstacle37);
                this.everyThing.add(obstacle38);
                this.walls.add(obstacle38);
                this.everyThing.add(obstacle39);
                this.walls.add(obstacle39);
                this.everyThing.add(obstacle310);
                this.walls.add(obstacle310);
                this.everyThing.add(obstacle311);
                this.walls.add(obstacle311);
                this.everyThing.add(obstacle312);
                this.walls.add(obstacle312);
                this.everyThing.add(obstacle313);
                this.walls.add(obstacle313);
                this.everyThing.add(obstacle314);
                this.walls.add(obstacle314);
                this.everyThing.add(obstacle315);
                this.walls.add(obstacle315);
                this.everyThing.add(obstacle316);
                this.walls.add(obstacle316);
                this.everyThing.add(obstacle317);
                this.walls.add(obstacle317);
                this.everyThing.add(obstacle318);
                this.walls.add(obstacle318);
                this.everyThing.add(obstacle319);
                this.walls.add(obstacle319);
                this.everyThing.add(obstacle320);
                this.walls.add(obstacle320);
                break;
            case 4:
                Wall obstacle41 = new Wall(400, 100, 40, true);
                Wall obstacle42 = new Wall(100, 100, 300, true);
                Wall obstacle43 = new Wall(200, 150, 80, true);
                Wall obstacle44 = new Wall(200, 230, 100, false);
                Wall obstacle45 = new Wall(300, 150, 80, true);
                Wall obstacle46 = new Wall(200, 330, 80, true);
                Wall obstacle47 = new Wall(200, 330, 100, false);
                Wall obstacle48 = new Wall(300, 330, 80, true);
                this.everyThing.add(obstacle41);
                this.walls.add(obstacle41);
                this.everyThing.add(obstacle42);
                this.walls.add(obstacle42);
                this.everyThing.add(obstacle43);
                this.walls.add(obstacle43);
                this.everyThing.add(obstacle44);
                this.walls.add(obstacle44);
                this.everyThing.add(obstacle45);
                this.walls.add(obstacle45);
                this.everyThing.add(obstacle46);
                this.walls.add(obstacle46);
                this.everyThing.add(obstacle47);
                this.walls.add(obstacle47);
                this.everyThing.add(obstacle48);
                this.walls.add(obstacle48);
                break;
        }


        int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
        player1.newRound(false, coordinatesP1[0], coordinatesP1[1]);
        this.everyThing.add(player1.getTank());
        int[] coordinatesP2 = player2.getCoordinates(everyThing, WIDTH, HEIGHT);
        player2.newRound(false, coordinatesP2[0], coordinatesP2[1]);
        this.everyThing.add(player2.getTank());
    }

    private void newRoundHandler2(Player player1, Player player2) {
        JOptionPane.showMessageDialog(this, " Equal !");
    }

    private void newRoundHandler(Player player1, Player player2, int winPoint, int map) {
        if (player1.getPoints() == winPoint) {
            System.out.println(player1.getPoints());
            JOptionPane.showMessageDialog(this, "Player1 : " + player1.getName() + " won!");
            this.dispose();
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        } else if (player2.getPoints() == winPoint) {
            JOptionPane.showMessageDialog(this, "Player2 : " + player2.getName() + " won!");
            System.out.println(player2.getPoints());
            System.out.println(winPoint);
            JButton newGameButton = new JButton("New Game");
            this.add(newGameButton);
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game newGame = new Game(player1, player2, winPoint, map);
                    newGame.setVisible(true);
                }
            });

            this.dispose();
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        } else
            JOptionPane.showMessageDialog(this, "New Round! Player1 : " + player1.getName() + " " + player1.getPoints() + " Player2 : " + player2.getName() + " " + player2.getPoints());


    }

    public void updateState(Player player1, Player player2, int winPoint) {
        Tank p1Tank = (Tank) player1.getTank();
        Tank p2Tank = (Tank) player2.getTank();
        this.tanks.add(p1Tank);
        this.tanks.add(p2Tank);

        if (p1Tank.shotTimer != 0) {
            p1Tank.shotTimer -= 1;
        }
        if (p2Tank.shotTimer != 0) {
            p2Tank.shotTimer -= 1;
        }

        for (PowerUp powerUp : this.powerUpsInTheAir) {
            if (powerUp.contacts(p1Tank) && !p1Tank.hadPowerUp && !p2Tank.hadPowerUp){
                this.everyThing.remove(powerUp);
                p1Tank.hasPowerUp = true;
                break;
            }
            if (powerUp.contacts(p2Tank) && !p2Tank.hadPowerUp && !p1Tank.hadPowerUp){
                this.everyThing.remove(powerUp);
                p2Tank.hasPowerUp = true;
                break;
            }
            if (p1Tank.hadPowerUp) {
                if (powerUp.contacts(p2Tank) && powerUp.age < 1000) {
                    this.everyThing.remove(p2Tank);
                    this.everyThing.remove(p1Tank);
                    int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                    player1.newRound(true, coordinatesP1[0], coordinatesP1[1]);
                    this.everyThing.add(player1.getTank());
                    int[] coordinatesP2 = player2.getCoordinates(everyThing, WIDTH, HEIGHT);
                    player2.newRound(false, coordinatesP2[0], coordinatesP2[1]);
                    this.everyThing.add(player2.getTank());
                    this.newRoundHandler(player1, player2, winPoint, map);
                    shotsInTheAir.clear();
                    powerUpsInTheAir.clear();
                    p1Tank.hadPowerUp = false;
                    break;
                }
            }
            if (p2Tank.hadPowerUp){
                if (powerUp.contacts(p1Tank) && powerUp.age < 1000) {
                    this.everyThing.remove(p1Tank);
                    this.everyThing.remove(p2Tank);
                    int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                    player1.newRound(false, coordinatesP1[0], coordinatesP1[1]);
                    this.everyThing.add(player1.getTank());
                    int[] coordinatesP2 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                    player2.newRound(true, coordinatesP2[0], coordinatesP2[1]);
                    this.everyThing.add(player2.getTank());
                    this.newRoundHandler(player1, player2, winPoint, map);
                    shotsInTheAir.clear();  // this mutates the iterator
                    powerUpsInTheAir.clear();
                    p2Tank.hadPowerUp = false;
                    break;
                }
            }
        }
        this.powerUpsInTheAir.forEach(PowerUp::growOld);
        this.powerUpsInTheAir.removeIf(PowerUp::isDead);


        for (Shot shot : this.shotsInTheAir) {
            for (Wall wall : this.walls) {
                if (wall.contacts(shot)) {
                    shot.bounceAgainst(wall);
                } else
                    shot.step();
            }
            if (p1Tank.contacts(shot) ) {
                this.everyThing.remove(p1Tank);
                this.everyThing.remove(p2Tank);
                this.tanks.remove(p1Tank);
                this.tanks.remove(p2Tank);
                int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                player1.newRound(false, coordinatesP1[0], coordinatesP1[1]);
                this.everyThing.add(player1.getTank());
                int[] coordinatesP2 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                player2.newRound(true, coordinatesP2[0], coordinatesP2[1]);
                this.everyThing.add(player2.getTank());
                this.newRoundHandler(player1, player2, winPoint, map);
                shotsInTheAir.clear();  // this mutates the iterator
                break;  // so can't use this loop anymore (and we don't need to)
            }
            if (p2Tank.contacts(shot)) {
                this.everyThing.remove(p2Tank);
                this.everyThing.remove(p1Tank);
                this.tanks.remove(p1Tank);
                this.tanks.remove(p2Tank);
                int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
                player1.newRound(true, coordinatesP1[0], coordinatesP1[1]);
                this.everyThing.add(player1.getTank());
                int[] coordinatesP2 = player2.getCoordinates(everyThing, WIDTH, HEIGHT);
                player2.newRound(false, coordinatesP2[0], coordinatesP2[1]);
                this.everyThing.add(player2.getTank());
                this.newRoundHandler(player1, player2, winPoint, map);
                shotsInTheAir.clear();
                break;
            }

        }
        if (p2Tank.shotCounter == 0 && p1Tank.shotCounter == 0 && shotsInTheAir.isEmpty()) {
            this.everyThing.remove(p2Tank);
            this.everyThing.remove(p1Tank);
            this.tanks.remove(p1Tank);
            this.tanks.remove(p2Tank);
            int[] coordinatesP1 = player1.getCoordinates(everyThing, WIDTH, HEIGHT);
            player1.newRound(true, coordinatesP1[0], coordinatesP1[1]);
            this.everyThing.add(player1.getTank());
            int[] coordinatesP2 = player2.getCoordinates(everyThing, WIDTH, HEIGHT);
            player2.newRound(false, coordinatesP2[0], coordinatesP2[1]);
            this.everyThing.add(player2.getTank());
            this.newRoundHandler2(player1, player2);
            shotsInTheAir.clear();
        }

        this.shotsInTheAir.forEach(Shot::growOld);
        this.shotsInTheAir.removeIf(Shot::isDead);


        GameActionListener listener = (GameActionListener) this.getKeyListeners()[0];
        if (listener.p1Move) {
            p1Tank.calculateVelocity();
            if (p1Tank.contacts(p2Tank)) {
                p1Tank.blockedBy(p2Tank);
                p2Tank.blockedBy(p1Tank);
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
        if (listener.p1Fire) {
            if (p1Tank.hasPowerUp){
                PowerUp test2 = new PowerUp(p1Tank.getGunX(), p1Tank.getGunY(), PowerUpType.MINE);
                this.powerUpsInTheAir.add(test2);
                p1Tank.hasPowerUp = false;
                p1Tank.hadPowerUp = true;
                p1Tank.shotTimer = 50;

            }
            else if(p1Tank.shotTimer == 0 && p1Tank.shotCounter != 0) {
                Shot shotP1 = new Shot(p1Tank.getGunX(), p1Tank.getGunY(), (float) p1Tank.getDirection());
                this.shotsInTheAir.add(shotP1);
                listener.p1Fire = false;
                p1Tank.shotCounter -= 1;
                p1Tank.shotTimer = 50;
            }
        }
        if (listener.p2Move) {
            p2Tank.calculateVelocity();
            if (p1Tank.contacts(p2Tank)) {
                p1Tank.blockedBy(p2Tank);
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
        if (listener.p2Fire) {
            if (p2Tank.hasPowerUp){
                PowerUp test2 = new PowerUp(p2Tank.getGunX(), p2Tank.getGunY(), PowerUpType.MINE);
                this.powerUpsInTheAir.add(test2);
                p2Tank.hasPowerUp = false;
                p2Tank.hadPowerUp = true;
                p2Tank.shotTimer = 50;
            }
            else if(p2Tank.shotTimer == 0 && p2Tank.shotCounter != 0) {
                Shot shotP1 = new Shot(p2Tank.getGunX(), p2Tank.getGunY(), (float) p2Tank.getDirection());
                this.shotsInTheAir.add(shotP1);
                listener.p2Fire = false;
                p2Tank.shotCounter -= 1;
                p2Tank.shotTimer = 50;
            }
        }
    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        this.everyThing.forEach(thing -> thing.draw(graphics));
        this.shotsInTheAir.forEach(shot -> shot.draw(graphics));
        Toolkit.getDefaultToolkit().sync();
    }
}
