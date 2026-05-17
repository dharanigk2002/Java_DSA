/*
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

https://leetcode.com/problems/number-of-islands/description
* */

private final int[][] dirs={{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
public int numIslands(char[][] grid) {
    int n=grid.length, m=grid[0].length, count=0;
    Queue<int[]> q=new LinkedList();
    for(int i=0;i<n;i++)
        for(int j=0;j<m;j++)
            if(grid[i][j]=='1') {
                count++;
                dfs(grid, i, j, n, m);
            }
    return count;
}
private void dfs(char[][] grid, int i, int j, int n, int m) {
    if(i<0 || i>=n || j<0 || j>=m || grid[i][j]=='0')
        return;
    grid[i][j]='0';
    for(int[] d:dirs)
        dfs(grid, i+d[0], j+d[1], n, m);
}
void main() {
    char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
    };
    int islands=numIslands(grid);
    IO.println(islands);
}