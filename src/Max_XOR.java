// Find max xor among array of give numbers
// Trie + greedy
// 1^0 = 0^1 = 1
// To maximize output xor with opposite bits
class Node {
    Node[] bits;
    Node() {
        bits=new Node[2];
    }
    boolean containsKey(int bit) {
        return bits[bit]!=null;
    }
    void put(int bit) {
        bits[bit]=new Node();
    }
    Node get(int bit) {
        return bits[bit];
    }
}
private Node root=new Node();
void insert(int num) {
    Node temp=root;
    for(int i=31;i>=0;i--) {
        int bit=(num>>i)&1;
        if(!temp.containsKey(bit))
            temp.put(bit);
        temp=temp.get(bit);
    }
}

int getMax(int num) {
    Node temp=root;
    int res=0;
    for(int i=31;i>=0;i--) {
        int bit=(num>>i)&1;
        if(temp.containsKey(1-bit)) {
            res|=(1<<i);
            temp=temp.get(1-bit);
        } else
            temp.get(bit);
    }
    return res;
}

void main() {
    int[] nums={2, 5, 1, 3, 7};
    for(int i:nums)
        insert(i);
    int max=(1<<31);
    for(int i:nums)
        max=Math.max(max, getMax(i));
    IO.println(max);
}