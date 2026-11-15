/*
Knight Dialer

The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagram:

A chess knight can move as indicated in the chess diagram below:

We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).

Given an integer n, return how many distinct phone numbers of length n we can dial.

You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

As the answer may be very large, return the answer modulo 109 + 7.

https://leetcode.com/problems/knight-dialer/description/
*/

private static final int MOD = 1_000_000_007;
private static final int[][] pad = {
        {4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9},
        {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}
};
private final Integer[][] dp=new Integer[5001][10];
private int f(int n, int num) {
    if (n == 0)
        return 1;

    if (dp[n][num] != null)
        return dp[n][num];

    long sum = 0;

    for (int next : pad[num])
        sum = (sum + f(n - 1, next));

    return dp[n][num] = (int)(sum%MOD);
}

public int knightDialer(int n) {
    int[][] dp = new int[n + 1][10];
    Arrays.fill(dp[0], 1);
    long result = 0;

    for(int i=1;i<n;i++)
        for(int num=0;num<10;num++) {
            long sum=0;
            for (int next : pad[num])
                sum+=dp[i-1][next];
            dp[i][num]=(int)(sum%MOD);
        }

    for (int i = 0; i < 10; i++)
        result = (result + dp[n-1][i]);
    return (int)(result%MOD);
}

void main() {
    int n=3131;
    IO.println(knightDialer(n));
}