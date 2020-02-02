package utank;

import java.awt.*;
import java.util.Map;

public class Tank extends MovingThing {
    final static int RADIUS = 25;
    final static int GUN_LENGTH = 30;
    int shotCounter = 10;
    int shotTimer = 0;
    Tank(int x, int y, float direction) {
        super(x, y, direction, 2, (float) 0.03);
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
        int delta_x = moving.x - this.x;
        int delta_y = moving.y - this.y;
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return distance < this.RADIUS + moving.getRadius();
    }
}
