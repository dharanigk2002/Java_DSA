/*
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

https://leetcode.com/problems/rotting-oranges/description/
*/

private final int[][] dirs={{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
public int orangesRotting(int[][] grid) {
    int fresh=0, min=0, m=grid.length, n=grid[0].length;
    Queue<int[]> q=new LinkedList();
    for(int i=0;i<m;i++) {
        for(int j=0;j<n;j++)
            if(grid[i][j]==1)
                fresh++;
            else if(grid[i][j]==2)
                q.add(new int[]{i, j});
    }
    if(fresh==0)
        return 0;
    while(!q.isEmpty()) {
        int size=q.size();
        boolean hasFresh=false;
        while(size-->0) {
            int[] cell=q.poll();
            int r=cell[0], c=cell[1];
            for(int[] d:dirs) {
                int nr=r+d[0], nc=c+d[1];
                if(nr<0 || nr>=m || nc<0 || nc>=n || grid[nr][nc]!=1) continue;
                grid[nr][nc]=2;
                q.add(new int[]{nr, nc});
                fresh--;
                hasFresh=true;
            }
        }
        if(hasFresh) min++;
    }
    return fresh==0?min:-1;
}

void main() {
    int[][] oranges = {{2,1,1},{1,1,0},{0,1,1}};
    int timeTaken = orangesRotting(oranges);
    IO.println(timeTaken);
}