/*
Min Cost Climbing Stairs

You are given an integer array cost where cost[i] is the cost of ith step on a staircase.

Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the staircase, which is the position just past the last step (index cost.length).

https://leetcode.com/problems/min-cost-climbing-stairs/description/
*/

private Integer[] dp;
private int f(int[] cost, int i) {
    int n=cost.length;
    if(dp[i]!=null)
        return dp[i];
    if(i==n)
        return dp[i]=0;
    int min=Integer.MAX_VALUE;
    min=Math.min(min, cost[i]+f(cost, i+1));
    return dp[i]=Math.min(min, cost[i]+f(cost, Math.min(i+2, n)));
}

public int minCostClimbingStairs(int[] cost) {
    int n=cost.length;
    int next1=0, next2=0;
    for(int i=n-1;i>=0;i--) {
        int curr=cost[i]+Math.min(next1, next2);
        next2=next1;
        next1=curr;
    }
    return Math.min(next1, next2);
}

void main() {
    int[] cost = {1,100,1,1,1,100,1,1,100,1};
    IO.println(minCostClimbingStairs(cost));
}