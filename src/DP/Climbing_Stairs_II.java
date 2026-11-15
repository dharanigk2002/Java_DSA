/*
Climbing Stairs II

You are climbing a staircase with n + 1 steps, numbered from 0 to n.

You are also given a 1-indexed integer array costs of length n, where costs[i] is the cost of step i.

From step i, you can jump only to step i + 1, i + 2, or i + 3. The cost of jumping from step i to step j is defined as: costs[j] + (j - i)2

You start from step 0 with cost = 0.

Return the minimum total cost to reach step n.

https://leetcode.com/problems/climbing-stairs-ii/description/
*/

private Integer[] dp;
private int solve(int[] costs, int i) {
    int n=costs.length;
    if(dp[i]!=null)
        return dp[i];
    if(i==n)
        return dp[i]=0;
    int result=costs[i]+solve(costs, i+1)+1;
    if(i+2<=n)
        result=Math.min(result, costs[i+1]+solve(costs, i+2)+4);
    if(i+3<=n)
        result=Math.min(result, costs[i+2]+solve(costs, i+3)+9);
    return dp[i]=result;
}

public int climbStairs(int n, int[] costs) {
    int[] dp=new int[n+1];
    int curr=0, temp1=0, temp2=0, temp3=0;
    for(int i=n-1;i>=0;i--) {
        int result=costs[i]+temp1+1;
        if(i+2<=n)
            result=Math.min(result, costs[i+1]+temp2+4);
        if(i+3<=n)
            result=Math.min(result, costs[i+2]+temp3+9);
        curr=result;
        temp3=temp2;
        temp2=temp1;
        temp1=curr;
    }
    return curr;
}

void main() {
    int n = 4;
    int[] costs = new int[]{1, 2, 3, 4};
    IO.println(climbStairs(n, costs));
}