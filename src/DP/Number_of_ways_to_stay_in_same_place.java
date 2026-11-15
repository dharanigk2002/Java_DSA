/*
Number of Ways to Stay in the Same Place After Some Steps

You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left, 1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).

Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps. Since the answer may be too large, return it modulo 109 + 7.

https://leetcode.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/description/
*/

private Integer[][] dp;
private static final int MOD = 1_000_000_007;

private int f(int steps, int len, int i) {
    if (i < 0 || i == len)
        return 0;

    if (steps == 0)
        return dp[0][i]=i == 0 ? 1 : 0;

    if (dp[steps][i] != null)
        return dp[steps][i];

    long sum = f(steps - 1, len, i);
    sum += f(steps - 1, len, i - 1);
    sum += f(steps - 1, len, i + 1);

    return dp[steps][i] = (int)(sum % MOD);
}

public int numWays(int steps, int arrLen) {
    int maxPos = Math.min(steps / 2, arrLen - 1);
    dp = new Integer[steps + 1][maxPos + 1];
    return f(steps, maxPos + 1, 0);
}

void main(String[] args) {
    int steps = 3, arrLen = 2;
    IO.println(numWays(steps, arrLen));
}
