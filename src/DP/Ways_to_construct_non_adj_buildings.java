/*
GFG (POTD)
Ways to Construct Non-Adjacent Buildings

There is a road passing through a city with n plots on each side. The plots on each side are arranged in a straight line. You can either construct a building or leave a plot empty. Find the total number of possible arrangements of buildings such that no two buildings are adjacent on the same side of the road.

The arrangements on the two sides are independent.
Since the answer can be very large, return the answer modulo 109 + 7.

https://www.geeksforgeeks.org/problems/count-possible-ways-to-construct-buildings5007/1
*/

private static final int MOD = 1_000_000_007;
private Integer[] dp;
private int f(int n) {
    if (dp[n] != null)
        return dp[n];
    if (n == 0)
        return dp[n] = 1;
    int notPick = f(n - 1);
    int pick = f(Math.max(0, n - 2));
    return dp[n] = (int)(((long) pick + notPick)%MOD);
}

public int totalWays(int n) {
    dp = new Integer[n + 1];
    int ways = f(n);
    return (int)(((long) ways * ways)%MOD);
}

public int bottomUp(int n) {
    int[] dp = new int[n + 1];
    dp[0]=1;
    for(int i=1;i<=n;i++) {
        int notPick = dp[i - 1];
        int pick = dp[Math.max(0, i - 2)];
        dp[i] = (int)(((long)pick + notPick)%MOD);
    }
    return (int)(((long)dp[n]*dp[n])%MOD);
}

void main() {
    IO.println(bottomUp(3));
}