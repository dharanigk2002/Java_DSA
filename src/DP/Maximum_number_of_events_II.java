/*
Maximum Number of Events That Can Be Attended II

You are given an array of events where events[i] = [startDayi, endDayi, valuei]. The ith event starts at startDayi and ends at endDayi, and if you attend this event, you will receive a value of valuei. You are also given an integer k which represents the maximum number of events you can attend.

You can only attend one event at a time. If you choose to attend an event, you must attend the entire event. Note that the end day is inclusive: that is, you cannot attend two events where one of them starts and the other ends on the same day.

Return the maximum sum of values that you can receive by attending events.

https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/description/
*/

private Integer[][] dp;
private int f(int[][] events, int i, int k) {
    if(dp[i][k]!=null)
        return dp[i][k];
    if(i==events.length)
        return dp[i][k]=0;
    int skip=f(events, i+1, k);
    int pick=0;
    int nextIdx=upper_bound(events, i+1, events[i][1]);
    if(k>0)
        pick=events[i][2]+f(events, nextIdx, k-1);
    return dp[i][k]=Math.max(skip, pick);
}

private int upper_bound(int[][] e, int l, int target) {
    int r=e.length;
    while(l<r) {
        int mid=l+(r-l)/2;
        if(e[mid][0]>target)
            r=mid;
        else
            l=mid+1;
    }
    return l;
}

public int maxValue(int[][] events, int k) {
    Arrays.sort(events, (a, b)->a[0]-b[0]);
    int n=events.length;
    int[][] dp=new int[n+1][k+1];
    for(int i=n-1;i>=0;i--) {
        for(int j=1;j<=k;j++) {
            int skip=dp[i+1][j];
            int nextIdx=upper_bound(events, i+1, events[i][1]);
            int pick=events[i][2]+dp[nextIdx][j-1];
            dp[i][j]=Math.max(skip, pick);
        }
    }
    return dp[0][k];
}

void main() {
    int[][] events = {{1,2,4},{3,4,3},{2,3,1}};
    int k = 2;
    IO.println(maxValue(events, k));
}