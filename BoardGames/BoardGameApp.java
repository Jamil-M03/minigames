package BoardGames;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;

// Always assume the user is a monkey with a keyboard
public class BoardGameApp {
    public static final Scanner input = new Scanner(System.in);
    public static ArrayList<String> games = new ArrayList<>(
            Arrays.asList("Connect Three", "Connect Four", "TicTacToe", "Hangman"));

    public static void main(String[] args) throws InterruptedException {

        // TODO: game intro animation
        while (true) {
            Designer.clearTerminal();
            Designer.displayMenu("Game Select", games);
            int gameNumber = selectGame();

            String gameName = getGameName(gameNumber);
            System.out.println("Welcome to " + gameName + "! :D");
            
            // TODO: remove when fixed
            int playerCount = 0;
            Player[] players = null;
            if (!games.get(gameNumber - 1).contains("TODO")){
                playerCount = getPlayerCount(gameNumber);
                if (gameNumber != 3){
                    Designer.clearLastXLines(2);
                }
                players = getPlayers(playerCount);
                Designer.clearLastXLines(2);
            }
            
            if (playerCount == 0 || players == null) {
                Thread.sleep(1000);
                continue;
            }

            char[] discs = getDiscs(playerCount);
            // TODO: find better way to do checks for quitting and returning to main menu
            // TODO: add a help command
            // TODO: find a better way to start the game
            AbstractGame game = null;
            while (true) {
                // game = GameFactory.createGame(gameNumber, players, discs);
                // String winner = "";
                if (gameNumber == 1) {
                    game = new ConnectThree(players, discs);
                }

                if (gameNumber == 2) {
                    game = new ConnectFour(players, discs);
                }

                if (gameNumber == 3) {
                    game = new TicTacToe(players, discs);
                }
                if (gameNumber == 4){
                    game = new Hangman(players);
                }
                game.startGame();

                Thread.sleep(700);
                if (game.quitGame){
                    break;
                }
                if (!playAgain(players)) {
                    Designer.clearTerminal();
                    System.out.println(color("Final Leaderboard: ", "green"));
                    // TODO: display advanced stats like: win% total games played
                    Leaderboard.display(players);
                    Thread.sleep(500);
                    System.out.println(color("Final Winner: ", "yellow"));
                    System.out.println(Designer.getTopPlayerCard(Leaderboard.getTopPlayer(players)));
                    System.out.println("Press Enter to continue");
                    pressAnyButtonToContinue();
                    Designer.clearTerminal();
                    Thread.sleep(100);
                    break;
                }
            }
        }

    }

    public static void pressAnyButtonToContinue(){
        String answer = input.nextLine();
        if (answer.equalsIgnoreCase("quit")) {
            quit();
        }
    }

    public static void startGame(int gameNumber, Player[] players, char[] discs) {
        if (gameNumber == 1) {
            ConnectThree game = new ConnectThree(players, discs);
            game.startGame();
        }

        if (gameNumber == 2) {
            ConnectFour game = new ConnectFour(players, discs);
            game.startGame();
        }

        if (gameNumber == 3) {
            TicTacToe game = new TicTacToe(players, discs);
            game.startGame();
        }
    }

    public static int selectGame() {
        int select;
        while (true) {
            try {
                select = input.nextInt();
                if (select < 1 || select > games.size()) {
                    System.out.println("Please input an integer (1-" + games.size() + ")");
                    continue;
                }
                input.nextLine();
                break;
            } catch (InputMismatchException ime) {
                String answer = input.next();
                if (answer.equalsIgnoreCase("quit")) {
                    quit();
                }
                if (answer.equalsIgnoreCase("menu")) {
                    System.out.println("You're already on the main menu silly");
                } else {
                    invalidInput(answer);
                }
                input.nextLine();
            }
        }
        return select;
    }

    public static String getGameName(int gameNumber) {
        // return games.get(gameNumber);
        if (gameNumber == 1) {
            return ConnectThree.gameName;
        }
        if (gameNumber == 2) {
            return ConnectFour.gameName;
        }
        if (gameNumber == 3) {
            return TicTacToe.gameName;
        }
        if (gameNumber == 4){
            return Hangman.gameName;
        }
        return "void. This game is coming soon";
    }

