/*
Minimum Difficulty of a Job Schedule

You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.

You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.

https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/description/
*/

private Integer[][] dp;
private int f(int[] job, int d, int idx) {
    int n=job.length;
    int max=0;
    if(dp[d][idx]!=null)
        return dp[d][idx];
    if(d==1) {
        for(int i=idx;i<n;i++)
            max=Math.max(max, job[i]);
        return dp[d][idx]=max;
    }
    int mini=300001;
    for(int i=idx;i<=n-d;i++) {
        max=Math.max(max, job[i]);
        mini=Math.min(mini, max+f(job, d-1, i+1));
    }
    return dp[d][idx]=mini;
}

private static final int MAX=1_000_000_001;
public int minDifficulty(int[] diff, int d) {
    int n=diff.length;
    if(d>n)
        return -1;
    int[][] dp=new int[n][d+1];
    for(int i=0;i<n;i++) {
        int max=0;
        for(int j=i;j<n;j++)
            max=Math.max(max, diff[j]);
        dp[i][1]=max;
    }
    for(int i=n-1;i>=0;i--) {
        for(int k=2;k<=d;k++) {
            int max=-1, minSum=MAX;
            for(int j=i;j<=n-k;j++) {
                max=Math.max(max, diff[j]);
                minSum=Math.min(minSum, max+dp[j+1][k-1]);
            }
            dp[i][k]=minSum;
        }
    }
    return dp[0][d];
}

void main() {
    int[] jobDifficulty = {6,5,4,3,2,1};
    int d = 2;
    dp=new Integer[d+1][jobDifficulty.length+1];
    IO.println(f(jobDifficulty, d, 0));
}