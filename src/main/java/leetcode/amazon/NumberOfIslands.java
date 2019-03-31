package leetcode.amazon;

class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int numberOfIslandFound = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid[row].length; column++) {
                // Create exception for starting coordinates
                if (grid[row][column] == '1') {
                    numberOfIslandFound++;
                    grid = destroyFoundIsland(grid, row, column);
                }
            }
        }
        return numberOfIslandFound;
    }

    private char[][] destroyFoundIsland(char[][] grid, int row, int column) {
        grid[row][column] = '0';
        // Check one cell up
        if (row > 0 &&  //Check for 1st row
                grid[row - 1][column] == '1') {
            grid = destroyFoundIsland(grid, row - 1, column);
        }
        // Check one cell down
        if (row < (grid.length - 1) &&  //Check for 1st row
                grid[row + 1][column] == '1') {
            grid = destroyFoundIsland(grid, row + 1, column);
        }
        // Check one cell left
        if (column > 0 && // Check for 1st column
                grid[row][column - 1] == '1') {
            grid = destroyFoundIsland(grid, row, column - 1);
        }
        // Check one cell right
        if (column < (grid[row].length - 1) && // Check for 1st column
                grid[row][column + 1] == '1') {
            grid = destroyFoundIsland(grid, row, column + 1);
        }
        return grid;
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        System.out.println(numberOfIslands.numIslands(grid));

        grid = new char[][] {{'1','1','1'},{'0','1','0'},{'1','1','1'}};
        System.out.println(numberOfIslands.numIslands(grid));

        grid = new char[][] {{'1','1','1'},{'0','1','0'},{'0','1','0'}};
        System.out.println(numberOfIslands.numIslands(grid));
    }
}
