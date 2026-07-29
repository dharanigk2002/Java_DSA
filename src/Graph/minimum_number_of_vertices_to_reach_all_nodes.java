/*
Minimum Number of Vertices to Reach All Nodes

Given a directed acyclic graph, with n vertices numbered from 0 to n-1, and an array edges where edges[i] = [fromi, toi] represents a directed edge from node fromi to node toi.

Find the smallest set of vertices from which all nodes in the graph are reachable. It's guaranteed that a unique solution exists.

Notice that you can return the vertices in any order.

https://leetcode.com/problems/minimum-number-of-vertices-to-reach-all-nodes/description/
*/
public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
    boolean[] indegree=new boolean[n];
    List<Integer> list=new ArrayList();
    for(List<Integer> edge:edges)
        indegree[edge.get(1)]=true;
    for(int i=0;i<n;i++)
        if(!indegree[i])
            list.add(i);
    return list;
}

void main() {
    int n = 6;
    List<List<Integer>> edges=List.of(
            List.of(0, 1),
            List.of(0, 2),
            List.of(2, 5),
            List.of(3, 4),
            List.of(4, 2)
    );
    System.out.println(findSmallestSetOfVertices(n, edges));
}