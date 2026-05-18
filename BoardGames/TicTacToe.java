package BoardGames;

public class TicTacToe extends AbstractGame {
    static final int minPlayerCount = 2;
    static final int maxPlayerCount = 2;
    static final String gameName = "TicTacToe";

    Grid grid;
    private char[] discs;

    public TicTacToe(Player[] players, char[] discs) {
        super(players);
        this.discs = discs;
        this.grid = new Grid(3, 3);
        if (players.length > 2){
            System.out.println("Maximum number of players is 2");
            return;
        }
        displayGrid();
    }

    @Override
    public int getPlayerInput(Player player) {
        return player.getInput(grid.getRows() * grid.getColumns());
    }

    @Override
    public boolean update(int move) {
        int row = move / grid.getRows();
        int col = move % grid.getColumns();
        if (grid.setItem(row, col, discs[currentPlayerIndex])){
            Designer.clearTerminal();
            displayGrid();
            return true;
        }
        return false;
    }

    @Override
    public boolean checkWin() {
        char disc = discs[currentPlayerIndex];
        return WinChecker.checkThreeInARow(grid, disc);
    }
    // private static boolean checkWinner(char[] board, char player) {
    // int[][] winningCombos = {
    // {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
    // {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
    // {0, 4, 8}, {2, 4, 6} // Diagonals
    // };
    // for (int[] combo : winningCombos) {
    // if (board[combo[0]] == player && board[combo[1]] == player && board[combo[2]]
    // == player) {
    // return true;
    // }
    // }
    // return false;
    // }

    @Override
    public boolean isDraw() {
        return grid.isFull() && !checkWin();
    }


    public void displayGrid() {
        System.out.println();
        char[][] board = grid.getBoard();
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                char cell = board[i][j];
                if (cell == '.'){
                    cell = ' ';
                }

                System.out.print(" " + cell + " ");
                if (j < grid.getColumns() - 1) {
                    System.out.print("│");
                }

            }
            System.out.println();

            if (i < grid.getRows() - 1){
                System.out.println("───┼───┼───");
                //System.out.println("---|---|---");
            }
        }
        System.out.println();
    }

    @Override
    public String getMessage(String string){
        if (string.equals("full")){
            return "Cell is already occupied. Try again.";
        }
        return "Invalid message code";
    }

}
