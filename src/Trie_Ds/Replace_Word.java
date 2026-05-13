import Trie_Ds.Trie.Node;
/*
In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word derivative.
For example, when the root "help" is followed by the word "ful", we can form a derivative "helpful".

Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more than one root, replace it with the root that has the shortest length.

Return the sentence after the replacement.
*/

private Node root=null;

void main() {
    root=new Node();
    String sentence="the cattle was rattled by the battery";
    String[] dictionary={ "cat","bat","rat" };
//    String sentence="aadsfasf absbs bbab cadsfafs";
//    String[] dictionary={ "a","b","c" };
    for(String dict:dictionary)
        addWord(dict);
    String[] words=sentence.split(" ");
    StringBuilder res=new StringBuilder();
    for(String word:words)
        res.append(getRoot(word)).append(" ");
    res.deleteCharAt(res.length()-1);
    IO.println(res.toString());
}

String getRoot(String s) {
    Node temp=root;
    StringBuilder sb=new StringBuilder();
    for(char c:s.toCharArray()) {
        if(!temp.containsKey(c))
            break;
        sb.append(c);
        temp=temp.get(c);
        if(temp.isEnd())
            return sb.toString();
    }
    return s;
}

void addWord(String word) {
    Node temp=root;
    for(char c:word.toCharArray()) {
        if(!temp.containsKey(c))
            temp.put(c);
        temp=temp.get(c);
    }
    temp.setEnd();
}