package Tree;

public class FenwickTree {
    int[] bit;
    int n;
    FenwickTree(int[] arr) {
        n=arr.length;
        bit=new int[n+1];
        for(int i=0;i<n;i++)
            update(i+1, arr[i]);
    }
    public void update(int id, int val) {
        while(id<=n) {
            bit[id]+=val;
            id+=(id&-id);
        }
    }
    public int query(int id) {
        int sum=0;
        while(id>0) {
            sum+=bit[id];
            id-=(id&-id);
        }
        return sum;
    }

    static void main() {
        int[] arr={1, 2, 1, 4, 2, 3, 1, 1};
        FenwickTree fenwickTree=new FenwickTree(arr);
        int range = fenwickTree.query(5) - fenwickTree.query(2);
        IO.println(range);
    }
}
