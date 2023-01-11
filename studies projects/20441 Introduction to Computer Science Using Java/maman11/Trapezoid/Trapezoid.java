// this program gets basic data of a trapezoid, and calculates the trapezoid's area and perimeter

import java.util.Scanner;
public class Trapezoid
{
    public static void main (String [] args)
    {   
        Scanner scan=new Scanner(System.in);
        int base1Length, xValueOfLeftPointBase1, yValueOfLeftPointBase1, xValueOfRightPointBase1, yValueOfRightPointBase1,
        base2Length, xValueOfLeftPointBase2, yValueOfLeftPointBase2, xValueOfRightPointBase2, yValueOfRightPointBase2;
        System.out.println ("Please enter the left point coordinates of the "+//getting the first base's data
            "base followed by its length: ");                                       
        xValueOfLeftPointBase1=  scan.nextInt ();//calculate the base data of the first base
        yValueOfLeftPointBase1=  scan.nextInt ();
        base1Length= scan.nextInt ();
        xValueOfRightPointBase1= xValueOfLeftPointBase1 + base1Length;
        yValueOfRightPointBase1= yValueOfLeftPointBase1;

        System.out.println ("Please enter the left point coordinates of the "+//getting the second base's data
            "other base followed by its length: ");                                           
        xValueOfLeftPointBase2= scan.nextInt ();//calculate the base data of the second base
        yValueOfLeftPointBase2=  scan.nextInt ();
        base2Length=  scan.nextInt ();
        xValueOfRightPointBase2= xValueOfLeftPointBase2 + base2Length;
        yValueOfRightPointBase2= yValueOfLeftPointBase2;

        int heightOfTrapezoid;//calculate the height and the sides of the trapeziod

        if (yValueOfRightPointBase1>yValueOfRightPointBase2)
            heightOfTrapezoid= yValueOfRightPointBase1-yValueOfRightPointBase2;
        else heightOfTrapezoid= yValueOfRightPointBase2-yValueOfRightPointBase1;                     

        double leftSideLength= Math.sqrt (Math.pow(xValueOfLeftPointBase1 - xValueOfLeftPointBase2,2)
                + Math.pow(yValueOfLeftPointBase1 - yValueOfLeftPointBase2,2));
        double rightSideLength= Math.sqrt (Math.pow(xValueOfRightPointBase1 - xValueOfRightPointBase2,2)
                + Math.pow(yValueOfRightPointBase1 - yValueOfRightPointBase2,2));  

        double trapezoidArea= (heightOfTrapezoid*(base1Length+base2Length))/2.0;//calculate the area and the perimeter 
        double trapezoidPerimeter= base1Length+base2Length+leftSideLength+rightSideLength;

        System.out.println ("The area of the trapeziod is "+ trapezoidArea);//output the area and the perimeter 
        System.out.println ("The perimeter of the trapeziod is "+ trapezoidPerimeter);    
    }
}