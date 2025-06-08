

import java.util.*;

public class hackathon2 {

    // 1. Sắp xếp: Sắp xếp nổi bọt
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Thêm: Sắp xếp trộn
    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; ++i) L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[m + 1 + j];

        int i = 0, j = 0, k = l;
        while (i < n1 && j < n2) {
            arr[k++] = (L[i] <= R[j]) ? L[i++] : R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // 2. Tìm kiếm: Tìm kiếm nhị phân
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Thêm: Tìm kiếm tuyến tính
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    // 3. Phương pháp tính: Tính giai thừa
    public static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    // Thêm: Tính tổ hợp C(n, k)
    public static long combination(int n, int k) {
        if (k > n) return 0;
        long res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (n - i + 1) / i;
        }
        return res;
    }

    // 4. Quy hoạch động: Dãy con tăng dài nhất
    public static int longestIncreasingSubsequence(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    // Thêm: Balo 0/1
    public static int knapsack(int W, int[] wt, int[] val, int n) {
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) dp[i][w] = 0;
                else if (wt[i - 1] <= w) dp[i][w] = Math.max(val[i - 1] + dp[i - 1][w - wt[i - 1]], dp[i - 1][w]);
                else dp[i][w] = dp[i - 1][w];
            }
        }
        return dp[n][W];
    }

    // 5. Cây: Duyệt cây nhị phân theo thứ tự giữa
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static void inorderTraversal(TreeNode root) {
        if (root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }

    // Thêm: Tính chiều cao cây nhị phân
    public static int treeHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
    }

    // 6. Lý thuyết đồ thị: BFS
    public static void bfs(int V, List<List<Integer>> adj, int start) {
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[start] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            System.out.print(u + " ");
            for (int v : adj.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
    }

    // Thêm: DFS
    public static void dfs(int V, List<List<Integer>> adj, int start, boolean[] visited) {
        visited[start] = true;
        System.out.print(start + " ");
        for (int v : adj.get(start)) {
            if (!visited[v]) dfs(V, adj, v, visited);
        }
    }

    // 7. Số học: Sàng nguyên tố Eratosthenes
    public static List<Integer> sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes.add(i);
        }
        return primes;
    }

    // Thêm: Ước chung lớn nhất
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 8. Hình học: Diện tích tam giác theo công thức Heron
    public static double triangleArea(double a, double b, double c) {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    // Thêm: Khoảng cách giữa hai điểm
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    // 9. Thuật toán tham lam: Chọn hoạt động tối đa
    public static int activitySelection(int[] start, int[] end) {
        int n = start.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, Comparator.comparingInt(i -> end[i]));
        int count = 1, last = idx[0];
        for (int i = 1; i < n; i++) {
            if (start[idx[i]] >= end[last]) {
                count++;
                last = idx[i];
            }
        }
        return count;
    }

    // Thêm: Đổi tiền (tham lam)
    public static int coinChangeGreedy(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        for (int i = coins.length - 1; i >= 0 && amount > 0; i--) {
            while (amount >= coins[i]) {
                amount -= coins[i];
                count++;
            }
        }
        return amount == 0 ? count : -1;
    }

    // 10. Lý thuyết trò chơi: Trò chơi Nim
    public static String nimGame(int[] piles) {
        int xor = 0;
        for (int pile : piles) xor ^= pile;
        return xor == 0 ? "Second" : "First";
    }

    // Thêm: Game Grundy Numbers (ví dụ: trò chơi chia cắt)
    public static int grundy(int n) {
        if (n == 0) return 0;
        Set<Integer> grundies = new HashSet<>();
        for (int i = 1; i <= n / 2; i++) {
            grundies.add(grundy(i) ^ grundy(n - i));
        }
        int g = 0;
        while (grundies.contains(g)) g++;
        return g;
    }
} 
