/*
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

https://leetcode.com/problems/accounts-merge/description/
*/

class DSU {
    int[] parent, rank;
    public DSU(int n) {
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++)
            parent[i]=i;
    }
    public boolean union(int u, int v) {
        int pu=findParent(u), pv=findParent(v);
        if(rank[pu]>rank[pv])
            parent[pv]=pu;
        else if(rank[pu]<rank[pv])
            parent[pu]=pv;
        else {
            parent[pv]=pu;
            rank[pu]++;
        }
        return false;
    }
    public int findParent(int node) {
        if(node==parent[node])
            return node;
        return parent[node]=findParent(parent[node]);
    }
}
public List<List<String>> accountsMerge(List<List<String>> accounts) {
    Map<String, Integer> mails=new HashMap<>();
    List<List<String>> res=new ArrayList();
    int n=accounts.size();
    DSU ds=new DSU(n);
    for(int i=0;i<n;i++) {
        for(int j=1;j<accounts.get(i).size();j++) {
            String mail=accounts.get(i).get(j);
            mails.putIfAbsent(mail, i);
            int ind=mails.get(mail);
            if(i!=ind)
                ds.union(i, ind);
        }
    }
    Map<Integer, List<String>> merge=new HashMap();
    for(String mail:mails.keySet()) {
        int index=mails.get(mail);
        int parent=ds.findParent(index);
        merge.putIfAbsent(parent, new ArrayList());
        merge.get(parent).add(mail);
    }
    for(int i:merge.keySet()) {
        List<String> mailList=merge.get(i);
        Collections.sort(mailList);
        mailList.add(0, accounts.get(i).get(0));
        res.add(mailList);
    }
    return res;
}


void main() {
    List<List<String>> accounts = List.of(
            List.of("John","johnsmith@mail.com","john_newyork@mail.com"),
            List.of("John","johnsmith@mail.com","john00@mail.com"),
            List.of("Mary","mary@mail.com"),
            List.of("John","johnnybravo@mail.com")
    );
    IO.println(accountsMerge(accounts));
}