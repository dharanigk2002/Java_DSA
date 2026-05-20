void main() {
    int[][] edges = {
            {0, 1, 4},
            {0, 2, 4},
            {2, 1, 2},
            {2, 3, 3},
            {2, 5, 6},
            {2, 4, 1},
            {3, 5, 2},
            {4, 5, 3},
    };
    int src=0;
    int V=6;
    int[] dist=new int[V];
    List<List<int[]>> adj=new ArrayList<>();
    for(int i=0;i<V;i++) {
        dist[i]=Integer.MAX_VALUE;
        adj.add(new ArrayList<>());
    }
    for(int[] edge:edges) {
        int u=edge[0], v=edge[1], w=edge[2];
        adj.get(u).add(new int[]{v, w});
        adj.get(v).add(new int[]{u, w});
    }
    dist[src]=0;
    TreeSet<int[]> set=new TreeSet<>((a, b) -> {
        if(a[0]==b[0])
            return Integer.compare(a[1], b[1]);
        return Integer.compare(a[0], b[0]);
        // Comparator.comparingInd(d->d[0]) will fail here because for two edges [4, 1] and [4, 3] d[0] will return 0 and
        // hence TreeSet will ignore one by considering duplicate.
    });
    set.add(new int[]{dist[src], src});
    while (!set.isEmpty()) {
        int[] node = set.pollFirst();
        int u=node[1], w=node[0];
        for (int[] nei:adj.get(u)) {
            if(dist[nei[0]]>w+nei[1]) {
                if(dist[nei[0]]!=Integer.MAX_VALUE)
                    set.remove(new int[]{dist[nei[0]], nei[0]});
                dist[nei[0]]=w+nei[1];
                set.add(new int[]{dist[nei[0]], nei[0]});
            }
        }
    }
    IO.println(Arrays.toString(dist));
}