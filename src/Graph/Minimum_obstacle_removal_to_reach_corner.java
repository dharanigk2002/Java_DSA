/*
You are given a 0-indexed 2D integer array grid of size m x n. Each cell has one of two values:

0 represents an empty cell,
1 represents an obstacle that may be removed.
You can move up, down, left, or right from and to an empty cell.

Return the minimum number of obstacles to remove so you can move from the upper left corner (0, 0) to the lower right corner (m - 1, n - 1).

https://leetcode.com/problems/minimum-obstacle-removal-to-reach-corner/description/
*/
int minimumObstacles(int[][] grid) {
    int m=grid.length, n=grid[0].length;
    final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    Queue<int[]> q=new PriorityQueue<>((a, b)->Integer.compare(a[0], b[0]));
    q.add(new int[]{0, 0, 0});
    int[][] block=new int[m][n];
    for(int i=0;i<m;i++)
        Arrays.fill(block[i], Integer.MAX_VALUE);
    block[0][0]=0;
    while(!q.isEmpty()) {
        int r=q.peek()[1], c=q.peek()[2], k=q.poll()[0];
        if(r==m-1 && c==n-1)
            return k;
        if(k>block[r][c]) continue;
        for(int[] d:DIRS) {
            int nr=d[0]+r, nc=c+d[1];
            if(nr<0 || nc<0 || nr>=m || nc>=n)
                continue;
            if(grid[nr][nc]==1 && block[nr][nc]>k+1) {
                block[nr][nc]=k+1;
                q.add(new int[]{k+1, nr, nc});
            } else if(grid[nr][nc]==0 && block[nr][nc]>k) {
                block[nr][nc]=k;
                q.add(new int[]{k, nr, nc});
            }
        }
    }
    return 0;
}
void main() {
    int[][] grid = {
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,0,0,1,0}
    };
    IO.println(minimumObstacles(grid));
}