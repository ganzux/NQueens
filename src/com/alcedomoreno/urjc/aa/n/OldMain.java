package com.alcedomoreno.urjc.aa.n;


/**
 * Write a description of class N_reinas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class OldMain
{
	
	public static void main (String[] args) {
		n_reinas (6);
	}
   public static void n_reinas (int n) {
      int[] c = new int[n];
      boolean[] f = new boolean[n];
      for (int i=0; i<n; i++)
         f[i] = true;
      boolean[] dp = new boolean[2*n-1];
      for (int i=0; i<2*n-1; i++)
         dp[i] = true;
      boolean[] ds = new boolean[2*n-1];
      for (int i=0; i<2*n-1; i++)
         ds[i] = true;
      buscarReinas (n-1, 0, c, f, dp, ds);
   }
   private static void buscarReinas (int n_1,
                                     int i,
                                     int[] solucion,
                                     boolean[] f,
                                     boolean[] dp,
                                     boolean[] ds) {
      for (int j=0; j<=n_1; j++)
         if (f[j] && dp[i-j+n_1] && ds[i+j]) {
            solucion[i] = j;
            f[j] = false;
            dp[i-j+n_1] = false;
            ds[i+j] = false;
            if (i==n_1)
               imprimir (solucion);
            else
               buscarReinas(n_1, i+1, solucion, f, dp, ds);
            f[j] = true;
            dp[i-j+n_1] = true;
            ds[i+j] = true;            
         }
   }
   private static void imprimir (int[] v) {
      for (int i=0; i<v.length; i++)
         System.out.print (v[i]+" ");
      System.out.println();
   }
}
