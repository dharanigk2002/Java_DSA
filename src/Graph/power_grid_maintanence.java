/*
Power Grid Maintenance

You are given an integer c representing c power stations, each with a unique identifier id from 1 to c (1‑based indexing).

These stations are interconnected via n bidirectional cables, represented by a 2D array connections, where each element connections[i] = [ui, vi] indicates a connection between station ui and station vi. Stations that are directly or indirectly connected form a power grid.

Initially, all stations are online (operational).

You are also given a 2D array queries, where each query is one of the following two types:

[1, x]: A maintenance check is requested for station x. If station x is online, it resolves the check by itself. If station x is offline, the check is resolved by the operational station with the smallest id in the same power grid as x. If no operational station exists in that grid, return -1.

[2, x]: Station x goes offline (i.e., it becomes non-operational).

Return an array of integers representing the results of each query of type [1, x] in the order they appear.

Note: The power grid preserves its structure; an offline (non‑operational) node remains part of its grid and taking it offline does not alter connectivity.

https://leetcode.com/problems/power-grid-maintenance/description/
*/
class DSU {
    private final int[] parent;
    private final int[] rank;

    public DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        IntStream.range(0, n).forEach(x -> {
            parent[x]=x;
        });
    }

    public int find(int u) {
        if(u==parent[u])
            return u;
        return parent[u]=find(parent[u]);
    }

    public void union(int u, int v) {
        int pu=find(u), pv=find(v);
        if(pu==pv)
            return;
        if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            if(rank[pu]==rank[pv])
                rank[pu]++;
        }
    }
}

public int[] processQueries(int n, int[][] connections, int[][] queries) {
    DSU ds=new DSU(n+1);
    for(int[] conn:connections)
        ds.union(conn[0], conn[1]);
    Map<Integer, TreeSet<Integer>> map=new HashMap();
    for(int station=1;station<=n;station++) {
        int root=ds.find(station);
        map.computeIfAbsent(root, k->new TreeSet<>()).add(station);
    }
    List<Integer> ans=new ArrayList();
    for(int[] q:queries) {
        int type=q[0], station=q[1];
        int root=ds.find(station);
        TreeSet<Integer> set=map.get(root);
        if(type==2)
            set.remove(station);
        else {
            if(set.contains(station))
                ans.add(station);
            else if(set.isEmpty())
                ans.add(-1);
            else
                ans.add(set.first());
        }
    }

    return ans.stream().mapToInt(Integer::intValue).toArray();
}

void main() {
    int c = 5;
    int[][] connections = {{1,2},{2,3},{3,4},{4,5}}, queries = {{1,3},{2,1},{1,1},{2,2},{1,2}};
    System.out.println(Arrays.toString(processQueries(c, connections, queries)));
}