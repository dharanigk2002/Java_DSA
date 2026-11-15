/*
Constrained Subsequence Sum

Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.

A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

https://leetcode.com/problems/constrained-subsequence-sum/description/
*/

public int constrainedSubsetSum(int[] nums, int k) {
    int n=nums.length;
    int max=Integer.MIN_VALUE;
    int[] dp=new int[n];
    for(int i=0;i<n;i++) {
        dp[i]=nums[i];
        for(int j=Math.max(0, i-k);j<i;j++)
            dp[i]=Math.max(dp[i], nums[i]+dp[j]);
        max=Math.max(max, dp[i]);
    }
    return max;
}

public int optimized(int[] nums, int k) {
    int n=nums.length;
    int max=nums[0];
    Queue<int[]> q=new PriorityQueue<>((a, b)->b[0]-a[0]);
    q.add(new int[]{nums[0], 0});
    for(int i=1;i<n;i++) {
        while(!q.isEmpty() && i-q.peek()[1]>k)
            q.poll();
        int curr=Math.max(0, q.peek()[0])+nums[i];
        max=Math.max(max, curr);
        q.add(new int[]{curr, i});
    }
    return max;
}

void main() {
    int[] nums = {10, 2, -10, 5, 20};
    int k = 2;
    IO.println(constrainedSubsetSum(nums, k));
}