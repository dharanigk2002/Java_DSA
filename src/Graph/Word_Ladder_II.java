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

public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> res=new ArrayList();
    Set<String> dict=new HashSet(wordList);
    if(!dict.contains(endWord))
        return res;
    Queue<List<String>> q=new LinkedList();
    q.add(new ArrayList<>(List.of(beginWord)));
    while(!q.isEmpty()) {
        int size=q.size();
        List<String> wordsFormed=new ArrayList();
        while(size-->0) {
            List<String> words=q.poll();
            String s=words.get(words.size()-1);
            if(s.equals(endWord))
                res.add(words);
            char[] ch=s.toCharArray();
            int n=ch.length;
            for(int i=0;i<n;i++) {
                char curr=ch[i];
                for(char c='a'; c<='z'; c++) {
                    if(curr==c) continue;
                    ch[i]=c;
                    String st=new String(ch);
                    if(dict.contains(st)) {
                        List<String> list=new ArrayList(words);
                        list.add(st);
                        wordsFormed.add(st); // To remove formed words from alien dictionary
                        q.add(list);
                    }
                }
                ch[i]=curr;
            }
        }
        for(String st:wordsFormed)
            dict.remove(st);
    }
    return res;
}

void main() {
    String beginWord = "hit", endWord = "cog";
    List<String> wordList = List.of("hot","dot","dog","lot","log","cog");
    List<List<String>> transforms = findLadders(beginWord, endWord, wordList);
    IO.println(transforms);
}