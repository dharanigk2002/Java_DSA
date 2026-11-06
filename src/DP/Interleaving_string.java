/*
Interleaving String

Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.

An interleaving of two strings s and t is a configuration where s and t are divided into n and m substrings respectively, such that:

s = s1 + s2 + ... + sn
t = t1 + t2 + ... + tm
|n - m| <= 1
The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
Note: a + b is the concatenation of strings a and b.

https://leetcode.com/problems/interleaving-string/description/
*/

private Boolean[][][] dp;
private boolean f(String a, String b, String c, int i, int j, int k) {
    if(dp[i][j][k]!=null)
        return dp[i][j][k];
    if(k==c.length())
        return dp[i][j][k]=i == a.length() && j == b.length();
    if(i<a.length() && a.charAt(i)==c.charAt(k) && f(a, b, c, i+1, j, k+1))
        return dp[i][j][k]=true;
    if(j<b.length() && b.charAt(j)==c.charAt(k) && f(a, b, c, i, j+1, k+1))
        return dp[i][j][k]=true;
    return dp[i][j][k]=false;
}

public boolean isInterleave(String s1, String s2, String s3) {
    int n1=s1.length(), n2=s2.length(), n3=s3.length();
    boolean[][][] dp=new boolean[n1+1][n2+1][n3+1];
    for(int i=n1;i>=0;i--)
        for(int j=n2;j>=0;j--)
            for(int k=n3;k>=0;k--) {
                if(k==n3)
                    dp[i][j][k]=i == n1 && j == n2;
                else if(i<n1 && s1.charAt(i)==s3.charAt(k) && dp[i+1][j][k+1])
                    dp[i][j][k]=true;
                else if(j<n2 && s2.charAt(j)==s3.charAt(k) && dp[i][j+1][k+1])
                    dp[i][j][k]=true;
                else
                    dp[i][j][k]=false;
            }
    return dp[0][0][0];
}

void main() {
    String s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac";
    IO.println(isInterleave(s1, s2, s3));
}