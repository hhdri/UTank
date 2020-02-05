package utank;

import java.awt.*;

enum PowerUpType {
    FRAG_BOMB, MINE, LASER
}

public class PowerUp extends Thing {
    private final static int RADIUS = 10;
    private final static int LIFE = 1000;
    int age;
    PowerUpType type;

    PowerUp(int x, int y, PowerUpType type) {
        super(x, y);
        this.age = 0;
        this.type = type;
    }

    void growUp() {
        this.age++;
    }

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
        graphics.fillOval(this.x, this.y, PowerUp.RADIUS, PowerUp.RADIUS);
        graphics.setColor(Color.black);
    }

    boolean contacts(MovingThing moving) {
        int delta_x = moving.x - this.x;
        int delta_y = moving.y - this.y;
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return distance < RADIUS + moving.getRadius();
    }
}
