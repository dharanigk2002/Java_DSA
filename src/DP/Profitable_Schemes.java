/*
Profitable Schemes

There is a group of n members, and a list of various crimes they could commit. The ith crime generates a profit[i] and requires group[i] members to participate in it. If a member participates in one crime, that member can't participate in another crime.

Let's call a profitable scheme any subset of these crimes that generates at least minProfit profit, and the total number of members participating in that subset of crimes is at most n.

Return the number of schemes that can be chosen. Since the answer may be very large, return it modulo 109 + 7.

https://leetcode.com/problems/profitable-schemes/description/
*/

private static final int MOD=1_000_000_007;
private Integer[][][] dp;
private int f(int n, int min, int i, int profit, int[] g, int[] p) {
    if(dp[n][i][profit]!=null)
        return dp[n][i][profit];
    if(i==g.length)
        return dp[n][i][profit]=profit>=min ? 1 : 0;
    long sum=0;
    sum+=f(n, min, i+1, profit, g, p);
    if(n-g[i]>=0)
        sum+=f(n-g[i], min, i+1, Math.min(min, profit+p[i]), g, p);
    return dp[n][i][profit]=(int)(sum%MOD);
}

public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
    int m=group.length;
    dp=new Integer[n+1][m+1][minProfit+1];
    return f(n, minProfit, 0, 0, group, profit);
}

void main() {
    int n = 5, minProfit = 3;
    int[] group = {2,2}, profit = {2,3};
    IO.println(profitableSchemes(n, minProfit, group, profit));
}
