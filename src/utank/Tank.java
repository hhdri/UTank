package utank;

import java.awt.*;
import java.util.Map;

public class Tank extends MovingThing {
    final static int RADIUS = 25;
    final static int GUN_LENGTH = 30;
    int shotCounter = 10;
    int shotTimer = 0;
    boolean hasPowerUp = false;
    boolean hadPowerUp = false;
    Color color;
    Tank(int x, int y, float direction, Color color) {
        super(x, y, direction, 2, (float) 0.03);
        this.color = color;
    }

    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(
                this.getRoundedX() - Tank.RADIUS,
                this.getRoundedY() - Tank.RADIUS,
                Tank.RADIUS * 2,
                Tank.RADIUS * 2
        );
        if(hasPowerUp) {
            graphics.setColor(Color.black);
            graphics.fillOval(
                    this.getRoundedX() - Tank.RADIUS + 7,
                    this.getRoundedY() - Tank.RADIUS + 7,
                    Tank.RADIUS * 2 - 15,
                    Tank.RADIUS * 2 - 15
            );
        }
        graphics.setColor(Color.black);
        graphics.drawOval(
                this.getRoundedX() - Tank.RADIUS,
                this.getRoundedY() - Tank.RADIUS,
                Tank.RADIUS * 2,
                Tank.RADIUS * 2
        );
        graphics.drawLine(this.getRoundedX(), this.getRoundedY(), this.getGunX(), this.getGunY());
        graphics.setColor(Color.black);
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
        return distance  < Tank.RADIUS + moving.getRadius();
    }
}