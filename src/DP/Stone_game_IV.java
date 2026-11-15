/*
Stone Game IV

Alice and Bob take turns playing a game, with Alice starting first.

Initially, there are n stones in a pile. On each player's turn, that player makes a move consisting of removing any non-zero square number of stones in the pile.

Also, if a player cannot make a move, he/she loses the game.

Given a positive integer n, return true if and only if Alice wins the game otherwise return false, assuming both players play optimally.

https://leetcode.com/problems/stone-game-iv/description/
*/

private Boolean[] dp;
private boolean solve(int n) {
    if(n == 0)
        return false;

    if(dp[n] != null)
        return dp[n];

    for(int d = 1; d * d <= n; d++) {
        if(!solve(n - d * d)) {
            return dp[n] = true;
        }
    }

    return dp[n] = false;
}

public boolean winnerSquareGame(int n) {
    boolean[] dp = new boolean[n + 1];
    for(int i=1;i<=n;i++) {
        for(int d = 1; d * d <= i; d++)
            if(!dp[i - d * d]) {
                dp[i] = true;
                break;
            }
    }
    return dp[n];
}

void main() {
    int n=4;
    IO.println(winnerSquareGame(n));
}