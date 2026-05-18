package BoardGames;

import java.util.ArrayList;
import java.util.List;

public class HangmanChar {
    public static final List<Character> ALLOWED_CHARS;
    static {
        List<Character> tempList = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            tempList.add(c);
            tempList.add(Character.toUpperCase(c));
        }
        ALLOWED_CHARS = List.copyOf(tempList);
    }

    char letter;
    boolean isVisible;

    public HangmanChar(char letter) {
        this.letter = letter;
        this.isVisible = false;
        if (!ALLOWED_CHARS.contains(letter)) {
            this.isVisible = true;
        }
    }

    public static ArrayList<HangmanChar> getCharList(char[] chars) {
        ArrayList<HangmanChar> charList = new ArrayList<>();
        for (char c : chars) {
            charList.add(new HangmanChar(c));
        }
        return charList;
    }

    public static ArrayList<HangmanChar> getCharList(String word) {
        char[] chars = word.toCharArray();
        return getCharList(chars);
    }

    public char getChar() {
        return this.letter;
    }

    @Override
    public String toString() {
        if (isVisible) {
            return letter + "";
        }
        return "_";
    }

}
