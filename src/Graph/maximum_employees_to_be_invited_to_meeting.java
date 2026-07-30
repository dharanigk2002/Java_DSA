/*
Maximum Employees to Be Invited to a Meeting

A company is organizing a meeting and has a list of n employees, waiting to be invited. They have arranged for a large circular table, capable of seating any number of employees.

The employees are numbered from 0 to n - 1. Each employee has a favorite person and they will attend the meeting only if they can sit next to their favorite person at the table. The favorite person of an employee is not themself.

Given a 0-indexed integer array favorite, where favorite[i] denotes the favorite person of the ith employee, return the maximum number of employees that can be invited to the meeting.

https://leetcode.com/problems/maximum-employees-to-be-invited-to-a-meeting/description/
*/

public int maximumInvitations(int[] favourite) {
    int n=favourite.length;
    int[] indegree=new int[n];
    for(int i=0;i<n;i++)
        indegree[favourite[i]]++;
    Queue<Integer> q=new LinkedList();
    int[] depth=new int[n];
    Arrays.fill(depth, 1);
    for(int i=0;i<n;i++)
        if(indegree[i]==0) {
            q.add(i);
            depth[i]=1;
        }
    while(!q.isEmpty()) {
        int node=q.poll();
        int next=favourite[node];
        depth[next]=Math.max(depth[next], depth[node]+1);
        if(--indegree[next]==0)
            q.add(next);
    }
    int longestCycle=0, twoPersons=0;
    for(int person=0;person<n;person++) {
        if(indegree[person]==0) continue;
        int cycle=0, node=person;
        while(indegree[node]!=0) {
            indegree[node]=0;
            cycle++;
            node=favourite[node];
        }
        if(cycle==2)
            twoPersons+=depth[person]+depth[favourite[person]];
        else
            longestCycle=Math.max(cycle, longestCycle);
    }
    return Math.max(twoPersons, longestCycle);
}

void main() {
    int[] favorite = {3,0,1,4,1};
    System.out.println(maximumInvitations(favorite));
}