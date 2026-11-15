/*
Stone Game VIII

Alice and Bob take turns playing a game, with Alice starting first.

There are n stones arranged in a row. On each player's turn, while the number of stones is more than one, they will do the following:

Choose an integer x > 1, and remove the leftmost x stones from the row.
Add the sum of the removed stones' values to the player's score.
Place a new stone, whose value is equal to that sum, on the left side of the row.
The game stops when only one stone is left in the row.

The score difference between Alice and Bob is (Alice's score - Bob's score). Alice's goal is to maximize the score difference, and Bob's goal is the minimize the score difference.

Given an integer array stones of length n where stones[i] represents the value of the ith stone from the left, return the score difference between Alice and Bob if they both play optimally.

https://leetcode.com/problems/stone-game-viii/description/
*/

private Integer[] dp;
private int solve(int[] val, int idx) {
    if(dp[idx]!=null)
        return dp[idx];
    if(idx==val.length-1)
        return dp[idx]=val[idx];
    int take=val[idx]-solve(val, idx+1);
    int skip=solve(val, idx+1);
    return dp[idx]=Math.max(take, skip);
}

public int stoneGameVIII(int[] stones) {
    int n=stones.length;
    int[] prefix=new int[n];
    prefix[0]=stones[0];
    for(int i=1;i<n;i++)
        prefix[i]=prefix[i-1]+stones[i];

    int next=prefix[n-1];
    for(int idx=n-2;idx>0;idx--)
        next=Math.max(prefix[idx]-next, next);
    return next;
}

void main() {
    int[] stones = new int[]{-1, 2, -3, 4, -5};
    IO.println(stoneGameVIII(stones));
}