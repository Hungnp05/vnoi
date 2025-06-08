package advancedtech;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;

public class AdvancedTechLibrary {

    // --- Pathfinding ---
    public static class Edge {
        int to, weight;
        public Edge(int to, int weight) { this.to = to; this.weight = weight; }
    }

    public static int[] dijkstra(int n, List<Edge>[] graph, int start) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;
            for (Edge e : graph[u]) {
                if (dist[e.to] > d + e.weight) {
                    dist[e.to] = d + e.weight;
                    pq.add(new int[]{e.to, dist[e.to]});
                }
            }
        }
        return dist;
    }

    // --- Grid Map & Obstacle Avoidance ---
    public static boolean isValid(int[][] grid, int x, int y) {
        return x >= 0 && y >= 0 && x < grid.length && y < grid[0].length && grid[x][y] == 0;
    }

    public static int bfsGrid(int[][] grid, int sx, int sy, int ex, int ey) {
        int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], d = cur[2];
            if (x == ex && y == ey) return d;
            for (int[] di : dir) {
                int nx = x + di[0], ny = y + di[1];
                if (isValid(grid, nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, d+1});
                }
            }
        }
        return -1;
    }

    // --- PID Control ---
    public static class PID {
        double kp, ki, kd, prevError = 0, integral = 0;
        public PID(double kp, double ki, double kd) {
            this.kp = kp; this.ki = ki; this.kd = kd;
        }
        public double update(double setpoint, double measured, double dt) {
            double error = setpoint - measured;
            integral += error * dt;
            double derivative = (error - prevError) / dt;
            prevError = error;
            return kp * error + ki * integral + kd * derivative;
        }
    }

    // --- Assignment Strategy ---
    public static int[] greedyAssignment(int[][] cost) {
        int n = cost.length;
        boolean[] assigned = new boolean[n];
        int[] result = new int[n];
        Arrays.fill(result, -1);
        for (int i = 0; i < n; i++) {
            int min = Integer.MAX_VALUE, best = -1;
            for (int j = 0; j < n; j++) {
                if (!assigned[j] && cost[i][j] < min) {
                    min = cost[i][j];
                    best = j;
                }
            }
            result[i] = best;
            assigned[best] = true;
        }
        return result;
    }

    // --- Landmark Detection (Stub) ---
    public static boolean detectLandmark(double[] sensorData) {
        for (double d : sensorData)
            if (d > 0.9) return true; // simple threshold detection
        return false;
    }

    // --- Load Balancing ---
    public static int[] roundRobin(int tasks, int servers) {
        int[] assign = new int[tasks];
        for (int i = 0; i < tasks; i++) assign[i] = i % servers;
        return assign;
    }

    // --- QR Recognition ---
    public static String decodeQRCode(BufferedImage img) throws Exception {
        LuminanceSource source = new BufferedImageLuminanceSource(img);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result = new MultiFormatReader().decode(bitmap);
        return result.getText();
    }

    // --- Evolutionary Algorithm - Genetic Algorithm ---
    public static int[] geneticOptimize(int geneLength, int popSize, int generations) {
        Random rand = new Random();
        int[][] pop = new int[popSize][geneLength];
        for (int i = 0; i < popSize; i++)
            for (int j = 0; j < geneLength; j++)
                pop[i][j] = rand.nextInt(2);

        for (int g = 0; g < generations; g++) {
            Arrays.sort(pop, Comparator.comparingInt(AdvancedTechLibrary::fitness).reversed());
            for (int i = popSize / 2; i < popSize; i++) {
                int[] p1 = pop[rand.nextInt(popSize / 2)];
                int[] p2 = pop[rand.nextInt(popSize / 2)];
                int cross = rand.nextInt(geneLength);
                for (int j = 0; j < geneLength; j++)
                    pop[i][j] = (j < cross ? p1[j] : p2[j]) ^ rand.nextInt(2); // crossover + mutation
            }
        }
        return pop[0];
    }

    public static int fitness(int[] gene) {
        int score = 0;
        for (int g : gene) score += g;
        return score; // maximize number of 1s
    }

    // --- Simulated Annealing ---
    public static int simulatedAnnealing(int[] state) {
        double T = 1000;
        Random rand = ThreadLocalRandom.current();
        int curr = fitness(state);
        while (T > 1) {
            int[] newState = state.clone();
            int i = rand.nextInt(state.length);
            newState[i] ^= 1; // flip a bit
            int newFit = fitness(newState);
            if (newFit > curr || Math.exp((newFit - curr) / T) > rand.nextDouble()) {
                state = newState; curr = newFit;
            }
            T *= 0.95;
        }
        return curr;
    }


    public class Demo {
   public static void main(String[] args) {
       int x = 5;
       // Converting int to string using Integer.toString(int)
       String xText = Integer.toString(x);
       System.out.println("convert int to String, Java: " + xText);
       
       // Converting int to string using concatenation
       String xTextConcat = x + "";
       System.out.println("convert int to String, Java (concatenation): " + xTextConcat);
       
       // Converting Integer to string using Integer.toString(Integer)
       Integer y = 7;
       String yText = Integer.toString(y);
       System.out.println("convert Integer to String: " + yText);
   }
}
} 
