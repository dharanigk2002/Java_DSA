package Trie_Ds.Trie;
// Prefix tree ds
public class Node {
    Node[] child;
    boolean end;

    public Node() {
        child=new Node[26];
        end=false;
    }

    public boolean containsKey(char c) {
        return child[c-97]!=null;
    }

    public void put(char ch) {
        child[ch-97]=new Node();
    }

    public void setEnd() {
        end=true;
    }

    public boolean isEnd() {
        return end;
    }

    public Node get(char ch) {
        return child[ch-97];
    }
}