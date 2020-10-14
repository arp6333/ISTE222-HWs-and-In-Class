/**
* Ellie Parobek
* ISTE222.01S2
* Exercise 3 - Searching
*/
import java.util.*; 

public class StringSearch {
    
   static int bruteForce(String str, String substr) {
      int n = str.length();
      int m = substr.length();
      int index;
      
      for(int i = 0; i <= n - m; i++) {
         int j = 0;
         
         while(j < m && substr.charAt(j) == str.charAt(i + j)) {
            j++;
         }
         
         if(j == m)
            return i;
      }
      
      return -1;
   }
   
   static LinkedList<Integer> rabinKarpMultiple(String str, String substr) {
      LinkedList<Integer> indexes = new LinkedList<Integer>();
      int n = str.length();
      int m = substr.length();
      int t = 0; // hash value for str window
      int p = 0; // hash value for substr
      
      // Calculate the hash value of the substring
      // and the first str window 
      for(int i = 0; i < m; i++) {  
         p = p + substr.charAt(i);
         t = t + str.charAt(i);
      } 
      
      int i = 0;
      while(i <= n - m) { 
         // if the hashes match, confirm that the window 
         // and substring match 
         if(p == t) { 
            int j;
            //System.out.println("Hashes matched: window = " + str.substring(i, i + m));
            
            for(j = 0; j < m; j++) {
               if(substr.charAt(j) != str.charAt(i + j))
                  break; 
            } 
         
            if(j == m) 
               indexes.add(i); 
         }
         
         if(i < n - m) {
            // compute the hash for the next window
            t = t - str.charAt(i);
            t = t + str.charAt(i + m);
         }
         
         i++;
      }
      
      return indexes;
   }
   
   static void countBases(String str) {
      System.out.println("2. 'A' count = " + rabinKarpMultiple(str, "A").size());
      System.out.println("\t'C' count = " + rabinKarpMultiple(str, "C").size());
      System.out.println("\t'T' count = " + rabinKarpMultiple(str, "T").size());
      System.out.println("\t'G' count = " + rabinKarpMultiple(str, "G").size());
   }
               
   public static void main(String[] args) {
      String str = "GTTGCAGTTACTTATTATCTGAAAACCAGTTGATGTTAAGGAATACTCTGTCTAAGACAACATATGTAATAAAAATTATATATTCGTTGGGTTCTCTCGA";
      String substr = "GTT";
      
      System.out.println("Intput: " + str);
      System.out.println("1. 'GTT' Rabin Karp Indexes = " + rabinKarpMultiple(str, substr));
      countBases(str);
   }
}