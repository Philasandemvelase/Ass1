/**
* Store records on the Unbalanced BST
* @author mvlphi006
*/

import java.io.*;
import java.util.Scanner;

public class PlaceNameBST
{
    private PlaceNameEntry data;
    private PlaceNameBST left;
    private PlaceNameBST right;
    private int comparisons;

    public PlaceNameBST()
    {
        data = null;
        left = null;
        right = null;
        comparisons = 0;
    }

    /**
    * @return the left child node
    */
    public PlaceNameEntry getData()
    {
        return data;
    }
    
    /**
    * @return the left child node
    */
    public PlaceNameBST getLeft()
    {
        return left;
    }
    
    /**
    * @return the right child node
    */ 
    public PlaceNameBST getRight()
    {
        return right;
    }
    
    /**
    * @return the comparison count
    */
    public int getComparisons()
    {
        return comparisons;
    }
    
    /**
    * @param placeName the place name to check
    * @return true if found, false otherwise
    */
    public boolean contains(String placeName)
    {
        if (data == null)
        {
           return false;
        }
        
        if (placeName.compareTo(data.getPlaceName()) == 0)
        {
           return true;
        }
        else if (placeName.compareTo(data.getPlaceName()) < 0)
        {
           return left != null && left.contains(placeName);
        }
        else
        {
           return right != null && right.contains(placeName);
        }
    }
    
    /**
    * @param entry the entry to insert
    */
    public void insert(PlaceNameEntry entry)
    {
        if (data == null)
        {
           data = entry;
           return;
        }
        
        if (entry.getPlaceName().compareTo(data.getPlaceName()) < 0)
        {
           if (left == null)
              left = new PlaceNameBST();
           left.insert(entry);
        }
        
        else if (entry.getPlaceName().compareTo(data.getPlaceName()) > 0)
        {
           if (right == null)
              right = new PlaceNameBST();
           right.insert(entry);
        }
    }
    
    /**
    * @param filename the path to the csv file
    * @param nodes the maximum number of records to load
    * @throws FileNotFoundException if not found
    */
    public void putFromFile(String filename, int nodes) throws FileNotFoundException
    {
        data = null;
        left = null;
        right = null;
        
        File file = new File(filename);
        Scanner fileScanner = new Scanner(file);
        
        if (fileScanner.hasNextLine())
        {
           fileScanner.nextLine();
        }
        
        int count = 0;
        while (fileScanner.hasNextLine() && count < nodes)
        {
           String line = fileScanner.nextLine();
           String[] parts = line.split(",");
           
           String placeName = parts[1];
           String municipality = parts[2];
           String province = parts[3];
           int population = Integer.parseInt(parts[4]);
           
           if (!contains(placeName))
           {
              insert(new PlaceNameEntry(placeName, municipality, province, population));
              count++;
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
        data = null;
        left = null;
        right = null;
        
        for (int i = 0; i < values && i < items.length; i++)
        {
            if (items[i] != null && !contains(items[i].getPlaceName()))
            {
                insert(items[i]);
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
        int[] counter = {0};
        PlaceNameEntry result = searchMethod(name, counter);
        comparisons = counter[0];
        return result;
    }
    
    /**
    * @param name the place name to search
    * @param counter an array used to track comparison count
    * @return the matching PlaceNameEntry, or null if not found
    */
    public PlaceNameEntry searchMethod(String name, int[] counter)
    {
        if (data == null) return null;
        
        counter[0]++;
        
        if (name.compareTo(data.getPlaceName()) == 0)
        {
           return data;
        }
        else if (name.compareTo(data.getPlaceName()) < 0)
        {
           return left == null ? null : left.searchMethod(name, counter);
        }
        else
        {
           return right == null ? null : right.searchMethod(name, counter);
        }
    }
    
}