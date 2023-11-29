package Graphs;

import java.util.*;

// Time - O(V + E)
// Space - O(V + E)
public class CourseSchedule {
    // A graph adjacency list where the key is the node and the values are the
    // nodes we can directly go from the current code (this models the course and
    // the prerequisite courses)
    Map<Integer, ArrayList<Integer>> map;
    // The courses that we have already visited
    Set<Integer> visited;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        map = new HashMap<>();
        visited = new HashSet<>();
        for (int i = 0; i < numCourses; ++i) {
            map.put(i, new ArrayList<>());
        }
        for (int[] pre : prerequisites) {
            map.get(pre[0]).add(pre[1]);
        }

        for (int i = 0; i < numCourses; ++i) {
            if (!isValid(i)) {
                return false;
            }
        }

        return true;
    }

    boolean isValid(int course) {
        // This means there is a loop
        if (visited.contains(course)) {
            return false;
        }
        // No prerequisites hence we can clearly take the course
        if (map.get(course).size() == 0) {
            return true;
        }

        // This recursive call checks if there is a loop or deadlock
        // in the DFS structure of the current course
        visited.add(course);
        for (int pre : map.get(course)) {
            if (!isValid(pre)) {
                return false;
            }
        }
        // Since the current course can be taken without any problem
        // We can free it up from the visited set so that further courses
        // can have this course as the prerequisite (and we also cleat up
        // the corresponding prerequisites.
        visited.remove(course);
        map.get(course).clear();

        return true;
    }
}
