/*
You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.

Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

https://leetcode.com/problems/path-with-minimum-effort/description/
*/

final int[][] dirs={{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
public int minimumEffortPath(int[][] heights) {
    int n=heights.length, m=heights[0].length;
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(d->d[0]));
    q.add(new int[]{0, 0, 0});
    int[][] dist=new int[n][m];
    for(int[] d:dist)
        Arrays.fill(d, Integer.MAX_VALUE);
    dist[0][0]=0;
    while(!q.isEmpty()) {
        int[] node=q.poll();
        int currEffort=node[0], r=node[1], c=node[2];
        if(currEffort > dist[r][c])
            continue;
        if(r==n-1 && c==m-1)
            return currEffort;
        for(int[] d:dirs) {
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr>=n || nc>=m) continue;
            int effort=Math.abs(heights[r][c]-heights[nr][nc]);
            int newEffort=Math.max(currEffort, effort);
            if(newEffort < dist[nr][nc]) {
                dist[nr][nc]=newEffort;
                q.add(new int[]{dist[nr][nc], nr, nc});
            }
        }
    }
    return dist[n-1][m-1];
}

void main() {
    int[][] heights = {
            {1,2,2},
            {3,8,2},
            {5,3,5}
    };
    /*
    Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
    This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
    */
    int minEffort=minimumEffortPath(heights);
    IO.println(minEffort);
}