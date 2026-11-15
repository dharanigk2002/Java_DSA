/*
Maximum Strength of K Disjoint Subarrays

You are given an array of integers nums with length n, and a positive odd integer k.

Select exactly k disjoint subarrays sub1, sub2, ..., subk from nums such that the last element of subi appears before the first element of sub{i+1} for all 1 <= i <= k-1. The goal is to maximize their combined strength.

The strength of the selected subarrays is defined as:

strength = k * sum(sub1)- (k - 1) * sum(sub2) + (k - 2) * sum(sub3) - ... - 2 * sum(sub{k-1}) + sum(subk)

where sum(subi) is the sum of the elements in the i-th subarray.

Return the maximum possible strength that can be obtained from selecting exactly k disjoint subarrays from nums.

Note that the chosen subarrays don't need to cover the entire array.

https://leetcode.com/problems/maximum-strength-of-k-disjoint-subarrays/description/
*/

private static final long NEG_INF = (long)-1e15;
private Long[][][] dp;

private long f(int[] nums, int i, int k, int isOpen) {
    int n = nums.length;
    if(dp[i][k][isOpen]!=null)
        return dp[i][k][isOpen];

    if (k == 0)
        return dp[i][k][isOpen]=isOpen == 1 ? NEG_INF : 0;

    if (i == n)
        return dp[i][k][isOpen]=NEG_INF;

    long notTake = NEG_INF, take = NEG_INF;

    // sign depends on the subarray's position from the start, not on remaining k
    int sign = (k % 2 == 1) ? 1 : -1;
    long weight = (long) nums[i] * k * sign;

    if (isOpen == 1) {
        // Continue current subarray
        take = weight + f(nums, i + 1, k, 1);
        // End current subarray after this element
        take = Math.max(take, weight + f(nums, i + 1, k - 1, 0));
    } else {
        // Skip current element
        notTake = f(nums, i + 1, k, 0);
        // Start a new subarray here
        take = weight + f(nums, i + 1, k, 1);
        take = Math.max(take, weight + f(nums, i + 1, k-1, 0));
    }

    return dp[i][k][isOpen]=Math.max(take, notTake);
}

public long maximumStrength(int[] nums, int k) {
    int n = nums.length;
    dp=new Long[n+1][k+1][2];
    return f(nums, 0, k, 0);
}

public long bottomUpOptimized(int[] nums, int k) {
    int n = nums.length;
    long[][] dp=new long[k+1][2];
    for(int i=n;i>=0;i--) {
        long[][] curr=new long[k+1][2];
        curr[0]=new long[]{0, NEG_INF};
        for(int j=1;j<=k;j++) {
            if (i == n) {
                curr[j]=new long[]{NEG_INF, NEG_INF};
                continue;
            }
            int sign = (j % 2 == 1) ? 1 : -1;
            long weight = (long) nums[i] * j * sign;
            curr[j][1] = weight+Math.max(dp[j][1], dp[j - 1][0]);
            curr[j][0] = Math.max(dp[j][0], weight + Math.max(dp[j][1], dp[j-1][0]));
        }
        dp=curr;
    }
    return dp[k][0];
}

public long bottomUp(int[] nums, int k) {
    int n = nums.length;
    long[][][] dp=new long[n+1][k+1][2];
    for(int i=n;i>=0;i--) {
        for(int j=0;j<=k;j++) {
            for(int isOpen=1;isOpen>=0;isOpen--) {
                if (j == 0) {
                    dp[i][j][isOpen]=isOpen == 1 ? NEG_INF : 0;
                    continue;
                }

                if (i == n) {
                    dp[i][j][isOpen]=NEG_INF;
                    continue;
                }

                long notTake = NEG_INF, take = NEG_INF;

                // sign depends on the subarray's position from the start, not on remaining k
                int sign = (j % 2 == 1) ? 1 : -1;
                long weight = (long) nums[i] * j * sign;

                if (isOpen == 1) {
                    // Continue current subarray
                    take = weight + dp[i + 1][j][1];
                    // End current subarray after this element
                    take = Math.max(take, weight + dp[i + 1][j - 1][0]);
                } else {
                    // Skip current element
                    notTake = dp[i + 1][j][0];
                    // Start a new subarray here
                    take = weight + dp[i + 1][j][1];
                    take = Math.max(take, weight + dp[i + 1][j-1][0]);
                }
                dp[i][j][isOpen]=Math.max(take, notTake);
            }
        }
    }
    return dp[0][k][0];
}

void main() {
    int[] nums = {1,2,3,-1,2};
    int k = 3;
    IO.println(bottomUpOptimized(nums, k));
}