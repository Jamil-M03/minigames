package BoardGames;

public abstract class AbstractGame {
    Player[] players;
    int currentPlayerIndex;
    public boolean quitGame = false;

    public AbstractGame(Player[] players) {
        this.players = players;
        currentPlayerIndex = 0;
    }

    public void startGame() {
        while (true) {
            Player currentPlayer = players[currentPlayerIndex];
            if (quitGame){
                break;
            }
            System.out.println(currentPlayer.getName() + "'s turn");

            while (true) {
                int playerInput = getPlayerInput(currentPlayer);
                if (goToMenu(playerInput) && confirm()) {
                    quitGame = true;
                    break;
                }
                if (update(playerInput)) {
                    break;
                } else if (currentPlayer instanceof HumanPlayer) {
                    System.out.println("full");
                }
            }

            if (checkWin()) {
                // System.out.println(currentPlayer.getName() + " wins!");
                System.out.println(Designer.getWinningMessage(currentPlayer.getName()));
                currentPlayer.updateScore();
                System.out.println("\nLeaderboard: ");
                System.out.println(Leaderboard.getLeaderboard(players));
                break;
            }

            if (isDraw()) {
                System.out.println(Designer.putInBox("It's a draw!"));
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            // Designer.clearTerminal();
        }
    }

    // public abstract void instruct();

    // public static abstract int getMaxPlayers();

    public boolean goToMenu(int userInput) {
        return userInput == -69;
    }

    public boolean confirm() {
        System.out.println("Are you sure you want to quit the game?");
        return BoardGameApp.getYesNo(players);
    }

    public Player getWinner() {
        if (checkWin()) {
            return players[currentPlayerIndex];
        }
        return null; // TODO: fix
    }

    public abstract String getMessage(String message);

    public abstract int getPlayerInput(Player player);

    public abstract boolean update(int move);

    public abstract boolean checkWin();

    public abstract boolean isDraw();

    public abstract void displayGrid();
}
