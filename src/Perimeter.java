import java.util.Scanner;

class Perimeter {
     public static void main(String[] args) {
         Scanner input=new Scanner(System.in);
         System.out.print("Welcome to perimeter");
         System.out.print("Please enter all sides of perimeters in cm :");

         double a= input.nextDouble();
         double b= input.nextDouble();
         double c= input.nextDouble();
         double d= input.nextDouble();

         double perimeter=a+b+c+d;
         System.out.print("Perimiter of yoyr rectangel is "+perimeter);


     }
}
