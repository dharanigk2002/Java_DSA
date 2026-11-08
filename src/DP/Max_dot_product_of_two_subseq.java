/*
Max Dot Product of Two Subsequences

Given two arrays nums1 and nums2.

Return the maximum dot product between non-empty subsequences of nums1 and nums2 with the same length.

A subsequence of an array is a new array which is formed from the original array by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, [2,3,5] is a subsequence of [1,2,3,4,5] while [1,5,3] is not).

https://leetcode.com/problems/max-dot-product-of-two-subsequences/description/
*/

private static final int MIN=-1_000_000_000;
private Integer[][] dp;
private int f(int[] a, int[] b, int i, int j) {
    if(dp[i][j]!=null)
        return dp[i][j];
    if(i==a.length || j==b.length)
        return dp[i][j]=MIN;
    int prod=a[i]*b[j];
    int op1=a[i]*b[j]+f(a, b, i+1, j+1);
    int op2=f(a, b, i, j+1);
    int op3=f(a, b, i+1, j);
    return dp[i][j]=Math.max(prod, Math.max(op1, Math.max(op2, op3)));
}

public int maxDotProduct(int[] a, int[] b) {
    int n=a.length, m=b.length;
    int[][] dp=new int[n+1][m+1];
    Arrays.fill(dp[n], MIN);
    for(int i=0;i<=n;i++)
        dp[i][m]=MIN;
    for(int i=n-1;i>=0;i--)
        for(int j=m-1;j>=0;j--) {
            int prod=a[i]*b[j];
            int op1=a[i]*b[j]+dp[i+1][j+1];
            int op2=dp[i][j+1];
            int op3=dp[i+1][j];
            dp[i][j]=Math.max(prod, Math.max(op1, Math.max(op2, op3)));
        }
    return dp[0][0];
}

void main() {
    int[] nums1 = {2,1,-2,5}, nums2 = {3,0,-6};
    IO.println(maxDotProduct(nums1, nums2));
}