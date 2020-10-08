/**
* Ellie Parobek
* ISTE222.01S2
* Assignment 2 - Non-Linear Data Structures
*/
import java.io.*;
import java.util.*;

public class IRSystem {
   private static HashMap<String, Integer> countMap;
   private static BinarySearchTree bst;
   
   // main method
   public static void main(String[] args) {
      // Setup //
      
      // Step 1. Read quotes into memory
      // Steps 2, 3 inside parse method:
      String[] words = parse("quotes.txt");
      
      // Step 4. Count how many times each individual term has occurred
      countTerms(words);
      
      // Step 5. Place all terms and counts into a binary search tree
      Iterator iterator = countMap.entrySet().iterator(); 
      LinkedList<TermEntry> tempTerms = new LinkedList<TermEntry>();
      while (iterator.hasNext()) {
         Map.Entry element = (Map.Entry)iterator.next();
         String term = element.getKey() + "";
         int count = (int)element.getValue();
         tempTerms.add(new TermEntry(term, count));
      }
      bst = new BinarySearchTree(tempTerms.toArray(new TermEntry[0]));
      
      // Test Cases //
      
      // 1. Inorder traversal of the binary search tree
      System.out.println("1. Inorder traversal of the binary search tree");
      bst.inorderTraversal(bst.root, false);
      
      // 2. Single term query for "all"
      System.out.println("2. Single term query for 'all'");
      TermEntry result = singleTermQuery("all");
      if (result == null) {
         System.out.println("Term 'all' was not found");
      }
      else {
         System.out.println("Term 'all' was found, count: " + result.getCount());
      }
      
      // 3. Single term query for "carrying"
      System.out.println("3. Single term query for 'carrying'");
      result = singleTermQuery("carrying");
      if (result == null) {
         System.out.println("Term 'carrying' was not found");
      }
      else {
         System.out.println("Term 'carrying' was found, count: " + result.getCount());
      }
      
      // 4. Single term query for "robot"
      System.out.println("4. Single term query for 'robot'");
      result = singleTermQuery("robot");
      if (result == null) {
         System.out.println("Term 'robot' was not found");
      }
      else {
         System.out.println("Term 'robot' was found, count: " + result.getCount());
      }
      
      // 5. Conjunctive term query for "seattle" and "mariners"
      System.out.println("5. Conjunctive term query for 'seattle' and 'mariners'");
      boolean andResult = andQuery("seattle", "mariners");
      if (andResult) {
         System.out.println("Conjunctive query for 'seattle' and 'mariners' was successful");
      }
      else {
         System.out.println("Conjunctive query for 'seattle' and 'mariners' was not successful");
      }
      
      // 6. Conjunctive term query for "four" and "score"
      System.out.println("6. Conjunctive term query for 'four' and 'score'");
      andResult = andQuery("four", "score");
      if (andResult) {
         System.out.println("Conjunctive query for 'four' and 'score' was successful");
      }
      else {
         System.out.println("Conjunctive query for 'four' and 'score' was not successful");
      }
      
      // 7. Disjunctive term query for "four" and "score"
      System.out.println("7. Disjunctive term query for 'four' and 'score'");
      boolean orResult = orQuery("four", "score");
      if (orResult) {
         System.out.println("Disjunctive query for 'four' and 'score' was successful");
      }
      else {
         System.out.println("Disjunctive query for 'four' and 'score' was not successful");
      }
      
      // 8. Disjunctive term query for "five" and "score"
      System.out.println("8. Disjunctive term query for 'five' and 'score'");
      orResult = orQuery("five", "score");
      if (orResult) {
         System.out.println("Disjunctive query for 'five' and 'score' was successful");
      }
      else {
         System.out.println("Disjunctive query for 'five' and 'score' was not successful");
      }
      
      // 9. Disjunctive term query for "five" and "robots"
      System.out.println("9. Disjunctive term query for 'five' and 'robots'");
      orResult = orQuery("five", "robots");
      if (orResult) {
         System.out.println("Disjunctive query for 'five' and 'robots' was successful");
      }
      else {
         System.out.println("Disjunctive query for 'five' and 'robots' was not successful");
      }
      
   }

