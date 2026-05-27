/*
In a town, there are n people labeled from 1 to n. There is a rumor that one of these people is secretly the town judge.

If the town judge exists, then:

The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.
You are given an array trust where trust[i] = [ai, bi] representing that the person labeled ai trusts the person labeled bi. If a trust relationship does not exist in trust array, then such a trust relationship does not exist.

Return the label of the town judge if the town judge exists and can be identified, or return -1 otherwise.

https://leetcode.com/problems/find-the-town-judge/description/
*/

int findJudge(int n, int[][] trust) {
    int[] score=new int[n+1];
    for(int[] t:trust) {
        score[t[1]]++;
        score[t[0]]--;
    }
    for(int i=1;i<=n;i++)
        if(score[i]==n-1)
            return i;
    return -1;
}

void main() {
    int[][] trust = {{1, 2}, {3, 2}};
    int n=3;
    IO.println(findJudge(n, trust));
}