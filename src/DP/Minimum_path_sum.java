/*
Minimum Path Sum

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

https://leetcode.com/problems/minimum-path-sum/description/
*/

private static final int MAX=80000;
private Integer[][] dp;
private int f(int[][] grid, int i, int j) {
    if(i<0 || j<0)
        return MAX;
    if(dp[i][j]!=null)
        return dp[i][j];
    if(i==0 && j==0)
        return dp[0][0]=grid[0][0];
    return dp[i][j]=Math.min(grid[i][j]+f(grid, i-1, j), grid[i][j]+f(grid, i, j-1));
}


public int minPathSum(int[][] grid) {
    int m=grid.length, n=grid[0].length;
    for(int j=n-2;j>=0;j--)
        grid[m-1][j]+=grid[m-1][j+1];
    for(int i=m-2;i>=0;i--)
        grid[i][n-1]+=grid[i+1][n-1];
    for(int i=m-2;i>=0;i--)
        for(int j=n-2;j>=0;j--)
            grid[i][j]+=Math.min(grid[i+1][j], grid[i][j+1]);
    return grid[0][0];
}

void main() {
    int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
    int m=grid.length, n=grid[0].length;
    dp=new Integer[m+1][n+1];
    IO.println(minPathSum(grid));
}