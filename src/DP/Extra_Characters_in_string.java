/*
Extra Characters in a String

You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.

Return the minimum number of extra characters left over if you break up s optimally.

https://leetcode.com/problems/extra-characters-in-a-string/description/
*/

private Set<String> set;
private Integer[] dp;
private int f(String s, int idx) {
    int n=s.length();
    if(dp[idx]!=null)
        return dp[idx];
    if(idx==n)
        return dp[idx]=0;
    int min=n;
    for(int j=idx;j<n;j++) {
        int currExtra=set.contains(s.substring(idx, j+1)) ? 0 : j-idx+1;
        int next=f(s, j+1);
        min=Math.min(min, next+currExtra);
    }
    return dp[idx]=min;
}

public int bottomUp(String s, String[] dictionary) {
    set=new HashSet();
    int n=s.length();
    for(String d:dictionary)
        set.add(d);
    int[] dp=new int[n+1];
    for(int idx=n-1;idx>=0;idx--) {
        int min=n;
        for(int j=idx;j<n;j++) {
            int currExtra=set.contains(s.substring(idx, j+1)) ? 0 : j-idx+1;
            int next=dp[j+1];
            min=Math.min(min, next+currExtra);
        }
        dp[idx]=min;
    }
    return dp[0];
}

public int minExtraChar(String s, String[] dictionary) {
    set=new HashSet();
    int n=s.length();
    for(String d:dictionary)
        set.add(d);
    int[] dp=new int[n+1];
    for(int idx=n-1;idx>=0;idx--) {
        int min=n;
        for(int j=idx;j<n;j++) {
            int currExtra=set.contains(s.substring(idx, j+1)) ? 0 : j-idx+1;
            int next=dp[j+1];
            min=Math.min(min, next+currExtra);
        }
        dp[idx]=min;
    }
    return dp[0];
}


void main() {
    String s = "leetscode";
    String[] dictionary = {"leet","code","leetcode"};
    IO.println(minExtraChar(s, dictionary));
}