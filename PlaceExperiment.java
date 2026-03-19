// A class to compare practice perfomance of the data structures to the theoritical prediction
// mvlphi006
// 13 March 2026

import java.util.*;
import java.io.*;

public class PlaceExperiment
{
    private int[] N = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
    private PlaceNameEntry[] asIs;
    private PlaceNameEntry[] sorted;
    private PlaceNameEntry[] optimal;
    private String[] queries;
    private int records;


    public static void main(String[] args) throws IOException
    {
        Locale.setDefault(Locale.US); // To make decimal be xx.x instead of xx,x
        
        PlaceExperiment main = new PlaceExperiment();

        main.load();
        
        System.out.printf("%-10s %-12s %-12s %-12s %-12s%n", "N", "Array", "BST(as-is)", "BST(sorted)", "BST(optimal)");
        
        for (int i = 0; i < main.N.length; i++)
        {
            int n = main.N[i];

            PlaceNameArray array = new PlaceNameArray(n);
            array.putFromArray(main.asIs, n);

            PlaceNameBST bstSorted = new PlaceNameBST();
            bstSorted.putFromArray(main.sorted, n);

            PlaceNameBST bstAsIs = new PlaceNameBST();
            bstAsIs.putFromArray(main.asIs, n);

            PlaceNameBST bstOptimal = new PlaceNameBST();
            bstOptimal.putFromArray(main.optimal, n);

            double avrArray = main.averageComp(array);
            double avrAsIs = main.averageComp(bstAsIs);
            double avrSorted = main.averageComp(bstSorted);
            double avrOptimal = main.averageComp(bstOptimal);

            System.out.printf("%-10d %-12.1f %-12.1f %-12.1f %-12.1f%n", n, avrArray, avrAsIs, avrSorted, avrOptimal);
        }
    }

    public void load() throws FileNotFoundException
    {
        int max = 10000;
        asIs = new PlaceNameEntry[max];
        int count = 0;
        
        File file = new File("SAPlaceNames.csv");
        Scanner fileScanner = new Scanner(file);
        if (fileScanner.hasNextLine()) fileScanner.nextLine(); //skip header
        
        while (fileScanner.hasNextLine() && count < max)
        {
            String line = fileScanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length < 5)
                continue;
            String placeName = parts[1].trim();
            String municipality = parts[2].trim();
            String province = parts[3].trim();
            int population = Integer.parseInt(parts[4].trim());
            
            boolean found = false;
            for (int i = 0; i < count; i++)
            {
                if (asIs[i].getPlaceName().equals(placeName))
                {
                    found = true;
                    break;
                }
            }
            
            if (!found)
                asIs[count++] = new PlaceNameEntry(placeName, municipality, province, population);
        }
        fileScanner.close();
        records = count;
        
        sorted = Arrays.copyOf(asIs, records);
        Arrays.sort(sorted, (a, b) -> a.getPlaceName().compareTo(b.getPlaceName()));
        
        optimal = new PlaceNameEntry[max];
        int optCount = 0;
        File optFile = new File("SAPlaceNamesOptimal.txt");
        Scanner sc = new Scanner(optFile);
        
        while (sc.hasNextLine() && optCount < max)
        {
            String name = sc.nextLine().trim();
            if (name.isEmpty())
                continue;
            for (int i = 0; i < records; i++)
            {
                if (asIs[i].getPlaceName().equals(name))
                {
                    optimal[optCount++] = asIs[i];
                    break;
                }
            }
        }
        sc.close();
        String[] temp = new String[100];
        int qCount = 0;
        Scanner qSc = new Scanner(new File("SearchQueries.txt"));
        while (qSc.hasNextLine())
        {
            String line = qSc.nextLine().trim();
            if (!line.isEmpty())
                temp[qCount++] = line;
                
        }
        qSc.close();
        queries = Arrays.copyOf(temp, qCount);
         
    }
    
    public double averageComp(PlaceNameArray array)
    {
        double total = 0;
        for (String q : queries)
        {
            array.search(q);
            total += array.getComparisons();
        }
        return (double) total / queries.length;
    }
    
    public double averageComp(PlaceNameBST bst)
    {
        double total = 0;
        for (String q : queries)
        {
            bst.search(q);
            total += bst.getComparisons();
        }
        return (double) total / queries.length;
    }
}