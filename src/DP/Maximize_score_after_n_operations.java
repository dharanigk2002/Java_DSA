/*
Maximize Score After N Operations

You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.

In the ith operation (1-indexed), you will:

Choose two elements, x and y.
Receive a score of i * gcd(x, y).
Remove x and y from nums.
Return the maximum score you can receive after performing n operations.

The function gcd(x, y) is the greatest common divisor of x and y.

https://leetcode.com/problems/maximize-score-after-n-operations/description/
*/
private int gcd(int a, int b) {
    // O(log n)
    while (a != 0 && b != 0) {
        if (a > b)
            a %= b;
        else
            b %= a;
    }
    return a > 0 ? a : b;
}
private Integer[] dp;
private int f(int[] nums, int vis) {
    if(dp[vis]!=null)
        return dp[vis];
    int n = nums.length;
    if (vis==(1<<n)-1)
        return dp[vis]=0;
    int max = 0;
    int op=Integer.bitCount(vis)/2+1;
    for (int i = 0; i < n - 1; i++) {
        if (((vis>>i)&1)==1)
            continue;
        for (int j = i + 1; j < n; j++) {
            if (((vis>>j)&1)==1)
                continue;
            int newMask=vis | (1<<i) | (1<<j);
            int score = gcd(nums[i], nums[j]) * op;
            max = Math.max(max, score + f(nums, newMask));
        }
    }
    return dp[vis]=max;
}

public int maxScore(int[] nums) {
    int n=nums.length;
    int[] dp=new int[1<<n];
    for(int vis=(1<<n)-2;vis>=0;vis--) {
        int max = 0;
        int op=Integer.bitCount(vis)/2+1;
        for (int i = 0; i < n - 1; i++) {
            if (((vis>>i)&1)==1)
                continue;
            for (int j = i + 1; j < n; j++) {
                if (((vis>>j)&1)==1)
                    continue;
                int newMask=vis | (1<<i) | (1<<j);
                int score = gcd(nums[i], nums[j]) * op;
                max = Math.max(max, score + dp[newMask]);
            }
        }
        dp[vis]=max;
    }
    return dp[0];
}

void main() {
    int[] nums = {1,2,3,4,5,6};
    int n=nums.length;
    dp=new Integer[(1<<n)+1];
    IO.println(maxScore(nums));
}