/*
Perfect Squares

Given an integer n, return the least number of perfect square numbers that sum to n.

A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

https://leetcode.com/problems/perfect-squares/description/
*/

private int f(int n) {
    if(n==0)
        return 0;
    int steps=Integer.MAX_VALUE;
    for(int next=1;next*next<=n;next++) {
        steps=Math.min(steps, 1+f(n-next*next));
    }
    return steps;
}

public int numSquares(int n) {
    return f(n);
}

public int bottomUp(int n) {
    int[] dp=new int[n+1];
    for(int i=1;i<=n;i++) {
        int steps=Integer.MAX_VALUE;
        for(int next=1;next*next<=i;next++)
            steps=Math.min(steps, 1+dp[i-next*next]);
        dp[i]=steps;
    }
    return dp[n];
}

void main() {
    int n = 12;
    IO.println(bottomUp(n));
}