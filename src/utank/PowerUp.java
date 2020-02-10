package utank;

import java.awt.*;

enum PowerUpType {
    FRAG_BOMB, MINE, LASER
}

public class PowerUp extends Thing {
    final static int RADIUS = 10;
    final static int LIFE = 500;
    int age;
    int time;
    PowerUpType type;

    PowerUp(float x, float y, PowerUpType type) {
        super(x, y);
        this.age = LIFE;
        this.type = type;
    }

    void growOld() {
        this.age = this.age - 1;
    }
    boolean isDead() { return this.age <= 0; }

    void draw(Graphics graphics) {
        switch (this.type) {
            case FRAG_BOMB:
                graphics.setColor(Color.cyan);
                break;
            case MINE:
                graphics.setColor(Color.red);
                break;
            case LASER:
                graphics.setColor(Color.gray);
                break;
        }
        graphics.setColor(Color.blue);
        graphics.fillOval(this.getRoundedX() , this.getRoundedY(), PowerUp.RADIUS, PowerUp.RADIUS);
        graphics.setColor(Color.black);
    }


    boolean contacts(MovingThing moving) {
        float delta_x = moving.x - this.x;
        float delta_y = moving.y - this.y;
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return distance < RADIUS + moving.getRadius();
    }
}
