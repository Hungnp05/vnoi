import java.io.*;
import java.util.*;

public class template2 {

    // ===================== MAIN =====================
    public static void main(String[] args) throws Exception {
        FastReader sc = new FastReader();
        PrintWriter out = new PrintWriter(System.out);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = sc.nextInt();

        long startTime = System.currentTimeMillis();

        Arrays.sort(arr);
        out.println("Sorted: " + Arrays.toString(arr));

        long endTime = System.currentTimeMillis();
        out.println("Time: " + (endTime - startTime) + "ms");

        out.flush();
    }

    // ===================== FAST INPUT =====================
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;
        public FastReader() { br = new BufferedReader(new InputStreamReader(System.in)); }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }
        int nextInt() { return Integer.parseInt(next()); }
        long nextLong() { return Long.parseLong(next()); }
        double nextDouble() { return Double.parseDouble(next()); }
        String nextLine() {
            String str = "";
            try { str = br.readLine(); }
            catch (IOException e) { e.printStackTrace(); }
            return str;
        }
    }

    // ===================== UTILITY FUNCTIONS =====================
    static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++)
            if (n % i == 0) return false;
        return true;
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static ArrayList<Integer> getDivisors(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                res.add(i);
                if (i != n / i) res.add(n / i);
            }
        }
        return res;
    }

    // ===================== DFS RECURSIVE =====================
    static void dfsRec(int node, boolean[] visited, List<List<Integer>> adj) {
        visited[node] = true;
        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsRec(neighbor, visited, adj);
            }
        }
    }

    // ===================== BFS =====================
    static void bfs(int start, List<List<Integer>> adj, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int neighbor : adj.get(cur)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.offer(neighbor);
                }
            }
        }
    }

    // ===================== DIJKSTRA =====================
    static int[] dijkstra(int src, List<List<int[]>> adj, int n) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;
            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    pq.add(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    // ===================== UNION-FIND =====================
    static class UnionFind {
        int[] parent, rank;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int a, int b) {
            int ra = find(a), rb = find(b);
            if (ra == rb) return;
            if (rank[ra] < rank[rb]) parent[ra] = rb;
            else if (rank[ra] > rank[rb]) parent[rb] = ra;
            else {
                parent[rb] = ra;
                rank[ra]++;
            }
        }
    }

    // ===================== DYNAMIC PROGRAMMING =====================
    static int fibonacciDP(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0; dp[1] = 1;
        for (int i = 2; i <= n; i++) dp[i] = dp[i-1] + dp[i-2];
        return dp[n];
    }

    // ===================== CUSTOM CLASS =====================
    static class Pair implements Comparable<Pair> {
        int a, b;
        Pair(int a, int b) { this.a = a; this.b = b; }

        public int compareTo(Pair o) {
            return Integer.compare(this.a, o.a); // sort theo a tăng
        }
    }
}
