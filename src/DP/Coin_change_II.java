/*
Coin Change II

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The final answer is guaranteed to fit into a signed 32-bit integer.

https://leetcode.com/problems/coin-change-ii/description/
*/

private int f(int i, int t, int[] arr, int[][] dp) {
    if(i==0)
        return (t%arr[0]==0)?1:0;
    if(dp[i][t]!=-1) return dp[i][t];
    int np=f(i-1, t, arr, dp);
    int p=(arr[i]<=t)?f(i, t-arr[i], arr, dp):0;
    return dp[i][t]=p+np;
}

public int change(int amount, int[] coins) {
    int n=coins.length;
    int[][] dp=new int[n][amount+1];
    for(int[] row:dp) Arrays.fill(row, -1);
    return f(n-1, amount, coins, dp);
}

public int bottomUp(int amount, int[] coins) {
    int n=coins.length;
    int[] dp=new int[amount+1];
    int[] curr=new int[amount+1];
    curr[0]=1;
    for(int amt=0;amt<=amount;amt++)
        dp[amt]=amt%coins[0]==0 ? 1 : 0;
    for(int i=1;i<=n-1;i++) {
        for(int amt=1;amt<=amount;amt++) {
            curr[amt]=dp[amt];
            if(amt>=coins[i])
                curr[amt]+=curr[amt-coins[i]];
        }
        dp=curr;
    }
    return dp[amount];
}
void main() {
    int amount = 5;
    int[] coins = {1,2,5};
    IO.println(bottomUp(amount, coins));
}