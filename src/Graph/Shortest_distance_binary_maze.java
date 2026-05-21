/*
Given a n * m matrix grid where each element can either be 0 or 1. You need to find the shortest distance between a given source cell to a destination cell. The path can only be created out of a cell if its value is 1.

If the path is not possible between source cell and destination cell, then return -1.

Note : You can move into an adjacent cell if that adjacent cell is filled with element 1. Two cells are adjacent if they share a side. In other words, you can move in one of the four directions, Up, Down, Left and Right. The source and destination cell are based on the zero based indexing. The destination cell should be 1.

https://www.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1
*/

private final int[][] dirs={{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
int shortestPath(int[][] grid, int[] source, int[] dest) {
    int n=grid.length, m=grid[0].length;
    if(grid[source[0]][source[1]]==0 || grid[dest[0]][dest[1]]==0)
        return -1;
    int src=source[0]*m+source[1];
    int dst=dest[0]*m+dest[1];
    Queue<int[]> q=new LinkedList<>();
    int[] dist=new int[n*m];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src]=0;
    q.add(new int[]{src, 0});
    while(!q.isEmpty()) {
        int[] node=q.poll();
        int u=node[0], w=node[1];
        if(u==dst)
            return dist[u];
        for(int[] d:dirs) {
            int r=u/m, c=u%m;
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr>=n || nc>=m || grid[nr][nc]==0) continue;
            int next=nr*m+nc;
            if(dist[next]>w+1) {
                dist[next]=w+1;
                q.add(new int[]{next, dist[next]});
            }
        }
    }
    return dist[dst]!=Integer.MAX_VALUE ? dist[dst] : -1;
}

void main() {
    int[][] grid = {
            {1, 1, 1, 1},
            {1, 1, 0, 1},
            {1, 1, 1, 1},
            {1, 1, 0, 0},
            {1, 0, 0, 1},
    };
    int[] source = {0, 1}, destination = {2, 2};
    int shortestDistance=shortestPath(grid, source, destination);
    IO.println(shortestDistance);
}