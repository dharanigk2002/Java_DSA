/*
Find X Value of Array I

You are given an array of positive integers nums, and a positive integer k.

You are allowed to perform an operation once on nums, where in each operation you can remove any non-overlapping prefix and suffix from nums such that nums remains non-empty.

You need to find the x-value of nums, which is the number of ways to perform this operation so that the product of the remaining elements leaves a remainder of x when divided by k.

Return an array result of size k where result[x] is the x-value of nums for 0 <= x <= k - 1.

A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.

A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.

Note that the prefix and suffix to be chosen for the operation can be empty.

https://leetcode.com/problems/find-x-value-of-array-i/description/
*/

public long[] brute(int[] nums, int k) {
    long[] res = new long[k];
    for (int i = 0; i < nums.length; i++) {
        long product = 1;
        for (int j = i; j < nums.length; j++) {
            product = (product * nums[j]) % k;
            res[(int) product]++;
        }
    }
    return res;
}

public long[] optimal(int[] nums, int k) {
    long[] res = new long[k];
    long[] dp = new long[k];

    for(int num:nums) {
        int mod=num%k;
        long[] next=new long[k];
        next[mod]++;
        for(int i=0;i<k;i++)
            next[(i*mod)%k]+=dp[i];
        dp=next;
        for(int i=0;i<k;i++)
            res[i]+=dp[i];
    }
    return res;
}

void main() {
    int[] nums = new int[]{1, 2, 3, 4, 5};
    int k = 3;

    long[] res=optimal(nums, k);
    IO.println(Arrays.toString(res));
}