/**
* Stores PlaceNameEntry records in an unsorted array.
* Supports linear search with comparison counting.
*
* @author mvlphi006
*/

import java.util.Scanner;
import java.io.*;

public class PlaceNameArray
{
    private PlaceNameEntry[] records;
    private int numRecords;
    private int comparisons;
    
    /**
    * Construct a PlaceNameArray with a given capacity.
    * @param N the maximum number of records to store
    */

    public PlaceNameArray(int N)
    {
        records = new PlaceNameEntry[N];
        numRecords = 0;
        comparisons = 0;
    }
    
    /**
    * Returns the number of comparisons made in the last search.
    * @return the comparison count
    */
    public int getComparisons()
    {
        return comparisons;
    }
    
    /** 
    * Checks if a place name already exists in the array.
    * @param placeName the place name to check
    * @return true if found, false otherwise
    */
    public boolean contains(String placeName)
    {
      for (int i = 0; i < numRecords; i++)
      {
         if (records[i].getPlaceName().equals(placeName))
         {
            return true;
         }
      }
      return false;
    }
    
    /**
    * Loads place records from a csv file into the array.
    * @param filename the path to the csv file
    * @param nodes the maximum number of records to load
    * @throws FileNotFoundException if the file does not exist
    */
    public void putFromFile(String filename, int nodes) throws FileNotFoundException
    {
      numRecords = 0;
      
      File file = new File(filename);
      Scanner fileScanner = new Scanner(file);
      
      if (fileScanner.hasNextLine())
         fileScanner.nextLine();
      
      while (fileScanner.hasNextLine() && numRecords < nodes)
      {
         String line = fileScanner.nextLine();
         String[] parts = line.split(",");
         String placeName = parts[1];
         String municipality = parts[2];
         String province = parts[3];
         int population = Integer.parseInt(parts[4]);
         
         if (!contains(placeName))
         {
            records[numRecords++] = new PlaceNameEntry(placeName, municipality, province, population);
         }
      }
      fileScanner.close();
      
    }
    
    /**
    * @param items the source array of PlaceNameEntry records
    * @param values the number of records to load
    */
    public void putFromArray(PlaceNameEntry[] items, int values)
    {
        numRecords = 0;
        for (int i = 0; i < values && i < items.length; i++)
        {
            if (items[i] != null && !contains(items[i].getPlaceName()))
            {
                records[numRecords++] = items[i];
            }
        }
    }
    
    /** 
    * @param name the place name to search for
    * @return the matching PlaceNameEntry, or null if not found
    */
    public PlaceNameEntry search(String name)
    {
    
      comparisons = 0;
      
      for (int i = 0; i < numRecords; i++)
      {
         comparisons++;
         if (records[i].getPlaceName().compareTo(name) == 0)
         {
            
            return records[i];
         }
         
      }
      return null;
    }

}