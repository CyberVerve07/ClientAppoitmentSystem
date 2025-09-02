import java.util.Scanner;

class Triangle {
     public static void main(String[] args) {
         Scanner input=new Scanner(System.in);
         System.out.println("Welcome to new code:");
         System.out.println("please neter your base in cms:");
         double base= input.nextDouble();

         System.out.println("Enter you perpinduclar height in cm");
         double height= input.nextDouble();
         double area =(base*height)/2;

         System.out.println("The area of triangle is :"+area+"cms");
         }
     }

