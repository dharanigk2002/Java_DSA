/*
Stone Game III

Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2, or 3 stones from the first remaining stones in the row.

The score of each player is the sum of the values of the stones taken. The score of each player is 0 initially.

The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

Assume Alice and Bob play optimally.

Return "Alice" if Alice will win, "Bob" if Bob will win, or "Tie" if they will end the game with the same score.

https://leetcode.com/problems/stone-game-iii/description/
*/

private Integer[] dp;
private int f(int[] stone, int i) {
    int n=stone.length;
    if(dp[i]!=null)
        return dp[i];
    if(i==n)
        return dp[i]=0;
    int result=stone[i]-f(stone, i+1);
    if(i+1<n)
        result=Math.max(result, stone[i]+stone[i+1]-f(stone, i+2));
    if(i+2<n)
        result=Math.max(result, stone[i]+stone[i+1]+stone[i+2]-f(stone, i+3));
    return dp[i]=result;
}

public String stoneGameIII(int[] stone) {
    int n=stone.length;
    int[] dp=new int[n+1];
    for(int i=n-1;i>=0;i--) {
        int result=stone[i]-dp[i+1];
        if(i+1<n)
            result=Math.max(result, stone[i]+stone[i+1]-dp[i+2]);
        if(i+2<n)
            result=Math.max(result, stone[i]+stone[i+1]+stone[i+2]-dp[i+3]);
        dp[i]=result;
    }
    int diff=dp[0];
    if(diff==0)
        return "Tie";
    if(diff<0)
        return "Bob";
    return "Alice";
}

void main() {
    int[] stoneValue = {1,2,3,-9};
    IO.println(stoneGameIII(stoneValue));
}