import java.util.Scanner;

public class HumanPlayer extends Player {
    private String name;

    HumanPlayer() {
        this.name = "Human";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // method to take player's name from input...
    public void get_players_name() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Your Name : ");

        name = input.nextLine();
    
        while (name.length() < 3 || name.length() > 12) {
            System.out.println("INVALID NAME!!! Enter Again: ");
            name = input.nextLine();
        }
        // input.close();
    }

    @Override
    public void init_move() {
        health = 100;
        this.specialMove.choose_special_move();
    }
}
