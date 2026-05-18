package BoardGames;

import java.util.ArrayList;
import java.util.LinkedHashMap;

// TODO: adjust everything to use StringBuilder
public class Designer {
    @SuppressWarnings("unused")
    private static final String[] DESIGN_SYMBOLS = { "─", "│", "┌", "┐", "└", "┘", "┬", "┴", "├", "┤", "┼" };

    private Designer() {

    }

    public static String getWinningMessage(String playerName) {
        // System.out.println("The winner is: ");
        playerName += " wins! :D";

        String celebrationCard = putInBox(playerName);

        return celebrationCard;
    }

    public static String putInBox(String string) {
        int length = string.length();
        String boxed = "";
        String cardWidth = "";
        for (int i = 0; i < length + 2; i++) {
            cardWidth += "─";
        }

        boxed += "┌" + cardWidth + "┐\n";
        boxed += "│ " + string + " │\n";
        boxed += "└" + cardWidth + "┘";

        return boxed;
    } // {"─", "│", "┌", "┐", "└" , "┘", "┬", "┴", "├", "┤", "┼" };

    public static String putInBoxColored(String coloredString) {
        int length = coloredString.length(); // TODO: make compatible with several colors
        String boxed = "";
        String cardWidth = "";
        for (int i = 0; i < length - 7; i++) {
            cardWidth += "─";
        }

        boxed += "┌" + cardWidth + "┐\n";
        boxed += "│ " + coloredString + " │\n";
        boxed += "└" + cardWidth + "┘";

        return boxed;
    }

    public static void displayMenu(String title, ArrayList<String> games) {
        String top = "│ " + color("--> " + title + " <--", "cyan") + " │";

        String line = "";
        for (int i = 0; i < title.length() + 10; i++) {
            line += "─";
        }
        System.out.println("┌" + line + "┐");
        System.out.println(top);
        System.out.println("├" + line + "┤");

        for (int i = 0; i < games.size(); i++) {
            if (i < 9){
                System.out.println("│ " + color((i + 1) + ": ", "purple") + padName(games.get(i), 16) + " │");
            } else {
                System.out.println("│ " + color((i + 1) + ": ", "purple") + padName(games.get(i), 15) + " │");
            }
        }

        System.out.println("└" + line + "┘");
    }

    public static void displayDifficultySelect(String Difficulty, ArrayList<String> difficulties){
        String top = "│ " + color("--> " + "Difficulty" + " <--", "cyan") + " │";

        String line = "";
        for (int i = 0; i < "Difficulty".length() + 10; i++) {
            line += "─";
        }
        System.out.println("┌" + line + "┐");
        System.out.println(top);
        System.out.println("├" + line + "┤");

        for (int i = 0; i < difficulties.size(); i++) {
            if (i < 10){
                System.out.println("│ " + color((i + 1) + ": ", "purple") + padName(difficulties.get(i), 15) + " │");
            } else {
                System.out.println("│ " + color((i + 1) + ": ", "purple") + padName(difficulties.get(i), 14) + " │");
            }
        }

        System.out.println("└" + line + "┘");
    }

    public static String getRankingTable(LinkedHashMap<String, Integer> map) {
        String leaderboard = ""; // TODO: account for draws in case several players have the same score

        ArrayList<String> names = new ArrayList<>();
        names.addAll(map.keySet());
        ArrayList<Integer> scores = new ArrayList<>();
        scores.addAll(map.values());

        int maxWidth = Leaderboard.getMaxNameLength(names);

        padNames(names, maxWidth);

        String topBorder = "";
        for (int i = 0; i < maxWidth + 17; i++) {
            topBorder += "─";
        }

        String namePadding = "";
        for (int i = 0; i < maxWidth - "Name".length(); i++) {
            namePadding += " "; // TODO: configure with padName method
        }

        String topLayer = "┌" + topBorder + "┐";
        String titleLayer = "│ Rank │ Name" + namePadding + " │ Score │";
        String midLayer = "├" + topBorder + "┤";
        String bottomLayer = "└" + topBorder + "┘";

        topLayer = adjustTopBorder(topLayer, titleLayer);
        bottomLayer = adjustBottomBorder(bottomLayer, titleLayer);
        midLayer = adjustMidBorder(midLayer, titleLayer, bottomLayer);

        titleLayer = "│ " + StringColorer.color("Rank", "purple") + " │ " + StringColorer.color("Name", "purple")
                + namePadding + " │ " + StringColorer.color("Score", "purple") + " │";
        leaderboard += topLayer + "\n";
        leaderboard += titleLayer + "\n";
        leaderboard += midLayer + "\n";

        for (int i = 0; i < names.size(); i++) {
            if (i == 0) {
                // String color = Colors.YELLOW;
                // leaderboard += "│ " + color(String.format("%4d", (i + 1)), color) + " │ " +
                // color(names.get(i), color) + " │ "
                // + color(String.format("%5d", scores.get(i)), color) + " │\n";
                leaderboard += "│ " + color(String.format("%4d", (i + 1)), "yellow")
                        + " │ " + color(names.get(i), "yellow")
                        + " │ " + color(String.format("%5d", scores.get(i)), "yellow") + " │\n";
            } else {
                leaderboard += "│ " + String.format("%4d", (i + 1)) + " │ " + names.get(i) + " │ "
                        + String.format("%5d", scores.get(i)) + " │\n";
            }
        }

        leaderboard += bottomLayer;

        return leaderboard;
    }

