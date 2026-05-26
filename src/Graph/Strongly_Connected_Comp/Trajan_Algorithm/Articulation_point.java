/*
Given an undirected connected graph with V vertices and adjacency list adj. You are required to find all the vertices removing which (and edges through it) disconnects the graph into 2 or more components and return it in sorted manner.
Note: Indexing is zero-based i.e nodes numbering from (0 to V-1). There might be loops present in the graph.

https://www.geeksforgeeks.org/problems/articulation-point-1/1
*/

private int[] disc;
private int[] low;
private boolean[] visited;
private boolean[] isAP;
private int timer=0;
public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
    ArrayList<Integer> ap=new ArrayList<>();
    disc=new int[V];
    low=new int[V];
    visited=new boolean[V];
    isAP=new boolean[V];
    Arrays.fill(disc, -1);
    for(int i=0;i<V;i++)
        if(disc[i]==-1)
            dfs(adj, i, -1);
    for(int i=0;i<V;i++)
        if(isAP[i])
            ap.add(i);
    if(ap.size()==0)
        ap.add(-1);
    return ap;
}
private void dfs(ArrayList<ArrayList<Integer>> adj, int u, int parent) {
    low[u]=disc[u]=timer++;
    visited[u]=true;
    int children=0;
    for(int v:adj.get(u)) {
        if(!visited[v]) {
            children++;
            dfs(adj, v, u);
            low[u]=Math.min(low[u], low[v]);
            if(low[v]>=disc[u] && parent!=-1)
                isAP[u]=true;
        } else if(v!=parent)
            low[u]=Math.min(low[u], disc[v]);
    }
    if(parent==-1 && children>=2)
        isAP[u]=true;
}


void main() {
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>(
            List.of(
                    new ArrayList<>(List.of(1)),
                    new ArrayList<>(List.of(0, 4)),
                    new ArrayList<>(List.of(3, 4)),
                    new ArrayList<>(List.of(4, 2)),
                    new ArrayList<>(List.of(1, 2, 3))
            )
    );
    ArrayList<Integer> list = articulationPoints(5, adj);
    IO.println(list);
}