/*
Flip String to Monotone Increasing

A binary string is monotone increasing if it consists of some number of 0's (possibly none), followed by some number of 1's (also possibly none).

You are given a binary string s. You can flip s[i] changing it from 0 to 1 or from 1 to 0.

Return the minimum number of flips to make s monotone increasing.

https://leetcode.com/problems/flip-string-to-monotone-increasing/description/
*/

private Integer[][] dp;
private int f(String s, int i, int prev) {
    if(dp[i][prev]!=null)
        return dp[i][prev];
    if(i==s.length())
        return dp[i][prev]=0;
    int flip=Integer.MAX_VALUE, noFlip=Integer.MAX_VALUE;
    if(s.charAt(i)=='0') {
        if(prev==0) {
            flip=1+f(s, i+1, 1);
            noFlip=f(s, i+1, 0);
        } else
            flip=1+f(s, i+1, 1);
    } else {
        if(prev==0) {
            flip=1+f(s, i+1, 0);
            noFlip=f(s, i+1, 1);
        } else
            noFlip=f(s, i+1, 1);
    }
    return dp[i][prev]=Math.min(flip, noFlip);
}

private int bottomUp(String s) {
    int n=s.length();
    int[][] dp=new int[n+1][2];
    dp[n][0]=dp[n][1]=0;
    for(int i=n-1;i>=0;i--) {
        for(int prev=1;prev>=0;prev--) {
            int flip=Integer.MAX_VALUE, noFlip=Integer.MAX_VALUE;
            if(s.charAt(i)=='0') {
                if(prev==0) {
                    flip=1+dp[i+1][1];
                    noFlip=dp[i+1][0];
                } else
                    flip=1+dp[i+1][1];
            } else {
                if(prev==0) {
                    flip=1+dp[i+1][0];
                    noFlip=dp[i+1][1];
                } else
                    noFlip=dp[i+1][1];
            }
            dp[i][prev]=Math.min(flip, noFlip);
        }
    }
    return dp[0][0];
}

private int spaceOpti(String s) {
    int n=s.length();
    int[] dp=new int[2];
    dp[0]=dp[1]=0;
    for(int i=n-1;i>=0;i--) {
        int[] curr=new int[2];
        for(int prev=1;prev>=0;prev--) {
            int flip=Integer.MAX_VALUE, noFlip=Integer.MAX_VALUE;
            if(s.charAt(i)=='0') {
                if(prev==0) {
                    flip=1+dp[1];
                    noFlip=dp[0];
                } else
                    flip=1+dp[1];
            } else {
                if(prev==0) {
                    flip=1+dp[0];
                    noFlip=dp[1];
                } else
                    noFlip=dp[1];
            }
            curr[prev]=Math.min(flip, noFlip);
        }
        dp=curr;
    }
    return dp[0];
}

private int optimized(String s) {
    int n=s.length();
    int dp0=0, dp1=0;
    for(int i=n-1;i>=0;i--)
        if(s.charAt(i)=='0') {
            dp1++;
            dp0=Math.min(dp0, dp1);
        } else
            dp0=Math.min(1+dp0, dp1);
    return dp0;
}

public int minFlipsMonoIncr(String s) {
    int n=s.length();
    return optimized(s);
}

void main() {
    String s="00110";
    IO.println(minFlipsMonoIncr(s));
}