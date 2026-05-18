package BoardGames;

import java.util.ArrayList;
import java.util.Scanner;

public class HangmanWord {
    // set word as char[] and have a boolean for each char
    // method to display word
    // constructor takes in string
    // if a char is correct display all the repeated chars
    String word;
    ArrayList<HangmanChar> chars;

    public HangmanWord (String word){
        this.word = word;
        this.chars = HangmanChar.getCharList(word); // make hangmanchar object so that it can have a boolean as field
    }

    public boolean checkChar(char c){
        if (word.contains(Character.toString(c))){
            for (HangmanChar hchar : chars){
                if (hchar.getChar() == c){
                    if (hchar.isVisible){
                        return false;
                    }
                    hchar.isVisible = true;
                }
            }
            return true;
        }
        return false;
    }

    public boolean isComplete(){
        for (HangmanChar hChar : chars){
            if (!hChar.isVisible){
                return false;
            }
        }
        return true;
    }

    public char getHint(){
        for (HangmanChar hangChar : chars){
            if (!hangChar.isVisible){
                return hangChar.getChar();
            }
        }
        return ' ';
    }

    public void reveal(){
        for (HangmanChar hangChar : chars){
            hangChar.isVisible = true;
        }
    } // testing

    @Override
    public String toString(){
        String result = "";

        for (HangmanChar c : chars){
            result += c + " ";
        }
        
        return result;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("enter a string");
        String s = input.nextLine();
        HangmanWord hWord = new HangmanWord(s);

        while (!hWord.isComplete()){
            System.out.println(hWord);
            System.out.println("enter a letter to check");  
            char c = input.next().charAt(0);
            if (!hWord.checkChar(c)){ //check if char is alphabetical first
                System.out.println("not found");
            }
        }
        
        System.out.println(hWord);
        System.out.println("You win");
        input.close();
    }
}
