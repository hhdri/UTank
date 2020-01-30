package utank;

import java.awt.*;

public class Shot extends MovingThing {
    final static int RADIUS = 10;
    final static int LIFE = 100;

    int age = Shot.LIFE;

    Shot(int x, int y, float direction) {
        super(x, y, 3, 0, direction);
    }

    void draw(Graphics graphics) {
        graphics.fillOval(this.x - Shot.RADIUS, this.y - Shot.RADIUS,
                Shot.RADIUS * 2, Shot.RADIUS * 2);
    }

    void growOld() {
        this.age = this.age - 1;
    }

    boolean isDead() {
        return this.age <= 0;
    }

    void bounceAgainst(Wall wall) {
        this.direction = (wall.isVertical? 0 : Math.PI) - this.direction;
    }

    @Override
    public int getRadius() {
        return RADIUS;
    }

    boolean contacts(MovingThing moving) {
        // TODO implement this
        return false;
    }
}
