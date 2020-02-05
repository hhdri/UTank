package utank;

import java.awt.*;

abstract class Thing {
    private float x, y;

    Thing(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract void draw(Graphics graphics);
    abstract boolean contacts(MovingThing moving);

    int getRoundedX() {
        return Math.round(this.x);
    }

    int getRoundedY() {
        return Math.round(this.y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}
