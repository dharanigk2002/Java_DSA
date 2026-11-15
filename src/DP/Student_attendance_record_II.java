/*
Student Attendance Record II

An attendance record for a student can be represented as a string where each character signifies whether the student was absent, late, or present on that day. The record only contains the following three characters:

'A': Absent.
'L': Late.
'P': Present.
Any student is eligible for an attendance award if they meet both of the following criteria:

The student was absent ('A') for strictly fewer than 2 days total.
The student was never late ('L') for 3 or more consecutive days.
Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award. The answer may be very large, so return it modulo 109 + 7.

https://leetcode.com/problems/student-attendance-record-ii/description/
*/


private static final int MOD=1_000_000_007;
private Integer[][][] dp;
private final int f(int n, int A, int L) {
    if(dp[n][A][L]!=null)
        return dp[n][A][L];
    if(n==0)
        return dp[n][A][L]=1;
    long result=f(n-1, A, 2);
    if(A>0)
        result+=f(n-1, A-1, 2);
    if(L>0)
        result+=f(n-1, A, L-1);
    return dp[n][A][L]=(int)(result % MOD);
}

public int checkRecord(int n) {
    int[][] dp=new int[2][3];
    for(int A=0;A<2;A++)
        for(int L=0;L<3;L++)
            dp[A][L]=1;
    for(int i=1;i<=n;i++) {
        int[][] curr=new int[2][3];
        for(int A=0;A<2;A++) {
            for(int L=0;L<3;L++) {
                long result=dp[A][2];
                if(A>0)
                    result+=dp[A-1][2];
                if(L>0)
                    result+=dp[A][L-1];
                curr[A][L]=(int)(result % MOD);
            }
        }
        dp=curr;
    }
    return dp[1][2];
}

void main() {
    int n = 2;
    IO.println(checkRecord(n));
}