/*
Partition Array for Maximum Sum

Given an integer array arr, partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.

Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

https://leetcode.com/problems/partition-array-for-maximum-sum/description/
*/

private Integer[] dp;
private int f(int[] arr, int k, int idx) {
    int n=arr.length;
    if(dp[idx]!=null)
        return dp[idx];
    if(idx==n)
        return dp[idx]=0;
    int sum=0, max=0;
    for(int i=idx;i<Math.min(idx+k, n);i++) {
        max=Math.max(max, arr[i]);
        sum=Math.max(sum, ((i-idx+1)*max)+f(arr, k, i+1));
    }
    return dp[idx]=sum;
}

public int maxSumAfterPartitioning(int[] arr, int k) {
    int n=arr.length;
    int[] dp=new int[n+1];
    for(int idx=n-1;idx>=0;idx--) {
        int sum=0, max=0;
        for(int i=idx;i<Math.min(idx+k, n);i++) {
            max=Math.max(max, arr[i]);
            sum=Math.max(sum, ((i-idx+1)*max)+dp[i+1]);
        }
        dp[idx]=sum;
    }
    return dp[0];
}

void main() {
    int[] arr = {1, 15, 7, 9, 2, 5, 10};
    int k = 3;
    IO.println(maxSumAfterPartitioning(arr, k));
}