/*
Painting the Walls

You are given two 0-indexed integer arrays, cost and time, of size n representing the costs and the time taken to paint n different walls respectively. There are two painters available:

A paid painter that paints the ith wall in time[i] units of time and takes cost[i] units of money.
A free painter that paints any wall in 1 unit of time at a cost of 0. But the free painter can only be used if the paid painter is already occupied.
Return the minimum amount of money required to paint the n walls.

https://leetcode.com/problems/painting-the-walls/description/
*/

private Integer[][] dp;
private int f(int[] cost, int[] time, int i, int rem) {
    if(dp[i][rem]!=null)
        return dp[i][rem];
    int n=cost.length;
    if(rem==0)
        return dp[i][rem]=0;
    if(i==n)
        return dp[i][rem]=Integer.MAX_VALUE/2;
    int notTake=f(cost, time, i+1, rem);
    int take=cost[i]+f(cost, time, i+1, Math.max(0, rem-time[i]-1));
    return dp[i][rem]=Math.min(take, notTake);
}

public int paintWalls(int[] cost, int[] time) {
    int n=cost.length;
    int[] dp=new int[n+1];
    for(int i=n;i>=0;i--) {
        int[] curr=new int[n+1];
        for(int rem=0;rem<=n;rem++) {
            if(rem==0) {
                curr[rem]=0;
                continue;
            }
            if(i==n) {
                curr[rem]=Integer.MAX_VALUE/2;
                continue;
            }
            int notTake=dp[rem];
            int take=cost[i]+dp[Math.max(0, rem-time[i]-1)];
            curr[rem]=Math.min(take, notTake);
        }
        dp=curr;
    }
    return dp[n];
}

void main() {
    int[] cost = {1,2,3,2}, time = {1,2,3,2};
    IO.println(paintWalls(cost, time));
}