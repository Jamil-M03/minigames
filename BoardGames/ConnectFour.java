package BoardGames;

public class ConnectFour extends AbstractGame {
    static final int minPlayerCount = 2;
    static final int maxPlayerCount = 6;
    static final String gameName = "Connect Four";
    Grid grid;
    private char[] discs;

    public ConnectFour(Player[] players, char[] discs) {
        super(players);
        this.discs = discs;
        grid = new Grid(6, 7);
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
        return WinChecker.checkFourInARow(grid, disc);
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

    @Override
    public String getMessage(String string){
        if (string.equals("full")){
            return "Column is already full. Try again.";
        }
        return "Invalid message code";
    }

}