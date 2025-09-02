import java.util.Scanner;

public class Compound {
    public static void main(String[] args) {
        Scanner input= new Scanner(System.in);
        System.out.println("Calcuate the Compound intrest");

        System.out.println("Enter the principal amount");
        int principal= input.nextInt();
        System.out.println("Enter the Rate");
        float rate=input.nextFloat();
        System.out.println("Enter the time ");
        float time=input.nextFloat();

        double comp=principal*Math.pow((1+rate/100),time);

            System.out.println("The Compound intrest is "+comp);
}}