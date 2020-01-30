package utank;

public abstract class MovingThing extends Thing{
    double direction; // rad
    float velocity; // pix/step
    float angularVelocity; // rad/step

    public MovingThing(int x, int y, double direction, float velocity, float angularVelocity) {
        super(x, y);
        this.direction = direction;
        this.velocity = velocity;
        this.angularVelocity = angularVelocity;
    }

    private void changeDirection(double amount) {
        this.direction = (this.direction + amount) % (2 * Math.PI);
    }

    public void turnLeft() {
        this.changeDirection(this.angularVelocity);
    }

    public void turnRight() {
        this.changeDirection(-this.angularVelocity);
    }

    void step() {
        this.x += Math.round(this.velocity * Math.sin(this.direction));
        this.y += Math.round(this.velocity * Math.cos(this.direction));
    }

    public double getDirection() {
        return direction;
    }

    public abstract int getRadius();
}

