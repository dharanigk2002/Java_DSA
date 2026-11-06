/*
Strange Printer

There is a strange printer with the following two special properties:

The printer can only print a sequence of the same character each time.
At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
Given a string s, return the minimum number of turns the printer needed to print it.

https://leetcode.com/problems/strange-printer/description/
*/

private Integer[][] dp;
private int f(String s, int l, int r) {
        if(l>r)
            return 0;
        if(dp[l][r]!=null)
            return dp[l][r];
        if(l==r)
            return dp[l][r]=1;
        int i=l+1;
        while(i<=r && s.charAt(i)==s.charAt(l))
            i++;
        if(i==r+1)
            return 1;
        int op1=1+f(s, i, r);
        int op2=Integer.MAX_VALUE;
        for(int j=i;j<=r;j++)
            if(s.charAt(j)==s.charAt(l)) {
                op2=Math.min(op2, f(s, i, j-1)+f(s, j, r));
            }
        return dp[l][r]=Math.min(op1, op2);
    }

public int strangePrinter(String s) {
    int n=s.length();
    int[][] dp=new int[n+1][n+1];
    for(int i=0;i<n;i++)
        dp[i][i]=1;
    for(int l=n-1;l>=0;l--) {
        for(int r=l;r<n;r++) {
            int i=l+1;
            while(i<=r && s.charAt(i)==s.charAt(l))
                i++;
            if(i==r+1) {
                dp[l][r]=1;
                continue;
            }
            int op1=1+dp[i][r];
            int op2=Integer.MAX_VALUE;
            for(int j=i;j<=r;j++)
                if(s.charAt(j)==s.charAt(l)) {
                    op2=Math.min(op2,dp[i][j-1]+dp[j][r]);
                }
            dp[l][r]=Math.min(op1, op2);
        }
    }
    return dp[0][n-1];
}

void main() {
    String s="aba";
    int n=s.length();
    dp=new Integer[n+1][n+1];
    IO.println(f(s, 0, n-1));
}