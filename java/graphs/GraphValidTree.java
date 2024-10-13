class Solution {
    private Map<Integer, List<Integer>> adjList;
    private Set<Integer> currPath;
    private Set<Integer> visited;

    public boolean validTree(int n, int[][] edges) {
        adjList = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        currPath = new HashSet<>();
        visited = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            if (!dfs(i, -1)) {
                return false;
            }
        }

        return visited.size() == n;
    }

    private boolean dfs(int course, int prev) {
        if (course == prev) {
            return true;
        }

        if (currPath.contains(course)) {
            return false;
        }

        if (visited.contains(course)) {
            return true;
        }

        currPath.add(course);
        for (int neigh : adjList.get(course)) {
            if (!dfs(neigh, course)) {
                return false;
            }
        }

        currPath.remove(course);
        visited.add(course);

        return true;
    }
}

