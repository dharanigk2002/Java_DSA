/*
Stone Game

Alice and Bob play a game with piles of stones. There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].

The objective of the game is to end with the most stones. The total number of stones across all the piles is odd, so there are no ties.

Alice and Bob take turns, with Alice starting first. Each turn, a player takes the entire pile of stones either from the beginning or from the end of the row. This continues until there are no more piles left, at which point the person with the most stones wins.

Assuming Alice and Bob play optimally, return true if Alice wins the game, or false if Bob wins.

https://leetcode.com/problems/stone-game/description/
*/

private Integer[][] dp;
private int solve(int[] piles, int i, int j) {
    if(i>j)
        return 0;
    if(dp[i][j]!=null)
        return dp[i][j];
    return dp[i][j]=Math.max(piles[i]-solve(piles, i+1, j), piles[j]-solve(piles, i, j-1));
}

public boolean stoneGame(int[] piles) {
    int n=piles.length;
    int[] dp=new int[n+1];
    for(int i=n-1;i>=0;i--) {
        int[] curr=new int[n+1];
        for(int j=i+1;j<n;j++)
            curr[j]=Math.max(piles[i]-dp[j], piles[j]-curr[j-1]);
        dp=curr;
    }
    return dp[n-1]>0;
}

void main() {
    int[] piles = new int[]{5, 3, 4, 5};
    IO.println(stoneGame(piles));
}