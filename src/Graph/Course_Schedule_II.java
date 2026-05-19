/*
There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.
Refer: Kahn's algorithm
https://leetcode.com/problems/course-schedule-ii/description/
*/
public int[] findOrder(int numCourses, int[][] prerequisites) {
    Queue<Integer> q=new LinkedList<>();
    int[] indegree=new int[numCourses];
    int[] order=new int[numCourses];
    ArrayList<Integer>[] adj=new ArrayList[numCourses];
    for(int i=0;i<numCourses;i++)
        adj[i]=new ArrayList<>();
    for(int[] edge:prerequisites) {
        adj[edge[1]].add(edge[0]);
        indegree[edge[0]]++;
    }
    for(int i=0;i<numCourses;i++)
        if(indegree[i]==0)
            q.add(i);
    int course=0;
    while(!q.isEmpty()) {
        int node=q.poll();
        order[course++]=node;
        for(int nei:adj[node]) {
            indegree[nei]--;
            if(indegree[nei]==0)
                q.add(nei);
        }
    }
    return course==numCourses?order:new int[0];
}
void main() {
    int n=4;
    int[][] prereq={{1,0},{2,0},{3,1},{3,2}};
    int[] order=findOrder(n, prereq);
    IO.println(Arrays.toString(order));
}