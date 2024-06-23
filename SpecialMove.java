import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SpecialMove {
    // data from the file will be read and stored in these arraylists....
    public ArrayList<String> desc; // to store the description of moves
    public ArrayList<String> status; // to store what move does, health/damage
    public ArrayList<Integer> benefit; // to store the benefit, (numerical value)
    private int currSpecialMove;
    private String currSpecialMoveDesc;

    SpecialMove() {
        currSpecialMove = 0;
        desc = new ArrayList<String>();
        status = new ArrayList<String>();
        benefit = new ArrayList<Integer>();
        try {
            read_moves();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getCurrSpecialMove() {
        return currSpecialMove;
    }

    public void setCurrSpecialMove(int currSpecialMove) {
        this.currSpecialMove = currSpecialMove;
        this.currSpecialMoveDesc = this.desc.get(currSpecialMove - 1);
    }

    public String getCurrSpecialMoveDesc() {
        return currSpecialMoveDesc;
    }

    public void setCurrSpecialMoveDesc(String currSpecialMoveDesc) {
        this.currSpecialMoveDesc = currSpecialMoveDesc;
    }

    // this method will read data into the array from the file.
    private void read_moves()  throws FileNotFoundException {
        String filename = "specialmoves.txt";

        try {
            File fileObj = new File(filename);
            Scanner fileReader = new Scanner(fileObj);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();

                // splitting the data based on comma.
                String[] data = line.split("[,]", 0);

                // storing the data into the respective arraylists.
                desc.add(data[0]);
                status.add(data[1]);
                benefit.add(Integer.parseInt(data[2]));
            }
            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error Occured while reaidng file" + e.getMessage());
        }
    }

    // this method displays the menu of moves
    public void display_special_move_menu() {
        System.out.println("------------------------------------");
        System.out.println("       CHOOSE Special MOVE");
        System.out.println("------------------------------------");
        for (int i = 0; i < 4; i++)
            System.out.println((i + 1) + ". " + desc.get(i) + " - " + status.get(i) + " - " + benefit.get(i));
        System.out.println("------------------------------------");
    }

    // this method selects and validates the move from the move menu...
    public void choose_special_move() {
        display_special_move_menu();

        Scanner input = new Scanner(System.in);
        System.out.print("Choose your Move : ");

        String choice = input.nextLine();

        currSpecialMove = Integer.parseInt(choice);

        while (currSpecialMove < 1 || currSpecialMove > 4) {
            System.out.println("INVALID CHOICE!!! Select Again");
            display_special_move_menu();
            choice = input.nextLine();
            currSpecialMove = Integer.parseInt(choice);
        }

        this.currSpecialMoveDesc = this.desc.get(currSpecialMove - 1);
    }
}
