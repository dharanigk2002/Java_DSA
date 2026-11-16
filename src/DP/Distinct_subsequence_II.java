/*
Distinct Subsequences II

Given a string s, return the number of distinct non-empty subsequences of s. Since the answer may be very large, return it modulo 109 + 7.

A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (i.e., "ace" is a subsequence of "abcde" while "aec" is not.

https://leetcode.com/problems/distinct-subsequences-ii/description/
*/

private static final int MOD=1_000_000_007;
private int[] lastSeen;
private Integer[] dp;

private final int solve(int n) {
    if(dp[n]!=null)
        return dp[n];
    if(n==0)
        return dp[n]=1;
    long take=2l*solve(n-1);
    if(lastSeen[n]!=0)
        take-=solve(lastSeen[n]-1);
    return dp[n]=(int)(take%MOD);
}

public int distinctSubseqII(String s) {
    int n=s.length();
    lastSeen=new int[n+1];
    int[] dp=new int[n+1];
    int[] prev=new int[26];
    for(int i=1;i<=n;i++) {
        int idx=s.charAt(i-1)-97;
        lastSeen[i]=prev[idx];
        prev[idx]=i;
    }
    dp[0]=1;
    for(int j=1;j<=n;j++) {
        long take=2l*dp[j-1];
        if(lastSeen[j]!=0)
            take-=dp[lastSeen[j]-1];
        dp[j]=(int)(take%MOD);
    }
    return (dp[n]-1+MOD)%MOD;
}

void main() {
    String s = "aba";
    IO.println(distinctSubseqII(s));
}