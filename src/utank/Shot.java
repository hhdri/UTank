package utank;

import java.awt.*;

public class Shot extends MovingThing {
    private final static int RADIUS = 10;
    private final static int LIFE = 30;
    
    int age;

    Shot(int x, int y, float direction) {
        super(x, y, direction, (float) 15, (float) 4);
        age = Shot.LIFE;
    }

    void draw(Graphics graphics) {
//        graphics.drawLine(this.getRoundedX(), this.getRoundedY(), (int) Math.round(this.getX() + (Shot.RADIUS * Math.sin(this.direction))), (int) Math.round(this.getY() + (Shot.RADIUS * Math.cos(this.direction))));
        graphics.fillOval(
                this.getRoundedX() - Shot.RADIUS + 7,
                this.getRoundedY() - Shot.RADIUS + 7,
                Shot.RADIUS * 2 - 15,
                Shot.RADIUS * 2 - 15
        );

    }

    void growOld() {
        this.age = this.age - 1;
    }

    boolean isDead() {
        return this.age <= 0;
    }

    void bounceAgainst(Wall wall) {
        this.direction = (wall.isVertical ? 0 : Math.PI) - this.direction;
        this.calculateVelocity();
    }

    @Override
    public int getRadius() {
        return RADIUS;
    }

    boolean contacts(MovingThing moving) {

        return false;
    }
}
