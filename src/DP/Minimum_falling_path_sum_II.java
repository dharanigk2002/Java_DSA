/*
Minimum Falling Path Sum II

Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.

A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements chosen in adjacent rows are in the same column.

https://leetcode.com/problems/minimum-falling-path-sum-ii/description/
*/

private Integer[][] dp;
private int n;
private int f(int[][] grid, int row, int prevCol) {
    if(dp[row][prevCol]!=null)
        return dp[row][prevCol];
    if(row==n)
        return dp[row][prevCol]=0;
    int min=Integer.MAX_VALUE/2;
    for(int col=0;col<n;col++)
        if(col!=prevCol)
            min=Math.min(min, grid[row][col]+f(grid, row+1, col));
    return dp[row][prevCol]=min;
}

public int minFallingPathSum(int[][] grid) {
    this.n=grid.length;
    dp=new Integer[n+1][n+1];
    return f(grid, 0, n);
}

public int bottomUp(int[][] grid) {
    this.n=grid.length;
    int[][] dp=new int[n+1][n+1];
    for(int row=n-1;row>=0;row--) {
        for(int prevCol=0;prevCol<=n;prevCol++) {
            int min=Integer.MAX_VALUE/2;
            for(int col=0;col<n;col++)
                if(col!=prevCol)
                    min=Math.min(min, grid[row][col]+dp[row+1][col]);
            dp[row][prevCol]=min;
        }
    }
    return dp[0][n];
}

public int bottomUpOptimized(int[][] grid) {
    this.n=grid.length;
    int[] dp=new int[n+1];
    for(int row=n-1;row>=0;row--) {
        int[] curr=new int[n+1];
        for(int prevCol=0;prevCol<=n;prevCol++) {
            int min=Integer.MAX_VALUE/2;
            for(int col=0;col<n;col++)
                if(col!=prevCol)
                    min=Math.min(min, grid[row][col]+dp[col]);
            curr[prevCol]=min;
        }
        dp=curr;
    }
    return dp[n];
}

void main() {
    int[][] grid = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    IO.println(minFallingPathSum(grid));
}