/*
You are given an integer n denoting the number of cities in a country. The cities are numbered from 0 to n - 1.

You are also given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.

You need to assign each city with an integer value from 1 to n, where each value can only be used once. The importance of a road is then defined as the sum of the values of the two cities it connects.

Return the maximum total importance of all roads possible after assigning the values optimally.

https://leetcode.com/problems/maximum-total-importance-of-roads/description/
*/
public long maximumImportance(int n, int[][] roads) {
    long[] degree=new long[n];
    for(int[] r:roads) {
        degree[r[0]]++;
        degree[r[1]]++;
    }
    Arrays.sort(degree);
    long ans=0;
    for(int i=1;i<=n;i++)
        ans+=i*degree[i-1];
    return ans;
}
void main() {
    int n = 5;
    int[][] roads = {{0, 1}, {1, 2}, {2, 3}, {0, 2}, {1, 3}, {2, 4}};
    IO.println(maximumImportance(n, roads));
}