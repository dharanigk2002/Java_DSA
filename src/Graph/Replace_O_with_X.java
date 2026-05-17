/*
You are given a grid[][] of size n*m, where every element is either 'O' or 'X'. You have to replace all 'O' or a group of 'O' with 'X' that are surrounded by 'X'.

A 'O' (or a set of 'O') is considered to be surrounded by 'X' if there are 'X' at locations just below, just above, just left and just right of it.
*/

void dfs(char[][] grid, int i, int j, int m, int n) {
    if(i<0 || j<0 || i>=m || j>=n || grid[i][j]!='O')
        return;
    grid[i][j]='*';
    dfs(grid, i+1, j, m, n);
    dfs(grid, i-1, j, m, n);
    dfs(grid, i, j+1, m, n);
    dfs(grid, i, j-1, m, n);
}

void main() {
    char[][] grid = {
            {'O', 'O', 'X', 'O', 'X'},
            {'O', 'X', 'X', 'X', 'O'},
            {'X', 'X', 'X', 'O', 'X'},
            {'O', 'O', 'X', 'O', 'X'},
    };
    int m=grid.length, n=grid[0].length;
    for(int i=0;i<m;i++) {
        if(grid[i][0]=='O')
            dfs(grid, i, 0, m, n);
        if(grid[i][n-1]=='O')
            dfs(grid, i, n-1, m, n);
    }
    for(int i=0;i<n;i++) {
        if(grid[0][i]=='O')
            dfs(grid, 0, i, m, n);
        if(grid[m-1][i]=='O')
            dfs(grid, m-1, i, m, n);
    }
    for(int i=0;i<m*n;i++) {
        int r=i/n, c=i%n;
        if(grid[r][c]=='*')
            grid[r][c]='O';
        else if(grid[r][c]=='O')
            grid[r][c]='X';
    }
    for(char[] row:grid)
        IO.println(Arrays.toString(row));

}