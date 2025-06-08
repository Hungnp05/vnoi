
import java.util.Scanner;

public class square {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        double result = 0;
        
        // if(1 <= m && m <= 3){
        //     for (int i = 0; i <= n; i++){
        //         double a = sc.nextInt();
        //         double canbac2 = Math.sqrt(a);
        //         if((canbac2 * 10) % 10 == 0){
        //             result = Math.pow(a, m);
        //         }
        //     }
            
        // }System.out.println(result);
        
        if(1 <= m && m <= 3){
            for (int i = 0; i <= n; i++){
                double a = sc.nextInt();
                double canbac2 = Math.sqrt(a);
                if((canbac2 * 10) % 10 == 0){
                    result = Math.pow(a, m);               
                    System.out.print(result);
                }
            }
            
        }
        

    }
}
