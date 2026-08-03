/*
Check if There is a Valid Path in a Grid

You are given an m x n grid. Each cell of grid represents a street. The street of grid[i][j] can be:

1 which means a street connecting the left cell and the right cell.
2 which means a street connecting the upper cell and the lower cell.
3 which means a street connecting the left cell and the lower cell.
4 which means a street connecting the right cell and the lower cell.
5 which means a street connecting the left cell and the upper cell.
6 which means a street connecting the right cell and the upper cell.

You will initially start at the street of the upper-left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1). The path should only follow the streets.

Notice that you are not allowed to change any street.

Return true if there is a valid path in the grid or false otherwise.

https://leetcode.com/problems/check-if-there-is-a-valid-path-in-a-grid/description/
*/
private static final List<List<int[]>> DIRS=List.of(
        List.of(new int[]{0, -1}, new int[]{0, 1}),
        List.of(new int[]{-1, 0}, new int[]{1, 0}),
        List.of(new int[]{0, -1}, new int[]{1, 0}),
        List.of(new int[]{0, 1}, new int[]{1, 0}),
        List.of(new int[]{0, -1}, new int[]{-1, 0}),
        List.of(new int[]{0, 1}, new int[]{-1, 0})
);

public boolean hasValidPath(int[][] grid) {
    return dfs(grid, 0, 0, new boolean[grid.length][grid[0].length]);
}

private boolean dfs(int[][] grid, int i, int j, boolean[][] vis) {
    int m=grid.length, n=grid[0].length;
    if(i==m-1 && j==n-1)
        return true;
    vis[i][j]=true;
    int node=grid[i][j];
    for(int[] next:DIRS.get(node-1)) {
        int nr=i+next[0], nc=j+next[1];
        if(nr<0 || nc<0 || nr==m || nc==n || vis[nr][nc])
            continue;
        int nextNode=grid[nr][nc];
        for(int[] back:DIRS.get(nextNode-1))
            if(nr+back[0]==i && nc+back[1]==j)
                if(dfs(grid, nr, nc, vis))
                    return true;
    }
    return false;
}
void main() {
    int[][] grid = {{2,4,3},{6,5,2}};
    System.out.println(hasValidPath(grid));
}