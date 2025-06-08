import java.util.Scanner;
public class bottomup {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long a = sc.nextLong();

        switch (a) {
            case 1:
                System.out.println("Can ly!");
                break;

            case 2:
                System.out.println("До дна!");
                break;
            case 3:
                System.out.println("乾杯 !");
                break;
            case 4:
                System.out.println("干杯 !");
                break;
            case 5:
                System.out.println("원샷!");
                break;
            default:
                System.out.println("Khong hop le!");
        }
    }
}   
