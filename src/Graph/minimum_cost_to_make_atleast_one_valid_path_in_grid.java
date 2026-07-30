/*
Minimum Cost to Make at Least One Valid Path in a Grid

Given an m x n grid. Each cell of the grid has a sign pointing to the next cell you should visit if you are currently in this cell. The sign of grid[i][j] can be:

1 which means go to the cell to the right. (i.e go from grid[i][j] to grid[i][j + 1])
2 which means go to the cell to the left. (i.e go from grid[i][j] to grid[i][j - 1])
3 which means go to the lower cell. (i.e go from grid[i][j] to grid[i + 1][j])
4 which means go to the upper cell. (i.e go from grid[i][j] to grid[i - 1][j])
Notice that there could be some signs on the cells of the grid that point outside the grid.

You will initially start at the upper left cell (0, 0). A valid path in the grid is a path that starts from the upper left cell (0, 0) and ends at the bottom-right cell (m - 1, n - 1) following the signs on the grid. The valid path does not have to be the shortest.

You can modify the sign on a cell with cost = 1. You can modify the sign on a cell one time only.

Return the minimum cost to make the grid have at least one valid path.

https://leetcode.com/problems/minimum-cost-to-make-at-least-one-valid-path-in-a-grid/description/
*/

private static final int[][] DIRS={{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
public int minCost(int[][] grid) {
    // 0-1 bfs
    int m=grid.length, n=grid[0].length;
    int[][] dist=new int[m][n];
    for(int[] d:dist)
        Arrays.fill(d, Integer.MAX_VALUE);
    Deque<int[]> q=new ArrayDeque();
    q.addFirst(new int[]{0, 0});
    dist[0][0]=0;
    while(!q.isEmpty()) {
        int[] cell=q.poll();
        int r=cell[0], c=cell[1];
        for(int direction=0;direction<4;direction++) {
            int nr=r+DIRS[direction][0], nc=c+DIRS[direction][1];
            if(nr<0 || nc<0 || nr==m || nc==n)
                continue;
            int nextCost=grid[r][c]==direction+1?0:1;
            int cost=dist[r][c]+nextCost;
            if(cost<dist[nr][nc]) {
                dist[nr][nc]=cost;
                if(nextCost==0)
                    q.addFirst(new int[]{nr, nc});
                else
                    q.addLast(new int[]{nr, nc});
            }
        }
    }
    return dist[m-1][n-1];
}

void main() {
    int[][] grid = {{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
    System.out.println(minCost(grid));
}