    private static String adjustTopBorder(String toAdjust, String rightBelow) {
        String adjustedBorder = "";

        if (toAdjust.length() != rightBelow.length()) {
            return "Error";
        }

        for (int i = 0; i < toAdjust.length(); i++) {
            if (toAdjust.charAt(i) == '─' && rightBelow.charAt(i) == '│') {
                adjustedBorder += '┬';
            } else {
                adjustedBorder += toAdjust.charAt(i);
            }
        }

        return adjustedBorder;
    }

    private static String adjustMidBorder(String toAdjust, String rightAbove, String rightBelow) {
        String adjustedBorder = "";

        if (toAdjust.length() != rightAbove.length() && toAdjust.length() != rightBelow.length()) {
            return "Error";
        }

        for (int i = 0; i < toAdjust.length(); i++) {
            if (toAdjust.charAt(i) == '─' && (rightAbove.charAt(i) == '│' || rightAbove.charAt(i) == '┬')
                    && (rightBelow.charAt(i) == '│' || rightBelow.charAt(i) == '┴')) {
                adjustedBorder += '┼';
            } else { // TODO: account for all cases
                adjustedBorder += toAdjust.charAt(i);
            }
        }

        return adjustedBorder;
    }

    private static String adjustBottomBorder(String toAdjust, String rightAbove) {
        String adjustedBorder = "";

        if (toAdjust.length() != rightAbove.length()) {
            return "Error";
        }

        for (int i = 0; i < toAdjust.length(); i++) {
            if (toAdjust.charAt(i) == '─' && rightAbove.charAt(i) == '│') {
                adjustedBorder += '┴';
            } else {
                adjustedBorder += toAdjust.charAt(i);
            }
        }

        return adjustedBorder;
    }

    private static void padNames(ArrayList<String> names, int maxWidth) {
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            String paddedName = padName(name, maxWidth);
            names.set(i, paddedName);
        }
    }

    private static String padName(String name, int maxWidth) {
        String namePadding = "";
        for (int j = 0; j < maxWidth - name.length(); j++) {
            namePadding += " ";
        }
        return name + namePadding;
    }

    // TODO: display top player better
    public static String getTopPlayerCard(Player topPlayer) {
        String card = "";
        String top = "";
        String mid1 = "│ Top Player: " + topPlayer.getName();
        String mid2 = "│ Score: " + topPlayer.getScore();
        int maxWidth = Math.max(mid1.length(), mid2.length());
        mid1 = padName(mid1, maxWidth) + " │";
        mid2 = padName(mid2, maxWidth) + " │";

        for (int i = 0; i < maxWidth; i++) {
            top += "─";
        }

        card += "┌" + top + "┐\n";
        card += mid1 + "\n";
        card += mid2 + "\n";
        card += "└" + top + "┘\n";

        return card;
    }

    private static String color(String string, String color) {
        return StringColorer.color(string, color);
    }

    public static void clearTerminal(){
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Unable to clear terminal.");
            e.printStackTrace();
        }
    }

    public static void clearLastLine() {
        // Move the cursor up by one line
        System.out.print("\033[A");
        // Clear the current line
        System.out.print("\033[K");
        System.out.flush();
    }

    public static void clearLastXLines(int lines){
        for (int i = 0; i < lines; i++) {
            clearLastLine();
        }
    }
}
