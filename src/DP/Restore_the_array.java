/*
Restore The Array

A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits s and all we know is that all integers in the array were in the range [1, k] and there are no leading zeros in the array.

Given the string s and the integer k, return the number of the possible arrays that can be printed as s using the mentioned program. Since the answer may be very large, return it modulo 109 + 7.

https://leetcode.com/problems/restore-the-array/description/
*/

private Integer[] dp;
private static final int MOD=1_000_000_007;
private int f(String s, int k, int i) {
    int n=s.length();
    if(dp[i]!=null)
        return dp[i];
    if(i==n)
        return dp[i]=1;
    if(s.charAt(i)=='0')
        return dp[i]=0;
    long num=0;
    long ways=0;
    for(int j=i;j<n;j++) {
        num=num*10+s.charAt(j)-48;
        if(num>k)
            break;
        ways+=f(s, k, j+1);
    }
    return dp[i]=(int)(ways%MOD);
}

public int numberOfArrays2(String s, int k) {
    int n=s.length();
    int[] dp=new int[n+1];
    dp[n]=1;
    for(int i=n-1;i>=0;i--) {
        if(s.charAt(i)=='0')
            continue;
        long num=0, ways=0;
        for(int j=i;j<n;j++) {
            num=num*10+s.charAt(j)-48;
            if(num>k)
                break;
            ways+=dp[j+1];
        }
        dp[i]=(int)(ways%MOD);
    }
    return dp[0];
}

public int numberOfArrays(String s, int k) {
    int n=s.length();
    dp=new Integer[n+1];
    return f(s, k, 0);
}

void main() {
    String s = "1317";
    int k = 2000;
    IO.println(numberOfArrays(s, k));
}