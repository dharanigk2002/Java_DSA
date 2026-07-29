/*
Minimum Genetic Mutation

A gene string can be represented by an 8-character long string, with choices from 'A', 'C', 'G', and 'T'.

Suppose we need to investigate a mutation from a gene string startGene to a gene string endGene where one mutation is defined as one single character changed in the gene string.

For example, "AACCGGTT" --> "AACCGGTA" is one mutation.
There is also a gene bank bank that records all the valid gene mutations. A gene must be in bank to make it a valid gene string.

Given the two gene strings startGene and endGene and the gene bank bank, return the minimum number of mutations needed to mutate from startGene to endGene. If there is no such a mutation, return -1.

Note that the starting point is assumed to be valid, so it might not be included in the bank.

https://leetcode.com/problems/minimum-genetic-mutation/description/
*/

public int minMutation(String startGene, String endGene, String[] bank) {
    Set<String> set=new HashSet(Arrays.asList(bank));
    if(!set.contains(endGene))
        return -1;
    int level=0;
    Queue<String> q=new LinkedList();
    char[] genes={'A', 'C', 'G', 'T'};
    q.add(startGene);
    while(!q.isEmpty()) {
        for(int size=q.size();size>0;size--) {
            String node=q.poll();
            if(node.equals(endGene))
                return level;
            char[] ch=node.toCharArray();
            for(int i=0;i<8;i++) {
                char curr=ch[i];
                for(char c:genes)
                    if(curr!=c) {
                        ch[i]=c;
                        String next=String.valueOf(ch);
                        if(set.contains(next)) {
                            set.remove(next);
                            q.add(next);
                        }
                    }
                ch[i]=curr;
            }
        }
        level++;
    }
    return -1;
}

void main() {
    String startGene = "AACCGGTT", endGene = "AAACGGTA";
    String[] bank = {"AACCGGTA","AACCGCTA","AAACGGTA"};
    System.out.println(minMutation(startGene, endGene, bank));
}