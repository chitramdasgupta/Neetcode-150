class Solution {
    private int numNodes;
    private int[] ranks;
    private int[] parents;

    public int[] findRedundantConnection(int[][] edges) {
        numNodes = edges.length;

        ranks = new int[numNodes + 1];
        parents = new int[numNodes + 1];
        for (int i = 1; i <= numNodes; ++i) {
            ranks[i] = 1;
            parents[i] = i;
        }

        int[] res = new int[2];
        for (int[] edge : edges) {
            if (!unionFind(edge[0], edge[1])) {
                res = new int[] {edge[0], edge[1]};
                break;
            }
        }

        return res;
    }

    private boolean unionFind(int first, int second) {
        int p1 = find(first);
        int p2 = find(second);

        if (p1 == p2) {
            return false;
        }

        if (ranks[p1] < ranks[p2]) {
            ranks[p2] += ranks[p1];
            parents[p1] = p2;
        } else {
            ranks[p1] += ranks[p2];
            parents[p2] = p1;
        }

        return true;
    }

    private int find(int node) {
        int parent = parents[node];

        while (parent != parents[parent]) {
            parents[parent] = parents[parents[parent]];
            parent = parents[parent];
        }

        return parent;
    }
}
