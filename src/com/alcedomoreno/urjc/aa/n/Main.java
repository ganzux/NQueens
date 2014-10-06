package com.alcedomoreno.urjc.aa.n;

import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main (String[] args) {
		Map<String,String> solutions = new HashMap<String, String>();
		n_reinas( 12, solutions );
	}
		
		
	public static void n_reinas (int n, Map<String,String> solutions) {
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

	      buscarReinas (n-1, 0, c, f, dp, ds, solutions);
	   }

	private static void buscarReinas (int n_1,
                                     int i,
                                     int[] solucion,
                                     boolean[] f,
                                     boolean[] dp,
                                     boolean[] ds,
                                     Map<String,String> solutions) {
		// main loop 0 .. (n_1 + 1)
		for (int j=0; j<=n_1; j++){
			// Condition to be a solution 
			if (f[j] && dp[i-j+n_1] && ds[i+j] ) {
	            solucion[i] = j;
	            f[j] = false;
	            dp[i-j+n_1] = false;
	            ds[i+j] = false;
	            if ( i==n_1 && !solutions.containsKey( getKey( solucion ) ) ){
	            	String partialSolution = getKey( solucion );
	            	solutions.put( partialSolution, partialSolution);

	            	// We add the symmetric; due the problem it´s not necessary to check out
	            	int[] symmetric = getSymmetric( solucion );
	            	String partialSymmetricSolution = getKey( symmetric );
	            	solutions.put( partialSymmetricSolution, partialSymmetricSolution);
	            	
	            	System.out.println( partialSolution );
	            	System.out.println( partialSymmetricSolution );
	            }
	            
	            else
	               buscarReinas(n_1, i+1, solucion, f, dp, ds, solutions);
	            f[j] = true;
	            dp[i-j+n_1] = true;
	            ds[i+j] = true;            
			}
		}
	}
	   
	private static String getKey(int[] value){
		   StringBuilder sb = new StringBuilder();
		   for (int i:value)
			   sb.append( i ).append("-");
		   return sb.toString().substring(0, sb.length()-1);
	   }
	
	private static int[] getSymmetric(int[] value){

		int[] symmetric = new int[ value.length ];
		for (int i=0;i<value.length;i++)
			symmetric[ i ] = value[ value.length - i - 1 ];
		return symmetric;
	   }

}
