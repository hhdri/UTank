package utank;

import java.awt.*;
import java.util.Map;

public class Tank extends MovingThing {
    final static int RADIUS = 25;
    final static int GUN_LENGTH = 30;

    Tank(int x, int y, float direction) {
        super(x, y, direction, 06f, (float) 0.1);
    }

    public void draw(Graphics graphics) {
        graphics.drawOval(
                this.x - Tank.RADIUS,
                this.y - Tank.RADIUS,
                Tank.RADIUS * 2,
                Tank.RADIUS * 2
        );
        graphics.drawLine(this.x, this.y, this.getGunX(), this.getGunY());
    }

    int getGunX() {
        return (int) Math.round(this.x + (Tank.GUN_LENGTH * Math.sin(this.direction)));
    }

    int getGunY() {
        return (int) Math.round(this.y + (Tank.GUN_LENGTH * Math.cos(this.direction)));
    }

    @Override
    public int getRadius() {
        return Tank.RADIUS;
    }

    boolean contacts(MovingThing moving) {
        // TODO implement this
        return false;
    }
}