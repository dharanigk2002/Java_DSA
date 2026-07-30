/*
Find Minimum Time to Reach Last Room I

There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds after which the room opens and can be moved to. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes exactly one second.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.

https://leetcode.com/problems/find-minimum-time-to-reach-last-room-i/description/
*/

private static final int[][] DIRS={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int minTimeToReach(int[][] grid) {
    int m=grid.length, n=grid[0].length;
    int[][] dist=new int[m][n];
    for(int[] row:dist)
        Arrays.fill(row, Integer.MAX_VALUE);
    Queue<int[]> q=new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0]));
    q.add(new int[]{0, 0, 0});
    dist[0][0]=0;
    while(!q.isEmpty()) {
        int[] current=q.poll();
        int cost=current[0], r=current[1], c=current[2];
        if(cost>dist[r][c]) continue;
        if(r==m-1 && c==n-1)
            return cost;
        for(int[] d:DIRS) {
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr==m || nc==n)
                continue;
            int nextCost=Math.max(cost, grid[nr][nc])+1;
            if(nextCost<dist[nr][nc]) {
                dist[nr][nc]=nextCost;
                q.add(new int[]{nextCost, nr, nc});
            }
        }
    }
    return dist[m-1][n-1];
}

void main() {
    int[][] moveTime = {{0,4},{4,4}};
    System.out.println(minTimeToReach(moveTime));
}