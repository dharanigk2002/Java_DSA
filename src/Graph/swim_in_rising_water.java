/*
Swim in Rising Water

You are given an n x n integer matrix grid where each value grid[i][j] represents the elevation at that point (i, j).

It starts raining, and water gradually rises over time. At time t, the water level is t, meaning any cell with elevation less than equal to t is submerged or reachable.

You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distances in zero time. Of course, you must stay within the boundaries of the grid during your swim.

Return the minimum time until you can reach the bottom right square (n - 1, n - 1) if you start at the top left square (0, 0).

https://leetcode.com/problems/swim-in-rising-water/description
*/

private static final int[][] DIRS={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int swimInWater(int[][] grid) {
    int n=grid.length;
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
    q.add(new int[]{grid[0][0], 0, 0});
    int[][] dist=new int[n][n];
    for(int[] row:dist)
        Arrays.fill(row, Integer.MAX_VALUE);
    dist[0][0]=grid[0][0];
    while(!q.isEmpty()) {
        int[] curr=q.poll();
        int t=curr[0], r=curr[1], c=curr[2];
        if(t>dist[r][c]) continue;
        if(r==n-1 && c==n-1)
            return t;
        for(int[] d:DIRS) {
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr==n || nc==n)
                continue;
            int nextTime=Math.max(grid[nr][nc], t);
            if(dist[nr][nc]>nextTime) {
                dist[nr][nc]=nextTime;
                q.add(new int[]{nextTime, nr, nc});
            }
        }
    }
    return dist[n-1][n-1];
}

void main() {
    int[][] grid = {{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}};
    System.out.println(swimInWater(grid));
}