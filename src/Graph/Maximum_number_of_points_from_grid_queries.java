/*
You are given an m x n integer matrix grid and an array queries of size k.

Find an array answer of size k such that for each integer queries[i] you start in the top left cell of the matrix and repeat the following process:

If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
Otherwise, you do not get any points, and you end this process.
After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.

Return the resulting array answer.

https://leetcode.com/problems/maximum-number-of-points-from-grid-queries/description/
*/
final int[][] DIRS={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
public int[] maxPoints(int[][] grid, int[] queries) {
    int m=grid.length, n=grid[0].length;
    int k=queries.length;
    int[] ans=new int[k];
    int[][] offline=new int[k][2];
    for(int i=0;i<k;i++) {
        offline[i][0]=queries[i];
        offline[i][1]=i;
    }
    Arrays.sort(offline, Comparator.comparingInt(a->a[0]));
    Queue<int[]> q=new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
    q.add(new int[]{grid[0][0], 0, 0});
    int score=0;
    boolean[][] visited=new boolean[m][n];
    visited[0][0]=true;
    for(int[] query:offline) {
        int val=query[0], idx=query[1];
        while(!q.isEmpty() && val>q.peek()[0]) {
            int r=q.peek()[1], c=q.poll()[2];
            score++;
            for(int[] d:DIRS) {
                int nr=r+d[0], nc=c+d[1];
                if(nr<0 || nc<0 || nr>=m || nc>=n || visited[nr][nc]) continue;
                visited[nr][nc]=true;
                q.add(new int[]{grid[nr][nc], nr, nc});
            }
        }
        ans[idx]=score;
    }
    return ans;
}
void main() {
    int[][] grid = {{1,2,3},{2,5,7},{3,5,1}};
    int[] queries = {5,6,2};
    IO.println(Arrays.toString(maxPoints(grid, queries)));
}