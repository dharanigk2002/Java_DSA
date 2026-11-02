/*
Arithmetic Slices II - Subsequence

Given an integer array nums, return the number of all the arithmetic subsequences of nums.

A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, [1, 3, 5, 7, 9], [7, 7, 7, 7], and [3, -1, -5, -9] are arithmetic sequences.
For example, [1, 1, 2, 5, 7] is not an arithmetic sequence.
A subsequence of an array is a sequence that can be formed by removing some elements (possibly none) of the array.

For example, [2,5,10] is a subsequence of [1,2,1,2,4,1,5,10].
The test cases are generated so that the answer fits in 32-bit integer.

https://leetcode.com/problems/arithmetic-slices-ii-subsequence/
*/

public int numberOfArithmeticSlices(int[] nums) {
    int n=nums.length;
    Map<Long, Integer>[] dp=new Map[n];
    for(int i=0;i<n;i++)
        dp[i]=new HashMap();
    long result=0;
    for(int i=1;i<n;i++)
        for(int j=0;j<i;j++) {
            long diff=(long)nums[i]-nums[j];
            int freq=dp[j].getOrDefault(diff, 0);
            dp[i].put(diff, dp[i].getOrDefault(diff, 0)+freq+1);
            result+=freq;
        }
    return (int)result;
}

void main() {
    int[] nums = {2, 4, 6, 8, 10};
    IO.println(numberOfArithmeticSlices(nums));
}