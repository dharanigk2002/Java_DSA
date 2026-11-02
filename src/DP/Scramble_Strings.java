/*
Scramble String

We can scramble a string s to get a string t using the following algorithm:

If the length of the string is 1, stop.
If the length of the string is > 1, do the following:
Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
Apply step 1 recursively on each of the two substrings x and y.
Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.

https://leetcode.com/problems/scramble-string/description/
*/

private Boolean[][][] dp;
private boolean f(String s1, String s2, int i, int j, int len) {
    if(dp[i][j][len]!=null)
        return dp[i][j][len];
    boolean isValid=true;
    for(int k=0;k<len;k++)
        if(s1.charAt(i+k)!=s2.charAt(j+k)) {
            isValid=false;
            break;
        }
    if(isValid)
        return dp[i][j][len]=true;
    int[] freq=new int[26];
    for(int k=0;k<len;k++) {
        freq[s1.charAt(i+k)-97]++;
        freq[s2.charAt(j+k)-97]--;
    }
    for(int k:freq)
        if(k!=0)
            return dp[i][j][len]=false;
    for(int k=1;k<len;k++) {
        boolean noSwap=f(s1, s2, i, j, k) && f(s1, s2, i+k, j+k, len-k);
        if(noSwap)
            return dp[i][j][len]=true;
        boolean swap=f(s1, s2, i, j+len-k, k) && f(s1, s2, i+k, j, len-k);
        if(swap)
            return dp[i][j][len]=true;
    }
    return dp[i][j][len]=false;
}

public boolean isScramble(String s1, String s2) {
    int n=s1.length();
    dp=new Boolean[n][n][n+1];
    return f(s1, s2, 0, 0, n);
}

void main() {
    String s1 = "great", s2 = "rgeat";
    int n=s1.length();
    IO.println(isScramble(s1, s2));
}