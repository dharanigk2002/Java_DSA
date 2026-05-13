class Node {
    Node[] child;
    Node fail;
    List<String> out;

    Node() {
        child=new Node[26];
        fail=null;
        out=new ArrayList<>();
    }
}
Node root=new Node();
void insert(String s) {
    Node node=root;
    for(char c:s.toCharArray()) {
        if (node.child[c - 97] == null)
            node.child[c - 97] = new Node();
        node=node.child[c-97];
    }
    node.out.add(s);
}
void buildFailure() {
    Queue<Node> q=new LinkedList<>();
    root.fail=root;
    for(int i=0;i<26;i++)
        if(root.child[i]!=null) {
            root.child[i].fail=root;
            q.add(root.child[i]);
        }
    while(!q.isEmpty()) {
        Node curr=q.poll();
        for(int i=0;i<26;i++) {
            Node nxt=curr.child[i];
            if(nxt==null) continue;
            Node f=curr.fail;
            while(f!=root && f.child[i]==null)
                f=f.fail;
            if(f.child[i]!=null)
                f=f.child[i];
            nxt.fail=f;
            nxt.out.addAll(f.out);
            q.add(nxt);
        }
    }
}
void main() {
    String[] words={"he", "his", "she", "her", "help"};
    String target="ahishelp";
    for(String s:words)
        insert(s);
    buildFailure();
    Node node=root;
    for(char c:target.toCharArray()) {
        int id=c-97;
        while(node!=root && node.child[id]==null)
            node=node.fail;
        if(node.child[id]!=null)
            node=node.child[id];
        for(String str:node.out)
            IO.println(str);
    }
}