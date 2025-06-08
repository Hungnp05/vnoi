import java.util.*;
import java.io.*;

public class template {
    // ===================== MAIN ======================
    public static void main(String[] args) throws Exception {
        FastReader sc = new FastReader();

        // Input mẫu
        int n = sc.nextInt();
        String s = sc.nextLine();

        // Mảng & ArrayList
        int[] arr = new int[n];
        ArrayList<Integer> list = new ArrayList<>();

        // HashMap & Set
        HashMap<String, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();

        // PriorityQueue (Min & Max Heap)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Đo thời gian
        long startTime = System.currentTimeMillis();

        // TODO: Viết thuật toán ở đây

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
    }

    // ===================== FUNCTION EXAMPLES ======================
    static int sum(int a, int b) {
        return a + b;
    }

    static int[] sortArray(int[] arr) {
        Arrays.sort(arr);
        return arr;
    }

    static void sortListDesc(ArrayList<Integer> list) {
        list.sort((a, b) -> b - a);
    }

    // DFS sử dụng Stack
    static void dfs(int start, boolean[] visited, List<List<Integer>> graph) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                for (int neighbor : graph.get(node)) {
                    stack.push(neighbor);
                }
            }
        }
    }

    // BFS sử dụng Queue
    static void bfs(int start, boolean[] visited, List<List<Integer>> graph) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    // ===================== CUSTOM CLASS ======================
    static class Point {
        int x, y;
        Point(int x, int y) { this.x = x; this.y = y; }
    }

    // Class với Comparable để sort custom
    static class Pair implements Comparable<Pair> {
        int a, b;
        Pair(int a, int b) { this.a = a; this.b = b; }

        public int compareTo(Pair other) {
            return Integer.compare(this.a, other.a); // Sort theo a tăng dần
        }
    }

    // ===================== FAST INPUT ======================
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
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
}
