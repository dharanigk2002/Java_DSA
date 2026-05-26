/*
You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.

https://leetcode.com/problems/shortest-bridge/description/
*/

int shortestBridge(int[][] grid) {
    int n=grid.length;
    int count=0;
    Queue<int[]> q=new LinkedList();
    boolean[][] visited=new boolean[n][n];
    for(int i=0;i<n*n;i++)
        if(grid[i/n][i%n]==1) {
            dfs(grid, i/n, i%n, q, visited);
            break;
        }
    final int[][] dirs={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    while(!q.isEmpty()) {
        for(int size=q.size();size>0;size--) {
            int r=q.peek()[0], c=q.poll()[1];
            for(int[] d:dirs) {
                int nr=r+d[0], nc=c+d[1];
                if(nr<0 || nc<0 || nr>=n || nc>=n || visited[nr][nc]) continue;
                q.add(new int[]{nr, nc});
                visited[nr][nc]=true;
                if(grid[nr][nc]==1)
                    return count;
            }
        }
        count++;
    }
    return count;
}
private void dfs(int[][] grid, int i, int j, Queue<int[]> q, boolean[][] visited) {
    if(i<0 || j<0 || i>=grid.length || j>=grid.length || grid[i][j]==0)
        return;
    q.add(new int[]{i, j});
    visited[i][j]=true;
    grid[i][j]=0;
    dfs(grid, i+1, j, q, visited);
    dfs(grid, i-1, j, q, visited);
    dfs(grid, i, j+1, q, visited);
    dfs(grid, i, j-1, q, visited);
}

void main() {
    int[][] grid = {
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 0, 0, 0, 0},
    };
    int shortBridge = shortestBridge(grid);
    IO.println(shortBridge);
}