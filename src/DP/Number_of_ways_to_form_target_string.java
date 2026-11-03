/*
Number of Ways to Form a Target String Given a Dictionary

You are given a list of strings of the same length words and a string target.

Your task is to form target using the given words under the following rules:

target should be formed from left to right.
To form the ith character (0-indexed) of target, you can choose the kth character of the jth string in words if target[i] = words[j][k].
Once you use the kth character of the jth string of words, you can no longer use the xth character of any string in words where x <= k. In other words, all characters to the left of or at index k become unusuable for every string.
Repeat the process until you form the string target.
Notice that you can use multiple characters from the same string in words provided the conditions above are met.

Return the number of ways to form target from words. Since the answer may be too large, return it modulo 109 + 7.

https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/description/
*/

private int MOD=1_000_000_007;
private Integer[][] dp;
private int f(int[][] freq, int i, int j, String target) {
    if(dp[i][j]!=null)
        return dp[i][j];
    if(j==target.length())
        return dp[i][j]=1;
    if(i==freq.length)
        return dp[i][j]=0;
    long np=f(freq, i+1, j, target);
    long pick=(long)freq[i][target.charAt(j)-97]*f(freq, i+1, j+1, target);
    return dp[i][j]=(int)((np+pick)%MOD);
}

public int numWays(String[] words, String target) {
    int n=words[0].length();
    int m=target.length();
    int[][] freq=new int[n][26];
    int[][] dp=new int[n+1][m+1];
    for(int i=0;i<=n;i++)
        dp[i][m]=1;
    for(String word:words)
        for(int i=0;i<n;i++)
            freq[i][word.charAt(i)-97]++;
    for(int i=n-1;i>=0;i--) {
        for(int j=m-1;j>=0;j--) {
            long np=dp[i+1][j];
            long pick=(long)freq[i][target.charAt(j)-97]*dp[i+1][j+1];
            dp[i][j]=(int)((np+pick)%MOD);
        }
    }
    return dp[0][0];
}

void main() {
    String[] words={"acca","bbbb","caca"};
    String target="aba";
    IO.println(numWays(words, target));
}