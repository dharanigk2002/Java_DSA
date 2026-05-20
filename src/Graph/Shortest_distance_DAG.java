/*
Find shortest distance in DAG
Steps:
1. Do topo sort on the graph
2. Relax edges by taking nodes out of stack and compute minimum distance
*/
void dfs(List<List<int[]>> adj, int src, boolean[] visited, Stack<Integer> topo) {
    visited[src]=true;
    for(int[] nei:adj.get(src))
        if(!visited[nei[0]])
            dfs(adj, nei[0], visited, topo);
    topo.push(src);
}

void main() {
    final int INF=Integer.MAX_VALUE;
    int V = 6;
    int[][] edges = {{0,1,2}, {0,4,1}, {4,5,4}, {4,2,2}, {1,2,3}, {2,3,6}, {5,3,1}};
    // edge[0] -> from node, edge[1] -> to node, edge[2] -> weight
    List<List<int[]>> adj=new ArrayList<>();
    int[] dist=new int[V];
    boolean[] visited=new boolean[V];
    for(int i=0;i<V;i++) {
        dist[i]=INF;
        adj.add(new ArrayList<>());
    }
    for(int[] e:edges)
        adj.get(e[0]).add(new int[]{e[1], e[2]});
    Stack<Integer> topo=new Stack<>();
    for(int i=0;i<V;i++)
        if(!visited[i])
            dfs(adj, i, visited, topo);
    // Relax edges
    dist[0]=0; // src node is 0. If src node is n then dist[n]=0
    while(!topo.isEmpty()) {
        int node=topo.pop();
        for(int[] nei:adj.get(node))
            if(dist[nei[0]] > dist[node]+nei[1])
                dist[nei[0]]=dist[node]+nei[1];
    }
    for(int i=0;i<V;i++)
        if(dist[i]==INF)
            dist[i]=-1;
    IO.println(Arrays.toString(dist));
}