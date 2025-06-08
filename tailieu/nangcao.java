import java.util.*;

public class nangcao {

    // Các thư viện bổ sung cho bài toán nâng cao
    static class Node implements Comparable<Node> {
        int x, y, cost;
        Node(int x, int y, int cost) {
            this.x = x; this.y = y; this.cost = cost;
        }
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    // 1. Pathfinding - Dijkstra
    public static int dijkstra(int[][] graph, int src, int dest) {
        int V = graph.length;
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            int u = -1;
            for (int j = 0; j < V; j++)
                if (!visited[j] && (u == -1 || dist[j] < dist[u]))
                    u = j;
            if (dist[u] == Integer.MAX_VALUE) break;
            visited[u] = true;
            for (int v = 0; v < V; v++)
                if (graph[u][v] != 0 && dist[u] + graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
        return dist[dest];
    }

    // 2. Pathfinding - A* Algorithm
    public static int aStar(int[][] grid, int[] start, int[] goal) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start[0], start[1], 0));

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.x == goal[0] && cur.y == goal[1]) return cur.cost;
            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;
            for (int[] d : dirs) {
                int nx = cur.x + d[0], ny = cur.y + d[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny] && grid[nx][ny] == 0) {
                    int h = Math.abs(nx - goal[0]) + Math.abs(ny - goal[1]);
                    pq.add(new Node(nx, ny, cur.cost + 1 + h));
                }
            }
        }
        return -1;
    }

    // 3. Grid Map - Obstacle Avoidance
    public static boolean isReachable(int[][] grid, int x, int y, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == 1 || visited[x][y]) return false;
        if (x == m-1 && y == n-1) return true;
        visited[x][y] = true;
        return isReachable(grid, x+1, y, visited) || isReachable(grid, x, y+1, visited);
    }

    // 4. PWM Simulation (duty cycle simulation)
    public static int simulatePWM(int target, int cycles) {
        int output = 0;
        for (int i = 0; i < cycles; i++) {
            output += (i % 100 < target) ? 1 : 0;
        }
        return output;
    }

    // 5. PID Control - basic simulation
    public static double pidController(double setPoint, double current, double kp, double ki, double kd, double[] errSum, double lastErr) {
        double error = setPoint - current;
        errSum[0] += error;
        double dErr = error - lastErr;
        return kp * error + ki * errSum[0] + kd * dErr;
    }

    // 6. Assignment Strategy (simple greedy matching)
    public static int assignTasks(int[] workers, int[] tasks) {
        Arrays.sort(workers);
        Arrays.sort(tasks);
        int i = 0, j = 0, count = 0;
        while (i < workers.length && j < tasks.length) {
            if (workers[i] >= tasks[j]) {
                count++;
                i++; j++;
            } else i++;
        }
        return count;
    }

    // 7. Line-following Simulation
    public static String followLine(char[][] grid) {
        int x = 0, y = 0, n = grid.length, m = grid[0].length;
        StringBuilder path = new StringBuilder();
        while (x < n && y < m && grid[x][y] != 'E') {
            path.append(grid[x][y]);
            if (grid[x][y] == 'R') y++;
            else if (grid[x][y] == 'D') x++;
            else break;
        }
        return path.toString();
    }

    // 8. Load Balancing (simple diff minimization)
    public static int balanceLoads(int[] servers) {
        int total = Arrays.stream(servers).sum();
        int avg = total / servers.length;
        int moves = 0;
        for (int s : servers) moves += Math.abs(s - avg);
        return moves / 2;
    }

    // 9. Genetic Algorithm (simplified string matching)
    public static String mutate(String s, Random rand) {
        char[] c = s.toCharArray();
        int idx = rand.nextInt(c.length);
        c[idx] = (char)('a' + rand.nextInt(26));
        return new String(c);
    }

    public static String geneticSearch(String target) {
        Random rand = new Random();
        String candidate = "a".repeat(target.length());
        while (!candidate.equals(target)) {
            String next = mutate(candidate, rand);
            if (score(next, target) > score(candidate, target)) candidate = next;
        }
        return candidate;
    }

    private static int score(String a, String b) {
        int s = 0;
        for (int i = 0; i < a.length(); i++) if (a.charAt(i) == b.charAt(i)) s++;
        return s;
    }

    // 10. Simulated Annealing (minimize f(x) = x^2)
    public static int simulatedAnnealing() {
        Random rand = new Random();
        int x = rand.nextInt(100) - 50;
        double T = 100.0, cooling = 0.99;
        while (T > 0.1) {
            int newX = x + rand.nextInt(11) - 5;
            int dE = newX * newX - x * x;
            if (dE < 0 || Math.exp(-dE / T) > rand.nextDouble()) x = newX;
            T *= cooling;
        }
        return x;
    }
}
