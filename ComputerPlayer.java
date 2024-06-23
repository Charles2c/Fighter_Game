public class ComputerPlayer extends Player {
    @Override
    public void init_move() {
        this.health = 100;
        this.specialMove.setCurrSpecialMove((int) Math.floor(Math.random() * 4 + 1));
    
    }

    public void random_move() {
        this.move.setCurrMove((int) Math.floor(Math.random() * 100 + 1));
    }

    public void random_special_move() {
        this.specialMove.setCurrSpecialMove((int) Math.floor(Math.random() * 3 + 1));
    }
}
