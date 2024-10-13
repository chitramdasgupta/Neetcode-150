class Solution {
    private Map<Integer, List<Integer>> adjList;
    private Set<Integer> currPath;
    private Set<Integer> visited;
    private List<Integer> res;

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        adjList = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] prereq : prerequisites) {
            adjList.get(prereq[0]).add(prereq[1]);
        }

        visited = new HashSet<>();
        currPath = new HashSet<>();

        res = new ArrayList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (!dfs(i)) {
                return new int[] {};
            }
        }

        return res.stream().mapToInt(i -> i).toArray();
    }

    private boolean dfs(int course) {
        if (currPath.contains(course)) {
            return false;
        }

        if (visited.contains(course)) {
            return true;
        }

        currPath.add(course);
        for (int neigh : adjList.get(course)) {
            if (!dfs(neigh)) {
                return false;
            }
        }

        currPath.remove(course);
        visited.add(course);
        res.add(course);

        return true;
    }
}
