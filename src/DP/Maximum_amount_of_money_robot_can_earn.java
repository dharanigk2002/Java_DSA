/*
Maximum Amount of Money Robot Can Earn

You are given an m x n grid. A robot starts at the top-left corner of the grid (0, 0) and wants to reach the bottom-right corner (m - 1, n - 1). The robot can move either right or down at any point in time.

The grid contains a value coins[i][j] in each cell:

If coins[i][j] >= 0, the robot gains that many coins.
If coins[i][j] < 0, the robot encounters a robber, and the robber steals the absolute value of coins[i][j] coins.
The robot has a special ability to neutralize robbers in at most 2 cells on its path, preventing them from stealing coins in those cells.

Note: The robot's total coins can be negative.

Return the maximum profit the robot can gain on the route.

https://leetcode.com/problems/maximum-amount-of-money-robot-can-earn/description/
*/

private Integer[][][] dp;
private int f(int[][] coins, int i, int j, int power) {
    int m=coins.length, n=coins[0].length;
    if(dp[i][j][power]!=null)
        return dp[i][j][power];
    if(i==m || j==n)
        return dp[i][j][power]=Integer.MIN_VALUE/2;
    int amount=coins[i][j];
    if(i==m-1 && j==n-1)
        return dp[i][j][power]=(amount<0 && power>0) ? 0 : coins[i][j];
    int op1=Integer.MIN_VALUE;
    if(amount<0 && power>0)
        op1=Math.max(f(coins, i+1, j, power-1), f(coins, i, j+1, power-1));
    int op2=amount+Math.max(f(coins, i+1, j, power), f(coins, i, j+1, power));
    return dp[i][j][power]=Math.max(op1, op2);
}

public int maximumAmount(int[][] coins) {
    int m=coins.length, n=coins[0].length;
    dp=new Integer[m+1][n+1][3];
    return f(coins, 0, 0, 2);
}

void main() {
    int[][] coins = new int[][]{{0, 1, -1}, {1, -2, 3}, {2, -3, 4}};
    IO.println(maximumAmount(coins));
}