   // parse the quotes collection into memory and convert terms to lowercase
   private static String[] parse(String fileName) {
      LinkedList<String> tempStrings = new LinkedList<String>();
      
      try {      
         // Step 2. Tokenize the quotes into individual terms
         String token;
         Scanner sc = new Scanner(new File(fileName)).useDelimiter("\\s+");
         
         // Step 3. Convert all terms to lowercase
         while (sc.hasNext()) {
            token = sc.next();
            tempStrings.add(token.toLowerCase());
         }
         sc.close();
      }
      catch (FileNotFoundException fnfe) {
         System.out.println("Error: File " + fileName + " not found.");
      }
      
      return tempStrings.toArray(new String[0]);
   }
   
   // count each occurence of each word in the quotes
   private static void countTerms(String[] terms) {
      countMap = new HashMap<String, Integer>();
      
      for (String word : terms) {
         Integer count = countMap.get(word);
         if (count != null) {
            countMap.put(word, count + 1);
         }
         else {
            countMap.put(word, 1);
         }
      }
   }
   
   // perform a single term query on the binary search tree
   private static TermEntry singleTermQuery(String term) {
      Node result = bst.search(bst.root, term);
      if (result != null) {
         return result.data;
      }
      return null;
   }
   
   // perform an and query on the binary search tree
   private static boolean andQuery(String term1, String term2) {
      Node result1 = bst.search(bst.root, term1);
      Node result2 = bst.search(bst.root, term2);
      if (result1 != null && result2 != null) {
         return true;
      }
      return false;
   }
   
   // perform an or query on the binary search tree
   private static boolean orQuery(String term1, String term2) {
      Node result1 = bst.search(bst.root, term1);
      Node result2 = bst.search(bst.root, term2);
      if (result1 != null || result2 != null) {
         return true;
      }
      return false;   
   }
   
   // term entry class
   private static class TermEntry {
      private String term;
      private int count;
      
      private TermEntry(String term, int count) {
         this.term = term;
         this.count = count;
      }
      
      private String getTerm() {
         return term;
      }
      
      private int getCount() {
         return count;
      }
   }

   // node class for the binary search tree
   private static class Node {
      private Node left;
      private Node right;
      private TermEntry data;
      
      private Node(TermEntry data) {
         this.data = data;
      }
   }

   // binary search tree class
   private static class BinarySearchTree {
      private Node root; // root node of the entire tree
      
      private BinarySearchTree(TermEntry[] keys) {
         // sort keys alphabetically
         Collections.sort(Arrays.asList(keys), Comparator.comparing(TermEntry::getTerm));
         int start = 0;
         int end = keys.length - 1;
         int mid = (start + end) / 2;
         root = new Node(keys[mid]);
      
         // left side of array passed to left subtree
         insert(root, keys, start, mid - 1);
         // right side of array passed to right subtree
         insert(root, keys, mid + 1, end);
      }
      
      public void insert(Node node, TermEntry[] keys, int start, int end) {
         if(start <= end) {
            int mid = (start + end) / 2;
            if(keys[mid].getTerm().compareTo(node.data.getTerm()) < 0) { // left subtree
               node.left = new Node(keys[mid]);
               insert(node.left, keys, start, mid - 1);
               insert(node.left, keys, mid + 1, end);
            }
            else { // right subtree
               node.right = new Node(keys[mid]);
               insert(node.right, keys, start, mid - 1);
               insert(node.right, keys, mid + 1, end);
            }
         }
      }
      
      private void inorderTraversal(Node node, boolean reverse) {
         // print the contents of the tree in increasing order
         if (reverse) {
            if(node != null) {
               inorderTraversal(node.right, reverse);
               System.out.println(node.data.getTerm() + " : " + node.data.getCount()); // print node's key value
               inorderTraversal(node.left, reverse);
            }
         }
         else {
            if(node != null) {
               inorderTraversal(node.left, reverse);
               System.out.println(node.data.getTerm() + " : " + node.data.getCount()); // print node's key value
               inorderTraversal(node.right, reverse);
            }   
         }
      }
      
      private Node search(Node node, String key) {
         if (node == null) {
            // hitting an empty node means search has failed
            return null;
         }
         if (node.data.getTerm().equals(key)) {
            // found a match, return the Node's data
            return node;
         }
         else if (node.data.getTerm().compareTo(key) > 0)
         {
            // need to search the left subtree since key is less than node value
            return search(node.left, key);
         }
         else
         {
            // key value is larger than current node, search right subtree
            return search(node.right, key);
         }
      }
   }   
}