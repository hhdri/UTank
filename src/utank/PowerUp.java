package utank;

import java.awt.*;
import java.util.List;

enum PowerUpStatus {
    UnPicked, Picked, Landed
}

public class PowerUp extends Thing { // PowerUp solely means land mine
    final static int RADIUS = 10;
    final static int LIFE = 500;
    int age;
    PowerUpStatus powerUpStatus;
    Tank carrier;

    PowerUp(float x, float y) {
        super(x, y);
        this.age = LIFE;
        this.powerUpStatus = PowerUpStatus.UnPicked;
    }

    void setPicked(Tank carrier) {
        this.powerUpStatus = PowerUpStatus.Picked;
        this.age = LIFE - 200;
        this.carrier = carrier;
    }

    void setLanded () {
        this.powerUpStatus = PowerUpStatus.Landed;
        this.age = LIFE;
        this.x = this.carrier.getRoundedX();
        this.y = this.carrier.getRoundedY();
    }

    void growOld() {
        this.age -= 1;
    }

    boolean isDead() {
        return this.age == 0;
    }

    void draw(Graphics graphics) {
        switch (powerUpStatus) {
            case UnPicked:
                graphics.setColor(Color.blue);
                graphics.fillOval(this.getRoundedX() - PowerUp.RADIUS,
                        this.getRoundedY() - PowerUp.RADIUS,
                        PowerUp.RADIUS * 2,
                        PowerUp.RADIUS * 2);
                graphics.setColor(Color.black);
                break;
            case Picked:
                graphics.setColor(Color.blue);
                graphics.fillOval(this.carrier.getRoundedX() - PowerUp.RADIUS,
                        this.carrier.getRoundedY() - PowerUp.RADIUS,
                        PowerUp.RADIUS * 2,
                        PowerUp.RADIUS * 2);
                graphics.setColor(Color.black);
            case Landed:
                break;
        }
    }

    boolean checkContact(java.util.List<Thing> everything, int[] coords) {
        boolean hasContactWithWall = false;
        for (int i = 0; i <everything.size() ; i++) {
            if (everything.get(i) instanceof Wall) {
                int[] props = {((Wall) everything.get(i)).i1, ((Wall) everything.get(i)).i2,
                        ((Wall) everything.get(i)).j, ((Wall) everything.get(i)).WIDTH};
                boolean isVert = ((Wall) everything.get(i)).isVertical;
                if (isVert) {
                    int[] borderPointsVerticalX = {props[2] - (25) * props[3], props[2] + (25) * props[3]};
                    int[] borderPointsVerticalY = {props[0] - (25) * props[3], props[1] + (25) * props[3]};
                    if (borderPointsVerticalX[0] <= coords[0] && coords[0] <= borderPointsVerticalX[1] &&
                            borderPointsVerticalY[0] <= coords[1] && coords[1] <= borderPointsVerticalY[1]) {
                        hasContactWithWall = true;
                    }
                } else {
                    int[] borderPointsY = {props[2] - (25) * props[3], props[2] + (25) * props[3]};
                    int[] borderPointsX = {props[0] - (25) * props[3], props[1] + (25) * props[3]};
                    if (borderPointsX[0] <= coords[0] && coords[0] <= borderPointsX[1] &&
                            borderPointsY[0] <= coords[1] && coords[1] <= borderPointsY[1]) {
                        hasContactWithWall = true;
                    }
                }
            }
        }
        return hasContactWithWall;
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

    boolean contacts(MovingThing moving) {
        float delta_x = moving.x - this.x;
        float delta_y = moving.y - this.y;
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return distance < RADIUS + moving.getRadius();
    }
}
