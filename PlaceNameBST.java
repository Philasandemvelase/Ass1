// Store records on the Unbalanced BST
// MVLPHI006
// 06 March 2026
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

    public PlaceNameEntry getData()
    {
        return data;
    }
    public PlaceNameBST getLeft()
    {
        return left;
    }
    public PlaceNameBST getRight()
    {
        return right;
    }
    public int getComparisons()
    {
        return comparisons;
    }
    
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
    
    public PlaceNameEntry search(String name)
    {
        comparisons = 0;
        int[] counter = {0};
        PlaceNameEntry result = searchMethod(name, counter);
        comparisons = counter[0];
        return result;
    }
    
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