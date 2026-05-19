public String foreignDictionary(String[] words) {
    int[] indegree=new int[26];
    int order=0;
    Queue<Integer> q=new LinkedList<>();
    StringBuilder res=new StringBuilder();
    List<Set<Integer>> adj=new ArrayList<>();
    for(int i=0;i<26;i++) {
        indegree[i]=-1;
        adj.add(new HashSet<>());
    }
    for(String word:words)
        for(char c:word.toCharArray())
            if(indegree[c-97]==-1) {
                order++;
                indegree[c-97]=0;
            }

    for(int i=1;i<words.length;i++) {
        String w1=words[i-1], w2=words[i];
        if(w1.length() > w2.length() && w1.startsWith(w2))
            return "";
        int len=Math.min(w1.length(), w2.length());
        for(int j=0;j<len;j++) {
            char c1 = w1.charAt(j), c2 = w2.charAt(j);
            if (c1 != c2) {
                if (!adj.get(c1 - 97).contains(c2 - 97)) {
                    adj.get(c1 - 97).add(c2 - 97);
                    indegree[c2 - 97]++;
                }
                break;
            }
        }
    }
    for(int i=0;i<26;i++)
        if(indegree[i]==0)
            q.add(i);
    while(!q.isEmpty()) {
        int node=q.poll();
        res.append((char)(node+97));
        for(int nei:adj.get(node)) {
            if(--indegree[nei]==0)
                q.add(nei);
        }
    }
    return order==res.length() ? res.toString() : "";
}

void main() {
    String[] words = {"hrn","hrf","er","enn","rfnn"};
    String order=foreignDictionary(words);
    IO.println(order);
}