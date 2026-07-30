/*
Maximum Number of Fish in a Grid

You are given a 0-indexed 2D matrix grid of size m x n, where (r, c) represents:

A land cell if grid[r][c] = 0, or
A water cell containing grid[r][c] fish, if grid[r][c] > 0.
A fisher can start at any water cell (r, c) and can do the following operations any number of times:

Catch all the fish at cell (r, c), or
Move to any adjacent water cell.
Return the maximum number of fish the fisher can catch if he chooses his starting cell optimally, or 0 if no water cell exists.

An adjacent cell of the cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) or (r - 1, c) if it exists.

https://leetcode.com/problems/maximum-number-of-fish-in-a-grid/description/
*/

private static final int[][] DIRS={{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
public int findMaxFish(int[][] grid) {
    int m=grid.length, n=grid[0].length, maxFish=0;
    for(int i=0;i<m;i++)
        for(int j=0;j<n;j++)
            if(grid[i][j]>0)
                maxFish=Math.max(maxFish, dfs(grid, i, j));
    return maxFish;
}

private int dfs(int[][] grid, int i, int j) {
    int m=grid.length, n=grid[0].length;
    int fish=grid[i][j];
    grid[i][j]=0;
    for(int[] d:DIRS) {
        int nr=i+d[0], nc=j+d[1];
        if(nr<0 || nc<0 || nr==m || nc==n || grid[nr][nc]==0)
            continue;
        fish+=dfs(grid, nr, nc);
    }
    return fish;
}

void main() {
    int[][] grid = {{0,2,1,0},{4,0,0,3},{1,0,0,4},{0,3,2,0}};
    System.out.println(findMaxFish(grid));
}