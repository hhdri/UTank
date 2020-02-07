package utank;

import java.awt.*;

abstract class Thing {
    public float x, y;

    Thing(float x, float y) {
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
