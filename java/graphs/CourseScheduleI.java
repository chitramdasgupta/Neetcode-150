class Solution {
    private Map<Integer, List<Integer>> adjList;
    private Set<Integer> currPath;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        adjList = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            adjList.put(i, new ArrayList<Integer>());
        }
        for (int[] prereq : prerequisites) {
            adjList.get(prereq[0]).add(prereq[1]);
        }

        currPath = new HashSet<>();

        boolean res = true;
        for (int i = 0; i < numCourses; ++i) {
            if (!dfs(i)) {
                res = false;
                break;
            }
        }

        return res;
    }

    private boolean dfs(int course) {
        if (currPath.contains(course)) {
            return false;
        }

        if (adjList.get(course).isEmpty()) {
            return true;
        }

        currPath.add(course);
        for (int neigh : adjList.get(course)) {
            if (!dfs(neigh)) {
                return false;
            }
        }
        currPath.remove(course);
        adjList.get(course).clear();

        return true;
    }
}
