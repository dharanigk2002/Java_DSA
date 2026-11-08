/*
Champagne Tower

We stack glasses in a pyramid, where the first row has 1 glass, the second row has 2 glasses, and so on until the 100th row.  Each glass holds one cup of champagne.

Then, some champagne is poured into the first glass at the top.  When the topmost glass is full, any excess liquid poured will fall equally to the glass immediately to the left and right of it.  When those glasses become full, any excess champagne will fall equally to the left and right of those glasses, and so on.  (A glass at the bottom row has its excess champagne fall on the floor.)

For example, after one cup of champagne is poured, the top most glass is full.  After two cups of champagne are poured, the two glasses on the second row are half full.  After three cups of champagne are poured, those two cups become full - there are 3 full glasses total now.  After four cups of champagne are poured, the third row has the middle glass half full, and the two outside glasses are a quarter full, as pictured below.

Now after pouring some non-negative integer cups of champagne, return how full the jth glass in the ith row is (both i and j are 0-indexed.)

https://leetcode.com/problems/champagne-tower/description/
*/

private Double[][] dp;

private double f(int poured, int i, int j) {
        if (j < 0 || j > i)
            return 0.0;

        if (dp[i][j] != null)
            return dp[i][j];

        if (i == 0 && j == 0)
            return dp[i][j] = (double) poured;

        double left = Math.max(0.0,
            (f(poured, i - 1, j - 1) - 1.0) / 2.0);

        double right = Math.max(0.0,
            (f(poured, i - 1, j) - 1.0) / 2.0);

        return dp[i][j] = left + right;
    }

public double champagneTower(int poured, int query_row, int query_glass) {
    double[][] dp = new double[query_row + 1][query_row + 1];
    dp[0][0]=(double)poured;
    for(int i=1;i<=query_row;i++) {
        for(int j=0;j<=i;j++) {
            double left = j>0 ? Math.max(0.0,
                    (dp[i-1][j-1] - 1.0) / 2.0) : 0.0;

            double right = j<i ? Math.max(0.0,
                    (dp[i-1][j] - 1.0) / 2.0) : 0.0;

            dp[i][j] = left + right;
        }
    }
    return Math.min(1.0, dp[query_row][query_glass]);
}

void main() {
    int poured = 2, query_row = 1, query_glass = 1;
    IO.println(champagneTower(poured, query_row, query_glass));
}