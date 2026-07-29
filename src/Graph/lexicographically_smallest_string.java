/*
Lexicographically Smallest Equivalent String

You are given two strings of the same length s1 and s2 and a string baseStr.

We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:

Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.

https://leetcode.com/problems/lexicographically-smallest-equivalent-string/description/
*/

private final int[] parent=new int[26];
public String smallestEquivalentString(String s1, String s2, String baseStr) {
    int n=s1.length();
    for(int i=0;i<26;i++)
        parent[i]=i;
    StringBuilder sb=new StringBuilder();
    for(int i=0;i<n;i++)
        union(s1.charAt(i)-97, s2.charAt(i)-97);
    for(char c:baseStr.toCharArray()) {
        int smallest=find(c-97);
        sb.append((char)(smallest+97));
    }
    return sb.toString();
}

private int find(int u) {
    if(u==parent[u])
        return u;
    return parent[u]=find(parent[u]);
}

private void union(int u, int v) {
    int pu=find(u), pv=find(v);
    if(pu==pv)
        return;
    if(pu<pv)
        parent[pv]=pu;
    else
        parent[pu]=pv;
}

void main() {
    String s1 = "parker", s2 = "morris", baseStr = "parser";
    System.out.println(smallestEquivalentString(s1, s2, baseStr));
}