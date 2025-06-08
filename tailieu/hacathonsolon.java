package hackathon;

import java.math.BigInteger;
import java.util.*;

public class hacathonsolon {

    // 1. Sắp xếp
    public static int[] bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

    public static int[] mergeSort(int[] arr) {
        if (arr.length < 2) return arr;
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length)
            result[k++] = (left[i] < right[j]) ? left[i++] : right[j++];
        while (i < left.length) result[k++] = left[i++];
        while (j < right.length) result[k++] = right[j++];
        return result;
    }

    // 2. Tìm kiếm
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target) return i;
        return -1;
    }

    // 3. Phương pháp tính
    public static long factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static long power(int base, int exp) {
        if (exp == 0) return 1;
        long half = power(base, exp / 2);
        return (exp % 2 == 0) ? half * half : half * half * base;
    }

    // Xử lý số lớn
    public static BigInteger bigFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }

    public static BigInteger bigPower(BigInteger base, int exp) {
        BigInteger result = BigInteger.ONE;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result.multiply(base);
            base = base.multiply(base);
            exp >>= 1;
        }
        return result;
    }

    // 4. Quy hoạch động
    public static int fibonacciDP(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++)
            dp[i] = dp[i - 1] + dp[i - 2];
        return dp[n];
    }

    public static int knapsack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) dp[i][w] = 0;
                else if (wt[i - 1] <= w)
                    dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                else dp[i][w] = dp[i - 1][w];
            }
        }
        return dp[n][W];
    }

    // 5. Cây
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public static boolean isBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if ((min != null && root.val <= min) || (max != null && root.val >= max)) return false;
        return isBST(root.left, min, root.val) && isBST(root.right, root.val, max);
    }

    // 6. Lý thuyết đồ thị
    public static void bfs(List<List<Integer>> adj, int start) {
        boolean[] visited = new boolean[adj.size()];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
    }

    public static void dfs(List<List<Integer>> adj, int u, boolean[] visited) {
        visited[u] = true;
        System.out.print(u + " ");
        for (int v : adj.get(u)) {
            if (!visited[v]) dfs(adj, v, visited);
        }
    }

    // 7. Số học
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    // 8. Hình học
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
    }

    public static double triangleArea(double a, double b, double c) {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    // 9. Thuật toán tham lam
    public static int activitySelection(int[] start, int[] end) {
        int n = start.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, Comparator.comparingInt(i -> end[i]));
        int count = 1, lastEnd = end[idx[0]];
        for (int i = 1; i < n; i++) {
            if (start[idx[i]] >= lastEnd) {
                count++;
                lastEnd = end[idx[i]];
            }
        }
        return count;
    }

    public static int coinChangeGreedy(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        for (int i = coins.length - 1; i >= 0 && amount > 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
            }
        }
        return (amount == 0) ? count : -1;
    }

    // 10. Lý thuyết trò chơi
    public static String nimGame(int[] piles) {
        int xor = 0;
        for (int pile : piles) xor ^= pile;
        return xor == 0 ? "Second" : "First";
    }

    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
