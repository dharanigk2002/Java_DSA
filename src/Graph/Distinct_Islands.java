int countDistinctIslands(int[][] grid) {
    int m=grid.length, n=grid[0].length;
    Set<String> islands=new HashSet<>();
    for(int i=0;i<m*n;i++)
        if(grid[i/n][i%n]==1) {
            StringBuilder sb=new StringBuilder();
            dfs(grid, i / n, i % n, m, n, sb, "S");
            islands.add(sb.toString());
        }
    return islands.size();
}

void dfs(int[][] grid, int i, int j, int m, int n, StringBuilder path, String dir) {
    if(i<0 || j<0 || i>=m || j>=n || grid[i][j]==0)
        return;
    grid[i][j]=0;
    path.append(dir);
    dfs(grid, i+1, j, m, n, path, dir+'D');
    dfs(grid, i-1, j, m, n, path, dir+'U');
    dfs(grid, i, j+1, m, n, path, dir+'R');
    dfs(grid, i, j-1, m, n, path,dir+'L');
    path.append('B');
}

void main() {
    int[][] grid={
            {1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0},
            {0, 0, 0, 0, 1},
            {1, 1, 0, 1, 1},
    };
    int islands=countDistinctIslands(grid);
    IO.println(islands);
}