package BoardGames;

import java.util.HashMap;
import java.util.Map;

public class GameFactory {
    private static final Map<Integer, String> gameRegistry = new HashMap<>();

    static {
        // Map game numbers to fully qualified class names
        gameRegistry.put(1, "BoardGames.ConnectThree");
        gameRegistry.put(2, "BoardGames.ConnectFour");
        gameRegistry.put(3, "BoardGames.TicTacToe");
    }

    public static AbstractGame createGame(int gameNumber, Player[] players, char[] discs) {
        String className = gameRegistry.get(gameNumber);

        if (className == null) {
            throw new IllegalArgumentException("Invalid game number: " + gameNumber);
        }

        try {
            // Use reflection to load the class and create an instance
            Class<?> clazz = Class.forName(className);
            return (AbstractGame) clazz.getConstructor(Player[].class, char[].class).newInstance(players, discs);
        } catch (Exception e) {
            throw new RuntimeException("Error creating game instance for: " + className, e);
        }
    }

    // Optional: Method to register a new game dynamically
    public static void registerGame(int gameNumber, String className) {
        gameRegistry.put(gameNumber, className);
    }
}
