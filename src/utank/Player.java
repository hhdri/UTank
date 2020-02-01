package utank;

public class Player {
    private Tank tank;
    private int points = 0;

    void newRound(boolean hasWon, int startX, int startY) {
        if (hasWon)
            this.points = this.points + 1;
        this.tank = new Tank(startX, startY, 0);
    }

    Thing getTank() {
        return this.tank;
    }
}
