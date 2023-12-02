package Graphs;

import java.util.*;

// Time - O(V + E)
// Space - O(V + E)
// This problem actually wants us to find the topological sort order of the given graph
public class CourseScheduleII {
    Map<Integer, List<Integer>> map;
    // This set will hold the nodes that we have already visited along with its
    // prerequisites
    Set<Integer> visited;
    // This set will hold the nodes we are visiting in the current path
    Set<Integer> visiting;
    // Our result
    List<Integer> res;
    // NOTE: We have a visited set and a res list separately because the visited
    // set will simply store the nodes we have already visited (without maintaining
    // the order--the order which we need to find in this problem), but the list
    // will store the courses in the order that we can finish them.

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        map = new HashMap<>();
        for (int i = 0; i < numCourses; ++i) {
            map.put(i, new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            map.get(pre[0]).add(pre[1]);
        }

        visited = new HashSet<>();
        visiting = new HashSet<>();
        res = new ArrayList<>();

        for (int i = 0; i < numCourses; ++i) {
            if (!dfs(i)) {
                return new int[]{};
            }
        }

        // We have to do this conversion from ArrayList to array because of the
        // return type of the given method
        int[] ans = new int[numCourses];
        for (int i = 0; i < ans.length; ++i) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    boolean dfs(int course) {
        // We have already visited this course twice in the current path--there
        // is a loop
        if (visiting.contains(course)) {
            return false;
        }
        // We have already covered this course and the prerequisites thereof
        if (visited.contains(course)) {
            return true;
        }

        visiting.add(course);
        for (int c : map.get(course)) {
            if (!dfs(c)) {
                return false;
            }
        }
        visiting.remove(course);
        visited.add(course);
        res.add(course);
        return true;
    }
}
