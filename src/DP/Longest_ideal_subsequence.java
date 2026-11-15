/*
Longest Ideal Subsequence

You are given a string s consisting of lowercase letters and an integer k. We call a string t ideal if the following conditions are satisfied:

t is a subsequence of the string s.
The absolute difference in the alphabet order of every two adjacent letters in t is less than or equal to k.
Return the length of the longest ideal string.

A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

Note that the alphabet order is not cyclic. For example, the absolute difference in the alphabet order of 'a' and 'z' is 25, not 1.

https://leetcode.com/problems/longest-ideal-subsequence/description/
*/

public int longestIdealString(String s, int k) {
    int n=s.length(), max=1;
    int[] dp=new int[n];
    for(int i=0;i<n;i++) {
        dp[i]=1;
        for(int j=0;j<i;j++) {
            int diff=Math.abs(s.charAt(i)-s.charAt(j));
            if(diff<=k && dp[j]+1>dp[i])
                dp[i]=dp[j]+1;
        }
        max=Math.max(max, dp[i]);
    }
    return max;
}

public int bottomUp(String s, int k) {
    int n=s.length(), max=1;
    int[] dp=new int[26];
    for(int i=0;i<n;i++) {
        int curr=s.charAt(i)-97;
        int left=Math.max(0, curr-k), right=Math.min(25, curr+k);
        int best=0;
        for(int j=left;j<=right;j++)
            best=Math.max(best, dp[j]);
        dp[curr]=best+1;
        max=Math.max(max, dp[curr]);
    }
    return max;
}

void main() {
    String s = "acfgbd";
    int k = 2;
    IO.println(bottomUp(s, k));
}