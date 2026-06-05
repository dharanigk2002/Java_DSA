/*
You have information about n different recipes. You are given a string array recipes and a 2D string array ingredients. The ith recipe has the name recipes[i], and you can create it if you have all the needed ingredients from ingredients[i]. A recipe can also be an ingredient for other recipes, i.e., ingredients[i] may contain a string that is in recipes.

You are also given a string array supplies containing all the ingredients that you initially have, and you have an infinite supply of all of them.

Return a list of all the recipes that you can create. You may return the answer in any order.

Note that two recipes may contain each other in their ingredients.

https://leetcode.com/problems/find-all-possible-recipes-from-given-supplies/description/
*/

List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
    Map<String, List<String>> g=new HashMap();
    Map<String, Integer> indegree=new HashMap();
    int n=recipes.length;
    for(int i=0;i<n;i++)
        for(String ing:ingredients.get(i)) {
            g.computeIfAbsent(ing, k->new ArrayList()).add(recipes[i]);
            indegree.put(recipes[i], indegree.getOrDefault(recipes[i], 0)+1);
        }
    Queue<String> q=new LinkedList();
    for(String s:supplies)
        q.add(s);
    Set<String> visited=new HashSet();
    while(!q.isEmpty()) {
        String node=q.poll();
        visited.add(node);
        for(String next:g.getOrDefault(node, List.of())) {
            indegree.put(next, indegree.get(next)-1);
            if(indegree.get(next)==0)
                q.add(next);
        }
    }
    List<String> ans=new ArrayList();
    for(String r:recipes)
        if(visited.contains(r))
            ans.add(r);
    return ans;
}

void main() {
    String[] recipes = {"bread","sandwich","burger"}, supplies = {"yeast","flour","meat"};
    List<List<String>> ingredients = List.of(
            List.of("yeast","flour"),
            List.of("bread","meat"),
            List.of("sandwich","meat","bread")
    );
    IO.println(findAllRecipes(recipes, ingredients, supplies));
}