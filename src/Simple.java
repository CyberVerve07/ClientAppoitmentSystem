import java.util.Scanner;

class Simple {
     public static void main(String[] args) {

         Scanner input =new Scanner(System.in);
         System.out.println("Code for calcuate simple intrest ");

         System.out.println("Please enter your principal amount ");
         int principal= input.nextInt();
         System.out.println("Now tell me your rate of intrest rate");
         float rate=input.nextFloat();
         System.out.println("Now enter the time period ");
         float time=input.nextFloat();

         float res=(principal*rate*time)/100;
         System.out.println("The Simple intrest is: "+res);





     }
}
