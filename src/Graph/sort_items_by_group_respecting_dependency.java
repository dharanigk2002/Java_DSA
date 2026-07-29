/*
Sort Items by Groups Respecting Dependencies

There are n items each belonging to zero or one of m groups where group[i] is the group that the i-th item belongs to and it's equal to -1 if the i-th item belongs to no group. The items and the groups are zero indexed. A group can have no item belonging to it.

Return a sorted list of the items such that:

The items that belong to the same group are next to each other in the sorted list.
There are some relations between these items where beforeItems[i] is a list containing all the items that should come before the i-th item in the sorted array (to the left of the i-th item).
Return any solution if there is more than one solution and return an empty list if there is no solution.

https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/description/
*/
public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
    for (int i = 0; i < n; i++)
        if (group[i] == -1)
            group[i] = m++;

    List<List<Integer>> itemGraph = new ArrayList<>();
    List<List<Integer>> groupGraph = new ArrayList<>();

    int[] itemDegree = new int[n];
    int[] groupDegree = new int[m];

    for (int i = 0; i < n; i++)
        itemGraph.add(new ArrayList<>());

    for (int i = 0; i < m; i++)
        groupGraph.add(new ArrayList<>());

    for (int item = 0; item < n; item++) {
        for (int before : beforeItems.get(item)) {
            itemGraph.get(before).add(item);
            itemDegree[item]++;

            int beforeGroup = group[before];
            int currentGroup = group[item];
            if (beforeGroup != currentGroup) {
                groupGraph.get(beforeGroup).add(currentGroup);
                groupDegree[currentGroup]++;
            }
        }
    }

    List<Integer> itemOrder = topologicalSort(itemGraph, itemDegree);

    if (itemOrder.size() != n)
        return new int[0];

    List<Integer> groupOrder = topologicalSort(groupGraph, groupDegree);

    if (groupOrder.size() != m)
        return new int[0];

    Map<Integer, List<Integer>> groupedItems = new HashMap<>();
    for (int item : itemOrder) {
        groupedItems
                .computeIfAbsent(group[item], key -> new ArrayList<>())
                .add(item);
    }

    int[] answer = new int[n];
    int index = 0;

    for (int currentGroup : groupOrder)
        for (int item : groupedItems.getOrDefault(currentGroup, Collections.emptyList()))
            answer[index++] = item;
    return answer;
}

private List<Integer> topologicalSort(List<List<Integer>> graph, int[] indegree) {
    Queue<Integer> queue = new ArrayDeque<>();
    List<Integer> order = new ArrayList<>();

    for (int node = 0; node < graph.size(); node++) {
        if (indegree[node] == 0) {
            queue.offer(node);
        }
    }

    while (!queue.isEmpty()) {
        int node = queue.poll();
        order.add(node);

        for (int next : graph.get(node)) {
            if (--indegree[next] == 0) {
                queue.offer(next);
            }
        }
    }

    return order;
}


void main() {
    int n = 8, m = 2;
    int[] group = {-1,-1,1,0,0,1,0,-1};
    List<List<Integer>> beforeItems = List.of(
            List.of(),
            List.of(6),
            List.of(5),
            List.of(6),
            List.of(3, 6),
            List.of(),
            List.of(),
            List.of()
    );
    System.out.println(Arrays.toString(sortItems(n, m, group, beforeItems)));
}