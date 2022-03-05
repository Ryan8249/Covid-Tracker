/*
Ryan Borland
L00162964
Program to read object of Covid class back in from file as ArrayList*/

import java.io.*;
import java.util.*;
public class ReadCovidMenu
{
   //ArrayList<Covid> coovidList = new ArrayList<>();
   public static void main(String[]args) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      Scanner keyboardIn = new Scanner(System.in);
      //Create objects to open file and read from it
      FileInputStream fis = new FileInputStream("Covid.txt");
      ObjectInputStream os = new ObjectInputStream(fis);
      
      //Create ArrayList
      ArrayList<Covid>covidList = (ArrayList<Covid>) os.readObject();
      
      //Declare variable
      int option = 0;
      do//Open do while loop
      {
         try
         {
            //Display Menu
            System.out.println("1. View all data for all 5 counties");
            System.out.println("2. View data by county");
            System.out.println("3. Find county with the highest number of cases");
            System.out.println("4. Calculate average age");
            System.out.println("5. Write all details of the county with the lowest number of cases to new file");
            System.out.println("0. Exit System");
            System.out.println("Enter your option");
            option = keyboardIn.nextInt();
            keyboardIn.nextLine();
         }
         catch(InputMismatchException ex)// Try catch for number only errors
         {
            System.out.println("Please enter numbers only");
            
         }

         switch(option)//Start switch statement
         {
            case 1:
            viewAllData(covidList);//Call method viewAllData and pass it the ArrayList
            break;
            
            case 2: 
            //Ask user the enter county name to search for
            System.out.println("Enter the county name to search for");
            String searchCounty = keyboardIn.next();
            keyboardIn.nextLine(); 
            //Call method to search for county
            searchCountyName(covidList, searchCounty);
            break;
            
            case 3:
            //Call method for highest number of cases
            highestNoCases(covidList);
            break;
            
            case 4:
            //Call method for calculating average age
            calAverageAge(covidList);
            break;
            
            case 5:
            //Call method for lowest number of cases
            lowestNoCases(covidList);
            break;
            
             default:
             //Default options if user enters a number that isn't between 1 & 5
            System.out.println("Invalid option chosen, please choose options 1-5.");
            
         }//Close switch statement
         
         
      }while(option != 0);//Close while when user enters option 0
    }//Close main method
    
    //Method to view all data
    public static void viewAllData(ArrayList<Covid> covidList)
    {
        for(Covid covid: covidList)
        {  
           System.out.println(covid);
        }//Close for loop
    }//Close method
        
    //Method to search for county name
    public static void searchCountyName(ArrayList<Covid> covidList, String searchCounty)
    {
      boolean countyFound = false;
      for (int i = 0; i < covidList.size(); i++)
      {
         if(searchCounty.equalsIgnoreCase(covidList.get(i).getCountyName()))
         {
            System.out.println(covidList.get(i));
            countyFound = true;
         }
           
       }//Close for loop
       if(countyFound == false)
         {
            System.out.println("No County matching found"+"\n");
         }  
    }//Close method
    
    //Method to find the county with the highest number of cases
    public static void highestNoCases(ArrayList<Covid> covidList)
    {
      //Declare a variable for the highest number of cases and set it = to 1st element of ArrayList
      double highestCases = covidList.get(0).getNoCases();
      String countyName = covidList.get(0).getCountyName();//Hold the name of county with the highest No of Cases
      
      for (int i = 0; i < covidList.size(); i++)
      {
         if(covidList.get(i).getNoCases() > highestCases)
         {
            highestCases = covidList.get(i).getNoCases();
            countyName = covidList.get(i).getCountyName();
         }
      }//Close for loop
      //Print Name of County
      System.out.println(countyName + " has the highest number of cases of "+highestCases);
   }//Close method
   
   //Method to calculate the average age of covid cases
   
   public static void calAverageAge(ArrayList<Covid> covidList)
   {
      //Declare variable for number of cases
      int [] age = new int [5];
      double totalAge = 0;
      double averageAge;
      
      for(int i = 0; i < covidList.size(); i++)
      {
         totalAge = covidList.get(i).getAge();
      }//Close loop
      //Calculate and display average age
      averageAge = totalAge/5; //Divide by length of array list
      System.out.print(averageAge + " was the average age of covid patients in all 5 countues"+"\n");
   }//Close Mehtod
         
   
   //Method to find the county with the lowest number of cases and write to file
    public static void lowestNoCases(ArrayList<Covid> covidList) throws FileNotFoundException, IOException
    {
       Scanner keyboardIn = new Scanner(System.in);
      //Create objects to open file and read from it
      FileOutputStream fos = new FileOutputStream("lowestCovidCases.txt");//Opens file for reading
      ObjectOutputStream os = new ObjectOutputStream(fos);//Read in from file
      
      
      //Declare a variable for the lowest number of cases and set it = to 1st element of ArrayList
      double lowestCases = covidList.get(0).getNoCases();
      String countyName = covidList.get(0).getCountyName();;
      
      for (int i = 0; i < covidList.size(); i++)
      {
         if(covidList.get(i).getNoCases() < lowestCases)
         {
            lowestCases = covidList.get(i).getNoCases();
            countyName = covidList.get(i).getCountyName();
          
         }
      }//Close for loop
      //Write to file
      os.writeObject(covidList);
      
      //Close file & Stream
      fos.close();
      os.close();
      
      //Print name of country
      System.out.println(countyName + " has the lowest number of cases of "+lowestCases);
   }//Close method
         
    
}//Close Class
        
         
         
   