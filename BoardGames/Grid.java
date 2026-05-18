package BoardGames;

public class Grid {
    char[][] board;

    public Grid(int rows, int cols) {
        board = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '.';
            }
        }
    }

    public void display(){
        for (char[] row : board){
            for (char cell : row){
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public boolean setItem(int row, int col, char item){
        if (board[row][col] == '.' || board[row][col] == ' '){
            board[row][col] = item;
            return true;
        }
        return false;
    }

    public char getItem(int row, int col){
        return board[row][col];
    }

    public int getRows(){
        return board.length;
    }

    public int getColumns(){
        return board[0].length;
    }

    public boolean isFull(){
        for (char[] row : board) {
            for (char cell : row) {
                if (cell == '.'){
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getBoard(){
        return this.board;
    }

    
}
