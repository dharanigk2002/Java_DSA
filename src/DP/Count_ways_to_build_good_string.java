/*
Count Ways To Build Good Strings

Given the integers zero, one, low, and high, we can construct a string by starting with an empty string, and then at each step perform either of the following:

Append the character '0' zero times.
Append the character '1' one times.
This can be performed any number of times.

A good string is a string constructed by the above process having a length between low and high (inclusive).

Return the number of different good strings that can be constructed satisfying these properties. Since the answer can be large, return it modulo 109 + 7.

https://leetcode.com/problems/count-ways-to-build-good-strings/description/
*/

private static final int MOD=1_000_000_007;
private Integer[] dp;
private int f(int l, int h, int z, int o, int n) {
 if(n>h)
     return 0;
 if(dp[n]!=null)
     return dp[n];
 long sum=0;
 if(n>=l && n<=h)
     sum=1;
 return dp[n]=(int)(sum+f(l, h, z, o, n+z)+f(l, h, z, o, n+o))%MOD;
}

public int countGoodStrings(int low, int high, int zero, int one) {
    int[] dp=new int[high+1];
    for(int n=high;n>=0;n--) {
        long sum=0;
        if(n>=low && n<=high)
            sum=1;
        if(n+zero<=high)
            sum+=dp[n+zero];
        if(n+one<=high)
            sum+=dp[n+one];
        dp[n]=(int)(sum%MOD);
    }
    return dp[0];
}

void main() {
    int low = 3, high = 3, zero = 1, one = 1;
    IO.println(countGoodStrings(low, high, zero, one));
}