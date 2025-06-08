import java.util.*;
public class bottomup2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long a = sc.nextLong();

        if(0 <= a && a <= Math.pow(2, 32)){ 
            if(a == 1){
            System.out.println("Can ly!");
            }
            else if(a == 2){
                System.out.println("До дна!");
            }

            else if(a == 3){
                System.out.println("乾杯!");     
            }

            else if(a == 4){
                System.out.println("干杯!");
            }

            else if(a == 5){
                System.out.println("원샷!");
            }
            else{
                System.out.println("Invalid!");
            }
        }
        
    }
}
