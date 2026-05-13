import Trie.Node;

private final Node root=new Node();
private Boolean[] dp;

boolean wordBreak(String s, List<String> wordDict) {
    for(String word:wordDict)
        addWords(word);
    dp=new Boolean[s.length()+1];
    return solve(s);
}

void addWords(String s) {
    Node temp=root;
    for(char c:s.toCharArray()) {
        if(!temp.containsKey(c))
            temp.put(c);
        temp=temp.get(c);
    }
    temp.setEnd();
}
/*
Memoized version
 */
boolean dfs(String s, int id) {
    if(id==s.length())
        return true;
    if(dp[id]!=null)
        return dp[id];
    Node temp=root;
    for(char c:s.toCharArray()) {
        if(!temp.containsKey(c))
            break;
        temp=temp.get(c);
        if(temp.isEnd() && dfs(s, id+1))
            return dp[id]=true;
    }
    return dp[id]=false;
}
/*
* DP bottom up approach
* */
boolean solve(String s) {
    int n=s.length();
    dp[n]=true;
    for(int i=n-1;i>=0;i--) {
        Node temp = root;
        dp[i] = false;
        for (int j = i; j < n; j++) {
            char ch = s.charAt(j);
            if (!temp.containsKey(ch))
                break;
            temp = temp.get(ch);
            if (temp.isEnd() && dp[j+1]) {
                dp[i] = true;
                break;
            }
        }
    }
    return dp[0];
}

void main() {
//    boolean isBreakable = wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet", "code")));
    boolean isBreakable = wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats","dog","sand","and","cat")));
    IO.println(isBreakable);
}