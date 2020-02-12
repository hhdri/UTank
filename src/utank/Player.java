package utank;

import java.awt.*;
import java.util.List;

public class Player {
    private Tank tank;
    private Tank newTank;
    private int points = 0;
    private String name;
    private int shotCounter;
    Color color;


    public Player(String name, Color color, int shotCounter) {
        this.color = color;
        this.name = name;
        this.shotCounter = shotCounter;
    }

    public String getName() {
        return name;
    }

    void newRound(int addedPoint, Tank newTank) {
        this.points += addedPoint;
        this.tank = newTank;
    }

    boolean checkContact(List<Thing> everything, Tank newTank ) {
        boolean hasContactWithWall = false;
        boolean hasContactWithTank = false;
        for (int i = 0; i < everything.size(); i++) {
            if (everything.get(i) instanceof Tank){
                if(((Tank) everything.get(i)).contacts(newTank, 6*Tank.RADIUS)){
                     hasContactWithTank = true;
                }
            }
            else {
                if(((Wall)everything.get(i)).contacts(newTank, 40*Wall.WIDTH)){
                    hasContactWithWall = true;
                }
            }
        }
        return hasContactWithTank || hasContactWithWall;
    }

    public Tank getNewTank(List<Thing> everything, int width, int height) {
        int newWidth = width - 60;
        int newHeight = height - 60;
        while (true) {
            this.newTank = new Tank((int) (Math.random() * newWidth) + 30, (int) (Math.random() * newHeight) + 30, 0, this.color, this.shotCounter);
            if (!checkContact(everything, this.newTank)) {
                return this.newTank;
            }
        }
    }

    public int getPoints() {
        return points;
    }

    Thing getTank() {
        return this.newTank;
    }
}
