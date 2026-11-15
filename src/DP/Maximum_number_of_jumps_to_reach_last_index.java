/*
Maximum Number of Jumps to Reach the Last Index

You are given a 0-indexed array nums of n integers and an integer target.

You are initially positioned at index 0. In one step, you can jump from index i to any index j such that:

0 <= i < j < n
-target <= nums[j] - nums[i] <= target
Return the maximum number of jumps you can make to reach index n - 1.

If there is no way to reach index n - 1, return -1.

https://leetcode.com/problems/maximum-number-of-jumps-to-reach-the-last-index/description/
*/

private static final int MIN=-1_000_000_000;
private Integer[] dp;
private int f(int[] nums, int i, int target) {
    int n=nums.length;
    if(dp[i]!=null)
        return dp[i];
    if(i==n-1)
        return dp[i]=0;
    int max=MIN;
    for(int j=i+1;j<n;j++)
        if(Math.abs(nums[i]-nums[j])<=target)
            max=Math.max(max, 1+f(nums, j, target));
    return dp[i]=max;
}

public int maximumJumps(int[] nums, int target) {
    int n=nums.length;
    int[] dp=new int[n];
    for(int i=n-2;i>=0;i--) {
        int max=MIN;
        for(int j=i+1;j<n;j++)
            if(Math.abs(nums[i]-nums[j])<=target)
                max=Math.max(max, 1+dp[j]);
        dp[i]=max;
    }
    int ways=dp[0];
    return ways<0 ? -1 : ways;
}

void main() {
    int[] nums = new int[]{1, 3, 6, 4, 1, 2};
    int target = 2;
    IO.println(maximumJumps(nums, target));
}