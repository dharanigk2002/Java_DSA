/*
Integer Break

Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

https://leetcode.com/problems/integer-break/description/
*/

private Integer[] dp;
private int f(int n) {
    if(dp[n]!=null)
        return dp[n];
    if(n==1)
        return dp[n]=1;
    int max=Integer.MIN_VALUE;
    for(int i=1;i<n;i++)
        max=Math.max(max, i*Math.max(n-i, f(n-i)));
    return dp[n]=max;
}

public int integerBreak(int n) {
    dp=new Integer[n+1];
    return f(n);
}

void main() {
    int n=10;
    IO.println(integerBreak(n));
}