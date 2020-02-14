package utank;

import java.awt.*;

public class Tank extends MovingThing {
    final static int RADIUS = 25;
    final static int GUN_LENGTH = 30;
    int shotCounter;
    int shotTimer = 0;
    Color color;

    Tank(int x, int y, float direction, Color color, int shotCounter) {
        super(x, y, direction, 5, (float) 0.075);
        this.color = color;
        this.shotCounter = shotCounter;
    }


    public void draw(Graphics graphics) {
        graphics.setColor(color);
        graphics.fillOval(
                this.getRoundedX() - Tank.RADIUS,
                this.getRoundedY() - Tank.RADIUS,
                Tank.RADIUS * 2,
                Tank.RADIUS * 2
        );
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
    public int getShotCounter() {
        return shotCounter;
    }

    public void setShotCounter(int shotCounter) {
        this.shotCounter = shotCounter;
    }

    @Override
    public int getRadius() {
        return Tank.RADIUS;
    }

    boolean _contacts(MovingThing moving, int alpha) {
        float delta_x = moving.getX() - this.getX();
        float delta_y = moving.getY() - this.getY();
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return distance  < Tank.RADIUS + moving.getRadius() + alpha;
    }
    boolean contacts(MovingThing movingThing){
        return _contacts(movingThing, 0);
    }
    boolean contacts(MovingThing movingThing, int alpha){
        return _contacts(movingThing, alpha);
    }
}