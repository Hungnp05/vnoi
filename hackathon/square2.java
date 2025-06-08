import java.util.Scanner;

public class square2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        double result = 0;
        for (int i = 0; i < n; i++){
            int a = sc.nextInt();
            double canbac2 = Math.sqrt(a);
            double temp = canbac2 * 10;
            temp %= 10;
            if(temp == 0){
                result = a * m;
            
        }
        else{
            System.out.println("0");
        }
    }
                    
    }
}
