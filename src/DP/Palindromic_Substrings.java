/*
Palindromic Substrings

Given a string s, return the number of palindromic substrings in it.

A string is a palindrome when it reads the same backward as forward.

A substring is a contiguous sequence of characters within the string.

https://leetcode.com/problems/palindromic-substrings/description/
*/

public int countSubstrings(String s) {
    int total=0, n=s.length();
    for(int i=0;i<n;i++)
        total+=paliSub(s, i, i) + paliSub(s, i, i+1);
    return total;
}

private int paliSub(String s, int l, int r) {
    int count=0;
    while(l>=0 && r<s.length() && s.charAt(l--)==s.charAt(r++))
        count++;
    return count;
}

void main() {
    String s = "aaa";
    IO.println(countSubstrings(s));
}