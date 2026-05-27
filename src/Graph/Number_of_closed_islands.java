/*
Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

https://leetcode.com/problems/number-of-closed-islands/description/
*/

int closedIsland(int[][] grid) {
    int n=grid.length, m=grid[0].length;
    int islands=0;
    for(int i=0;i<n;i++) {
        dfs(grid, i, 0);
        dfs(grid, i, m-1);
    }
    for(int j=0;j<m;j++) {
        dfs(grid, 0, j);
        dfs(grid, n-1, j);
    }
    for(int i=1;i<n-1;i++)
        for(int j=1;j<m-1;j++)
            if(grid[i][j]==0) {
                islands++;
                dfs(grid, i, j);
            }
    return islands;
}

private void dfs(int[][] grid, int i, int j) {
    int n=grid.length, m=grid[0].length;
    if(i<0 || j<0 || i>=n || j>=m || grid[i][j]==1)
        return;
    grid[i][j]=1;
    dfs(grid, i+1, j);
    dfs(grid, i-1, j);
    dfs(grid, i, j+1);
    dfs(grid, i, j-1);
}

void main() {
    int[][] grid = {
            {1, 1, 1, 1, 1, 1, 1, 0},
            {1, 0, 0 ,0, 0, 1, 1, 0},
            {1, 0, 1, 0, 1, 1, 1, 0},
            {1, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 0},
    };

    int closedIslands=closedIsland(grid);
    IO.println(closedIslands);
}