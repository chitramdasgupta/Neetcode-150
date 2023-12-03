package Graphs;

// Time - O(n)
// Space - O(n)
public class NoOfConnectedComponentsUndirectedGraph {
    int[] parents;
    int[] ranks;

    public int countComponents(int n, int[][] edges) {
        parents = new int[n];
        ranks = new int[n];

        for (int i = 0; i < n; ++i) {
            parents[i] = i;
            ranks[i] = 1;
        }

        // We initialize res to n (by initially assuming each node is a separate
        // connected component
        int res = n;
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            // Each time we merge two nodes we decrease the number of connected
            // components because 2 previously assumed connected components
            // become 1 connected component.
            res -= doUnion(n1, n2);
        }

        // We return the number of actual connected components
        return res;
    }

    int doFind(int node) {
        int parent = parents[node];

        while (parent != parents[parent]) {
            parents[parent] = parents[parents[parent]];
            parent = parents[parent];
        }

        return parent;
    }

    int doUnion(int n1, int n2) {
        int parent1 = doFind(n1);
        int parent2 = doFind(n2);

        if (parent1 == parent2) {
            return 0;
        }

        if (ranks[parent1] > ranks[parent2]) {
            parents[parent2] = parent1;
            ranks[parent1] += 1;
        } else {
            parents[parent1] = parent2;
            ranks[parent2] += 1;
        }

        return 1;
    }
}
