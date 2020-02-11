package utank;

import java.awt.*;
import java.util.List;

public class Player {
    private Tank tank;
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

    void newRound(int addedPoint, int startX, int startY) {
        this.points += addedPoint;
        this.tank = new Tank(startX, startY, 0, this.color, this.shotCounter);
    }

    boolean checkContact(List<Thing> everything, int[] coords) {
        boolean hasContactWithWall = false;
        boolean hasContactWithTank = false;
        boolean hasContactWithPowerUp = false;
        for (int i = 0; i < everything.size(); i++) {
            if (everything.get(i) instanceof Tank) {
                int[] props = {((Tank) everything.get(i)).getRoundedX(), ((Tank) everything.get(i)).getRoundedY(), Tank.RADIUS};
                int[] borderPointsX = {props[0] - (8) * props[2], props[0] + (8) * props[2]};
                int[] borderPointsY = {props[1] - (8) * props[2], props[1] + (8) * props[2]};
                if (borderPointsX[0] <= coords[0] && coords[0] <= borderPointsX[1] &&
                        borderPointsY[0] <= coords[1] && coords[1] <= borderPointsY[1]) {
                    hasContactWithTank = true;
                }
            } else if (everything.get(i) instanceof PowerUp) {
                int[] props = {((PowerUp) everything.get(i)).getRoundedX(), ((PowerUp) everything.get(i)).getRoundedY(), ((PowerUp) everything.get(i)).RADIUS};
                int[] borderPointsX = {props[0] - (3) * props[2], props[0] + (3) * props[2]};
                int[] borderPointsY = {props[1] - (3) * props[2], props[1] + (3) * props[2]};
                if (borderPointsX[0] <= coords[0] && coords[0] <= borderPointsX[1] &&
                        borderPointsY[0] <= coords[1] && coords[1] <= borderPointsY[1]) {
                    hasContactWithPowerUp = true;
                }
            } else {
                int[] props = {((Wall) everything.get(i)).i1, ((Wall) everything.get(i)).i2,
                        ((Wall) everything.get(i)).j, ((Wall) everything.get(i)).WIDTH};
                boolean isVert = ((Wall) everything.get(i)).isVertical;
                if (isVert) {
                    int[] borderPointsVerticalX = {props[2] - (50) * props[3], props[2] + (50) * props[3]};
                    int[] borderPointsVerticalY = {props[0] - (50) * props[3], props[1] + (50) * props[3]};
                    if (borderPointsVerticalX[0] <= coords[0] && coords[0] <= borderPointsVerticalX[1] &&
                            borderPointsVerticalY[0] <= coords[1] && coords[1] <= borderPointsVerticalY[1]) {
                        hasContactWithWall = true;
                    }
                } else {
                    int[] borderPointsY = {props[2] - (50) * props[3], props[2] + (50) * props[3]};
                    int[] borderPointsX = {props[0] - (50) * props[3], props[1] + (50) * props[3]};
                    if (borderPointsX[0] <= coords[0] && coords[0] <= borderPointsX[1] &&
                            borderPointsY[0] <= coords[1] && coords[1] <= borderPointsY[1]) {
                        hasContactWithWall = true;
                    }
                }
            }
        }
        return hasContactWithTank || hasContactWithWall || hasContactWithPowerUp;
    }

    public int[] getCoordinates(List<Thing> everything, int width, int height) {
        int newWidth = width - 60;
        int newHeight = height - 60;
        while (true) {
            int[] coords = {(int) (Math.random() * newWidth) + 30, (int) (Math.random() * newHeight) + 30};
            if (!checkContact(everything, coords)) {
                return coords;
            }
        }
    }

    public int getPoints() {
        return points;
    }

    Thing getTank() {
        return this.tank;
    }
}
