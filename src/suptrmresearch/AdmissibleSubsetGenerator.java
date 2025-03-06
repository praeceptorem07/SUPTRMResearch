/* Last modified : 22:54 EST -- CMG
 * 
 * Authors: Cody Goldschmidt, Jackson Johnson, Joseph Nwachukwu, Brandon Enlund 
 * Mentor: Dr. Zhenheng Li
 * 
 * GitHub -- https://github.com/praeceptorem07/SUPTRMResearch.git
 * 
 * University of South Carolina Aiken
 * College of Sciences & Engineering
 * Department of Computer Science, Engineering, & Mathematics
 * 
 * Purpose: to generate all admissible subsets for a symplectic (m x m) matrix in which m = 2 * n
 *  
 * Sample Run:
 * Input n: 2
 * 
 * Please make a selection:
 * (1) Display to Terminal
 * (2) Display to Terminal and Save to File
 * 
 * Your Selection: 1
 * 
 * Number of Admissible Subsets: 10
 * Admissible Subsets:
 * {}
 * {1}
 * {2}
 * {3}
 * {4}
 * {1, 2}
 * {1, 3}
 * {2, 4}
 * {3, 4}
 * {1, 2, 3, 4}
 */

package suptrmresearch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AdmissibleSubsetGenerator {
    public static void main(String[] args) {
        // takes user input for a 2n x 2n matrix
        Scanner input = new Scanner(System.in);
        System.out.print("Input n: ");
        int n = input.nextInt();

        
        // user can choose to save the admissible subsets to a file or just print to terminal
        System.out.print("\nPlease make a selection:\n(1) Display to Terminal\n(2) Display to Terminal and Save to File\n\nYour Selection: ");
        int choice = input.nextInt();
        switch (choice) {
            case 1:
                // displays admissible subsets for n
                List<List<Integer>> display1 = generate(n);
                System.out.println("\nNumber of Admissible Subsets: " + display1.size());
                System.out.println("Admissible Subsets:");
                printSubsets(display1);
                break;

            case 2:
                // displays admissible subsets for n
                List<List<Integer>> display2 = generate(n);
                System.out.println("\nNumber of Admissible Subsets: " + display2.size());
                System.out.println("Admissible Subsets:");
                printSubsets(display2);

                // store subsets to a file
                try {
                    storeSubsetsToFile(display2,"n=" + n + "subsets.txt", n);
                } catch (IOException e) {
                        e.printStackTrace();
                }
            break;

            default: 
                System.out.println("Invalid Option -- Program Terminated");
        } 
        
        input.close();
    }


    // method to generate admissible subsets given n
    public static List<List<Integer>> generate(int n) {
        // initializes admissibleSubset array to store all subsets
        List<List<Integer>> admissibleSubsets = new ArrayList<>();

        // stores null set in admissibleSubsets
        admissibleSubsets.add(new ArrayList<>());

        // generate all subsets with 1 to n elements
        List<Integer> elements = new ArrayList<>();
        for (int i = 1; i <= 2 * n; i++) {
            elements.add(i);
        }
        for (int size = 1; size <= n; size++) {
            generateSubsetsRecursive(elements, new ArrayList<>(), admissibleSubsets, 0, size);
        }

        // remove all subsets in which i and f(i) are in the same subset
        for (int i = 1; i <= n; i++) {
            int image = 2 * n - i + 1;
            Iterator<List<Integer>> iterator = admissibleSubsets.iterator();
            while (iterator.hasNext()) {
                List<Integer> subset = iterator.next();
                if (subset != null && subset.contains(i) && subset.contains(image)) {
                    iterator.remove();
                }
            }
        }

        // add full set {1,2,...,2n}
        List<Integer> fullSet = new ArrayList<>();
        for (int i = 1; i <= 2 * n; i++) {
            fullSet.add(i);
        }
        admissibleSubsets.add(fullSet);

        return admissibleSubsets;
    }

    // recursive method to generate subsets
    private static void generateSubsetsRecursive(List<Integer> elements, List<Integer> current, List<List<Integer>> admissibleSubsets, int index, int size) {
        if (current.size() == size) {
            admissibleSubsets.add(new ArrayList<>(current));
            return;
        }
        for (int i = index; i < elements.size(); i++) {
            current.add(elements.get(i));
            generateSubsetsRecursive(elements, current, admissibleSubsets, i + 1, size);
            current.remove(current.size() - 1);
        }
    }

    // method to print subsets with commas and curly braces
    public static void printSubsets(List<List<Integer>> subsets) {
        for (List<Integer> subset : subsets) {
            System.out.print("{");
            for (int i = 0; i < subset.size(); i++) {
                System.out.print(subset.get(i));
                if (i < subset.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("}");
        }
    }

    // method to store subsets to a file
    public static void storeSubsetsToFile(List<List<Integer>> subsets, String filename, int n) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // write n, # of subsets, and admissible subsets
            writer.write("n = " + n);
            writer.write("\nNumber of Admissible Subsets: " + subsets.size());
            writer.write("\nAdmissible Subsets:");
            writer.newLine();

            // write each subset to the file
            for (List<Integer> subset : subsets) {
                writer.write("{");
                for (int i = 0; i < subset.size(); i++) {
                    writer.write(subset.get(i).toString());
                    if (i < subset.size() - 1) {
                        writer.write(", ");
                    }
                }
                writer.write("}");
                writer.newLine();
            }
        }
    }
}