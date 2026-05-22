class DSU {
    int[] rank;
    int[] parent;

    DSU(int n) {
        rank=new int[n];
        parent=new int[n];
        for (int i = 0; i < n; i++)
            parent[i]=i;
    }

    public boolean union(int node1, int node2) {
        int pu=findParent(node1), pv=findParent(node2);
        if(pu==pv)
            return true;
        if(rank[pu] > rank[pv])
            parent[pv]=pu;
        else if(rank[pu] < rank[pv])
            parent[pu]=pv;
        else {
            parent[pu]=pv;
            rank[pv]++;
        }
        return false;
    }

    public int findParent(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=findParent(parent[node]);
    }
}
// DSU helpful in finding number of connected components
/*
int countConnected(int V, ArrayList<ArrayList<Integer>> edges) {
    DSU ds=new DSU(V);
    int count=0;
    for(ArrayList<Integer> edge:edges)
        ds.union(edge.get(0), edge.get(1));
    for(int i=0;i<V;i++)
        if(i==ds.findParent(i))
            count++;
    return count;
}
*/
// Kruskal's algorithm
void main() {
    int[][] edges = {{5, 4, 9}, {5, 1, 4}, {4, 1, 1}, {4, 3, 5}, {4, 2, 3}, {1, 2, 2}, {2, 3, 3}, {3, 6, 8}, {2, 6, 7}};
    Arrays.sort(edges, Comparator.comparing(edge->edge[2]));
    int cost=0;
    int V=6;
    List<List<Integer>> mst=new ArrayList<>();
    DSU ds=new DSU(V+1);
    for(int[] edge:edges)
        if(!ds.union(edge[0], edge[1])) {
            cost+=edge[2];
            mst.add(new ArrayList<>(List.of(edge[0], edge[1])));
        }
    IO.println("MST: "+mst);
    IO.println("Cost of MST: "+cost);
}