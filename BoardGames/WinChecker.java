package BoardGames;

public class WinChecker {
    private WinChecker(){

    }

    protected static boolean checkThreeInARow(Grid grid, char disc){
        int rows = grid.getRows();
        int cols = grid.getColumns();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (grid.getItem(row, col) != disc) {
                    continue;
                }
                
                if (col + 2 < cols
                        && grid.getItem(row, col + 1) == disc
                        && grid.getItem(row, col + 2) == disc) {
                        return true;
                }

                if (row + 2 < rows
                        && grid.getItem(row + 1, col) == disc
                        && grid.getItem(row + 2, col) == disc) {
                    return true;
                }

                if (row + 2 < rows && col + 2 < cols
                        && grid.getItem(row + 1, col + 1) == disc
                        && grid.getItem(row + 2, col + 2) == disc) {
                    return true;
                }

                if (row + 2 < rows && col - 2 >= 0
                        && grid.getItem(row + 1, col - 1) == disc
                        && grid.getItem(row + 2, col - 2) == disc) {
                    return true;
                }

            }
        }

        return false;
    }

    public static boolean checkFourInARow(Grid grid, char disc){
        int rows = grid.getRows();
        int cols = grid.getColumns();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (grid.getItem(row, col) != disc) {
                    continue;
                }

                if (col + 3 < cols
                        && grid.getItem(row, col + 1) == disc
                        && grid.getItem(row, col + 2) == disc
                        && grid.getItem(row, col + 3) == disc) {
                        return true;
                }

                if (row + 3 < rows
                        && grid.getItem(row + 1, col) == disc
                        && grid.getItem(row + 2, col) == disc
                        && grid.getItem(row + 3, col) == disc) {
                    return true;
                }

                if (row + 3 < rows && col + 3 < cols
                        && grid.getItem(row + 1, col + 1) == disc
                        && grid.getItem(row + 2, col + 2) == disc
                        && grid.getItem(row + 3, col + 3) == disc) {
                    return true;
                }

                if (row + 3 < rows && col - 3 >= 0
                        && grid.getItem(row + 1, col - 1) == disc
                        && grid.getItem(row + 2, col - 2) == disc
                        && grid.getItem(row + 3, col - 3) == disc) {
                    return true;
                }

            }
        }

        return false;
    }

}
