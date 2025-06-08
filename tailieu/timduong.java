package advancedtech;

import java.util.*;

public class timduong {

    static class Edge {
        int to, weight;
        public Edge(int to, int weight) { this.to = to; this.weight = weight; }
    }

    // 1. Dijkstra cơ bản
    public static int[] basicDijkstra(int n, List<Edge>[] graph, int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;
            for (Edge e : graph[u]) {
                if (dist[e.to] > dist[u] + e.weight) {
                    dist[e.to] = dist[u] + e.weight;
                    pq.add(new int[]{e.to, dist[e.to]});
                }
            }
        }
        return dist;
    }

    // 2. Multi-source Dijkstra
    public static int[] multiSourceDijkstra(int n, List<Edge>[] graph, List<Integer> sources) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int src : sources) {
            dist[src] = 0;
            pq.add(new int[]{src, 0});
        }
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;
            for (Edge e : graph[u]) {
                if (dist[e.to] > dist[u] + e.weight) {
                    dist[e.to] = dist[u] + e.weight;
                    pq.add(new int[]{e.to, dist[e.to]});
                }
            }
        }
        return dist;
    }

    // 3. Dijkstra trên lưới (grid map)
    public static int dijkstraGrid(int[][] grid, int sx, int sy, int ex, int ey) {
        int m = grid.length, n = grid[0].length;
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[sx][sy] = grid[sx][sy];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.add(new int[]{sx, sy, dist[sx][sy]});
        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0], y = cur[1], d = cur[2];
            if (x == ex && y == ey) return d;
            for (int[] di : dir) {
                int nx = x + di[0], ny = y + di[1];
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && dist[nx][ny] > d + grid[nx][ny]) {
                    dist[nx][ny] = d + grid[nx][ny];
                    pq.add(new int[]{nx, ny, dist[nx][ny]});
                }
            }
        }
        return -1;
    }

    // 4. Dijkstra với trạng thái (state-based)
    static class State {
        int node, cost, fuel;
        public State(int node, int cost, int fuel) {
            this.node = node; this.cost = cost; this.fuel = fuel;
        }
    }

    public static int dijkstraWithState(int n, List<Edge>[] graph, int start, int fuelStart) {
        int[][] dist = new int[n][fuelStart+1];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[start][fuelStart] = 0;
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(s -> s.cost));
        pq.add(new State(start, 0, fuelStart));

        while (!pq.isEmpty()) {
            State s = pq.poll();
            if (dist[s.node][s.fuel] < s.cost) continue;
            for (Edge e : graph[s.node]) {
                if (s.fuel >= e.weight) {
                    int nextFuel = s.fuel - e.weight;
                    if (dist[e.to][nextFuel] > s.cost + e.weight) {
                        dist[e.to][nextFuel] = s.cost + e.weight;
                        pq.add(new State(e.to, dist[e.to][nextFuel], nextFuel));
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= fuelStart; i++) min = Math.min(min, dist[n-1][i]);
        return min;
    }

    // 5. 0-1 Dijkstra
    public static int[] zeroOneDijkstra(List<Edge>[] graph, int n, int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addFirst(start);

        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            for (Edge e : graph[u]) {
                int w = e.weight;
                if (dist[e.to] > dist[u] + w) {
                    dist[e.to] = dist[u] + w;
                    if (w == 0) dq.addFirst(e.to);
                    else dq.addLast(e.to);
                }
            }
        }
        return dist;
    }
}  
