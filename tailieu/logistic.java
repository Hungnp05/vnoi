package hackathon;

import java.util.*;
import java.math.BigInteger;

public class logistic {

    // 1. Sorting
    public static void sortByDeliveryTime(int[][] deliveries) {
        Arrays.sort(deliveries, Comparator.comparingInt(a -> a[1]));
    }

    public static void sortWarehousesByStock(int[] stocks) {
        Arrays.sort(stocks);
    }

    // 2. Searching
    public static int findWarehouseWithItem(int[] warehouseIds, int target) {
        for (int i = 0; i < warehouseIds.length; i++) {
            if (warehouseIds[i] == target) return i;
        }
        return -1;
    }

    public static boolean binarySearchOrder(int[] orders, int target) {
        int l = 0, r = orders.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (orders[mid] == target) return true;
            if (orders[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return false;
    }

    // 3. Mathematical Methods
    public static int calculateTruckCapacity(int[] boxes) {
        int total = 0;
        for (int b : boxes) total += b;
        return total;
    }

    public static double calculateAverageDeliveryTime(int[] times) {
        double sum = 0;
        for (int t : times) sum += t;
        return sum / times.length;
    }

    // 4. Dynamic Programming
    public static int minDeliveryCost(int[] costs, int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= costs.length; j++) {
                if (i - j >= 0) dp[i] = Math.min(dp[i], dp[i-j] + costs[j-1]);
            }
        }
        return dp[n];
    }

    public static int maxPackageValue(int[] weights, int[] values, int capacity) {
        int[][] dp = new int[weights.length + 1][capacity + 1];
        for (int i = 1; i <= weights.length; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i-1] <= w)
                    dp[i][w] = Math.max(dp[i-1][w], dp[i-1][w - weights[i-1]] + values[i-1]);
                else
                    dp[i][w] = dp[i-1][w];
            }
        }
        return dp[weights.length][capacity];
    }

    // 5. Tree
    static class TreeNode {
        int val;
        List<TreeNode> children = new ArrayList<>();
        TreeNode(int val) { this.val = val; }
    }

    public static int countWarehouses(TreeNode root) {
        if (root == null) return 0;
        int count = 1;
        for (TreeNode child : root.children)
            count += countWarehouses(child);
        return count;
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int max = 0;
        for (TreeNode child : root.children)
            max = Math.max(max, maxDepth(child));
        return max + 1;
    }

    // 6. Graph Theory
    public static boolean isReachable(int[][] graph, int start, int end) {
        boolean[] visited = new boolean[graph.length];
        return dfs(graph, start, end, visited);
    }

    private static boolean dfs(int[][] graph, int current, int end, boolean[] visited) {
        if (current == end) return true;
        visited[current] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[current][i] == 1 && !visited[i] && dfs(graph, i, end, visited)) return true;
        }
        return false;
    }

    public static int shortestDeliveryPath(int[][] graph, int src, int dest) {
        int[] dist = new int[graph.length];
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < graph.length; i++) {
            int u = -1;
            for (int j = 0; j < graph.length; j++) {
                if (!visited[j] && (u == -1 || dist[j] < dist[u])) u = j;
            }
            if (dist[u] == Integer.MAX_VALUE) break;
            visited[u] = true;
            for (int v = 0; v < graph.length; v++) {
                if (graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
            }
        }
        return dist[dest];
    }

    // 7. Number Theory
    public static boolean isDeliveryDayPrime(int day) {
        if (day <= 1) return false;
        for (int i = 2; i * i <= day; i++)
            if (day % i == 0) return false;
        return true;
    }

    public static BigInteger factorialDelivery(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    // 8. Geometry
    public static double distanceBetweenWarehouses(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public static boolean isWithinDeliveryZone(double x, double y, double cx, double cy, double r) {
        return distanceBetweenWarehouses(x, y, cx, cy) <= r;
    }

    // 9. Greedy Algorithms
    public static int greedyPackageSelection(int[] weights, int capacity) {
        Arrays.sort(weights);
        int total = 0, count = 0;
        for (int w : weights) {
            if (total + w > capacity) break;
            total += w; count++;
        }
        return count;
    }

    public static int minimumTrucks(int[] packages, int truckCapacity) {
        Arrays.sort(packages);
        int i = 0, j = packages.length - 1, trucks = 0;
        while (i <= j) {
            if (packages[i] + packages[j] <= truckCapacity) i++;
            j--; trucks++;
        }
        return trucks;
    }

    // 10. Game Theory
    public static String deliveryGameWinner(int n) {
        return (n % 2 == 0) ? "Alice" : "Bob";
    }

    public static boolean canFirstDriverWin(int[] deliveries) {
        int xor = 0;
        for (int d : deliveries) xor ^= d;
        return xor != 0;
    }
}
