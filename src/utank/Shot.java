package utank;

import java.awt.*;

public class Shot extends MovingThing {
    private final static int RADIUS = 10;
    private final static int LIFE = 100;

    int age;

    Shot(int x, int y, float direction) {
        super(x, y, direction, (float) 1, (float) 0.01);
        age = Shot.LIFE;
    }

    void draw(Graphics graphics) {
        graphics.drawLine(this.x, this.y, (int) Math.round(this.x + (Shot.RADIUS * Math.sin(this.direction))), (int) Math.round(this.y + (Shot.RADIUS * Math.cos(this.direction))));
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
