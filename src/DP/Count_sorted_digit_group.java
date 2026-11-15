/*
Count Sorted Digit Groupings

Given a string s consisting of digits, you can split it into contiguous substrings (sub-groups). For example, the string "112" can be split as: ["1","1","2"], ["11","2"], ["1","12"], and ["112"].

A grouping is considered valid if the sums of digits of the sub-groups form a non-decreasing sequence from left to right.

Determine the total number of such valid groupings for the given string.

https://www.geeksforgeeks.org/problems/count-digit-groupings-of-a-number1520/1
*/

private Integer[][] dp;
private final int f(String s, int idx, int prev) {
    int n=s.length();
    if(dp[idx][prev]!=null)
        return dp[idx][prev];
    if(idx==n)
        return dp[idx][prev]=1;
    int num=0, ways=0;
    for(int j=idx;j<n;j++) {
        num+=(s.charAt(j)-48);
        if(prev<=num)
            ways+=f(s, j+1, num);
    }
    return dp[idx][prev]=ways;
}

public int validGroups(String s) {
    dp=new Integer[s.length()+1][901];

    return f(s, 0, 0);
}

void main() {
    String s = "1119";
    IO.println(validGroups(s));
}