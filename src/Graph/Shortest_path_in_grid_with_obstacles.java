/*
You are given an m x n integer matrix grid where each cell is either 0 (empty) or 1 (obstacle). You can move up, down, left, or right from and to an empty cell in one step.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1) given that you can eliminate at most k obstacles. If it is not possible to find such walk return -1.

https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/description/
*/

int shortestPath(int[][] grid, int k) {
    int m=grid.length, n=grid[0].length;
    final int[][] dirs={{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    boolean[][][] visited=new boolean[m][n][k+1];
    Queue<int[]> q=new LinkedList();
    int moves=0;
    q.add(new int[]{0, 0, k});
    visited[0][0][k]=true;
    while(!q.isEmpty()) {
        for(int size=q.size();size>0;size--) {
            int r=q.peek()[0], c=q.peek()[1], block=q.poll()[2];
            if(r==m-1 && c==n-1) return moves;
            for(int[] d:dirs) {
                int nr=r+d[0], nc=c+d[1];
                if(nr<0 || nc<0 || nr>=m || nc>=n) continue;
                if(grid[nr][nc]==0 && !visited[nr][nc][block]) {
                    visited[nr][nc][block]=true;
                    q.add(new int[]{nr, nc, block});
                } else if(grid[nr][nc]==1 && block>0 && !visited[nr][nc][block-1]) {
                    visited[nr][nc][block-1]=true;
                    q.add(new int[]{nr, nc, block-1});
                }
            }
        }
        moves++;
    }
    return -1;
}

void main() {
    int[][] grid = {
            {0, 0, 0},
            {1, 1, 0},
            {0, 0, 0},
            {0, 1, 1},
            {0, 0, 0},
    };
    int k=1;
    int shortestpath=shortestPath(grid, k);
    IO.println(shortestpath);
}