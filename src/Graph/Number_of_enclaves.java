/*
You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
https://leetcode.com/problems/number-of-enclaves/
*/

public int numEnclaves(int[][] grid) {
    int m=grid.length, n=grid[0].length, enclaves=0;
    for(int i=0;i<n;i++) {
        if(grid[0][i]==1)
            dfs(grid, 0, i, m, n);
        if(grid[m-1][i]==1)
            dfs(grid, m-1, i, m, n);
    }
    for(int i=0;i<m;i++) {
        if(grid[i][0]==1)
            dfs(grid, i, 0, m, n);
        if(grid[i][n-1]==1)
            dfs(grid, i, n-1, m, n);
    }
    for(int i=0;i<m*n;i++)
        enclaves+=grid[i/n][i%n];
    return enclaves;
}
private void dfs(int[][] grid, int i, int j, int m, int n) {
    if(i<0 || j<0 || i>=m || j>=n || grid[i][j]==0)
        return;
    grid[i][j]=0;
    dfs(grid, i+1, j, m, n);
    dfs(grid, i-1, j, m, n);
    dfs(grid, i, j+1, m, n);
    dfs(grid, i, j-1, m, n);
}
void main() {
    int[][] grid={{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
    int enclaves=numEnclaves(grid);
    IO.println(enclaves);
}