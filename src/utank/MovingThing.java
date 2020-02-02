package utank;

public abstract class MovingThing extends Thing{
    double direction; // rad
    float velocity; // pix/step
    int vX, vY;
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
        this.vX = (int) Math.round(this.velocity * Math.sin(this.direction));
        this.vY = (int) Math.round(this.velocity * Math.cos(this.direction));
    }

    void step() {
        this.x += this.vX;
        this.y += this.vY;
    }

    void blockedBy(Wall wall) {
        if (wall.isVertical) {
            if ((wall.j < x && vX < 0) || (wall.j > x && vX > 0))
                vX = 0;
        } else {
            if ((wall.j < y && vY < 0) || (wall.j > y && vY > 0))
                vY = 0;
        }
    }

    public double getDirection() {
        return direction;
    }

    public abstract int getRadius();
}

