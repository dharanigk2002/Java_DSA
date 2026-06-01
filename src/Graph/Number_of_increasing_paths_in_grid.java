/*
You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.

Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell. Since the answer may be very large, return it modulo 109 + 7.

Two paths are considered different if they do not have exactly the same sequence of visited cells.

https://leetcode.com/problems/number-of-increasing-paths-in-a-grid/description/
*/

private int[][] grid;
private int m, n;
private final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
private Integer[][] dp;
private final int MOD = 1_000_000_007;
public int countPaths(int[][] grid) {
    this.grid=grid;
    m=grid.length;
    n=grid[0].length;
    long ans=0;
    dp=new Integer[m][n];
    for(int i=0;i<m;i++)
        for(int j=0;j<n;j++)
            ans=(ans+dfs(i, j))%MOD;
    return (int)ans;
}
private int dfs(int i, int j) {
    if(dp[i][j]!=null)
        return dp[i][j];
    long count=1;
    for(int[] d:DIRS) {
        int nr=i+d[0], nc=j+d[1];
        if(nr<0 || nc<0 || nr>=m || nc>=n || grid[nr][nc]<=grid[i][j]) continue;
        count=(count+dfs(nr, nc))%MOD;
    }
    return dp[i][j]=(int)count;
}

void main() {
    int[][] grid={{1, 1}, {3, 4}};
    IO.println(countPaths(grid));
}