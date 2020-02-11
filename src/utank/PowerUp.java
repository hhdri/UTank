package utank;

import java.awt.*;

enum PowerUpStatus {
    UnPicked, Picked, Landed
}

public class PowerUp extends Thing { // PowerUp solely means land mine
    final static int RADIUS = 10;
    final static int LIFE = 1000;
    int age;
    PowerUpStatus powerUpStatus;
    Tank carrier;

    PowerUp(float x, float y) {
        super(x, y);
        this.age = LIFE;
        this.powerUpStatus = PowerUpStatus.UnPicked;
    }

    void setPicked(Tank carrier) {
        this.powerUpStatus = PowerUpStatus.Picked;
        this.carrier = carrier;
    }

    void setLanded () {
        this.powerUpStatus = PowerUpStatus.Landed;
        this.x = this.carrier.getRoundedX();
        this.y = this.carrier.getRoundedY();
    }

    void growOld() {
        if (powerUpStatus == PowerUpStatus.Picked)
            this.age = this.age - 1;
    }

    boolean isDead() {
        if (powerUpStatus == PowerUpStatus.Picked)
            return this.age == 0;
        else
            return false;
    }

    void draw(Graphics graphics) {
        switch (powerUpStatus) {
            case UnPicked:
                graphics.setColor(Color.blue);
                graphics.fillOval(this.getRoundedX() - PowerUp.RADIUS,
                        this.getRoundedY() - PowerUp.RADIUS,
                        PowerUp.RADIUS * 2,
                        PowerUp.RADIUS * 2);
                graphics.setColor(Color.black);
                break;
            case Picked:
                graphics.setColor(Color.blue);
                graphics.fillOval(this.carrier.getRoundedX() - PowerUp.RADIUS,
                        this.carrier.getRoundedY() - PowerUp.RADIUS,
                        PowerUp.RADIUS * 2,
                        PowerUp.RADIUS * 2);
                graphics.setColor(Color.black);
            case Landed:
                break;
        }
    }

    boolean contacts(MovingThing moving) {
        float delta_x = moving.x - this.x;
        float delta_y = moving.y - this.y;
        double distance = Math.sqrt(delta_x * delta_x + delta_y * delta_y);
        return distance < RADIUS + moving.getRadius();
    }
}
