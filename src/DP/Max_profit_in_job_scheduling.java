/*
Maximum Profit in Job Scheduling

We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X.

https://leetcode.com/problems/maximum-profit-in-job-scheduling/description/
*/

private Integer[] dp;

private int memo(int[][] jobs, int i) {
    if(dp[i]!=null)
        return dp[i];
    if(i==jobs.length)
        return dp[i]=0;
    int skip=memo(jobs, i+1);
    int pick=jobs[i][2]+memo(jobs, lb(jobs, i+1, jobs[i][1]));
    return dp[i]=Math.max(skip, pick);
}

private int bottomUp(int[][] jobs) {
    int n=jobs.length;
    int[] dp=new int[n+1];
    for(int i=n-1;i>=0;i--)
        dp[i]=Math.max(dp[i+1], jobs[i][2]+dp[lb(jobs, i+1, jobs[i][1])]);
    return dp[0];
}

private int recur(int[][] jobs, int i) {
    if(i==jobs.length)
        return 0;
    int skip=recur(jobs, i+1);
    int pick=jobs[i][2]+recur(jobs, lb(jobs, i+1, jobs[i][1]));
    return Math.max(skip, pick);
}

private int lb(int[][] jobs, int l, int target) {
    int r=jobs.length;
    while(l<r) {
        int mid=l+(r-l)/2;
        if(jobs[mid][0]>=target)
            r=mid;
        else
            l=mid+1;
    }
    return l;
}

public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
    int n=profit.length;
    int[][] jobs=new int[n][3];
    for(int i=0;i<n;i++)
        jobs[i]=new int[]{startTime[i], endTime[i], profit[i]};
    Arrays.sort(jobs, Comparator.comparingInt(a -> a[0]));
    dp=new Integer[n+1];
    return memo(jobs, 0);
}

void main() {
    int[] startTime = {1,2,3,4,6}, endTime = {3,5,10,6,9}, profit = {20,20,100,70,60};
    IO.println(jobScheduling(startTime, endTime, profit));
}
