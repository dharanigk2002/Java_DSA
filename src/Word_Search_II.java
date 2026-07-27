/*
Given an m x n board of characters and a list of strings words, return all words on the board.

Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

https://leetcode.com/problems/word-search-ii/description/
*/
import java.util.*;

class Node {
    private String word;
    private final Node[] children;

    public Node() {
        this.word=null;
        children=new Node[26];
    }

    public boolean containsKey(char c) {
        return children[c-97]!=null;
    }

    public Node get(char c) {
        return children[c-97];
    }

    public void put(char c) {
        children[c-97]=new Node();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word=word;
    }
}

private final int[][] DIRS={{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
private Node root;
public List<String> findWords(char[][] board, String[] words) {
    root=new Node();
    List<String> list=new ArrayList();
    int m=board.length, n=board[0].length;
    for(String word:words)
        insert(word);
    for(int i=0;i<m;i++)
        for(int j=0;j<n;j++)
            if(root.containsKey(board[i][j]))
                dfs(board, i, j, root, list);
    return list;
}

private void insert(String word) {
    Node temp=root;
    for(char c:word.toCharArray()) {
        if(!temp.containsKey(c))
            temp.put(c);
        temp=temp.get(c);
    }
    temp.setWord(word);
}

private void dfs(char[][] board, int i, int j, Node node, List<String> list) {
    int m=board.length, n=board[0].length;
    char c=board[i][j];
    Node current=node.get(c);
    if(current.getWord()!=null) {
        list.add(current.getWord());
        current.setWord(null);
    }
    board[i][j]='*';
    for(int[] d:DIRS) {
        int nr=i+d[0], nc=j+d[1];
        if(nr<0 || nc<0 || nr==m || nc==n || board[nr][nc]=='*' || !current.containsKey(board[nr][nc]))
            continue;
        dfs(board, nr, nc, current, list);
    }
    board[i][j]=c;
}

void main() {
    char[][] board = {
            {'o', 'a', 'a', 'n'},
            {'e', 't', 'a', 'e'},
            {'i', 'h', 'k', 'r'},
            {'i', 'f', 'l', 'v'},
    };
    String[] words = {"oath","pea","eat","rain"};

    List<String> wordList=findWords(board, words);
    IO.println(wordList);
}