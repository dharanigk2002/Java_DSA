/*
Stone Game V

There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row), then Bob calculates the value of each row which is the sum of the values of all the stones in this row. Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row. If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.

The game ends when there is only one stone remaining. Alice's score is initially zero.

Return the maximum score that Alice can obtain.

https://leetcode.com/problems/stone-game-v/description/
*/

private Integer[][] dp;
private int solve(int[] val, int i, int j, int total) {
    if(i>j)
        return Integer.MIN_VALUE/2;
    if(dp[i][j]!=null)
        return dp[i][j];
    if(i==j)
        return dp[i][j]=0;
    int sum=0, max=0;
    for(int k=i;k<j;k++) {
        sum+=val[k];
        if(total-sum>sum)
            max=Math.max(max, sum+solve(val, i, k, sum));
        else if(total-sum==sum)
            max=Math.max(max, sum+Math.max(solve(val, i, k, sum), solve(val, k+1, j, sum)));
        else
            max=Math.max(max, total-sum+solve(val, k+1, j, total-sum));
    }
    return dp[i][j]=max;
}

public int stoneGameV(int[] stoneValue) {
    int total=0, n=stoneValue.length;
    for(int val:stoneValue)
        total+=val;
    dp=new Integer[n+1][n+1];
    return solve(stoneValue, 0, stoneValue.length-1, total);
}

void main() {
    int[] stoneValue = new int[]{6, 2, 3, 4, 5, 5};
    IO.println(stoneGameV(stoneValue));
}