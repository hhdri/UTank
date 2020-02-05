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
                this.getRoundedX() - Tank.RADIUS,
                this.getRoundedY() - Tank.RADIUS,
                Tank.RADIUS * 2,
                Tank.RADIUS * 2
        );
        graphics.drawLine(this.getRoundedX(), this.getRoundedY(), this.getGunX(), this.getGunY());
    }

    int getGunX() {
        return (int) Math.round(this.getX() + (Tank.GUN_LENGTH * Math.sin(this.direction)));
    }

    int getGunY() {
        return (int) Math.round(this.getY() + (Tank.GUN_LENGTH * Math.cos(this.direction)));
    }

    @Override
    public int getRadius() {
        return Tank.RADIUS;
    }

    boolean contacts(MovingThing moving) {
        float delta_x = moving.getX() - this.getX();
        float delta_y = moving.getY() - this.getY();
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return distance < Tank.RADIUS + moving.getRadius();
    }
}
