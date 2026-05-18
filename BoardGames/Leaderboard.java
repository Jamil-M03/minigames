package BoardGames;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Leaderboard {
    private Leaderboard(){

    }

    public static String getLeaderboard(Player[] players){
        LinkedHashMap<String, Integer> leaderboard = getPlayerScoreMap(players);
        sortMap(leaderboard);

        return Designer.getRankingTable(leaderboard);
    }
    
    protected static int getMaxNameLength(Player[] players){
        int maxNameLength = 5;
        for (Player p : players){
            if (p.getName().length() > maxNameLength){
                maxNameLength = p.getName().length();
            }
        }

        return maxNameLength;
    }

    protected static int getMaxNameLength(ArrayList<String> names){
        int maxNameLength = 5;
        for (String name : names){
            if (name.length() > maxNameLength){
                maxNameLength = name.length();
            }
        }

        return maxNameLength;
    }

    private static LinkedHashMap<String,Integer> getPlayerScoreMap(Player[] players){
        LinkedHashMap<String, Integer> playerScoreMap = new LinkedHashMap<>();

        for (Player p : players){
            playerScoreMap.put(p.getName(), p.getScore());
        }

        return playerScoreMap;
    }

    private static LinkedHashMap<String, Integer> sortMap(LinkedHashMap<String, Integer> map){
        // TODO: sort linked hashmap
        // Sort entries by value and collect back into the same LinkedHashMap
        // Sort entries by value in descending order and collect into a list
        List<Map.Entry<String, Integer>> sortedEntries = map.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Sort by value descending
                .collect(Collectors.toList());

        // Clear the original map and reinsert entries in sorted order
        map.clear();
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }


    public static void display(Player[] players) {
        System.out.println(getLeaderboard(players));
    }

    public static Player getTopPlayer(Player[] players){
        LinkedHashMap<String, Integer> leaderboard = sortMap(getPlayerScoreMap(players));
        String winnerName = (String)leaderboard.keySet().toArray()[0];
        Player topPlayer = null;

        for (Player p : players){
            if (p.getName().equals(winnerName)){
                topPlayer = p;
            }
        }

        return topPlayer;
    }

    public static void main(String[] args) {
        Player[] players = {new HumanPlayer("Player 1"), new HumanPlayer("Player 2"), new AIPlayer("AI")};
        Random rand = new Random();

        for (int i = 0; i < 20; i++) {
            int index = rand.nextInt(players.length);
            players[index].updateScore();
        }

        display(players);
        
        System.out.println(getTopPlayer(players).getName());
        System.out.println(Designer.getTopPlayerCard(getTopPlayer(players)));
        System.out.println(StringColorer.color("Hello", "blue") + "\n" + StringColorer.color("test", "purple"));
    }

}
