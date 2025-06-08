package hackathon;

import java.util.Scanner;

public class packa {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = 0, b = 0;
        for (int i = n / 2; i >= 1; i--) {
            if (gcd(i, n - i) == 1) {
                a = i;
                b = n - i;
                break;
            }
        }
        System.out.println(a + " " + b);
    }
}
