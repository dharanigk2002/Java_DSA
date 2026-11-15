/*
Twisted Mirror Path Count

Given an m x n binary grid grid where:

grid[i][j] == 0 represents an empty cell, and
grid[i][j] == 1 represents a mirror.
A robot starts at the top-left corner of the grid (0, 0) and wants to reach the bottom-right corner (m - 1, n - 1). It can move only right or down. If the robot attempts to move into a mirror cell, it is reflected before entering that cell:

If it tries to move right into a mirror, it is turned down and moved into the cell directly below the mirror.
If it tries to move down into a mirror, it is turned right and moved into the cell directly to the right of the mirror.
If this reflection would cause the robot to move outside the grid boundaries, the path is considered invalid and should not be counted.

Return the number of unique valid paths from (0, 0) to (m - 1, n - 1).

Since the answer may be very large, return it modulo 109 + 7.

Note: If a reflection moves the robot into a mirror cell, the robot is immediately reflected again based on the direction it used to enter that mirror: if it entered while moving right, it will be turned down; if it entered while moving down, it will be turned right. This process will continue until either the last cell is reached, the robot moves out of bounds or the robot moves to a non-mirror cell.

https://leetcode.com/problems/twisted-mirror-path-count/description/
*/

private Integer[][][] dp;
private static final int MOD=1_000_000_007;
private int f(int[][] grid, int i, int j, int up) {
    int m=grid.length, n=grid[0].length;
    if(i<0 || j<0 || i==m || j==n)
        return 0;
    if(dp[i][j][up]!=null)
        return dp[i][j][up];
    if(i==m-1 && j==n-1)
        return dp[i][j][up]=1;
    if(grid[i][j]==1)
        if(up==1)
            return f(grid, i, j+1, 0);
        else
            return f(grid, i+1, j, 1);
    return dp[i][j][up]=(int)(((long)f(grid, i, j+1, 0)+f(grid, i+1, j, 1))%MOD);
}

public int uniquePaths(int[][] grid) {
    dp=new Integer[grid.length][grid[0].length][2];
    return f(grid, 0, 0, 0);
}

void main() {
    int[][] grid = new int[][]{{0, 1, 0}, {0, 0, 1}, {1, 0, 0}};
    IO.println(uniquePaths(grid));
}