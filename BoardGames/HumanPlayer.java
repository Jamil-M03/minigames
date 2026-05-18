package BoardGames;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HumanPlayer extends Player {
    Scanner input;

    public HumanPlayer(String name){
        super(name);
        input = new Scanner(System.in);
    }

    @Override
    public int getInput(int range) {
        int move;

        while (true) {
            System.out.println("Enter a number (1-" + range + "): ");
            try {
                move = input.nextInt();
                if (move < 1 || move > range){
                    System.out.println("Invalid input.");
                    input.nextLine();
                    continue;
                }
                break;
            } catch (InputMismatchException ime){
                String answer = input.next();
                if (answer.equalsIgnoreCase("quit")){
                    BoardGameApp.quit();
                }
                if (answer.equalsIgnoreCase("menu")){
                    return -69;
                }
                BoardGameApp.invalidInput(answer);
                input.nextLine();
            }
        }

        return move - 1;
    }
    
}
