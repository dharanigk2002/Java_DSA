/*
You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.

You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.

Return the answers to all queries. If a single answer cannot be determined, return -1.0.

Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.

Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.

https://leetcode.com/problems/evaluate-division/description/
*/

class Pair {
    String node;
    double val;
    Pair(String f, double v) {
        node=f;
        val=v;
    }
}

double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    int n=queries.size();
    double[] ans=new double[n];
    Map<String, List<Pair>> g=new HashMap();
    for(int i=0;i<values.length;i++) {
        List<String> eqn=equations.get(i);
        String u=eqn.get(0), v=eqn.get(1);
        g.putIfAbsent(u, new ArrayList());
        g.putIfAbsent(v, new ArrayList());
        g.get(u).add(new Pair(v, values[i]));
        g.get(v).add(new Pair(u, 1.0/values[i]));
    }
    for(int i=0;i<n;i++) {
        String u=queries.get(i).get(0), v=queries.get(i).get(1);
        ans[i]=dfs(g, u, v, new HashSet<>());
    }
    return ans;
}

double dfs(Map<String, List<Pair>> g, String src, String dst, Set<String> visited) {
    if(!g.containsKey(src) || !g.containsKey(dst))
        return -1.0;
    if(src.equals(dst))
        return 1.0;
    visited.add(src);
    for(Pair next:g.get(src))
        if(!visited.contains(next.node)) {
            double res=dfs(g, next.node, dst, visited);
            if(res!=-1.0)
                return next.val*res;
        }
    return -1.0;
}

void main() {
    List<List<String>> eqns = List.of(List.of("a", "b"), List.of("b", "c"));
    double[] values = {2.0, 3.0};
    List<List<String>> queries = List.of(
            List.of("a", "c"),
            List.of("b", "a"),
            List.of("a", "e"),
            List.of("a", "a"),
            List.of("x", "x")
    );
    IO.println(Arrays.toString(calcEquation(eqns, values, queries)));
}