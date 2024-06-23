import java.util.Scanner;

public class Move {
    private int currMove = 0;

    public int getCurrMove() {
        return currMove;
    }

    public void setCurrMove(int currMove) {
        this.currMove = currMove;
    }

    // this method displays the menu of moves
    public void display_move_menu() {
        System.out.println("------------------------------------");
        System.out.println("        CHOOSE Your MOVE");
        System.out.println("------------------------------------");
        System.out.println("1. Punch Damage 10");
        System.out.println("2. Kick  Damage 20");
        System.out.println("3. Block 50% less Damage");
        System.out.println("4. Special Move");
        System.out.println("5. Cheat");
        System.out.println("------------------------------------");
    }

    // this method selects and validates the move from the move menu...
    public void choose_move() {
        display_move_menu();

        Scanner input = new Scanner(System.in);
        String choice = input.nextLine();
        this.currMove = Integer.parseInt(choice);

        while (this.currMove < 1 || this.currMove > 5) {
            System.out.println("INVALID CHOICE!!! Select Again");
            display_move_menu();
            choice = input.nextLine();
         this.currMove = Integer.parseInt(choice);
        }

        // input.close();
    }
}
