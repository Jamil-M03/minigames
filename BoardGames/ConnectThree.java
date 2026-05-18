package BoardGames;

public class ConnectThree extends AbstractGame {
    static final int minPlayerCount = 2;
    static final int maxPlayerCount = 3;
    static final String gameName = "Connect Three";

    Grid grid;
    private char[] discs;

    public ConnectThree(Player[] players, char[] discs) {
        super(players);
        this.discs = discs;
        grid = new Grid(4, 4);
        displayGrid();
    }

    @Override
    public int getPlayerInput(Player player) {
        return player.getInput(grid.getColumns());
    }

    @Override
    public boolean update(int column) {
        for (int row = grid.getRows() - 1; row >= 0; row--) {
            if (grid.setItem(row, column, discs[currentPlayerIndex])) {
                Designer.clearTerminal();
                displayGrid();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkWin() {
        char disc = discs[currentPlayerIndex];
        return WinChecker.checkThreeInARow(grid, disc);
    }

    @Override
    public boolean isDraw() {
        return grid.isFull();
    }

    @Override
    public void displayGrid(){
        char[][] board = grid.getBoard();

        System.out.println();
        for (char[] row : board){
            for (char cell : row){
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    public static int getMaxPlayers(){
        return maxPlayerCount;
    }

    @Override
    public String getMessage(String string){
        if (string.equals("full")){
            return "Column is already full. Try again.";
        }
        return "Invalid message code";
    }

}
