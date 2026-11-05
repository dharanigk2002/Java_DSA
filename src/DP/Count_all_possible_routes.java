/*
Count All Possible Routes

You are given an array of distinct positive integers locations where locations[i] represents the position of city i. You are also given integers start, finish and fuel representing the starting city, ending city, and the initial amount of fuel you have, respectively.

At each step, if you are at city i, you can pick any city j such that j != i and 0 <= j < locations.length and move to city j. Moving from city i to city j reduces the amount of fuel you have by |locations[i] - locations[j]|. Please notice that |x| denotes the absolute value of x.

Notice that fuel cannot become negative at any point in time, and that you are allowed to visit any city more than once (including start and finish).

Return the count of all possible routes from start to finish. Since the answer may be too large, return it modulo 109 + 7.

https://leetcode.com/problems/count-all-possible-routes/description/
*/

private Integer[][] dp;
private static final int MOD=1_000_000_007;
private int f(int[] l, int i, int f, int fuel) {
    if(dp[i][fuel]!=null)
        return dp[i][fuel];
    long count=0;
    if(i==f)
        count=1;
    int n=l.length;
    for(int k=0;k<n;k++) {
        if(k!=i && fuel>=Math.abs(l[i]-l[k]))
            count+=f(l, k, f, fuel-Math.abs(l[i]-l[k]));
    }
    return dp[i][fuel]=(int)(count%MOD);
}

public int countRoutes(int[] locations, int start, int finish, int fuel) {
    int n=locations.length;
    dp=new Integer[n+1][fuel+1];
    return f(locations, start,finish, fuel);
}

void main() {
    int[] locations = {2,3,6,8,4};
    int start = 1, finish = 3, fuel = 5;
    IO.println(countRoutes(locations, start, finish, fuel));
}