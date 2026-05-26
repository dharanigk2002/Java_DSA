/*
Tarjan’s Algorithm is a highly efficient graph theory algorithm that finds all Strongly Connected Components (SCCs) in a directed graph using a single Depth-First Search (DFS).
It runs in linear time, \(O(V + E)\), and is often used in cycle detection, network analysis, and circuit design.
Core ConceptsTo understand Tarjan's algorithm, you need to be familiar with the following metrics:
Discovery Time (disc[u]): The exact time step when a node is first visited during the DFS.
Low-Link Value (low[u]): The lowest discovery time reachable from a node u using at most one back-edge.
Stack: A helper data structure that keeps track of visited nodes that haven't yet been assigned to an SCC.
*/
int time=0;
List<List<Integer>> scc=new ArrayList<>();
void main() {
    int[][] edges = {{0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 3}, {4, 5}};
    int n=6;

    List<Integer>[] adj=new ArrayList[n];
    for(int i=0;i<n;i++)
        adj[i]=new ArrayList<>();
    for(int[] edge:edges)
        adj[edge[0]].add(edge[1]);
    int[] low=new int[n], disc=new int[n];
    Arrays.fill(disc, -1);
    boolean[] onStack=new boolean[n];
    Deque<Integer> st=new ArrayDeque<>();
    for(int i=0;i<n;i++)
        if(disc[i]==-1)
            dfs(adj, i, low, disc, onStack, st);
    IO.println(scc);
}

void dfs(List<Integer>[] adj, int u, int[] low, int[] disc, boolean[] onStack, Deque<Integer> st) {
    low[u]=disc[u]=time++;
    onStack[u]=true;
    st.push(u);
    for(int v:adj[u])
        if(disc[v]==-1) {
            dfs(adj, v, low, disc, onStack, st);
            low[u]=Math.min(low[u], low[v]);
        } else if(onStack[v])
            low[u]=Math.min(low[u], disc[v]);
    if(low[u]==disc[u]) {
        List<Integer> list=new LinkedList<>();
        while(true) {
            int node=st.pop();
            list.addFirst(node);
            onStack[node]=false;
            if(node==u)
                break;
        }
        scc.add(list);
    }
}