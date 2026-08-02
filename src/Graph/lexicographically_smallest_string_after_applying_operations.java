/*
Lexicographically Smallest String After Applying Operations

You are given a string s of even length consisting of digits from 0 to 9, and two integers a and b.

You can apply either of the following two operations any number of times and in any order on s:

Add a to all odd indices of s (0-indexed). Digits post 9 are cycled back to 0. For example, if s = "3456" and a = 5, s becomes "3951".
Rotate s to the right by b positions. For example, if s = "3456" and b = 1, s becomes "6345".
Return the lexicographically smallest string you can obtain by applying the above operations any number of times on s.

A string a is lexicographically smaller than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears earlier in the alphabet than the corresponding letter in b. For example, "0158" is lexicographically smaller than "0190" because the first position they differ is at the third letter, and '5' comes before '9'.

https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/description/
*/

public String findLexSmallestString(String s, int a, int b) {
    int n=s.length();
    Set<String> set=new HashSet();
    Queue<String> q=new PriorityQueue();
    q.add(s);
    set.add(s);
    String smallest=s;
    while(!q.isEmpty()) {
        String node=q.poll();
        if(node.compareTo(smallest)<0)
            smallest=node;
        char[] ch=node.toCharArray();
        for(int i=1;i<n;i+=2) {
            int num=(int)(ch[i]-48);
            num=(num+a)%10;
            ch[i]=(char)(num+48);
        }
        String next=new String(ch);
        if(set.add(next))
            q.add(next);
        ch=node.toCharArray();
        rotate(ch, b);
        next=new String(ch);
        if(set.add(next))
            q.add(next);
    }
    return smallest;
}

private void reverse(char[] ch, int i, int j) {
    while(i<j) {
        char temp=ch[i];
        ch[i++]=ch[j];
        ch[j--]=temp;
    }
}

private void rotate(char[] ch, int b) {
    int n=ch.length;
    reverse(ch, 0, b-1);
    reverse(ch, b, n-1);
    reverse(ch, 0, n-1);
}

void main() {
    String s = "5525";
    int a = 9, b = 2;

    System.out.println(findLexSmallestString(s, a, b));
}