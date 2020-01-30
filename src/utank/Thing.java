package utank;

import java.awt.*;

abstract class Thing {
    int x, y;

    Thing(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract void draw(Graphics graphics);
    abstract boolean contacts(MovingThing moving);
}
