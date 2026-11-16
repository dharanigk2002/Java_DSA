/*
Distinct Subsequences

Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

https://leetcode.com/problems/distinct-subsequences/description/
*/

private Integer[][] dp;
private int solve(String s, String t, int i, int j) {
    if(dp[i][j]!=null)
        return dp[i][j];
    int n=s.length(), m=t.length();
    if(j==m)
        return dp[i][j]=1;
    if(i==n)
        return dp[i][j]=0;
    int ways=0;
    if(s.charAt(i)==t.charAt(j))
        ways+=solve(s, t, i+1, j+1);
    ways+=solve(s, t, i+1, j);
    return dp[i][j]=ways;
}

public int numDistinct(String s, String t) {
    int n=s.length(), m=t.length();
    int[] dp=new int[m+1];
    dp[m]=1;
    for(int i=n-1;i>=0;i--) {
        int[] curr=new int[m+1];
        curr[m]=1;
        for(int j=m-1;j>=0;j--) {
            int ways=0;
            if(s.charAt(i)==t.charAt(j))
                ways+=dp[j+1];
            ways+=dp[j];
            curr[j]=ways;
        }
        dp=curr;
    }
    return dp[0];
}

void main() {
    String s = "babgbag";
    String t = "bag";
    IO.println(numDistinct(s, t));
}