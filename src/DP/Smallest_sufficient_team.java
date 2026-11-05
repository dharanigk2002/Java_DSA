/*
Smallest Sufficient Team

In a project, you have a list of required skills req_skills, and a list of people. The ith person people[i] contains a list of skills that the person has.

Consider a sufficient team: a set of people such that for every required skill in req_skills, there is at least one person in the team who has that skill. We can represent these teams by the index of each person.

For example, team = [0, 1, 3] represents the people with skills people[0], people[1], and people[3].
Return any sufficient team of the smallest possible size, represented by the index of each person. You may return the answer in any order.

It is guaranteed an answer exists.

https://leetcode.com/problems/smallest-sufficient-team/description/
*/

private int full, n;
private int[] peop_skill;
private static final long INF=Long.MAX_VALUE;
private final Map<String, Integer> map=new HashMap();
private Long[][] dp;

private long f(int i, int mask) {
    if(dp[i][mask]!=null)
        return dp[i][mask];
    if(mask==full)
        return dp[i][mask]=0L;
    if(i==n)
        return dp[i][mask]=INF;
    long skip=f(i+1, mask);
    long take=INF;
    if((mask|peop_skill[i])!=mask) {
        long sub=f(i+1, mask|peop_skill[i]);
        if(sub!=INF)
            take=sub|(1L<<i);
    }
    return dp[i][mask]=Long.bitCount(skip)<Long.bitCount(take)?skip:take;
}

private int[] toArray(long team) {
    int[] res = new int[Long.bitCount(team)];
    for (int i = 0, k = 0; i < 64; i++)
        if ((team >> i & 1) == 1) res[k++] = i;
    return res;
}

public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
    n=people.size();
    int m=req_skills.length;
    full=(1<<m)-1;
    for(int i=0;i<m;i++)
        map.put(req_skills[i], i);
    m=people.size();
    long[][] dp=new long[m+1][full+1];
    peop_skill=new int[m];
    for(int i=0;i<m;i++)
        for(String s:people.get(i))
            peop_skill[i]|=(1<<map.get(s));
    for(int mask=0;mask<full;mask++)
        dp[m][mask]=INF;
    for(int i=m-1;i>=0;i--) {
        for(int mask=full;mask>=0;mask--) {
            long skip=dp[i+1][mask];
            long take=INF;
            if((mask|peop_skill[i])!=mask) {
                long sub=dp[i+1][mask|peop_skill[i]];
                if(sub!=INF)
                    take=sub|(1L<<i);
            }
            dp[i][mask]=Long.bitCount(skip)<Long.bitCount(take)?skip:take;
        }
    }
    long team = dp[0][0];
    return toArray(team);
}

void main() {
    String[] req_skills = {"java","nodejs","reactjs"};
    List<List<String>> people = List.of(
        List.of("java"),
        List.of("nodejs"),
        List.of("nodejs","reactjs")
    );
}