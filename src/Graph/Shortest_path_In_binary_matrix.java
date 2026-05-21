/*
Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

https://leetcode.com/problems/shortest-path-in-binary-matrix/
*/

final int[][] dirs={{0, -1}, {-1, 0}, {1, 0}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
public int shortestPathBinaryMatrix(int[][] grid) {
    int n=grid.length;
    if(grid[0][0]==1 || grid[n-1][n-1]==1)
        return -1;
    Queue<int[]> q=new LinkedList();
    int[] dist=new int[n*n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[0]=1;
    q.add(new int[]{0, 1});
    while(!q.isEmpty()) {
        int[] node=q.poll();
        if(node[0]==n*n-1) return node[1];
        for(int[] d:dirs) {
            int r=node[0]/n, c=node[0]%n, w=node[1];
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr>=n || nc>=n || grid[nr][nc]==1) continue;
            int next=nr*n+nc;
            if(dist[next] > w+1) {
                dist[next]=w+1;
                q.add(new int[]{next, dist[next]});
            }
        }
    }
    return dist[n*n-1]!=Integer.MAX_VALUE ? dist[n*n-1] : -1;
}

void main() {
    int[][] grid = {
            {0,0,0},
            {1,1,0},
            {1,1,0}
    };
    int shortestPath=shortestPathBinaryMatrix(grid);
    IO.println(shortestPath);
}