import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class main
{
    public static void main (String [] args) throws Exception
    {
        Scanner scan=new Scanner(System.in);
        int m, k;  //variables as they appear in the booklet
        String path, dataToEnter, dataToCheck;
        System.out.print ("This program enable entering elements to data structure,"); //the interface with the user
        System.out.println (" and also enable checking if element is allready in the structure");
        System.out.println ("Please enter the size of the table: ");                                       
        m=  scan.nextInt();
        System.out.println ("Please enter number of hash functions: ");                                       
        k=  scan.nextInt ();scan.nextLine ();
        System.out.println ("Please enter the path of the file with the elements you want to entering the structure: ");                                       
        path=  scan.nextLine ();
        dataToEnter= getData (path);
        System.out.println ("Please enter the path of the file with the elements you want to check if they in the structure: ");                                       
        path=  scan.nextLine ();
        dataToCheck= getData (path);

        //end of interface

        boolean [] hashTable= new boolean[m]; //constructe hashTable and equate all the cells to false
        int param=spreader(m, dataToEnter); //parameter multiply with each element to spread the elements from each other

        //end of auxiliary constructions

        enterToTable (k,hashTable,dataToEnter,param); //entering the elements to the table
        checkElements (k,hashTable,dataToCheck,param); //checking if each element is in the table
    }

    /**
     *Enter elements to the structure. 
     * @param numOfFunc the number of functions entering the elements to the table 
     * @param hashTable the table
     * @param data the list of the elements
     */
    public static void enterToTable ( int numOfFunc, boolean [] hashTable ,String data, int param)
    {
        String element; //the element
        int tempOrg; //the numerical representation of element

        do  //as long as there is more elements to enter the hash table, continue
        {
            element= data.substring (0,endOfElement(data)); //defines the first element that has not yet entered the hash table
            tempOrg=strToInt(element); //converte the element into number
            if (endOfElement(data)!=data.length()) //there is more elements in the list
            {
                data=data.substring (endOfElement(data)+1); //define the data as the rest of the list without this element
            }
            else //there is no more elements in the list
            {
                data="";
            }
            for (int i=0; i<numOfFunc;i++) //for each function
            {
                hashTable [hashFunctionK (hashTable.length, i, tempOrg*param)]=true;
            }
        }
        while (!data.isEmpty());
    }

    /**
     *Checking each element if he in the was entered the structure.
     *print fot each element if he in was found in the structure
     * @param numOfFunc the number of functions entering the elements to the table 
     * @param hashTable the table
     * @param data the list of the elements
     */
    public static void checkElements ( int numOfFunc, boolean [] hashTable ,String data, int param)
    {
        String element;
        int tempOrg= 0;

        do //as long there is more elements to check, continue
        {
            element= data.substring (0,endOfElement(data));
            tempOrg=strToInt(element);
            if (endOfElement(data)!=data.length()) //there is more elements in the list
            {
                data=data.substring (endOfElement(data)+1); //define the data as the rest of the list without this element
            }
            else //there is no more elements in the list
            {
                data="";
            }
            String temp= "found";
            for (int i=0; i<numOfFunc;i++) //check presence for each functiion
            {
                if (hashTable [hashFunctionK (hashTable.length, i, tempOrg*param)]==false) //if the function didn't found the element
                {
                    temp="not found"; //the element declare as not found
                    break;
                }
            }
            System.out.println("the element "+ element+ " has been "+ temp); //print the status of the element
        }
        while (!data.isEmpty());
    }

    /**
     * Converting file into string.
     * if the has been not found, the method will throw exeption
     * @param path the location of the file wanted to be converte
     * @return string with the data from the file
     */
    public static String getData (String path) throws Exception
    {
        try { //happend only if the file has been found
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            String data = myReader.nextLine();
            myReader.close();
            return data;
        }
        catch (FileNotFoundException e) { //happend if the file has been not found
            throw new Exception ("This exception is handled here.");    
        }
    }

    /**
     * calculate parameter that will spread the elements of the list throw all over the table
     * if there is elements that bigger than the table's size, the parameter will be 1 (he willn't change the elements)
     * @param tableSize the size of the table
     * @param data the string with the elements we want fit the table
     * @return parameter for this speacific elements and table
     */
    public static int spreader (int tableSize, String data)
    {
        int temp= tableSize/max (data); //find the ratio between the biggest element and the table's size
        if (temp==0) //if the biggest element is bigger than the table's size
        {
            temp=1;
        }
        return temp;
    }

    /**
     * found the biggest element from list
     * the method find the biggest element (represented by number) in the list (represented by string)
     * @param data the list represented by string
     * @return the numerical represente of the biggest element 
     */
    public static int max (String data)
    {
        String element; //the element
        int tempOrg; //the numerical representation of element
        int max=1;
        do  //as long as there is more elements to enter the hash table, continue
        {
            element= data.substring (0,endOfElement(data)); //defines the first element that has not yet entered the hash table
            tempOrg=strToInt(element); //converte the element into number
            if (endOfElement(data)!=data.length()) //there is more elements in the list
            {
                data=data.substring (endOfElement(data)+1); //define the data as the rest of the list without this element
            }
            else //there is no more elements in the list
            {
                data="";
            }
            if (tempOrg>max)
            {
                max=tempOrg;
            }
        }
        while (!data.isEmpty());
        return max;
    }

    /**
     * Convert string into number.
     * there is a different if the order of the chars is different
     * @param str the string wanted to be convert
     * @return the numerical representation of element
     */
    public static int strToInt (String str)
    {
        int sum=0;
        for (int i=0;i<str.length();i++)
        {
            sum+=str.charAt(i)*(i+1); //sum the value of the chars in the string, after multiply each char with the value of his location
        }
        return sum;
    }

    /**
     * Hash fuction by parameters
     * @param charSize the size of the hash table
     * @param numOfFunc the nubber of the function, from 0 to k-1
     * @param element the element represented by number, intended entering the hash table
     * @return the cell's number the element intended to enter
     */
    public static int hashFunctionK (int tableSize, int numOfFunc, int element)
    {
        int temp=tableSize/(numOfFunc+1); //generate parameter for the division function
        int cell= element%temp; //division (modulu) function
        return cell;
    }

    /**
     * Return the last char in the first element in a list represented by string.
     * end of string ,represented by ',' or the end of the string
     * @param list the list of the oragens 
     */
    public static int endOfElement (String list)
    {

        int i=list.indexOf(","); 
        if (i==-1) //if there is non ',' in the string (this element is the last in the list)
        {
            i=list.length(); //return the end of the element
        }
        return i;
    }
}