import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.annotation.processing.SupportedOptions;

public class Fighter {

    public static void main(String[] args) throws FileNotFoundException {
        // initiating human player
        HumanPlayer humanPlayer = new HumanPlayer();
        humanPlayer.get_players_name();
        humanPlayer.init_move();

        // initiating computer player
        ComputerPlayer computerPlayer = new ComputerPlayer();
        computerPlayer.init_move();

        // displaying special moves
        System.out.println("\n-------------------------------------");
        System.out.println("Computer's Special Move: " + computerPlayer.specialMove.getCurrSpecialMoveDesc());
        System.out.println(
                humanPlayer.getName() + "'s Special Move: " + humanPlayer.specialMove.getCurrSpecialMoveDesc());
        System.out.println("-------------------------------------");

        boolean fiftyPctDamRedHum = false;
        boolean fiftyPctDamRedComp = false;

        if (computerPlayer.specialMove.getCurrSpecialMove() == 4)
            humanPlayer.setHealth(50);
        if (humanPlayer.specialMove.getCurrSpecialMove() == 4)
            computerPlayer.setHealth(50);

        // counter to count the turns...
        int count = 1;
        // starting the round
        while (humanPlayer.getHealth() > 0 && computerPlayer.getHealth() > 0) {

            // displaying both player's health
            printHealth(humanPlayer.getName(), humanPlayer.getHealth(), computerPlayer.getHealth());

            /* -------------------------------------------------------- */

            Boolean isRefLooking = false;
            if (Math.floor(Math.random() * 2) == 1)
                isRefLooking = true;

            System.out.println("\n=+=+=+=+=+=+=+=+=+");
            System.out.println("Turn No. " + count);
            System.out.println("=+=+=+=+=+=+=+=+=+");

            /* -------------------------------------------------------- */

            System.out.println("\n====================================");
            System.out.println(humanPlayer.getName() + "'s Move");
            System.out.println("====================================");

            int turn_damage = 0;
            int turn_health = 0;

            humanPlayer.move.choose_move();

            // if human player chooses 1, it will be a KICK with 20 damage to computer
            // player
            int turn_move = humanPlayer.move.getCurrMove();

            if (turn_move == 1) {
                System.out.println("-> [ PUNCH - 10 Damage ]");
                turn_damage += 20;
            } else if (turn_move == 2) {
                System.out.println("-> [ KICK - 20 Damage ]");
                turn_damage += 20;
            }
            // if human player chooses 2, it will block the damage caused with 50%
            // reduction,
            // this will be stored and used in the next encounter
            else if (turn_move == 3) {
                System.out.println("-> [ BLOCK - 50% Less Damage ]");
                fiftyPctDamRedHum = true;
            }
            // if player want to play his/her special move.
            else if (turn_move == 4) {
                // if player has bribed the referee (4th special move),
                // then let him/her choose the special move again.
                if (humanPlayer.specialMove.getCurrSpecialMove() == 4) {
                    humanPlayer.specialMove.choose_special_move();

                    // while user doesn't choose his special move except bribed referee,
                    // keep him/her asking again.
                    while (humanPlayer.specialMove.getCurrSpecialMove() == 4)
                        humanPlayer.specialMove.choose_special_move();

                    int turn_sp_move = humanPlayer.specialMove.getCurrSpecialMove();

                    // special move 1 will be double punch with 15 damage
                    if (turn_sp_move == 1) {
                        System.out.println("-> [ PUNCH - 15 Damage ]");
                        turn_damage += 15;
                    }

                    // special move 2 will be Kick with damage 30
                    else if (turn_sp_move == 2) {
                        System.out.println("-> [ KICK - 30 Damage ]");
                        turn_damage += 30;

                    }

                    // special move 3 will be Heal with health 20
                    else if (turn_sp_move == 3) {
                        System.out.println("-> [ Heal - 20 ]");
                        turn_health += 20;

                    }
                }
            }
            // if user 'CHEATS',
            else if (turn_move == 5) {
                // if the referee is looking, there'll be penalty... (if not bribed)
                if (isRefLooking) {
                    if (computerPlayer.specialMove.getCurrSpecialMove() == 4)
                        System.out.println("-> [ Ref. was looking - No Penalty ]");
                    else {
                        turn_health -= 20;
                        System.out.println("-> [ Ref. was looking - Health -20 ]");
                    }

                } else {
                    turn_damage += 40;
                    System.out.println("-> [ Ref. was not looking - Damage 40 ]");
                }
            }

            // player's turn completes here...
            /* ++++++++++++++++++++ CALCULATING MOVE +++++++++++++++++++++ */
            // if fiftyPctDamRed is true.
            if (fiftyPctDamRedComp)
                turn_damage = (turn_damage / 2);

            // setting new health of both plyers.
            computerPlayer.setHealth(computerPlayer.getHealth() - turn_damage);
            humanPlayer.setHealth(humanPlayer.getHealth() + turn_health);

            // displaying both player's health
            printHealth(humanPlayer.getName(), humanPlayer.getHealth(), computerPlayer.getHealth());

            // resetting the variables...
            fiftyPctDamRedComp = false;
            turn_damage = 0;
            turn_health = 0;
            /* ++++++++++++++++++++ CALCULATING MOVE +++++++++++++++++++++ */

            System.out.println("\n====================================");
            System.out.println("Computer Player's Move");
            System.out.println("====================================");

            computerPlayer.random_move();
            turn_move = computerPlayer.move.getCurrMove();

            // 25 PCT PUNCH prob.
            if (turn_move < 26) {
                System.out.println("-> [ PUNCH - 10 Damage ]");
                turn_damage += 10;
            }
            // 25 PCT KICK prob.
            else if (turn_move < 51) {
                System.out.println("-> [ KICK - 20 Damage ]");
                turn_damage += 20;
            }
            // 20 PCT BLOCK prob.
            else if (turn_move < 71) {
                System.out.println("-> [ BLOCK - 50% Less Damage ]");
                fiftyPctDamRedComp = true;
            }
            // 20 PCT special move prob.
            else if (turn_move < 91) {
                // if player has bribed the referee (4th special move),
                // then let him/her choose the special move again.
                if (computerPlayer.specialMove.getCurrSpecialMove() == 4) {
                    computerPlayer.random_special_move();

                    int turn_sp_move = computerPlayer.specialMove.getCurrSpecialMove();

                    // special move 1 will be double punch with 15 damage
                    if (turn_sp_move == 1) {
                        System.out.println("-> [ PUNCH - 15 Damage ]");
                        turn_damage += 15;
                    }

                    // special move 2 will be Kick with damage 30
                    else if (turn_sp_move == 2) {
                        System.out.println("-> [ KICK - 30 Damage ]");
                        turn_damage += 30;
                    }

                    // special move 3 will be Heal with health 20
                    else if (turn_sp_move == 3) {
                        System.out.println("-> [ Heal - 20 ]");
                        turn_health += 20;
                    }
                }
            }
            // 10 PCT cheat prob.
            else {
                if (isRefLooking) {
                    if (computerPlayer.specialMove.getCurrSpecialMove() == 4)
                        System.out.println("-> [ Ref. was looking - No Penalty ]");
                    else {
                        turn_health -= 20;
                        System.out.println("-> [ Ref. was looking - Health -20 ]");
                    }

                } else {
                    turn_damage += 40;
                    System.out.println("-> [ Ref. was not looking - Damage 40 ]");
                }
            }

            // player's turn completes here...
            /* ++++++++++++++++++++ CALCULATING MOVE +++++++++++++++++++++ */
            // if fiftyPctDamRed is true.
            if (fiftyPctDamRedHum)
                turn_damage = (turn_damage / 2);

            // setting new health of both plyers.
            computerPlayer.setHealth(computerPlayer.getHealth() + turn_health);
            humanPlayer.setHealth(humanPlayer.getHealth() - turn_damage);

            // resetting the variables...
            fiftyPctDamRedHum = false;
            turn_damage = 0;
            turn_health = 0;
            /* ++++++++++++++++++++ CALCULATING MOVE DONE +++++++++++++++++++++ */

            //
            count++;
        }

        String winner = "";
        if (computerPlayer.getHealth() > humanPlayer.getHealth()) {
            System.out.println("\n\n=============================");
            System.out.println("COMPUTER PLAYER WINS!!!!!");
            System.out.println("=============================");
            winner = "Computer";
        } else {
            System.out.println("\n\n=============================");
            System.out.println(humanPlayer.getName() + " WINS!!!!!");
            System.out.println("=============================");
            winner = humanPlayer.getName();
        }

        writeToFile("outcome.txt", winner, count);
    }

    public static void printHealth(String hu_player_name, int h_player_health, int comp_player_health) {
        System.out.println();
        System.out.println();

        System.out.println(hu_player_name + "'s Health");
        for (int i = 0; i < h_player_health; i += 2)
            System.out.print("+");
        System.out.println(" " + h_player_health + "%");

        System.out.println("Computer Player's Health");
        for (int i = 0; i < comp_player_health; i += 2)
            System.out.print("+");
        System.out.println(" " + comp_player_health + "%");
    }

    public static void writeToFile(String filename, String winner_name, int no_of_moves) {
        try {
            FileWriter myWriter = new FileWriter(filename);
            myWriter.write("Winner       ->" + winner_name + "\n");
            myWriter.write("No. of Moves -> " + no_of_moves + "\n");
            myWriter.close();
            System.out.println("outcome.txt successfully generated....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}