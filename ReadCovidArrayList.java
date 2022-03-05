/*Program to read object of Covid class back in from file as ArrayList*/

import java.io.*;
import java.util.*;
public class ReadCovidArrayList
{
   public static void main(String[]args) throws FileNotFoundException, IOException, ClassNotFoundException
   {
      Scanner keyboardIn = new Scanner(System.in);
      //Create objects to open file and read from it
      FileInputStream fis = new FileInputStream("Covid.txt");//Opens file for reading
      ObjectInputStream in = new ObjectInputStream(fis);//Read in from file
      
      //Create ArrayList to catch object from file
      ArrayList<Covid> covidList = (ArrayList<Covid>) in.readObject();
      
      //Search Covid Cases in County
      String searchCounty;
      System.out.print("Enter the County: ");
      searchCounty = keyboardIn.nextLine();
      for(int i = 0; i < covidList.size(); i++)
      {
         if(searchCounty.equalsIgnoreCase(covidList.get(i).getCountyName()))
         {
            System.out.println(covidList.get(i));
         }
      }
      
      //Print ArrayList to screen
     /* for(Covid covid :covidList)
      {
      System.out.println(covid);
      }*/
               
   }
}
      
    