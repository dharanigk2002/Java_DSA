

/*
Decode Ways

You have intercepted a secret message encoded as a string of numbers. The message is decoded via the following mapping:

"1" -> 'A'

"2" -> 'B'

...

"25" -> 'Y'

"26" -> 'Z'

However, while decoding the message, you realize that there are many different ways you can decode the message because some codes are contained in other codes ("2" and "5" vs "25").

For example, "11106" can be decoded into:

"AAJF" with the grouping (1, 1, 10, 6)
"KJF" with the grouping (11, 10, 6)
The grouping (1, 11, 06) is invalid because "06" is not a valid code (only "6" is valid).
Note: there may be strings that are impossible to decode.

Given a string s containing only digits, return the number of ways to decode it. If the entire string cannot be decoded in any valid way, return 0.

The test cases are generated so that the answer fits in a 32-bit integer.

https://leetcode.com/problems/decode-ways/description/
*/

private Integer[] dp;
private int f(String s, int idx) {
    int n=s.length();
    if(dp[idx]!=null)
        return dp[idx];
    if(idx==n)
        return dp[idx]=1;
    int num=0, ways=0;
    for(int j=idx;j<n;j++) {
        num=num*10+(s.charAt(j)-48);
        if((num==0 && j==idx) || num>26)
            break;
        ways+=f(s, j+1);
    }
    return dp[idx]=ways;
}

public int numDecodings(String s) {
    int n=s.length();
    int[] dp=new int[n+1];
    dp[n]=1;
    for(int idx=n-1;idx>=0;idx--) {
        int num=0, ways=0;
        for(int j=idx;j<n;j++) {
            num=num*10+(s.charAt(j)-48);
            if((num==0 && j==idx) || num>26)
                break;
            ways+=dp[j+1];
        }
        dp[idx]=ways;
    }
    return dp[0];
}

void main() {
    String s = "226";
    IO.println(numDecodings(s));
}