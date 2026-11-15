/*
2 Keys Keyboard

There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:

Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.

https://leetcode.com/problems/2-keys-keyboard/description/
*/

private int[][] memo;

private int f(int n, int isCopied, int i) {
    if (i > n) return Integer.MAX_VALUE / 2;
    if (i == n) return 0;
    if (memo[isCopied][i] != -1) return memo[isCopied][i];

    int paste = Integer.MAX_VALUE / 2;
    if (isCopied > 0) {
        paste = 1 + f(n, isCopied, isCopied + i);
    }

    int copy = Integer.MAX_VALUE / 2;
    if (isCopied != i) {
        copy = 1 + f(n, i, i);
    }

    return memo[isCopied][i] = Math.min(paste, copy);
}

public int minSteps(int n) {
    if (n == 1) return 0;
    memo = new int[n + 1][n + 1];
    for (int[] row : memo)
        Arrays.fill(row, -1);
    return f(n, 0, 1);
}

public int bottomUp(int n) {
    if(n==1)
        return 0;
    int minOps=n;
    for(int div=2;div*div<=n;div++) {
        if(n%div==0)
            minOps=Math.min(minOps, minSteps(n/div)+div);
    }
    return minOps;
}

void main() {
    int n=3;
    IO.println(bottomUp(n));
}