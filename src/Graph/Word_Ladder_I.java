/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: 5
Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.

https://leetcode.com/problems/word-ladder/description/
*/

public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    Set<String> dict=new HashSet<>(wordList);
    if(!dict.contains(endWord))
        return 0;
    Queue<String> q=new LinkedList<>();
    q.add(beginWord);
    int level=0;
    while(!q.isEmpty()) {
        int size=q.size();
        level++;
        while(size-->0) {
            String word=q.poll();
            if(word.equals(endWord))
                return level;
            char[] ch=word.toCharArray();
            int n=ch.length;
            for(int i=0;i<n;i++) {
                char curr=ch[i];
                for(char c='a';c<='z';c++) {
                    if(curr==c) continue;
                    ch[i]=c;
                    String s=new String(ch);
                    if(dict.contains(s)) {
                        q.add(s);
                        dict.remove(s);
                    }
                }
                ch[i]=curr;
            }
        }
    }
    return 0;
}

void main() {
    String beginWord = "hit", endWord = "cog";
    List<String> wordList = List.of("hot","dot","dog","lot","log","cog");
    int transformations = ladderLength(beginWord, endWord, wordList);
    IO.println(transformations);
}