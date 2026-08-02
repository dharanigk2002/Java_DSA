/*
Find Minimum Time to Reach Last Room II

There is a dungeon with n x m rooms arranged as a grid.

You are given a 2D array moveTime of size n x m, where moveTime[i][j] represents the minimum time in seconds when you can start moving to that room. You start from the room (0, 0) at time t = 0 and can move to an adjacent room. Moving between adjacent rooms takes one second for one move and two seconds for the next, alternating between the two.

Return the minimum time to reach the room (n - 1, m - 1).

Two rooms are adjacent if they share a common wall, either horizontally or vertically.

https://leetcode.com/problems/find-minimum-time-to-reach-last-room-ii/description/
*/
private static final int[][] DIRS={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int minTimeToReach(int[][] grid) {
    int m=grid.length, n=grid[0].length;
    int[][] dist=new int[m][n];
    for(int[] row:dist)
        Arrays.fill(row, Integer.MAX_VALUE);
    dist[0][0]=0;
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    q.add(new int[]{0, 1, 0, 0});
    while(!q.isEmpty()) {
        int[] curr=q.poll();
        int r=curr[2], c=curr[3];
        int time=curr[0], moveTime=curr[1];
        if(time>dist[r][c]) continue;
        if(r==m-1 && c==n-1) return time;
        for(int[] d:DIRS) {
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr==m || nc==n) continue;
            int nextTime=Math.max(grid[nr][nc], time)+moveTime;
            int nextMoveTime=moveTime==1?2:1;
            if(dist[nr][nc]>nextTime) {
                dist[nr][nc]=nextTime;
                q.add(new int[]{nextTime, nextMoveTime, nr, nc});
            }
        }
    }
    return dist[m-1][n-1];
}

void main() {
    int[][]  moveTime = {{0,4},{4,4}};
    System.out.println(minTimeToReach(moveTime));
}