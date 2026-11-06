/*
Frog Jump

A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.

Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.

https://leetcode.com/problems/frog-jump/description/
*/

private Map<Integer, Integer> map;
private Boolean[][] memo;

private boolean f(int[] a, int i, int k) {
    if(i == a.length - 1)
        return true;

    if(memo[i][k] != null)
        return memo[i][k];

    int[] jumps = {k - 1, k, k + 1};

    for(int jump : jumps) {
        if(jump <= 0)
            continue;

        int nextPosition = a[i] + jump;

        int nextIndex = map.getOrDefault(nextPosition, -1);

        if(nextIndex != -1 && f(a, nextIndex, jump))
            return memo[i][k] = true;
    }

    return memo[i][k] = false;
}

public boolean canCross(int[] stones) {
    int n = stones.length;

    if(n > 1 && stones[1] != 1)
        return false;

    map = new HashMap<>();
    memo = new Boolean[n][n + 1];

    for(int i = 0; i < n; i++)
        map.put(stones[i], i);

    return f(stones, 0, 0);
}

void main() {
    int[] stones = {0,1,3,5,6,8,12,17};
    IO.println(canCross(stones));
}