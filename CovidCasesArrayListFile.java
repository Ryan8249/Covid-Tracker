/*
Ryan Borland
L00162964
Program to create covid cases, add them to ArrayList
and write ArrayList to file*/

import java.util.*;
import java.io.*;

public class CovidCasesArrayListFile
{
   public static void main(String []args) throws FileNotFoundException, IOException
   {
      //Create objects to open the file and write to it
      FileOutputStream fos = new FileOutputStream("Covid.txt");// Create and open file for writing
      ObjectOutputStream os = new ObjectOutputStream(fos);//Write objects to the file
      
      //Create ArrayList for Covid Cases
      ArrayList<Covid> covidList = new ArrayList<>();
      Scanner keyboardIn = new Scanner(System.in);
      //Declare Variables
      String countyName, date;
      int noCases = 0, noMales = 0, noFemales = 0, medianAge = 0;
      char response;
            do//Start of do while loop
            {
               System.out.print("Enter the date: ");
               date = keyboardIn.nextLine();
               keyboardIn.nextLine(); 
               System.out.print("Enter County: ");
               countyName = keyboardIn.nextLine();
               try
               {
               System.out.print("Enter Number Of Covid 19 Cases: ");
               noCases = keyboardIn.nextInt();
               System.out.print("Enter Number Of Males with Covid 19: ");
               noMales = keyboardIn.nextInt();
               System.out.print("Enter Number Of Females with Covid 19: ");
               noFemales = keyboardIn.nextInt();
               System.out.print("Enter the median age ");
               medianAge = keyboardIn.nextInt();
               keyboardIn.nextLine();
               }
               catch(IllegalArgumentException ex)// Try catch for negative value errors
               {
                  System.out.println(ex);
               }
               catch(InputMismatchException ex)// Try catch for number only errors
               {
                  System.out.println("Please enter numbers only");
                  keyboardIn.nextLine();
               }
               
               //Add Covid object to ArrayList
               covidList.add(new Covid(date, countyName, noCases, noMales, noFemales, medianAge));
               
               //Ask user if they want to add another county
               System.out.println("Do you want to add another county?");
               response = keyboardIn.next().charAt(0);
            }while(response == 'y' || response == 'Y');//Ask user to enter Y or y if they want to add another county
            //Close do while loop after repsonse from user
      
      //Write ArrayList to file
      os.writeObject(covidList);
      
      //Close Files
      fos.close();
      os.close();
      
      System.out.println("File Successfully created");
      
   }
}