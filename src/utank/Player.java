package utank;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private Tank tank;
    private int points = 0;

    void newRound(boolean hasWon, int startX, int startY) {
        if (hasWon)
            this.points = this.points + 1;
        this.tank = new Tank(startX, startY, 0);
    }

    boolean checkContact (List<Thing> everything, int[] coords) {
        boolean hasContactWithWall = false;
        boolean hasContactWithTank = false;
        for (int i = 0; i < everything.size(); i++) {
            System.out.println(everything.get(i));
            if (everything.get(i) instanceof Tank) {
                int[] props = {((Tank) everything.get(i)).x,((Tank) everything.get(i)).y, ((Tank) everything.get(i)).RADIUS};
                int[] borderPointsX = {props[0] - (8)*props[2], props[0] + (8)*props[2]};
                int[] borderPointsY = {props[1] - (8)*props[2], props[1] + (8)*props[2]};
                if (borderPointsX[0] <= coords[0] && coords[0] <= borderPointsX[1] &&
                    borderPointsY[0] <= coords[1] && coords[1] <= borderPointsY[1]){
                    hasContactWithTank = true;
                    System.out.println("tankkkkkkk");
                }
            }
            else if(everything.get(i) instanceof Shot){
                System.out.println("shot");
            }
            else {
                int[] props = {((Wall)everything.get(i)).i1, ((Wall)everything.get(i)).i2,
                        ((Wall)everything.get(i)).j, ((Wall)everything.get(i)).WIDTH};
                boolean isVert = ((Wall)everything.get(i)).isVertical;
                if(isVert) {
                    System.out.println("wall");

                    int[] borderPointsVerticalX = {props[2] - props[3], props[0] + (2)*props[3]};
                    int[] borderPointsVerticalY = {props[0] - props[3], props[1] + props[3]};
                    if(borderPointsVerticalX[0] <= coords[0] && coords[0] <= borderPointsVerticalX[1] &&
                            borderPointsVerticalY[0] <= coords[1] && coords[1] <= borderPointsVerticalY[1]){
                        hasContactWithWall = true;
                        System.out.println("wallll");
                    }
                }
                else {
                    System.out.println("wallaasaddaw");
                    int[] borderPointsY = {props[2] - props[3], props[0] + (2)*props[3]};
                    int[] borderPointsX = {props[0] - props[3], props[1] + props[3]};
                    if(borderPointsX[0] <= coords[0] && coords[0] <= borderPointsX[1] &&
                            borderPointsY[0] <= coords[1] && coords[1] <= borderPointsY[1]){
                        hasContactWithWall = true;
                        System.out.println("wallll");
                    }
            }
        }
        }
        return hasContactWithTank || hasContactWithWall;
    }

    public int[] getCoordinates (List<Thing> everything, int width, int height){
        int newWidth = width - 60;
        int newHeight = height - 60;
        while (true) {
            int[] coords = {(int)(Math.random()*newWidth)+30, (int)(Math.random()*newHeight)+30};
            if (!checkContact(everything, coords))
                return coords;
        }
    }

    public int getPoints() {
        return points;
    }

    Thing getTank() {
        return this.tank;
    }
}
