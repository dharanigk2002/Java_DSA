/*
Uncrossed Lines

You are given two integer arrays nums1 and nums2. We write the integers of nums1 and nums2 (in the order they are given) on two separate horizontal lines.

We may draw connecting lines: a straight line connecting two numbers nums1[i] and nums2[j] such that:

nums1[i] == nums2[j], and
the line we draw does not intersect any other connecting (non-horizontal) line.
Note that a connecting line cannot intersect even at the endpoints (i.e., each number can only belong to one connecting line).

Return the maximum number of connecting lines we can draw in this way.

https://leetcode.com/problems/uncrossed-lines/description/
*/
private Integer[][] dp;
private int f(int[] nums1, int[] nums2, int i, int j) {
    if(dp[i][j]!=null)
        return dp[i][j];
    if(i==0 || j==0)
        return dp[i][j]=0;
    if(nums1[i-1]==nums2[j-1])
        return dp[i][j]=1+f(nums1, nums2, i-1, j-1);
    return dp[i][j]=Math.max(f(nums1, nums2, i-1, j), f(nums1, nums2, i, j-1));
}

public int maxUncrossedLines(int[] nums1, int[] nums2) {
    int n=nums1.length, m=nums2.length;
    int[][] dp=new int[n+1][m+1];
    for(int i=1;i<=n;i++)
        for(int j=1;j<=m;j++)
            if(nums1[i-1]==nums2[j-1])
                dp[i][j]=1+dp[i-1][j-1];
            else
                dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
    return dp[n][m];
}

void main() {
    int[] nums1 = {2,5,1,2,5}, nums2 = {10,5,2,1,5,2};
    int n=nums1.length, m=nums2.length;
    dp=new Integer[n+1][m+1];
    IO.println(f(nums1, nums2, n, m));
}