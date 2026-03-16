// A class to compare practice perfomance of the data structures to the theoritical prediction
// mvlphi006
// 13 March 2026
import java.util.Scanner;
import java.io.*;

public class PlaceExperiment
{
    int[] N = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};
    PlaceNameEntry[] asIs;
    PlaceNameEntry[] sorted;
    PlaceNameEntry[] optimal;
    Strings data;
    int records;


    public static void main(String[] args) throws FileNotFoundException
    {
        PlaceExpirement main = PlaceExperiment();

        main.load();
        main.sort();
        
        for (int i = 0; i < main.N.length; i++)
        {
            int n = main.N[i];

            System.out.printf("%-10s %-12s %-12s %-12s%n", "N", "Array", "BST(as-is)", "BST(sorted)", "BST(optimal)");

            PlaceNameArray array = new PlaceNameArray(n);
            array.putFromArray(main.asIs, n);

            PlaceNameBST bstSorted = new PlaceNameBST();
            bstSorted.putFromArray(main.sorted, n);

            PlaceNameBST bstAsIs = new PlaceNameBST();
            bstAsIs.putFromArray(main.asIs, n);

            PlaceNameBST bstOptimal = new PlaceNameBST();
            bstOptimal.putFromArray(main.optimal, n);

            double avrArray = main.averageComp(array, main.data);
            double avrAsIs = main.averageComp(bstAsIs, main.data);
            double avrSorted = main.averageComp(bstSorted, main.data);
            double avrOptimal = main.averageComp(bstOptimal, main.data);

            System.out.printf("%-10d %-12.1f %-12.1f %-12.1f %-12.1%n", n, avrArray, avrAsIs, avrSorted, avrOptimal);
        }
    }

    public void load() throws FileNotFoundException
    {
        
    }

    public void sort()
    {

    }

    public void deduplicate()
    {

    }
}