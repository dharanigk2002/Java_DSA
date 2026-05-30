/*
You are given an integer n and a 2D integer array queries.

There are n cities numbered from 0 to n - 1. Initially, there is a unidirectional road from city i to city i + 1 for all 0 <= i < n - 1.

queries[i] = [ui, vi] represents the addition of a new unidirectional road from city ui to city vi. After each query, you need to find the length of the shortest path from city 0 to city n - 1.

There are no two queries such that queries[i][0] < queries[j][0] < queries[i][1] < queries[j][1].

Return an array answer where for each i in the range [0, queries.length - 1], answer[i] is the length of the shortest path from city 0 to city n - 1 after processing the first i + 1 queries.

https://leetcode.com/problems/shortest-distance-after-road-addition-queries-ii/description/
*/
int[] shortestDistanceAfterQueries(int n, int[][] queries) {
    TreeSet<Integer> road=new TreeSet();
    int l=queries.length;
    int[] ans=new int[l];
    for(int i=0;i<n;i++)
        road.add(i);
    for(int i=0;i<l;i++) {
        List<Integer> subset=new ArrayList<>(road.subSet(queries[i][0]+1, true, queries[i][1], false));
        road.removeAll(subset);
        ans[i]=road.size()-1;
    }
    return ans;
}

void main() {
    int n = 5;
    int[][] queries = {{2, 4}, {0, 2}, {0, 4}};
    IO.println(Arrays.toString(shortestDistanceAfterQueries(n, queries)));
}