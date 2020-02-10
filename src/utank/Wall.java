package utank;

import java.awt.*;

public class Wall extends Thing {
    final static int WIDTH = 1;

    int i1, i2, j;
    boolean isVertical;

    public Wall(int x, int y, int length, boolean isVertical) {
        super(x, y);
        this.isVertical = isVertical;
        if (isVertical) {
            this.i1 = y;
            this.i2 = y + length;
            this.j = x;
        }
        else {
            this.i1 = x;
            this.i2 = x + length;
            this.j = y;
        }
    }

    public void draw(Graphics graphics) {
        int width = (this.isVertical)? Wall.WIDTH : this.i2 - this.i1;
        int height = (this.isVertical)? this.i2 - this.i1 : Wall.WIDTH;
        graphics.fillRect(this.getRoundedX(), this.getRoundedY(), width, height);
    }

    boolean contacts(MovingThing moving) {
        float repX = 0, repY = 0;
        if (isVertical) {
            if (moving.getY() > Math.min(this.i1, this.i2) && moving.getY() < Math.max(this.i1, this.i2)) {
                repX = this.j;
                repY = moving.getY();
            }
            else if (moving.getY() < Math.min(this.i1, this.i2)) {
                repX = this.j;
                repY = Math.min(this.i1, this.i2) + 4;
            }
            else {
                repX = this.j;
                repY = Math.max(this.i1, this.i2) - 4;
            }
        }
        else {
            if (moving.getX() > Math.min(this.i1, this.i2) && moving.getX() < Math.max(this.i1, this.i2)) {
                repY = this.j;
                repX = moving.getX();
            }
            else if (moving.getX() < Math.min(this.i1, this.i2)) {
                repY = this.j;
                repX = Math.min(this.i1, this.i2) + 4;
            }
            else {
                repY = this.j;
                repX = Math.max(this.i1, this.i2) - 4;
            }
        }
        return Math.hypot(repX - moving.getX(), repY - moving.getY()) < moving.getRadius()+5;
    }
}
