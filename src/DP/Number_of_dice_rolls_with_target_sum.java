/*
Number of Dice Rolls With Target Sum

You have n dice, and each dice has k faces numbered from 1 to k.

Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice, so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.

https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/description/
*/

private static final int MOD=1_000_000_007;
private Integer[][] dp;

private int f(int n, int k, int target) {
    if(dp[n][target]!=null)
        return dp[n][target];
    if(n==0)
        return dp[n][target]=target==0 ? 1 : 0;
    long ways=0;
    for(int i=1;i<=k;i++)
        if(target-i>=0)
            ways+=f(n-1, k, target-i);
    return dp[n][target]=(int)(ways%MOD);
}

public int numRollsToTarget(int n, int k, int target) {
    int[][] dp=new int[n+1][target+1];
    dp[0][0]=1;
    for(int i=1;i<=n;i++) {
        for(int t=1;t<=target;t++) {
            long ways=0;
            for(int j=1;j<=k;j++)
                if(t-j>=0)
                    ways+=dp[i-1][t-j];
            dp[i][t]=(int)(ways%MOD);
        }
    }
    return dp[n][target];
}

void main() {
    int n = 2, k = 6, target = 7;
    IO.println(numRollsToTarget(n, k, target));
}