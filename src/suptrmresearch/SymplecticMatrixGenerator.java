/* Last modified : 22:56 EST -- CMG
 * 
 * Authors: Cody Goldschmidt, Jackson Johnson, Joseph Nwachkwu, Brandon Enlund 
 * Mentor: Dr. Zhenheng Li
 * 
 * GitHub -- https://github.com/praeceptorem07/SUPTRMResearch.git
 * 
 * University of South Carolina Aiken
 * College of Sciences & Engineering
 * Department of Computer Science, Engineering, & Mathematics
 * 
 * Purpose: to generate the domain and range for all symplectic (m * m) rook matrices in which m = 2 * n
 *  
 * Sample Run:
 */
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
        System.out.println("\nNumber of admissible Subsets: " + domain.size());
        System.out.println("Admissible Subsets: ");
        AdmissibleSubsetGenerator.printSubsets(domain);

    }
}