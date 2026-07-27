package Tree;

public class LazyPropagation {
    int[] lazy;
    int[] nodes;
    int n;
    LazyPropagation(int[] arr) {
        n=arr.length;
        lazy=new int[4*n];
        nodes=new int[4*n];
        build(arr, 0, 0, n-1);
    }
    private void build(int[] arr, int node, int l, int r) {
        if(l==r) {
            nodes[node]=arr[l];
            return;
        }
        int mid=(l+r)/2;
        build(arr, 2*node+1, l, mid);
        build(arr, 2*node+2, mid+1, r);
        nodes[node]=nodes[2*node+1]+nodes[2*node+2];
    }
    private void push(int node, int start, int end) {
        if(lazy[node]==0)
            return;
        nodes[node]+=(end-start+1)*lazy[node];
        if(start!=end) {
            lazy[2*node+1]+=lazy[node];
            lazy[2*node+2]+=lazy[node];
        }
        lazy[node]=0;
    }
    public int query(int l, int r) {
        return query(0, 0, n-1, l, r);
    }
    private int query(int node, int start, int end, int l, int r) {
        push(node, start, end);
        if(r<start || l>end)
            return 0;
        if(start>=l && end<=r)
            return nodes[node];
        int mid=(start+end)/2;
        return query(2*node+1, start, mid, l, r) + query(2*node+2, mid+1, end, l, r);
    }
    public void update(int val, int l, int r) {
        update(0, val, 0, n-1, l, r);
    }

    private void update(int node, int val, int start, int end, int l, int r) {
        push(node, start, end);
        if(l>end || r<start)
            return;
        if(start>=l && end<=r) {
            lazy[node]+=val;
            push(node, start, end);
            return;
        }
        int mid=(start+end)/2;
        update(2*node+1, val, start, mid, l, r);
        update(2*node+2, val, mid+1, end, l, r);
        nodes[node]=nodes[2*node+1]+nodes[2*node+2];
    }

    static void main() {
        int[] arr={1, 2, 1, 4, 2, 3, 1, 1};
        LazyPropagation lazyPropagation=new LazyPropagation(arr);
        int s=lazyPropagation.query(1, 3);
        IO.println(s);
        lazyPropagation.update(2, 1, 4);
        s=lazyPropagation.query(1, 3);
        IO.println(s);
    }
}
