/*
You are given a 0-indexed 2D matrix grid of size n x n, where (r, c) represents:

A cell containing a thief if grid[r][c] = 1
An empty cell if grid[r][c] = 0
You are initially positioned at cell (0, 0). In one move, you can move to any adjacent cell in the grid, including cells containing thieves.

The safeness factor of a path on the grid is defined as the minimum manhattan distance from any cell in the path to any thief in the grid.

Return the maximum safeness factor of all paths leading to cell (n - 1, n - 1).

An adjacent cell of cell (r, c), is one of the cells (r, c + 1), (r, c - 1), (r + 1, c) and (r - 1, c) if it exists.

The Manhattan distance between two cells (a, b) and (x, y) is equal to |a - x| + |b - y|, where |val| denotes the absolute value of val.

https://leetcode.com/problems/find-the-safest-path-in-a-grid/description/
*/

final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int maximumSafenessFactor(List<List<Integer>> grid) {
    int n=grid.size();
    int[][] dist=new int[n][n];
    Queue<int[]> q=new LinkedList();
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            if(grid.get(i).get(j)==1) {
                dist[i][j]=0;
                q.add(new int[]{i, j});
            } else
                dist[i][j]=-1;
    while(!q.isEmpty()) {
        int r=q.peek()[0], c=q.poll()[1];
        for(int[] d:DIRS) {
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr>=n || nc>=n || dist[nr][nc]!=-1) continue;
            dist[nr][nc]=dist[r][c]+1;
            q.add(new int[]{nr, nc});
        }
    }

    Queue<int[]> pq=new PriorityQueue<>((a, b)->Integer.compare(b[0], a[0]));
    pq.add(new int[]{dist[0][0], 0, 0});
    boolean[][] visited=new boolean[n][n];
    visited[0][0]=true;
    while(!pq.isEmpty()) {
        int curr=pq.peek()[0], r=pq.peek()[1], c=pq.poll()[2];
        if(r==n-1 && c==n-1)
            return curr;
        for(int[] d:DIRS) {
            int nr=r+d[0], nc=c+d[1];
            if(nr<0 || nc<0 || nr>=n || nc>=n || visited[nr][nc])
                continue;
            visited[nr][nc]=true;
            int newDist=Math.min(curr, dist[nr][nc]);
            pq.add(new int[]{newDist, nr, nc});
        }
    }
    return -1;
}
void main() {
    List<List<Integer>> grid = List.of(
            List.of(0, 0, 0, 1),
            List.of(0, 0, 0, 0),
            List.of(0, 0, 0, 0),
            List.of(1, 0, 0, 0)
    );
    IO.println(maximumSafenessFactor(grid));
}