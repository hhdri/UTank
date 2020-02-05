package utank;

public abstract class MovingThing extends Thing{
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
            if ((wall.j < this.getRoundedX() && vX < 0) || (wall.j > this.getRoundedX() && vX > 0))
                vX = 0;
        } else {
            if ((wall.j < this.getRoundedY() && vY < 0) || (wall.j > this.getRoundedY() && vY > 0))
                vY = 0;
        }
    }

    void blockedBy(Tank tank) {
        this.vX = tank.getY() - this.getY();
        this.vY = this.getX() - tank.getX();

        double scale = Math.sqrt(this.vX * this.vX + this.vY * this.vY) / 3;

        this.vX /= scale;
        this.vY /= scale;
    }

    public double getDirection() {
        return direction;
    }

    public abstract int getRadius();
}

