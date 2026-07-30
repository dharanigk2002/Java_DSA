/*
Minimum Time to Visit a Cell In a Grid

You are given a m x n matrix grid consisting of non-negative integers where grid[row][col] represents the minimum time required to be able to visit the cell (row, col), which means you can visit the cell (row, col) only when the time you visit it is greater than or equal to grid[row][col].

You are standing in the top-left cell of the matrix in the 0th second, and you must move to any adjacent cell in the four directions: up, down, left, and right. Each move you make takes 1 second.

Return the minimum time required in which you can visit the bottom-right cell of the matrix. If you cannot visit the bottom-right cell, then return -1.

https://leetcode.com/problems/minimum-time-to-visit-a-cell-in-a-grid/description/
*/

private static final int[][] DIRS={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int minimumTime(int[][] grid) {
    int m=grid.length, n=grid[0].length;
    if(m>1 && n>1 && grid[0][1]>1 && grid[1][0]>1)
        return -1;
    int[][] dist=new int[m][n];
    for(int[] row:dist)
        Arrays.fill(row, Integer.MAX_VALUE);
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
    q.add(new int[]{0, 0, 0});
    dist[0][0]=0;
    while(!q.isEmpty()) {
        int[] node=q.poll();
        int r=node[1], c=node[2];
        int time=node[0];
        if(r==m-1 && c==n-1)
            return time;
        if(time!=dist[r][c]) continue;
        for(int[] d:DIRS) {
            int nextTime=time+1;
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr==m || nc==n) continue;
            if(grid[nr][nc]>nextTime) {
                int diff=grid[nr][nc]-nextTime;
                nextTime=grid[nr][nc]+diff%2;
            }
            if(nextTime<dist[nr][nc]) {
                dist[nr][nc]=nextTime;
                q.add(new int[]{nextTime, nr, nc});
            }
        }
    }
    return -1;
}

void main() {
    int[][] grid = {{0,1,3,2},{5,1,2,5},{4,3,8,6}};
    System.out.println(minimumTime(grid));
}