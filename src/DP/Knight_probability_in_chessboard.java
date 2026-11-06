/*
Knight Probability in Chessboard

On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves. The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).

A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.

Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.

The knight continues moving until it has made exactly k moves or has moved off the chessboard.

Return the probability that the knight remains on the board after it has stopped moving.

https://leetcode.com/problems/knight-probability-in-chessboard/description/
*/

private static final int[][] DIRS = {
        { -2, 1 }, { -2, -1 }, { 2, 1 }, { 2, -1 },
        { -1, 2 }, { -1, -2 }, { 1, 2 }, { 1, -2 }
};
private Double[][][] dp;
private double f(int r, int c, int n, int k) {
    if(dp[r][c][k]!=null)
        return dp[r][c][k];
    if (k == 0)
        return dp[r][c][k]=1D;
    double res = 0.0;
    for (int[] d : DIRS) {
        int nr = r + d[0], nc = c + d[1];
        if (nr < 0 || nc < 0 || nr >= n || nc >= n)
            continue;
        res += (double)f(nr, nc, n, k - 1);
    }
    return dp[r][c][k]=(double)(res / 8.0);
}

public double knightProbability(int n, int k, int row, int column) {
    dp=new Double[n+1][n+1][k+1];
    return f(row, column, n, k);
}

void main() {
    int n = 3, k = 2, row = 0, column = 0;
    IO.println(knightProbability(n, k, row, column));
}