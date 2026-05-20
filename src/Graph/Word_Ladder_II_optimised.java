/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"

https://leetcode.com/problems/word-ladder-ii/description/
*/
List<List<String>> ans=null;
Map<String, Integer> map=null;
String b=null;
public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    Set<String> dict=new HashSet<>(wordList);
    ans=new ArrayList<>();
    map=new HashMap<>();
    if(!dict.contains(endWord))
        return ans;
    b=beginWord;
    Queue<String> q=new LinkedList<>();
    q.add(beginWord);
    map.put(beginWord, 1);
    dict.remove(beginWord);
    while (!q.isEmpty()) {
        String word = q.poll();
        if (word.equals(endWord)) break;
        int level = map.get(word);
        char[] ch = word.toCharArray();
        int n = ch.length;
        for (int i = 0; i < n; i++) {
            char curr = ch[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (curr == c) continue;
                ch[i] = c;
                String s = new String(ch);
                if (dict.contains(s)) {
                    q.add(s);
                    dict.remove(s);
                    map.put(s, level + 1);
                }
            }
            ch[i] = curr;
        }
    }
    List<String> seq=new ArrayList<>();
    if(map.containsKey(endWord)) {
        seq.add(endWord);
        dfs(endWord, seq);
    }
    return ans;
}

private void dfs(String word, List<String> seq) {
    if(word.equals(b)) {
        List<String> rev=new ArrayList<>(seq);
        Collections.reverse(rev);
        ans.add(rev);
        return;
    }
    char[] ch=word.toCharArray();
    int level=map.get(word);
    int n=ch.length;
    for(int i=0;i<n;i++) {
        char curr=ch[i];
        for(char c='a';c<='z';c++) {
            if(curr==c) continue;
            ch[i]=c;
            String s=new String(ch);
            if(map.containsKey(s) && map.get(s)+1==level) {
                seq.add(s);
                dfs(s, seq);
                seq.remove(seq.size()-1);
            }
        }
        ch[i]=curr;
    }
}

void main() {
    String beginWord = "hit", endWord = "cog";
    List<String> wordList = List.of("hot","dot","dog","lot","log","cog");
    List<List<String>> transforms = findLadders(beginWord, endWord, wordList);
    IO.println(transforms);
}