// An interactive clss that provides a text based menu using BST
// mvlphi006
// 12 March 2026

import java.util.Scanner;
import java.io.*;

public class PlaceSearchBST
{
    Scanner scanner;
    PlaceNameBST bst;

    public static void main(String[] args)
    {
        PlaceSearchBST main = new PlaceSearchBST();

        main.scanner = new Scanner(System.in);
        main.bst = new PlaceNameBST();
        main.menu();
    }

    public void menu()
    {
        boolean loop = true;
        while (loop)
        {
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
            else{
                System.out.println("Invalid");
            }

        }

    }

    public void loadData()
    {
        System.out.println("Enter the filename");
        String filename = scanner.nextLine();

        System.out.println("Enter the number of records to load:");
        int N = scanner.nextInt();
        scanner.nextLine();

        try
        {
            bst = new PlaceNameBST();
            bst.putFromFile(filename, N);
            System.out.println("successful loading.");
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

        PlaceNameEntry result = bst.search(name);

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
        System.out.println("Comparisons: " + bst.getComparisons());
    }
    
}