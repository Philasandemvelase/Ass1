// An interactive application that provides a text based menu
// mvlphi006
import java.util.Scanner;
import java.io.*;

public class PlaceSearchArray
{
    Scanner scanner;
    PlaceNameArray array;
    
    public static void main(String[] args)
    {
        PlaceSearchArray main = new PlaceSearchArray();
        
        main.scanner = new Scanner(System.in);
        main.array = new PlaceNameArray(14062);
        main.menu();


    }

    public void loadData()
    {
        System.out.println("Enter the file name:");
        String filename = scanner.nextLine();
        
        System.out.println("Enter the number of record to load:");
        int N = scanner.nextInt();
        scanner.nextLine();
        try
        {
            array = new PlaceNameArray(N);
            array.putFromFile(filename, N);
            System.out.println("loaded.");
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
 
    }
    
    public void search()
    {
        System.out.println("Enter the placename:");
        String name = scanner.nextLine();
        
        PlaceNameEntry result = array.search(name);
        
        if (result == null)
        {
            System.out.println("Not found");
        }
        else
        {
            System.out.println("Place: " + result.getPlaceName());
            System.out.println("Municipality: " + result.getMunicipality());
            System.out.println("Province: " + result.getProvince());
            System.out.println("Population: " + result.getPopulation());
        }
        System.out.println("Comparisons: " + array.getComparisons());
    }

    public void menu()
    {
        boolean loop = true;

        while (loop) {

            System.out.println("1. Load data");
            System.out.println("2. Search");
            System.out.println("3. Quit");

            System.out.println("Enter the choice:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1)
            {
                loadData();
            }
            else if (choice == 2)
            {
                search();
            }
            else if (choice == 3)
            {
                loop = false;
            }
            else{
                System.out.println("Invalid");
            }
            
        } 
    }

}