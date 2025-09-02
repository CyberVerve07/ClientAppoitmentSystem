import java.io.InputStream;
import java.util.Scanner;

public class Condition {
    public static void main(String[] args) {

        Scanner input =new Scanner(System.in);
        System.out.println("enter your age");
        int age= input.nextInt();
        if (age>=18) {
            System.out.println("You are eligible for vote");
        } else  {
                System.out.println("You are not elible right now");


    }}}

