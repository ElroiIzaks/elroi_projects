public class Ex14
{
   /**
     * Make Amir win Tamar (or get a tie) in the game.
     * The method check which kind of the arrey's values, as the hithest sum value- the evens, or the odds. 
     * Accordingly, the method send Amir take coins, only from this kind.
     * Due to the fact that the arrey is even, Tamar must take a coin from the other kind, and by that,
     * release a coin from the other kind for Amir.
     * Space complexity- O(7) = O(1).
     * Time complexity- 4 * O(n) = O(n)
     * @param arr The row of coins.
     */
    public static void win (int [] arr)
    {
        int sum=0;  
        int sumOfOdds=0;
        int left=0;
        int right=arr.length-1;
        for (int i=0; i<arr.length; i++) //check the sum of all the nombers.Time complexity- O(n) (run all the cells in the arrey-n).
        {
            sum+=arr[i];
        }
        for (int i=0; i<arr.length; i+=2) //check the sum of all the odds nombers.Time complexity- O(n)
        {                                 // (run all the odds cells in the arrey- n/2).
            sumOfOdds+=arr[i];
        }
        int sumOfEvens= sum-sumOfOdds;
        if (sumOfOdds>=sumOfEvens) //check if Amir should take the odds nombers in the arrey.
        {
            System.out.println ("Amir took " + arr[left]);
            left= 1;
            while (left<=right) //Time complexity- O(n) (run as long there is more coins in the arrey.
            {                   //every running, the loop take two coins- n/2).
                if (arr[left]<arr[right]) //check if the right coin ass more value than the left one.
                {
                    System.out.println ("Tamar took " + arr[right]);
                    right-=1;
                    if (left<=right) //check if there is more coins in the arrey.
                    {System.out.println ("Amir took " + arr [right]);
                        right-=1;

                    }
                }
                else
                {
                    System.out.println ("Tamar took " + arr [left]);
                    left+=1;
                    if (left<=right) //check if there is more coins in the arrey.
                    {System.out.println ("Amir took " + arr [left]);
                        left+=1;
                    }
                }
            }
            System.out.println ("Final Score:");
            System.out.println ("Amir took " + sumOfOdds);
            System.out.println ("Tamar took " + sumOfEvens);
        }
        else //If Amir should take the evens nombers in the arrey.
        {
            System.out.println ("Amir took " + arr[right]);
            right-=1;
            while (left<=right) //Time complexity- O(n) (run as long there is more coins in the arrey.
            {                   // every running, the loop take two coins- n/2).
                if (arr[left]<arr[right]) //check if the right coin ass more value than the left one.
                {
                    System.out.println ("Tamar took " + arr[right]);
                    right-=1;
                    if (left<=right) //check if there is more coins in the arrey.
                    {System.out.println ("Amir took " + arr [right]);
                        right-=1;

                    }
                }
                else
                {
                    System.out.println ("Tamar took " + arr [left]);
                    left+=1;
                    if (left<=right) //check if there is more coins in the arrey.
                    {System.out.println ("Amir took " + arr [left]);
                        left+=1;
                    }
                }
            }
            System.out.println ("Final Score:");
            System.out.println ("Amir took " + sumOfEvens);
            System.out.println ("Tamar took " + sumOfOdds);
        }
    } 

    
   /**
     *Find the highest double of any three values, from an arrey of integers.
     *The method sort the three most highest values, and the two most lowest values.
     *Accordingly to the variables values, the mathod return the highest double.
     *Space complexity- O(15) = O(1).
     * Time complexity- 5 * O(n) = O(n) 
     *@param arr the arrey with the integers.
     */
    public static int findTriplet (int [] arr)
    {
        int maxValue;
        int maxCell=0;
        int secondValue;
        int secondCell=0;
        int thirdValue;
        int thirdCell=0;
        int minValue;
        int minCell=0;
        int secondMinValue;
        int secondMinCell=1;
        for (int i=0; i<arr.length; i++) //check what is the highest value in the arrey. Time complexity- O(n).
        {
            if (arr[i]>arr[maxCell])
            {
                maxCell=i;
            }
        }
        maxValue=arr[maxCell]; //sort the highest value to the end of the arrey.
        arr[maxCell]=arr[arr.length-1];
        arr[arr.length-1]=maxValue;
        for (int i=0; i<arr.length-1; i++) //check what is the second most high value in the arrey. Time complexity- O(n). (O(n-1)).
        {
            if (arr[i]>arr[secondCell])
            {
                secondCell=i;
            }
        }
        secondValue=arr[secondCell]; //sort the second most high value to the end of the arrey, minos one place.
        arr[secondCell]=arr[arr.length-2];
        arr[arr.length-2]=secondValue;
        for (int i=0; i<arr.length-2; i++) //check what is the third most high value in the arrey. Time complexity- O(n). (O(n-2)).
        {
            if (arr[i]>arr[thirdCell])
            {
                thirdCell=i;
            }
        }
        thirdValue=arr[thirdCell]; //sort the third most high value to the end of the arrey, minos two places.
        arr[thirdCell]=arr[arr.length-3];
        arr[arr.length-3]=thirdValue;
        for (int i=0; i<arr.length-3; i++) //check what is the lowest value in the arrey. Time complexity- O(n).(O(n-3)).
        {
            if (arr[i]<arr[minCell])
            {
                minCell=i;
            }
        }
        minValue=arr[minCell]; //sort the lowest value to the begining of the arrey.
        arr[minCell]=arr[0];
        arr[0]=minValue;
        for (int i=1; i<arr.length-3; i++) //check what is the second most lowe value in the arrey. Time complexity- O(n).(O(n-4)).
        {
            if (arr[i]<arr[secondMinCell])
            {
                secondMinCell=i;
            }
        }
        secondMinValue=arr[secondMinCell]; //sort the second most low value to the second cell of the arrey.
        arr[secondMinCell]=arr[1];
        arr[1]=secondMinValue;
        if ((maxValue<=0) ||//if all of the values in the arrey are negetive, or
        (secondValue*thirdValue>minValue*secondMinValue))  //if the multiple of the second and third most highest values,
        {                                                  //is higher than the multiple of the two most low values.
            System.out.println (maxValue + " " + secondValue + " " + thirdValue); //return the double of the three most highs values.
            return maxValue*secondValue*thirdValue;
        }
        else //if 
        {
            System.out.println (maxValue + " " + secondMinValue + " " + minValue);  
        }//return the double of the highest integer, with the two lowest integers of the arrey.
        return maxValue*secondMinValue*minValue;
    }

    
    /**
     * Count how many times, a substring exist in a string, continuity or discontinuity.
     * @param str the string.
     * @param pattern the substring.
     */
    public static int count (String str, String pattern)
    {
        return count(str, pattern, str.length(), pattern.length());
    }

    private static int count(String str, String pattern, int s, int p)
    {                      //s=str.length **of the first input**, p=pattern.length **of the first input**. 
        if ((s == 0 && p == 0) || p == 0) //if all of the pattern founded in the string. 
        {
            return 1; 
        }
        if (s == 0) //if the string is empty without the pattern was found.
        {
            return 0; 
        }
        if (str.charAt(s - 1) == pattern.charAt(p - 1)) //if the last char is the same: 
        {    
            return count(str, pattern, s - 1, p - 1) +  //check the next char in both strings,
            count(str, pattern, s - 1, p);              //calculate the cases that the char wasn't found.
        }
        else                                      // if last char is different:
        {
            return count(str, pattern, s - 1, p); //don't count the cases that the last char is equal.
        }
    } 

    
    /**
     * Check what is the lowest steps the prince need to get for arriving the evil.
     * @param drm the 2D arrey, represent the map of the city.
     * @param i the location of the prince, vertically.
     * @param j the location of the prince, horizontally.
     */
    public static int prince(int [][]drm, int i, int j)
    {
        int result = prince(drm,i,j,1); //call for the overloaded method. the steps counter start to count.
        if(result == 200) //if there is no way to get to the evil
        {
            return -1;
        }

        return result;
    }

    private static int prince(int [][]drm, int i, int j, int steps_counter)
    {
        if (drm[i][j] == -1)//if the currentlly location is the evil's location.
        {
            return steps_counter;
        }

        int up, down, left, right, current;
        current = drm[i][j]; //change the value of the currentlly location, to ensure the prince won't came back to this location.  
        drm[i][j] = -20;

        // set up
        if (check_index(drm, current, i - 1,j))
        {
            up = prince(drm, i - 1, j, steps_counter+1);
        }
        else //there is no way
        {
            up = 200;
        }

        // set down
        if (check_index(drm, current, i + 1,j))
        {
            down = prince(drm, i + 1, j, steps_counter+1);
        }
        else //there is no way
        {
            down= 200;
        }

        // set left
        if (check_index(drm, current, i ,j -1))
        {
            left = prince(drm, i, j-1, steps_counter+1);
        }
        else //there is no way
        {
            left= 200;
        }

        // set right
        if (check_index(drm, current, i ,j + 1))
        {
            right = prince(drm, i, j + 1, steps_counter+1);
        }
        else //there is no way
        {
            right= 200;
        }

        drm[i][j] = current; //return the value of this location.
        return min_value(up,down,left,right);
    }

    private static boolean check_index(int [][]drm, int value, int i_next, int j_next) //check if going to the next location is legal.
    {
        if (i_next >= drm.length) //the location is in the map; i value.
        {
            return false;
        }
        if (j_next >= drm.length) //the location is in the map; j value.
        {
            return false;
        }

        if (i_next < 0) //the location is in the map; i value.
        {
            return false;
        }
        if (j_next < 0) //the location is in the map; j value.
        {
            return false;
        }

        if(drm[i_next][j_next] == -1) //the next location is the evil location.
        {
            return true;
        }

        if(drm[i_next][j_next] < -1) //the next location has illegal value
        {
            return false;
        }

        if(drm[i_next][j_next] < value - 2) //if the difference between the current location & the next location is more than -2.
        {
            return false;
        }

        if(drm[i_next][j_next] > value + 1)//if the difference between the current location & the next location is more than 1.
        {
            return false;
        }

        return true;
    }

    private static int min_value(int a, int b, int c, int d) //return the minimal number.
    {
        int minimum=a;
        if (b<minimum)
        {
            minimum=b;
        }
        if (c<minimum)
        {
            minimum=c;
        }
        if (d<minimum)
        {
            minimum=d;
        }

        return minimum;
    }
}