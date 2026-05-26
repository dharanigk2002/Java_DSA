/*
You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.

https://leetcode.com/problems/shortest-path-with-alternating-colors/description/
*/

int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
    int[] answer=new int[n];
    List<Integer>[] red=new ArrayList[n];
    List<Integer>[] blue=new ArrayList[n];
    for(int i=0;i<n;i++) {
        answer[i]=-1;
        red[i]=new ArrayList();
        blue[i]=new ArrayList();
    }
    for(int[] edge: redEdges)
        red[edge[0]].add(edge[1]);
    for(int[] edge:blueEdges)
        blue[edge[0]].add(edge[1]);
    Queue<int[]> q=new LinkedList();
    q.add(new int[]{0, 0});
    q.add(new int[]{0, 1});
    boolean[][] visited=new boolean[n][2];
    visited[0][0]=visited[0][1]=true;
    int moves=0;
    while(!q.isEmpty()) {
        for(int size=q.size();size>0;size--) {
            int node=q.peek()[0], color=q.poll()[1];
            if(answer[node]==-1)
                answer[node]=moves;
            color=1-color;
            List<Integer> neighbors=(color==0) ? blue[node] : red[node];
            for(int next:neighbors)
                if(!visited[next][color]) {
                    visited[next][color]=true;
                    q.add(new int[]{next, color});
                }

        }
        moves++;
    }
    return answer;
}

void main() {
    int n = 5;
    int[][] redEdges = {{0, 1},{1, 2}, {2, 3}, {3, 4}}, blueEdges = {{1, 2}, {2, 3}, {3, 1}};
    int[] paths = shortestAlternatingPaths(n, redEdges, blueEdges);
    IO.println(Arrays.toString(paths));
}