    public static int getPlayerCount(int gameNumber) {
        switch (gameNumber) {
            case 1:
                return getPlayerCount(ConnectThree.minPlayerCount, ConnectThree.getMaxPlayers(), ConnectThree.gameName);
            
            case 2:
                return getPlayerCount(ConnectFour.minPlayerCount, ConnectFour.maxPlayerCount, ConnectFour.gameName);

            case 3:
                return getPlayerCount(TicTacToe.minPlayerCount, TicTacToe.maxPlayerCount, TicTacToe.gameName);

            case 4:
                return getPlayerCount(Hangman.minPlayerCount, Hangman.maxPlayerCount, Hangman.gameName);
            
            default:
                return -1;
        }
    }

    public static int getPlayerCount(int minPlayers, int maxPlayers, String gameName) {
        int count;
        System.out.println("Enter the number of players: ");
        while (true) {
            try {
                count = input.nextInt();
                if (count < minPlayers) {
                    System.out.println(gameName + " must have at least " + minPlayers + " player(s)");
                    continue;
                }
                if (count > maxPlayers) {
                    System.out.println("Max number of players in " + gameName + " is " + maxPlayers);
                    continue;
                }
                input.nextLine();
                break;
            } catch (InputMismatchException ime) {
                String answer = input.next();
                if (answer.equalsIgnoreCase("menu") || answer.contains("menu")) {
                    return 0;
                }
                if (answer.equalsIgnoreCase("quit")) {
                    quit();
                } // TODO: make into its own method

                invalidInput(answer);
                input.nextLine();
            }
        }
        return count;
    }

    public static boolean isAI(String playerName) {
        if (playerName.replaceAll(" ", "").equalsIgnoreCase("ai")) {
            return true;
        }

        while (true) {
            System.out.println("Is " + playerName + " a human or ai?");
            String answer = input.next();
            input.nextLine();
            if (answer.equalsIgnoreCase("human")) {
                return false;
            }
            if (answer.equalsIgnoreCase("ai")) {
                return true;
            }
            if (answer.equalsIgnoreCase("quit")) {
                quit();
            }
            invalidInput(answer);
        }
    }

    public static char[] getDiscs(int playerCount) {
        char[] options = { 'X', 'O', '@', '*', '#', '^' };
        char[] discs = new char[playerCount];

        for (int i = 0; i < discs.length; i++) {
            discs[i] = options[i];
        }
        return discs;
    }

    public static Player[] getPlayers(int playerCount) {
        Player[] players = new Player[playerCount];
        HashSet<String> names = new HashSet<>();

        for (int i = 0; i < playerCount; i++) {
            System.out.println("Enter player " + (i + 1) + "'s name.");
            String playerName = input.nextLine().trim();// .replaceAll(" ", "")

            if (names.contains(playerName)) {
                System.out.println("Player already exists");
                i--;
                continue;
            }
            if (playerName.trim().equalsIgnoreCase("menu")) {
                return null;
            }
            if (playerName.trim().equalsIgnoreCase("quit")) {
                quit();
            }
            names.add(playerName);

            boolean isAI = isAI(playerName);
            if (isAI) {
                players[i] = new AIPlayer(playerName);
            } else {
                players[i] = new HumanPlayer(playerName);
            }

        }

        return players;
    }

    public static boolean playAgain(Player[] players) {
        System.out.println("Play again?");
        return getYesNo(players);
    }

    public static void quit() {
        // Designer.clearTerminal();
        System.out.println(Designer.putInBoxColored(color("--> Goodbye :( <--", "red")));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ie){}
        Designer.clearTerminal();
        System.exit(0);
    }

    public static boolean getYesNo(Player[] players) {
        while (true) {
            String answer = input.nextLine();
            if (answer.equalsIgnoreCase("yes") || answer.toLowerCase().contains("yes")
                    || answer.equalsIgnoreCase("y")) {
                return true;
            } else if (answer.equalsIgnoreCase("no") || answer.toLowerCase().contains("no")
                    || answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("menu")) {
                return false;
            } else if (answer.equalsIgnoreCase("quit")) {
                quit();
            } else if (answer.equalsIgnoreCase("leaderboard")) {
                System.out.println("Leaderboard: ");
                Leaderboard.display(players);
                System.out.println("Play again?");
                return getYesNo(players);
            }
            invalidInput(answer);
            System.out.println("Please answer with (yes/no)");
        }
    }

    private static String color(String string, String color){
        return StringColorer.color(string, color);
    }

    public static void invalidInput(String answer){
        System.out.println("Invalid input: " + answer);
        
        try {
            Thread.sleep(700);
        } catch (InterruptedException ie){
            System.out.println(ie.getMessage());
        }

        Designer.clearLastXLines(2);
    }

}