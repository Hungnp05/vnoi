package hackathon;

import java.util.Arrays;

public class packa2 {
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
}
