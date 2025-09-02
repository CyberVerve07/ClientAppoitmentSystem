import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Scanner input=new Scanner(System.in);
        System.out.print("enter the first number");
        float first=input.nextInt();
        System.out.print("Enter second number");
         float second=input.nextInt();

         float add=first+second;
         float sub=first-second;
         float mul=first*second;
         float div=first/second;
         float mod =first%second;

         System.out.println("Addition is "+add);
         System.out.println("Subtraction is"+sub);
         System.out.println("Multiplication is "+mul);
         System.out.println("The division is "+div);
         System.out.print("The  modules is "+mod);



    }
}