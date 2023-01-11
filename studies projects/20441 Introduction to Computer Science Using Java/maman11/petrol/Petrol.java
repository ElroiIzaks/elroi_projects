// this program converts fuel consumption from miles/gallon to litres/100km

import java.util.Scanner;
public class Petrol
{
    public static void main(String[] args)
    {
        final double MAX = 100; // final, use for rounding
        final double KM_IN_MILE = 1.609; //(km)
        final double LITRES_IN_GALLON = 3.785; 
        final double LITRES_PER_100KM= LITRES_IN_GALLON *100/ KM_IN_MILE; //the fixed ratio between litres/100km to miles/gallon
        System.out.println ("Please enter the car's petrol consumption " +
            "measured in miles/gallon:");
        Scanner scan = new Scanner (System.in);
        double milesPerGallon = scan.nextDouble();
        double beforeRounding = LITRES_PER_100KM/milesPerGallon;
        double afterRounding = Math.round (beforeRounding * MAX)/(MAX*1.0); 
        System.out.println ("The car's petrol consumption " +
            "converted to litres/100km is: ");
        System.out.println (afterRounding);
    } //end of method main

} //end of class petrol