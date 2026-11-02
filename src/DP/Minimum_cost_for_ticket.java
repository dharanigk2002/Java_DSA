/*
Minimum Cost For Tickets

You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.

Train tickets are sold in three different ways:

a 1-day pass is sold for costs[0] dollars,
a 7-day pass is sold for costs[1] dollars, and
a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.

https://leetcode.com/problems/minimum-cost-for-tickets/description/
*/

public int mincostTickets(int[] days, int[] costs) {
    int n=days.length;
    int[] dp=new int[n+1];
    dp[n]=0;
    for(int i=n-1;i>=0;i--) {
        int minCost=costs[0]+dp[i+1];
        int j=i;
        while(j<n && days[j]<days[i]+7)
            j++;
        minCost=Math.min(minCost, costs[1]+dp[j]);
        j=i;
        while(j<n && days[j]<days[i]+30)
            j++;
        dp[i]=Math.min(minCost, costs[2]+dp[j]);
    }
    return dp[0];
}

private Integer[] dp;
private int f(int[] days, int[] costs, int i) {
    int n=days.length;
    if(dp[i]!=null)
        return dp[i];
    if(i==n)
        return dp[n]=0;
    int minCost=costs[0]+f(days, costs, i+1);
    int j=i;
    while(j<n && days[j]<days[i]+7)
        j++;
    minCost=Math.min(minCost, costs[1]+f(days, costs, j));
    j=i;
    while(j<n && days[j]<days[i]+30)
        j++;
    return dp[i]=Math.min(minCost, costs[2]+f(days, costs, j));
}

void main() {
    int[] days = {1,4,6,7,8,20}, costs = {2,7,15};
    int n=days.length;
    dp=new Integer[n+1];
    IO.println(mincostTickets(days, costs));
}