package Tree;

public class SegmentTree {
    int[] nodes;
    int[] arr;
    int n;
    SegmentTree(int[] arr) {
        this.arr=arr;
        n=arr.length;
        nodes=new int[4*n];
        build(1, 0, n-1);
    }

    private void build(int node, int l, int r) {
        if(l==r) {
            nodes[node]=arr[l];
            return;
        }
        int m=(l+r)/2;
        build(2*node, l, m);
        build(2*node+1, m+1, r);
        nodes[node]=nodes[2*node]+nodes[2*node+1];
    }

    public int query(int l, int r) {
        return query(1, 0, n-1, l, r);
    }

    private int query(int node, int tl, int tr, int l, int r) {
        if(tl>=l && tr<=r)
            return nodes[node];
        if(tr<l || tl>r)
            return 0;
        int mid=(tl+tr)/2;
        return query(2*node, tl, mid, l, r) + query(2*node+1, mid+1, tr, l, r);
    }

    public void update(int index, int value) {
        update(index, value, 1, 0, n-1);
    }

    private void update(int index, int val, int node, int l, int r) {
        if(l==r) {
            nodes[node]=arr[l]=val;
            return;
        }
        int mid=(l+r)/2;
        if(index<=mid)
            update(index, val, 2*node, l, mid);
        else
            update(index, val, 2*node+1, mid+1, r);
        nodes[node]=nodes[2*node]+nodes[2*node+1];
    }

    public static void main(String[] args) {
        int[] arr={1, 2, 1, 4, 2, 3, 1, 1};
        SegmentTree segmentTree=new SegmentTree(arr);
        IO.println(segmentTree.query(2, 6));
        segmentTree.update(2, 2);
        IO.println(segmentTree.query(2, 6));
    }
}

