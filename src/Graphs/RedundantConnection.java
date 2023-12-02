package Graphs;

// Time - O(n)
// Space - O(n)
public class RedundantConnection {
    int[] parents;
    int[] ranks;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;

        // Contains the farthest ancestor node which is a parent of the node at
        // the index
        parents = new int[n + 1]; // n + 1 because the nodes are numbered 1 through n
        // The size of the connected graph that the node at the index belongs to
        ranks = new int[n + 1];
        for (int i = 1; i < n; ++i) {
            // We assume each node is a parent of itself
            parents[i] = i;
            // We assume each node is in a connected graph of length 1
            ranks[i] = 1;
        }

        int[] res = new int[2];
        for (int[] edge : edges) {
            int n1 = edge[0];
            int n2 = edge[1];
            // If we cannot do a union between two nodes it means that the two
            // nodes are already connected and hence the current edge is the
            // redundant one
            if (!doUnion(n1, n2)) {
                res[0] = n1;
                res[1] = n2;
                break;
            }
        }

        return res;
    }

    int doFind(int node) {
        int parent = parents[node];
        // This condition checks if we are not already at the farthest parent
        // node of the given node
        while (parent != parents[parent]) {
            // this line performs an optimization called path compression;
            // has no effect when we are already at the farthest parent node
            parents[parent] = parents[parents[parent]];
            parent = parents[parent];
        }
        return parent;
    }

    boolean doUnion(int n1, int n2) {
        int parent1 = doFind(n1);
        int parent2 = doFind(n2);
        // If both the nodes have the same parent it means that they are already
        // connected--that is they are part of the same connected graph
        if (parent1 == parent2) {
            return false;
        }

        // We connect the smaller graph to the larger one
        if (ranks[parent1] > ranks[parent2]) {
            parents[parent2] = parent1;
            ranks[parent1] += 1;
        } else {
            parents[parent1] = parent2;
            ranks[parent2] += 1;
        }

        // We return true because we did, indeed, connect two separate graphs into one
        return true;
    }
}
