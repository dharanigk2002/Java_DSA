/*
Check if There is a Valid Partition For The Array

You are given a 0-indexed integer array nums. You have to partition the array into one or more contiguous subarrays.

We call a partition of the array valid if each of the obtained subarrays satisfies one of the following conditions:

The subarray consists of exactly 2, equal elements. For example, the subarray [2,2] is good.
The subarray consists of exactly 3, equal elements. For example, the subarray [4,4,4] is good.
The subarray consists of exactly 3 consecutive increasing elements, that is, the difference between adjacent elements is 1. For example, the subarray [3,4,5] is good, but the subarray [1,3,5] is not.
Return true if the array has at least one valid partition. Otherwise, return false.

https://leetcode.com/problems/check-if-there-is-a-valid-partition-for-the-array/description/
*/

private Boolean[] dp;
private boolean f(int[] nums, int i) {
        if(dp[i]!=null)
            return dp[i];
        int n=nums.length;
        if(i==n)
            return dp[i]=true;
        if(i+1<n && nums[i]==nums[i+1] && f(nums, Math.min(i+2, n)))
            return dp[i]=true;
        if(i+2<n && nums[i]==nums[i+1] && nums[i]==nums[i+2] && f(nums, Math.min(i+3, n)))
            return dp[i]=true;
        if(i+2<n && nums[i]==nums[i+1]-1 && nums[i+1]==nums[i+2]-1 && f(nums, Math.min(n, i+3)))
            return dp[i]=true;
        return dp[i]=false;
    }

public boolean validPartition(int[] nums) {
    boolean[] dp=new boolean[nums.length+1];
    int n=nums.length;
    dp[n]=true;
    for(int i=n-1;i>=0;i--) {
        if(i==n)
            dp[i]=true;
        else if(i+1<n && nums[i]==nums[i+1] && dp[Math.min(i+2, n)])
            dp[i]=true;
        else if(i+2<n && nums[i]==nums[i+1] && nums[i]==nums[i+2] && dp[Math.min(i+3, n)])
            dp[i]=true;
        else if(i+2<n && nums[i]==nums[i+1]-1 && nums[i+1]==nums[i+2]-1 && dp[Math.min(n, i+3)])
            dp[i]=true;
        else
            dp[i]=false;
    }
    return dp[0];
}

void main() {
    int[] nums = {4,4,4,5,6};
    IO.println(validPartition(nums));
}