class Node {
    Node[] child=new Node[26];
    Node fail;
    List<int[]> out=new ArrayList();
}

private Node root=new Node();
private int INF=(int)1e9;
public int minimumCost(String target, String[] words, int[] costs) {
    for(int i=0;i<costs.length;i++)
        insert(words[i], costs[i]);
    buildFailure();
    int n=target.length();
    int[] dp=new int[n+1];
    Arrays.fill(dp, INF);
    dp[0]=0;
    Node node=root;
    for(int i=0;i<n;i++) {
        int id=target.charAt(i)-97;
        while(node!=root && node.child[id]==null)
            node=node.fail;
        if(node.child[id]!=null)
            node=node.child[id];
        for(int[] match:node.out) {
            int l=match[0], cost=match[1];
            dp[i+1]=Math.min(dp[i+1], dp[i+1-l]+cost);
        }
    }
    return dp[n]==INF?-1:dp[n];
}
private void insert(String s, int cost) {
    Node node=root;
    for(char c:s.toCharArray()) {
        int idx=c-97;
        if(node.child[idx]==null)
            node.child[idx]=new Node();
        node=node.child[idx];
    }
    node.out.add(new int[]{s.length(), cost});
}
private void buildFailure() {
    Queue<Node> q=new LinkedList();
    root.fail=root;
    for(int i=0;i<26;i++) {
        if(root.child[i]!=null) {
            root.child[i].fail=root;
            q.add(root.child[i]);
        }
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
    String target="abcdef";
    String[] words={"abdef","abc","d","def","ef"};
    int[] costs={100, 1, 1, 10, 5};
    int minCost=minimumCost(target, words, costs);
    IO.println(minCost);

}