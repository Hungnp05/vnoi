package algolib;

import java.util.*;
import java.awt.image.BufferedImage;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;

public class AlgorithmLibrary {

    // 1. Sorting - QuickSort
    public static void quickSort(int[] a, int l, int r) {
        if (l >= r) return;
        int pivot = a[(l + r) / 2], i = l, j = r;
        while (i <= j) {
            while (a[i] < pivot) i++;
            while (a[j] > pivot) j--;
            if (i <= j) {
                int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
                i++; j--;
            }
        }
        quickSort(a, l, j);
        quickSort(a, i, r);
    }

    // 2. Searching - Binary Search
    public static int binarySearch(int[] a, int x) {
        int l = 0, r = a.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (a[mid] == x) return mid;
            else if (a[mid] < x) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }

    // 3. Math - Combination
    public static long factorial(int n) {
        long res = 1;
        for (int i = 2; i <= n; i++) res *= i;
        return res;
    }

    public static long combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    // 4. Dynamic Programming - LIS
    public static int lis(int[] a) {
        int n = a.length, res = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++)
            for (int j = 0; j < i; j++)
                if (a[i] > a[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
        for (int x : dp) res = Math.max(res, x);
        return res;
    }

    // 5. Tree - In-Order Traversal
    public static class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int x) { val = x; }
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    // 6. Graph - BFS
    public static void bfs(int n, List<Integer>[] adj, int start) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            System.out.print(u + " ");
            for (int v : adj[u])
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
        }
    }

    // 7. Number Theory - Sieve
    public static List<Integer> sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        for (int i = 2; i * i <= n; i++)
            if (isPrime[i])
                for (int j = i * i; j <= n; j += i)
                    isPrime[j] = false;
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++)
            if (isPrime[i]) primes.add(i);
        return primes;
    }

    // 8. Geometry - Triangle Area
    public static double triangleArea(int x1, int y1, int x2, int y2, int x3, int y3) {
        return Math.abs((x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2)) / 2.0);
    }

    // 9. Greedy - Activity Selection
    public static class Activity {
        public int start, end;
        public Activity(int s, int e) { start = s; end = e; }
    }

    public static int maxActivities(Activity[] acts) {
        Arrays.sort(acts, Comparator.comparingInt(a -> a.end));
        int count = 1, lastEnd = acts[0].end;
        for (int i = 1; i < acts.length; i++)
            if (acts[i].start >= lastEnd) {
                count++;
                lastEnd = acts[i].end;
            }
        return count;
    }

    // 10. Game Theory - Nim
    public static String nimGameWinner(int[] piles) {
        int xor = 0;
        for (int pile : piles) xor ^= pile;
        return xor != 0 ? "First player wins" : "Second player wins";
    }

    // Extra: QR Recognition (ZXing)
    public static String decodeQRCode(BufferedImage img) throws Exception {
        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }
} 
