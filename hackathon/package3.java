import java.util.Scanner;

public class package3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //so luong nguoi
        int n = sc.nextInt();
        //suc chua
        int k = sc.nextInt();

        //tang
        //int sotang = sc.nextInt();
        int[] tang = new int[n];
        for (int i = 0; i < n; i++){
            tang[i] = sc.nextInt();
        }
        bubbleSort(tang);

        
        for (int i = 0; i <= tang.length; i++){
            int cc = tang.length - 1;
            if(n > 0 || n > k){
                int s = k * (tang[cc] - 1) + s;
                n -= k;
            }
        }
        
    }

    public static void bubbleSort(int[] tang) {
        int n = tang.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (tang[j] > tang[j + 1]) {
                    // Hoán đổi
                    int temp = tang[j];
                    tang[j] = tang[j + 1];
                    tang[j + 1] = temp;
                }
    }

}

