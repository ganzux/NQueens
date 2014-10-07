package com.alcedomoreno.urjc.aa.n;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main (String[] args) {
		Map<String,String> solutions = new HashMap<String, String>();
		nQueens( 12, solutions );
	}


	public static void nQueens (int n, Map<String,String> solutions) {
		int[] c = new int[n];			// solution vector

		boolean[] f = new boolean[n];	// position free
		for (int i=0; i<n; i++)
			f[i] = true;

		boolean[] dp = new boolean[2*n-1];	// main diagonal
		for (int i=0; i<2*n-1; i++)
			dp[i] = true;

		boolean[] ds = new boolean[2*n-1];	// secondary diagonal
		for (int i=0; i<2*n-1; i++)
			ds[i] = true;

		lookForQueens (n-1, 0, c, f, dp, ds, solutions);
	}

	/**
	 * Method to discover the solution to the N-Queens Problem
	 * @param n_1 The size minus 1 of the problem (because we use 0-position arrays) 
	 * @param i the actual position for recursive calls; first one is zero
	 * @param solucion The partial solution to be stored while recursive calls are called 
	 * @param f the position calculated, for the rows
	 * @param dp the free main diagonal
	 * @param ds the free secondary diagonal
	 * @param solutions a map with the solutions
	 */
	private static void lookForQueens (int n_1,
                                     int i,
                                     int[] solucion,
                                     boolean[] f,
                                     boolean[] dp,
                                     boolean[] ds,
                                     Map<String,String> solutions) {
		// main loop 0 .. (n_1 + 1)
		for (int j=0; j<=n_1; j++){
			// Necessary Condition to be a solution 
			if (f[j] && dp[i-j+n_1] && ds[i+j] ) {
	            solucion[i] = j;
	            f[j] = false;
	            dp[i-j+n_1] = false;
	            ds[i+j] = false;

	            String partialSolution = getKey( solucion );
	            // if we are in the last position and the solution has not been discovered
	            // we store and publish the new one
	            if ( i==n_1 && !solutions.containsKey( partialSolution ) ){
	            	solutions.put( partialSolution, partialSolution);

	            	// We add the symmetric; due the problem it´s not necessary to check out
	            	int[] symmetric = getSymmetric( solucion );
	            	String partialSymmetricSolution = getKey( symmetric );
	            	solutions.put( partialSymmetricSolution, partialSymmetricSolution);
	            	
	            	System.out.println( partialSolution );
	            	System.out.println( partialSymmetricSolution );
	            }

	            // explore the next level ( i+1 )
	            else
	               lookForQueens(n_1, i+1, solucion, f, dp, ds, solutions);

	            f[j] = true;
	            dp[i-j+n_1] = true;
	            ds[i+j] = true;            
			}
		}
	}

	/**
	 * Method to create a unique key form an array
	 * @param value the array
	 * @return the String separated by dashes
	 */
	private static String getKey(int[] value){
		StringBuilder sb = new StringBuilder();
		for (int i:value)
			sb.append( i ).append("-");
		return sb.toString().substring(0, sb.length()-1);
	}

	/**
	 * Method that return the symmetric of an Array
	 * @param value The original Array
	 * @return The symmetric Array from the original
	 */
	private static int[] getSymmetric(int[] value){
		int[] symmetric = new int[ value.length ];
		for (int i=0;i<value.length;i++)
			symmetric[ i ] = value[ value.length - i - 1 ];
		return symmetric;
	}

}
