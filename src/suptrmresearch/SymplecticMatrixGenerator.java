package suptrmresearch;

import java.util.*;

public class SymplecticMatrixGenerator {

    public static void main(String[] args) {
        // takes user input for a 2n x 2n matrix
        Scanner input = new Scanner(System.in);
        System.out.print("Input n: ");
        int n = input.nextInt();
        input.close();
        
        // loads list of every admissible subset given n into domain and range
        List<List<Integer>> domain = AdmissibleSubsetGenerator.generate(n);
        List<List<Integer>> range = domain;

        // print the number of admissible subsets and admissible subsets to verify
        System.out.println("Number of admissible Subsets: " + domain.size());
        System.out.println("Admissible Subsets: ");
        AdmissibleSubsetGenerator.printSubsets(domain);

    }
}