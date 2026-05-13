import java.util.*;
/*
* Longest Common Suffix Queries

* You are given two arrays of strings wordsContainer and wordsQuery.

For each wordsQuery[i], you need to find a string from wordsContainer that has the longest common suffix with wordsQuery[i]. If there are two or more strings in wordsContainer that share the longest common suffix, find the string that is the smallest in length. If there are two or more such strings that have the same smallest length, find the one that occurred earlier in wordsContainer.

Return an array of integers ans, where ans[i] is the index of the string in wordsContainer that has the longest common suffix with wordsQuery[i].
* */
class Trie {
    private Trie[] nodes;
    private int len, id;

    public Trie() {
        nodes=new Trie[26];
        len=Integer.MAX_VALUE;
        id=-1;
    }

    public Trie get(char ch) {
        return nodes[ch-97];
    }

    public void put(char ch) {
        nodes[ch-97]=new Trie();
    }

    public boolean containsKey(char ch) {
        return nodes[ch-97]!=null;
    }

    public int getId() {
        return id;
    }

    public void setId(int i) {
        id=i;
    }

    public int getLength() {
        return len;
    }

    public void setLength(int length, int i) {
        if(length<len) {
            len=length;
            setId(i);
        }
    }
}

private Trie root=new Trie();
int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
    int[] ans=new int[wordsQuery.length];
    for(int i=0;i<wordsContainer.length;i++)
        addSuffix(wordsContainer[i], i);
    for(int i=0;i<ans.length;i++)
        ans[i]=getId(wordsQuery[i]);
    return ans;
}
private void addSuffix(String s, int id) {
    Trie temp=root;
    int n=s.length();
    root.setLength(n, id);
    for(int i=n-1;i>=0;i--) {
        char ch=s.charAt(i);
        if(!temp.containsKey(ch))
            temp.put(ch);
        temp=temp.get(ch);
        temp.setLength(n, id);
    }
}
int getId(String q) {
    Trie temp=root;
    for(int i=q.length()-1;i>=0;i--) {
        char ch=q.charAt(i);
        if(!temp.containsKey(ch))
            break;
        temp=temp.get(ch);
    }
    return temp.getId();
}

void main() {
//    int[] res=stringIndices(new String[]{"abcd","bcd","xbcd"}, new String[]{"cd","bcd","xyz"});
    int[] res=stringIndices(new String[]{"abcdefgh","poiuygh","ghghgh"}, new String[]{"gh","acbfgh","acbfegh"});
    IO.println(Arrays.toString(res));
}