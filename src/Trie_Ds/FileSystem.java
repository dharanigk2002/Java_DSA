class Node {
    String name;
    Node parent;
    Map<String, Node> children;
    int descendants;
    public Node(String name) {
        this.name=name;
        parent=null;
        descendants=0;
        children=new HashMap<>();
    }
}

Node root;
long totalNodes = 0;

void main() {
    int n=3, q=5;
    String[] tree={"root a b c", "root/a d e", "root/c f g"};
    String[] initialTree=tree[0].split("\\s+");
    root=new Node(initialTree[0]);
    totalNodes=1;
    for(int i=1;i<initialTree.length;i++) {
        totalNodes++;
        Node child=new Node(initialTree[i]);
        child.parent=root;
        root.children.put(child.name, child);
    }
    for(int i=1;i<tree.length;i++) {
        String[] paths=tree[i].split("\\s+");
        Node node=getNode(paths[0]);
        for(int j=1;j<paths.length;j++) {
            totalNodes++;
            Node child=new Node(paths[j]);
            node.children.put(child.name, child);
            child.parent=node;
        }
    }
    computeDescendants(root);
    // countDescendants root/a
    Node node=getNode("root/a");
    if(node==null)
        IO.println("Invalid command");
    else
        IO.println(node.descendants);

    // copyPaste root/a/d root/b
    if(copyPaste("root/a/d", "root/b"))
        IO.println("OK");
    else
        IO.println("Invalid command");

    // cutPaste root/c/f root/b
    if(cutPaste("root/c/f", "root/b"))
        IO.println("OK");
    else
        IO.println("Invalid command");

    // countDescendants root/b
    node=getNode("root/b");
    if(node==null)
        IO.println("Inavlid command");
    else
        IO.println(node.descendants);

    // countDescendants root/c/f
    node=getNode("root/c/f");
    if(node==null)
        IO.println("Inavlid command");
    else
        IO.println(node.descendants);
}

Node getNode(String path) {
    String[] nodes=path.split("/");
    if(!nodes[0].equals(root.name))
        return null;
    Node temp=root;
    for(int i=1;i<nodes.length;i++) {
        temp=temp.children.get(nodes[i]);
        if(temp==null)
            return null;
    }
    return temp;
}

int computeDescendants(Node node) {
    int count=0;
    for(Node child:node.children.values()) {
        count+=1;
        count+=computeDescendants(child);
    }
    return node.descendants=count;
}

boolean isAncestor(Node source, Node dst) {
    while(dst!=null) {
        if(dst==source)
            return true;
        dst=dst.parent;
    }
    return false;
}

boolean canPaste(Node src, Node dest) {
    if(src==null || dest==null)
        return false;
    if(src==dest)
        return false;
    if(isAncestor(src, dest))
        return false;
    if(dest.children.containsKey(src.name))
        return false;
    return true;
}

Node cloneTree(Node node) {
    Node copy=new Node(node.name);
    copy.descendants=node.descendants;
    for(Node child:node.children.values()) {
        Node copyChild=cloneTree(child);
        copyChild.parent=copy;
        copy.children.putIfAbsent(copyChild.name, copyChild);
    }
    return copy;
}

boolean cutPaste(String src, String dest) {
    Node s=getNode(src), dst=getNode(dest);
    if(s==root || !canPaste(s, dst))
        return false;
    Node oldParent=s.parent;
    int subtreeSize=s.descendants+1;
    updateDescendants(oldParent, -subtreeSize);
    oldParent.children.remove(s.name);
    dst.children.put(s.name, s);
    s.parent=dst;
    updateDescendants(dst, subtreeSize);
    return true;
}

boolean copyPaste(String src, String dest) {
    Node s=getNode(src), d=getNode(dest);
    if(!canPaste(s, d))
        return false;
    int subtreeSize=s.descendants+1;
    if(totalNodes+subtreeSize>1_000_000)
        return false;
    Node copy=cloneTree(s);
    d.children.put(copy.name, copy);
    copy.parent=d;
    updateDescendants(d, subtreeSize);
    totalNodes+=subtreeSize;
    return true;
}

void updateDescendants(Node node, int delta) {
    while(node!=null) {
        node.descendants+=delta;
        node=node.parent;
    }
}