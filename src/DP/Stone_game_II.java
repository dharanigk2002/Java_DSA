/*
Stone Game II

Alice and Bob continue their games with piles of stones. There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i]. The objective of the game is to end with the most stones.

Alice and Bob take turns, with Alice starting first.

On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M. Then, we set M = max(M, X). Initially, M = 1.

The game continues until all the stones have been taken.

Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

https://leetcode.com/problems/stone-game-ii/description/
*/

private Integer[][][] dp;
private int f(int[] p, int i, int M, int alice) {
    int n=p.length;
    if(dp[i][M][alice]!=null)
        return dp[i][M][alice];
    if(i==n)
        return dp[i][M][alice]=0;
    int score=0, result=alice==0 ? 0 : Integer.MAX_VALUE;
    for(int x=1;x<=Math.min(2*M, n-i);x++) {
        score+=p[i+x-1];
        if(alice==0)
            result=Math.max(result, score+f(p, i+x, Math.max(M, x), alice^1));
        else
            result=Math.min(result, f(p, i+x, Math.max(M, x), alice^1));
    }
    return dp[i][M][alice]=result;
}
/**
 * Optimized approach
 * @param suffix suffix array
 * @return max score gained by Alice
 * */
private int f(int[] suffix, int i, int M, Integer[][] dp) {
    if(dp[i][M]!=null)
        return dp[i][M];
    int n=suffix.length;
    if(i==n)
        return dp[i][M]=0;
    int result=-1;
    for(int x=1;x<=Math.min(2*M, n-i);x++)
        result=Math.max(result, suffix[i]-f(suffix, i+x, Math.max(M, x), dp));
    return dp[i][M]=result;
}

public int stoneGameII_opti(int[] piles) {
    int n=piles.length;
    int[] suffix=new int[n];
    Integer[][] dp=new Integer[n+1][n+1];
    suffix[n-1]=piles[n-1];
    for(int i=n-2;i>=0;i--)
        suffix[i]=piles[i]+suffix[i+1];
    return f(suffix, 0, 1, dp);
}

public int stoneGameII(int[] piles) {
    int n=piles.length;
    dp=new Integer[n+1][n+1][2];
    return f(piles, 0, 1, 0);
}

void main() {
    int[] piles = {2,7,9,4,4};
    IO.println(stoneGameII(piles));
}