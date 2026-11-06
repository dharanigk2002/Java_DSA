/*
Number of Music Playlists

Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip. To avoid boredom, you will create a playlist so that:

Every song is played at least once.
A song can only be played again only if k other songs have been played.
Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 109 + 7.

https://leetcode.com/problems/number-of-music-playlists/description/
*/

private static final int MOD=1_000_000_007;
private Integer[][] dp;

private int f(int n, int goal, int k, int used_songs, int unique) {
    if(dp[used_songs][unique]!=null)
        return dp[used_songs][unique];
    if(used_songs==goal)
        return dp[used_songs][unique]=n==unique ? 1 : 0;
    long result=0;
    if (unique < n) {
        result += (long) (n - unique) *
                f(n, goal, k, used_songs + 1, unique + 1);
    }
    result+=(1L*f(n, goal, k, used_songs+1, unique)*Math.max(0, unique-k)%MOD);
    return dp[used_songs][unique]=(int)(result%MOD);
}

public int numMusicPlaylists(int n, int goal, int k) {
    dp=new Integer[goal+1][n+1];
    return f(n, goal, k, 0, 0);
}

void main() {
    int n = 3, goal = 3, k = 1;
    dp=new Integer[goal+1][n+1];
    IO.println(f(n, goal, k, 0, 0));
}