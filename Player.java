abstract class Player {
    protected int health;
    public SpecialMove specialMove;
    public Move move;
    private boolean fiftyPctDamRed;

    Player() {
        health = 100;
        fiftyPctDamRed = false;
        specialMove = new SpecialMove();
        move = new Move();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {

        this.health = health;
        if (this.health > 100)
            this.health = 100;

    }

    public void setFiftyPctDamRed(boolean fiftyPctDamRed) {
        this.fiftyPctDamRed = fiftyPctDamRed;
    }

    public boolean getFiftyPctDamRed() {
        return this.fiftyPctDamRed;
    }

    public abstract void init_move();
}
