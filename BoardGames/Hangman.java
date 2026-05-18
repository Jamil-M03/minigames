package BoardGames;

import java.util.ArrayList;
// import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Hangman extends AbstractGame {
    static final int minPlayerCount = 1;
    static final int maxPlayerCount = 1;
    static final String gameName = "Hangman";
    
    static final Random rand = new Random();
    static final Scanner input = new Scanner(System.in);
    
    // static ArrayList<String> wordsSoFar = new ArrayList<>(Arrays.asList(""));

    String word;
    HangmanWord hangWord;
    Noose noose;
    ArrayList<Character> lettersGuessed;

    public Hangman(Player[] players){
        super(players);
    }

    @Override
    public void startGame(){
        while(true){
            this.noose = new Noose();
            this.word = WordBank.getWord();
            this.hangWord = new HangmanWord(word);
            this.lettersGuessed = new ArrayList<>();

            // System.out.println(word);
            while (!noose.isComplete() && !hangWord.isComplete()) {
                noose.displayNoose();
                displayGrid();
                System.out.println("Enter a letter");
                char c = input.next().charAt(0);
                // char c = getPlayerInput();

                if (!HangmanChar.ALLOWED_CHARS.contains(c)){
                    System.out.println("Invalid character: " + c);
                    System.out.println("Please enter a letter");
                    try{
                        Thread.sleep(800);
                    } catch (InterruptedException ie){
                        System.out.println(ie.getMessage());
                    }
                    continue;
                }

                if (lettersGuessed.contains(c)){
                    System.out.println("You have already guessed: " + c);
                    System.out.println("Here's the list of letters you have guessed so far:");
                    System.out.println(lettersGuessed);
                    continue;
                }
                lettersGuessed.add(c);


                if (noose.getIncorrectGuesses() == 2){
                    System.out.println("* Hint: The theme is " + WordBank.getWordTheme(word)); // TODO: fix so it gives a letter on easy diff
                }

                if (noose.getIncorrectGuesses() == 4){
                    System.out.println("* Hint: The word contains the letter " + hangWord.getHint());
                }

                if (!hangWord.checkChar(c)){
                    noose.incrementIncorrectGuesses();
                }
            }

            if (noose.isComplete()){
                Noose.displayLossAnimation();
                System.out.println("The word was: " + word);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie){
                    System.out.println(ie.getMessage());
                }
            }

            if (hangWord.isComplete()){
                displayGrid();
                System.out.println("The word is: " + StringColorer.color(word, "green"));
                System.out.println("You win! :D");
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie){
                System.out.println(ie.getMessage());
            }
            System.out.println("Play again?");
            if (!BoardGameApp.getYesNo(players)){
                break;
            }
        }
    }

    public char getPlayerInput(){
        // System.out.println("Enter a letter"); // might remove if therse no need
        String answer = input.nextLine().trim();
        if (answer.isEmpty()){
            return getPlayerInput();
        }
        char c = answer.charAt(0);

        if (answer.length() == 1){
          if (!HangmanChar.ALLOWED_CHARS.contains(c)){
            System.out.println("Invalid input: " + c + ".Please enter a letter from the alphabet");
            getPlayerInput();
            }
        } else if (answer.length() > 1){
            if (answer.equalsIgnoreCase(word)){
                hangWord.reveal();
            }
        }
        return c;
    }

    
    
    // public char getPlayerInput(){
    //     System.out.println("Enter a letter"); // might remove if therse no need
    //     char c = input.next().charAt(0);
    //     // TODO: add method to check for full word in case the user enters a full one
    //     // TODO: but dont penalize in case its wrong cause it mightve been entered by mistake
    //     if (!HangmanChar.ALLOWED_CHARS.contains(c)){
    //         System.out.println("Invalid input: " + c + ".Please enter a letter from the alphabet");
    //         getPlayerInput();
    //     }
    //     return c;
    // }
    
    
    @Override
    public boolean checkWin() {
        return hangWord.isComplete();
    }

    @Override
    public void displayGrid() {
        System.out.println(hangWord);
    }
    
    @Override
    public String getMessage(String message) {
        if (message.equals("full")){
            return "Word is already complete";
        }
        if (message.equals("lost")){
            return "You have already lost";
        }
        return "Invalid message code";
    }

    // unused methods left in due to inheritence
    // refactor AbstractGame class
    // make grid games run on a seperate game logic
    @Override
    public boolean update(int move) {return false;}
    @Override
    public boolean isDraw() {return false;}
    @Override
    public int getPlayerInput(Player player) {return 0;}
    

    public static void main(String[] args) {
        Designer.clearTerminal();
        System.out.println("Welcome to Hangman! :D");
        try {
            Thread.sleep(300);
        } catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }
        Hangman game = new Hangman(null);
        game.startGame();
    }
}
