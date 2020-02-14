package utank;

public abstract class MovingThing extends Thing {
    double direction; // rad
    float velocity; // pix/step
    float vX, vY;
    float angularVelocity; // rad/step

    MovingThing(int x, int y, double direction, float velocity, float angularVelocity) {
        super(x, y);
        this.direction = direction;
        this.velocity = velocity;
        this.calculateVelocity();
        this.angularVelocity = angularVelocity;
    }

    private void changeDirection(double amount) {
        this.direction = (this.direction + amount) % (2 * Math.PI);
        this.calculateVelocity();
    }

    public void turnLeft() {
        this.changeDirection(this.angularVelocity);
    }

    public void turnRight() {
        this.changeDirection(-this.angularVelocity);
    }

    void calculateVelocity() {
        this.vX = (float) (this.velocity * Math.sin(this.direction));
        this.vY = (float) (this.velocity * Math.cos(this.direction));
    }

    void step() {
        this.setX(this.getX() + this.vX);
        this.setY(this.getY() + this.vY);
    }

    void blockedBy(Wall wall) {
        if (wall.isVertical) {
            if (this.getY() > Math.min(wall.i1, wall.i2) && this.getY() < Math.max(wall.i1, wall.i2)) {
                if ((wall.j < this.getRoundedX() && vX < 0) || (wall.j > this.getRoundedX() && vX > 0))
                    vX = 0;
            } else if (this.getY() < Math.min(wall.i1, wall.i2)) {
                float repX = wall.j;
                float repY = Math.min(wall.i1, wall.i2);
                float m = (repY - this.getY()) / (repX - this.getX());
                float vX1 = (1 / (1 + m * m)) * (this.vX * m * m - this.vY * m);
                float vY1 = (1 / (1 + m * m)) * (-this.vX * m + this.vY);
                this.vX = vX1;
                this.vY = vY1;
            } else {
                float repX = wall.j;
                float repY = Math.max(wall.i1, wall.i2) - 2;
                float m = (repY - this.getY()) / (repX - this.getX());
                float vX1 = (1 / (1 + m * m)) * (this.vX * m * m - this.vY * m);
                float vY1 = (1 / (1 + m * m)) * (-this.vX * m + this.vY);
                this.vX = vX1;
                this.vY = vY1;
            }
        } else {
            if (this.getX() > Math.min(wall.i1, wall.i2) && this.getX() < Math.max(wall.i1, wall.i2)) {
                if ((wall.j < this.getRoundedY() && vY < 0) || (wall.j > this.getRoundedY() && vY > 0))
                    vY = 0;
            } else if (this.getX() < Math.min(wall.i1, wall.i2)) {
                float repY = wall.j;
                float repX = Math.min(wall.i1, wall.i2) + 2;
                float m = (repY - this.getY()) / (repX - this.getX());
                float vX1 = (1 / (1 + m * m)) * (this.vX * m * m - this.vY * m);
                float vY1 = (1 / (1 + m * m)) * (-this.vX * m + this.vY);
                this.vX = vX1;
                this.vY = vY1;
            } else {
                float repY = wall.j;
                float repX = Math.max(wall.i1, wall.i2) - 2;
                float m = (repY - this.getY()) / (repX - this.getX());
                float vX1 = (1 / (1 + m * m)) * (this.vX * m * m - this.vY * m);
                float vY1 = (1 / (1 + m * m)) * (-this.vX * m + this.vY);
                this.vX = vX1;
                this.vY = vY1;
            }
        }
    }

    void blockedBy(Tank tank) {
        float m = (tank.getY() - this.getY()) / (tank.getX() - this.getX());
        float vX1 = (1 / (1 + m * m)) * (this.vX * m * m - this.vY * m);
        float vY1 = (1 / (1 + m * m)) * (-this.vX * m + this.vY);
        this.vX = vX1;
        this.vY = vY1;
    }

    void blockedByBoth(Tank tank, Wall wall) {
        if (wall.isVertical) {
            if (this.y < tank.y)
                this.vY = 0;
            else
                tank.vY = 0;
        } else {
            if (this.x < tank.x)
                this.vX = 0;
            else
                tank.vX = 0;
        }


    }

    public double getDirection() {
        return direction;
    }

    public abstract int getRadius();
}

