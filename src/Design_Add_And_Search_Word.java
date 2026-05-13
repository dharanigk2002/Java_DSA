import Trie.Node;
/*
Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

WordDictionary() Initializes the object.
void addWord(word) Adds word to the data structure, it can be matched later.
bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
word may contain dots '.' where dots can be matched with any letter.
*/

private Node root=null;
void addWord(String word) {
    Node temp=root;
    for(char c:word.toCharArray()) {
        if(!temp.containsKey(c))
            temp.put(c);
        temp=temp.get(c);
    }
    temp.setEnd();
}

boolean dfs(String s, int id, Node root) {
    if(id==s.length())
        return true;
    char c=s.charAt(id);
    if(c=='.') {
        for(char ch='a';ch<='z';ch++)
            if(root.containsKey(ch) && dfs(s, id+1, root.get(ch)))
                return true;
        return false;
    }
    if(!root.containsKey(c))
        return false;
    return dfs(s, id+1, root.get(c));
}

boolean search(String word) {
    return dfs(word, 0, root);
}

void main() {
    root=new Node();
    addWord("bad");
    addWord("dad");
    addWord("mad");
    IO.println("Word pad is"+(search("pad")?" found":" not found"));
    IO.println("Word bad is"+(search("bad")?" found":" not found"));
    IO.println("Word .ad is"+(search(".ad")?" found":" not found"));
    IO.println("Word b.. is"+(search("b..")?" found":" not found"));
}