/*
Given a grid of size n*n filled with 0, 1, 2, 3. Check whether there is a path possible from the source to destination. You can traverse up, down, right and left.
The description of cells is as follows:

A value of cell 1 means Source.
A value of cell 2 means Destination.
A value of cell 3 means Blank cell.
A value of cell 0 means Wall (blocked cell which we cannot traverse).
Note: There are only a single source and a single destination.

https://www.geeksforgeeks.org/problems/find-whether-path-exist5238/1
*/

private final int[][] dirs={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public boolean is_Possible(int[][] grid) {
    int m=grid.length, n=grid[0].length;
    for(int i=0;i<m*n;i++)
        if(grid[i/n][i%n]==1 && dfs(grid, i/n, i%n, m, n))
            return true;
    return false;
}

private boolean dfs(int[][] grid, int i, int j, int m, int n) {
    if(i<0 || j<0 || i>=m || j>=n || grid[i][j]==0)
        return false;
    if(grid[i][j]==2)
        return true;
    grid[i][j]=0;
    for(int[] d:dirs)
        if(dfs(grid, i+d[0], j+d[1], m, n))
            return true;
    return false;
}

void main() {
//    int[][] grid={
//            {3,0,3,0,0},
//            {3,0,0,0,3},
//            {3,3,3,3,3},
//            {0,2,3,0,0},
//            {3,0,0,1,3},
//    };
    int[][] grid={
            {1, 3},
            {3, 2},
    };
    IO.println(is_Possible(grid));
}