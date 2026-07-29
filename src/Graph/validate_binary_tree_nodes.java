/*
Validate Binary Tree Nodes

You have n binary tree nodes numbered from 0 to n - 1 where node i has two children leftChild[i] and rightChild[i], return true if and only if all the given nodes form exactly one valid binary tree.

If node i has no left child then leftChild[i] will equal -1, similarly for the right child.

Note that the nodes have no values and that we only use the node numbers in this problem.
*/
// Approach-1
public boolean validateBinaryTreeNodes1(int n, int[] leftChild, int[] rightChild) {
    List<List<Integer>> adj=new ArrayList();
    Map<Integer, Integer> map=new HashMap();
    boolean[] visited=new boolean[n];

    for(int i=0;i<n;i++) {
        int node=i;
        adj.add(new ArrayList());
        int left=leftChild[i], right=rightChild[i];
        if(left!=-1) {
            if(map.containsKey(left))
                return false;
            map.put(left, node);
        }
        if(right!=-1) {
            if(map.containsKey(right))
                return false;
            map.put(right, node);
        }
    }

    int root=-1;
    for(int i=0;i<n;i++)
        if(!map.containsKey(i)) {
            if(root!=-1)
                return false;
            root=i;
        }
    if(root==-1)
        return false;

    for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
        int child=entry.getKey();
        int parent=entry.getValue();
        adj.get(parent).add(child);
    }

    Queue<Integer> q=new LinkedList();
    visited[root]=true;
    q.add(root);
    while(!q.isEmpty()) {
        int node=q.poll();
        for(int next:adj.get(node))
            if(!visited[next]) {
                visited[next]=true;
                q.add(next);
            }
    }
    return IntStream.range(0, n).allMatch(i->visited[i]);
}

// Approach-2
class DSU {
    private final int[] parent;
    public DSU(int n) {
        parent=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }

    public int find(int u) {
        if(u==parent[u])
            return u;
        return parent[u]=find(parent[u]);
    }

    public boolean union(int p, int c) {
        if(find(c)!=c)
            return false;
        if(find(p)==c)
            return false;
        parent[c]=p;
        return true;
    }
}
 public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
    DSU ds=new DSU(n);
    int m=leftChild.length;
    for(int i=0;i<m;i++) {
        if(leftChild[i]!=-1)
            if(ds.union(i, leftChild[i]))
                n--;
            else
                return false;
        if(rightChild[i]!=-1)
            if(ds.union(i, rightChild[i]))
                n--;
            else
                return false;
    }
    return n==1;
}

void main() {
    int n = 4;
    int[] leftChild = {1,-1,3,-1}, rightChild = {2,3,-1,-1};
    System.out.println(validateBinaryTreeNodes(n, leftChild, rightChild));
}