/*
Course Schedule IV

There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course ai first if you want to take course bi.

For example, the pair [0, 1] indicates that you have to take course 0 before you can take course 1.
Prerequisites can also be indirect. If course a is a prerequisite of course b, and course b is a prerequisite of course c, then course a is a prerequisite of course c.

You are also given an array queries where queries[j] = [uj, vj]. For the jth query, you should answer whether course uj is a prerequisite of course vj or not.

Return a boolean array answer, where answer[j] is the answer to the jth query.

https://leetcode.com/problems/course-schedule-iv/description/
*/

public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
    List<List<Integer>> adj=new ArrayList();
    for(int i=0;i<n;i++)
        adj.add(new ArrayList());
    for(int[] pre:prerequisites)
        adj.get(pre[0]).add(pre[1]);
    List<Boolean> list=new ArrayList();
    for(int[] q:queries)
        list.add(isPre(adj, q[0], q[1], new boolean[n]));
    return list;
}

private boolean isPre(List<List<Integer>> adj, int node, int target, boolean[] vis) {
    vis[node]=true;
    if(node==target)
        return true;
    for(int next:adj.get(node))
        if(!vis[next] && isPre(adj, next, target, vis))
            return true;
    return false;
}

void main() {
    int numCourses = 3;
    int[][] prerequisites = {{1,2},{1,0},{2,0}}, queries = {{1,0},{1,2}};
    System.out.println(checkIfPrerequisite(numCourses, prerequisites, queries));
}