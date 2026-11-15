/*
Out of Boundary Paths

There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn]. You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.

Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary. Since the answer can be very large, return it modulo 109 + 7.

https://leetcode.com/problems/out-of-boundary-paths/description/
*/

private static int MOD = 1_000_000_007;
private static final int[][] DIRS={{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
private Integer[][][] dp;

private int paths(int r, int c, int m, int n, int maxMove){
    if(r<0 || c<0 || r==m || c==n) return 1;
    if(maxMove ==0) return 0;
    if(dp[r][c][maxMove]!=null) return dp[r][c][maxMove];

    long ways=0;
    for(int[] d:DIRS) {
        int nr=r+d[0], nc=c+d[1];
        ways+=paths(nr, nc, m, n, maxMove-1);
    }
    int result =(int) (ways % MOD);
    return dp[r][c][maxMove] = result;
}

public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
    dp = new Integer[m][n][maxMove+1];
    return paths(startRow, startColumn, m, n, maxMove);
}

void main() {
    int m = 2;
    int n = 2;
    int maxMove = 2;
    int startRow = 0;
    int startColumn = 0;
    IO.println(findPaths(m, n, maxMove, startRow, startColumn));
}