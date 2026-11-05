/*
Longest Arithmetic Subsequence

Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.

Note that:

A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).

https://leetcode.com/problems/longest-arithmetic-subsequence/description/
*/

public int longestArithSeqLength(int[] nums) {
    int n=nums.length, max=2;
    int[][] dp=new int[n][1001];
    for(int i=0;i<n;i++) {
        for(int j=0;j<i;j++) {
            int diff=nums[i]-nums[j]+500;
            dp[i][diff]=Math.max(dp[j][diff]+1, 2);
            max=Math.max(max, dp[i][diff]);
        }
    }
    return max;
}

void main() {
    int[] nums = {3, 6, 9, 12};
    IO.println(longestArithSeqLength(nums));
}