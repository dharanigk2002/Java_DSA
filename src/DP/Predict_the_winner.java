/*
Predict the Winner

You are given an integer array nums.

Two players are playing a game with this array: Player 1 and Player 2.

Player 1 and Player 2 take turns, with Player 1 starting first. Both players start the game with a score of 0.

At each turn, the current player takes the number at either end of the array (i.e., nums[0] or nums[nums.length - 1]), removing it from the array and adding it to their own score. The game ends when there are no more elements in the array.

Return true if Player 1's final score is greater than or equal to Player 2's final score, and false otherwise.

Note that a tie counts as a win for Player 1. You may assume that both players play optimally.

https://leetcode.com/problems/predict-the-winner/description/
*/

// Similar to stone game III
private Integer[][] dp;
private int f(int[] nums, int i, int j) {
    if(i>j)
        return 0;
    if(dp[i][j]!=null)
        return dp[i][j];
    int op1=nums[i]-f(nums, i+1, j);
    int op2=nums[j]-f(nums, i, j-1);
    return dp[i][j]=Math.max(op1, op2);
}

public boolean predictTheWinner(int[] nums) {
    int n=nums.length;
    dp=new Integer[n+1][n+1];
    return f(nums, 0, n-1)>=0;
}

void main() {
    int[] nums = {1,5,2};
    IO.println(predictTheWinner(nums));
}