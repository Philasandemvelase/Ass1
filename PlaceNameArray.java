// A class to store the records in the arrray
// mvlphi006
import java.util.Scanner;
import java.io.*;

public class PlaceNameArray
{
    private PlaceNameEntry[] records;
    private int numRecords;
    private int comparisons;

    public PlaceNameArray(int N)
    {
        records = new PlaceNameEntry[N];
        numRecords = 0;
        comparisons = 0;
    }
    
    public int getComparisons()
    {
        return comparisons;
    }
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