/*
Minimum ASCII Delete Sum for Two Strings

Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/description/
*/

private Integer[][] dp;
private int f(String a, String b, int i, int j) {
    if(dp[i][j]!=null)
        return dp[i][j];
    if(i==0 && j==0)
        return dp[i][j]=0;
    if(i==0)
        return dp[i][j]=(int)b.charAt(j-1)+f(a, b, i, j-1);
    if(j==0)
        return dp[i][j]=(int)a.charAt(i-1)+f(a, b, i-1, j);
    if(a.charAt(i-1)==b.charAt(j-1))
        return dp[i][j]=f(a, b, i-1, j-1);
    int delete1=(int)a.charAt(i-1)+f(a, b, i-1, j);
    int delete2=(int)b.charAt(j-1)+f(a, b, i, j-1);
    return dp[i][j]=Math.min(delete1, delete2);
}

public int minimumDeleteSum(String a, String b) {
        int m=a.length(), n=b.length();
        int[][] dp=new int[m+1][n+1];
        for(int i=0;i<=m;i++) {
            for(int j=0;j<=n;j++) {
                if(i==0 && j==0)
                    dp[i][j]=0;
                else if(i==0)
                    dp[i][j]=(int)b.charAt(j-1)+dp[i][j-1];
                else if(j==0)
                    dp[i][j]=(int)a.charAt(i-1)+dp[i-1][j];
                else if(a.charAt(i-1)==b.charAt(j-1))
                    dp[i][j]=dp[i-1][j-1];
                else {
                    int delete1=(int)a.charAt(i-1)+dp[i-1][j];
                    int delete2=(int)b.charAt(j-1)+dp[i][j-1];
                    dp[i][j]=Math.min(delete1, delete2);
                }
            }
        }
        return dp[m][n];
    }

void main() {
    String s1 = "sea", s2 = "eat";
    int n=s1.length(), m=s2.length();
    dp=new Integer[n+1][m+1];
    IO.println(f(s1, s2, n, m));
}