/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return true if you can finish all courses. Otherwise, return false.
Refer: Kahn's algorithm
https://leetcode.com/problems/course-schedule/description/
*/

public boolean canFinish(int numCourses, int[][] prerequisites) {
    List<List<Integer>> adj=new ArrayList();
    int[] indeg=new int[numCourses];
    Queue<Integer> q=new LinkedList();
    for(int i=0;i<numCourses;i++)
        adj.add(new ArrayList());
    for(int[] edge:prerequisites) {
        adj.get(edge[0]).add(edge[1]);
        indeg[edge[1]]++;
    }
    for(int i=0;i<numCourses;i++)
        if(indeg[i]==0)
            q.add(i);
    while(!q.isEmpty()) {
        int node=q.poll();
        numCourses--;
        for(int nei:adj.get(node)) {
            indeg[nei]--;
            if(indeg[nei]==0)
                q.add(nei);
        }
    }
    return numCourses==0;
}

void main() {
    int courses=4;
    int[][] prereq={{1, 0}, {2, 1}, {3, 2}};
    boolean canFinish=canFinish(courses, prereq);
    IO.println(canFinish);
}