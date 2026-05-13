class Node {
    Node[] child=new Node[26];
    boolean end=false;
    int cost=Integer.MAX_VALUE;
}

private Node root=new Node();
private Integer[] dp;

public int minimumCost(String target, String[] words, int[] costs) {
    for(int i=0;i<words.length;i++)
        addWord(words[i], costs[i]);
    dp=new Integer[target.length()];
//    int res=dfs(target, 0);
    int res=dfsDP(target);
    return res==Integer.MAX_VALUE ? -1 : res;
}

private int dfs(String s, int id) {
    if(id==s.length())
        return 0;
    if(dp[id]!=null)
        return dp[id];
    Node temp=root;
    int ans=Integer.MAX_VALUE;
    for(int i=id;i<s.length();i++) {
        char c=s.charAt(i);
        if(temp.child[c-97]==null)
            break;
        temp=temp.child[c-97];
        if(temp.end) {
            int next=dfs(s, i+1);
            if(next!=Integer.MAX_VALUE)
                ans=Math.min(ans, next+temp.cost);
        }
    }
    return dp[id]=ans;
}

private int dfsDP(String target) {
    int n=target.length();
    int[] cost=new int[n+1];
    Arrays.fill(cost, Integer.MAX_VALUE);
    cost[n]=0;
    for(int i=n-1;i>=0;i--) {
        Node temp=root;
        for(int j=i;j<n;j++) {
            char c=target.charAt(j);
            if(temp.child[c-97]==null)
                break;
            temp=temp.child[c-97];
            if(temp.end && cost[j+1]!=Integer.MAX_VALUE) {
                cost[i]=Math.min(cost[i], cost[j+1]+temp.cost);
            }
        }
    }
    return cost[0];
}

private void addWord(String s, int cost) {
    Node temp=root;
    for(char c:s.toCharArray()) {
        if(temp.child[c-97]==null)
            temp.child[c-97]=new Node();
        temp=temp.child[c-97];
    }
    temp.end=true;
    temp.cost=Math.min(temp.cost, cost);
}

void main() {
//    String target="abcdef";
//    String[] words={"abdef","abc","d","def","ef"};
    String target="wvgafw";
    String[] words={"wvgafw","w"};
    int[] costs={1, 2};
    int minCost=minimumCost(target, words, costs);
    IO.println(minCost); // abc+d+ef = 1+1+5 = 7 | abc+def = 1+10 = 11 > 7 So, minimum cost is 7.
}