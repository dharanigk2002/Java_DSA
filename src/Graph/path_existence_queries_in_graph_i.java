/*
Path Existence Queries in a Graph I

You are given an integer n representing the number of nodes in a graph, labeled from 0 to n - 1.

You are also given an integer array nums of length n sorted in non-decreasing order, and an integer maxDiff.

An undirected edge exists between nodes i and j if the absolute difference between nums[i] and nums[j] is at most maxDiff (i.e., |nums[i] - nums[j]| <= maxDiff).

You are also given a 2D integer array queries. For each queries[i] = [ui, vi], determine whether there exists a path between nodes ui and vi.

Return a boolean array answer, where answer[i] is true if there exists a path between ui and vi in the ith query and false otherwise.

https://leetcode.com/problems/path-existence-queries-in-a-graph-i/description/
*/

public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
    int[] component=new int[n];
    int m=queries.length;
    boolean[] ans=new boolean[m];
    int id=0;
    for(int i=1;i<n;i++) {
        if(nums[i]-nums[i-1]>maxDiff)
            id++;
        component[i]=id;
    }
    for(int i=0;i<m;i++)
        ans[i]=component[queries[i][0]]==component[queries[i][1]];
    return ans;
}

void main() {
    int n = 4;
    int[] nums = {2,5,6,8};
    int maxDiff = 2;
    int[][] queries = {{0,1},{0,2},{1,3},{2,3}};
    System.out.println(Arrays.toString(pathExistenceQueries(n, nums, maxDiff, queries)));
}
