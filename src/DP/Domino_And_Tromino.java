/*
Domino and Tromino Tiling

You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.

Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.

In a tiling, every square must be covered by a tile. Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

https://leetcode.com/problems/domino-and-tromino-tiling/description/
*/

private Integer[][] dp;
private static final int MOD=1_000_000_007;
private int f(int r1, int r2) {
    if(r1==0 && r2==0)
        return dp[r1][r2]=1;
    if(r1<=0 || r2<=0)
        return 0;
    if(dp[r1][r2]!=null)
        return dp[r1][r2];
    long sum=0;
    if(r1==r2) {
        sum+=f(r1-2, r2-2);
        sum+=f(r1-1, r2-1);
        sum+=f(r1-1, r2-2);
        sum+=f(r1-2, r2-1);
    }
    else if(r1>r2) {
        sum+=f(r1-2, r2);
        sum+=f(r1-2, r2-1);
    }
    else {
        sum+=f(r1, r2-2);
        sum+=f(r1-1, r2-2);
    }
    return dp[r1][r2]=(int)(sum%MOD);
}

private int bottomUp(int n) {
    int[] dp=new int[n+1];
    dp[0]=1;
    if(n>=1)
        dp[1]=1;
    if(n>=2)
        dp[2]=2;
    for(int i=3;i<=n;i++)
        dp[i]=(int)((2l*dp[i-1])%MOD+dp[i-3])%MOD;
    return dp[n];
}

public int numTilings(int n) {
    dp=new Integer[n+1][n+1];
    return f(n, n);
}

void main() {
    int n=3;
    dp=new Integer[n+1][n+1];
    IO.println(numTilings(n));
}