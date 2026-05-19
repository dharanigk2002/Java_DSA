/*
In an alien language, surprisingly, they also use English lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of lowercase letters.

Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted lexicographically in this alien language.

https://leetcode.com/problems/verifying-an-alien-dictionary/description/
*/
public boolean isAlienSorted(String[] words, String order) {
    int[] map=new int[26];
    int n=words.length;
    for(int i=0;i<26;i++)
        map[order.charAt(i)-97]=i;
    for(int i=1;i<n;i++) {
        String w1=words[i-1], w2=words[i];
        if(w1.length() > w2.length() && w1.startsWith(w2))
            return false;
        int j=0, len=Math.min(w1.length(), w2.length());
        while(j<len) {
            char c1=w1.charAt(j), c2=w2.charAt(j);
            if(c1!=c2) {
                if(map[c1-97]>map[c2-97])
                    return false;
                break;
            }
            j++;
        }
    }
    return true;
}
void main() {
    String[] words = {"hello","leetcode"};
//    String[] words = {"apple","app"};
    String order = "hlabcdefgijkmnopqrstuvwxyz";
    IO.println(isAlienSorted(words, order));
}