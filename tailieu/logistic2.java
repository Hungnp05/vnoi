package logistics;

import java.util.*;

public class logistic2 {

    // 1. SORTING
    // Problem 1: Sort packages by weight
    public static void sortPackagesByWeight(int[] weights) {
        Arrays.sort(weights);
    }

    // Problem 2: Sort deliveries by priority and distance
    public static void sortDeliveriesByPriority(int[][] deliveries) {
        Arrays.sort(deliveries, (a, b) -> a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]);
    }

    // 2. SEARCHING
    // Problem 1: Find the first delivery with a specific destination
    public static int findDeliveryByDestination(String[] destinations, String target) {
        for (int i = 0; i < destinations.length; i++) {
            if (destinations[i].equals(target)) return i;
        }
        return -1;
    }

    // Problem 2: Binary search delivery times
    public static int binarySearchDeliveryTime(int[] times, int target) {
        int left = 0, right = times.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (times[mid] == target) return mid;
            if (times[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // 3. MATHEMATICAL METHODS
    // Problem 1: Calculate delivery cost with tax and discount
    public static double calculateDeliveryCost(double base, double taxRate, double discount) {
        return base + base * taxRate - discount;
    }

    // Problem 2: Estimate fuel needed for a round trip
    public static double estimateFuel(double distance, double efficiency) {
        return 2 * distance / efficiency;
    }

    // 4. DYNAMIC PROGRAMMING
    // Problem 1: Minimum cost path in grid
    public static int minPathCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) dp[i][0] = dp[i - 1][0] + grid[i][0];
        for (int j = 1; j < n; j++) dp[0][j] = dp[0][j - 1] + grid[0][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m - 1][n - 1];
    }

    // Problem 2: Maximize load distribution
    public static int knapsack(int[] weights, int[] values, int W) {
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][W];
    }

    // 5. TREES
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // Problem 1: Sum all values in a binary tree
    public static int treeSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + treeSum(root.left) + treeSum(root.right);
    }

    // Problem 2: Find max depth of a tree
    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // 6. GRAPH THEORY
    // Problem 1: Shortest path (Dijkstra)
    public static int[] dijkstra(int[][] graph, int src) {
        int n = graph.length;
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;
            for (int v = 0; v < n; v++) {
                if (graph[u][v] > 0 && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    // Problem 2: Detect cycle in a graph
    public static boolean hasCycle(int V, List<List<Integer>> adj) {
        boolean[] visited = new boolean[V];
        boolean[] recStack = new boolean[V];
        for (int i = 0; i < V; i++)
            if (cycleUtil(i, visited, recStack, adj)) return true;
        return false;
    }

    private static boolean cycleUtil(int v, boolean[] visited, boolean[] recStack, List<List<Integer>> adj) {
        if (recStack[v]) return true;
        if (visited[v]) return false;
        visited[v] = recStack[v] = true;
        for (int neigh : adj.get(v))
            if (cycleUtil(neigh, visited, recStack, adj)) return true;
        recStack[v] = false;
        return false;
    }

    // 7. NUMBER THEORY
    // Problem 1: GCD
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Problem 2: Prime check
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    // 8. GEOMETRY
    // Problem 1: Distance between 2 points
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    // Problem 2: Check point in rectangle
    public static boolean isInsideRectangle(double x, double y, double x1, double y1, double x2, double y2) {
        return x >= x1 && x <= x2 && y >= y1 && y <= y2;
    }

    // 9. GREEDY ALGORITHM
    // Problem 1: Min number of trucks needed
    public static int minTrucks(int[] packages, int limit) {
        Arrays.sort(packages);
        int i = 0, j = packages.length - 1, trucks = 0;
        while (i <= j) {
            if (packages[i] + packages[j] <= limit) i++;
            j--;
            trucks++;
        }
        return trucks;
    }

    // Problem 2: Activity selection (max non-overlapping deliveries)
    public static int maxDeliveries(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int count = 0, lastEnd = -1;
        for (int[] interval : intervals) {
            if (interval[0] >= lastEnd) {
                count++;
                lastEnd = interval[1];
            }
        }
        return count;
    }

    // 10. GAME THEORY
    // Problem 1: Nim Game (basic)
    public static boolean nimGame(int n) {
        return n % 4 != 0;
    }

    // Problem 2: Logistics game - 2 players pick optimal routes
    public static String logisticsGameWinner(int[] points) {
        int sum = 0;
        for (int i = points.length - 1; i >= 0; i--) {
            sum = Math.max(points[i] - sum, sum);
        }
        return sum > 0 ? "First" : "Second";
    }
} 
