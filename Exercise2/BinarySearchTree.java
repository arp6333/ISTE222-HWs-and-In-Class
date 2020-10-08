/**
* Ellie Parobek
* ISTE222.01S2
* Exercise 2 - Binary Search Trees
*/
import java.util.Arrays;

class Node {
   Node left;
   Node right;
   int data;
   
   public Node(int value) {
      data = value;
   }
}

public class BinarySearchTree {
   Node root; // root node of the entire tree
   static int levels;
   
   public BinarySearchTree(int[] keys) {
     // sort keys in ascending order
      Arrays.sort(keys);
      int start = 0;
      int end = keys.length - 1;
      int mid = (start + end) / 2;
      root = new Node(keys[mid]);
      
      // left side of array passed to left subtree
      insert(root, keys, start, mid - 1);
      // right side of array passed to right subtree
      insert(root, keys, mid + 1, end);
   }
   
   public void insert(Node node, int[] keys, int start, int end) {
      if(start <= end) {
         int mid = (start + end) / 2;
         if(keys[mid] < node.data) { // left subtree
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
   
   public void inorderTraversal(Node node, boolean reverse) {
      // print the contents of the tree in increasing order
      if (reverse) {
         if(node != null) {
            inorderTraversal(node.right, reverse);
            System.out.println("Visited " + node.data); // print node's key value
            inorderTraversal(node.left, reverse);
         }
      }
      else {
         if(node != null) {
            inorderTraversal(node.left, reverse);
            System.out.println("Visited " + node.data); // print node's key value
            inorderTraversal(node.right, reverse);
         }   
      }
   }
   
   public void preorderTraversal(Node node) {
      if (node != null) {
         System.out.println("Visited " + node.data); // print node's key value
         preorderTraversal(node.left);
         preorderTraversal(node.right);
      }
   }
   
   public void postorderTraversal(Node node) {
      if (node != null) {
         preorderTraversal(node.left);
         preorderTraversal(node.right);
         System.out.println("Visited " + node.data); // print node's key value
      }
   }
   
   public Node search(Node node, int key) {
      if(node == null)
         // hitting an empty node means search has failed
         return null;
      if(node.data == key)
         // found a match, return the Node's data
         return node;
      else if(node.data > key)
      {
         // need to search the left subtree since key is less than node value
         levels++;
         return search(node.left, key);
      }
      else
      {
         // key value is larger than current node, search right subtree
         levels++;
         return search(node.right, key);
      }
   }
   
   public static void main(String args[]) {
      int[] key_values = {16, 70, 11, 23, 15, 25, 106};
      levels = 0;
      BinarySearchTree bst = new BinarySearchTree(key_values);
      
      System.out.println("Preorder tree traversal");
      bst.preorderTraversal(bst.root);
      
      System.out.println("\nPostorder tree traversal");
      bst.postorderTraversal(bst.root);
      
      System.out.println("\nInorder tree traversal with reverse = true");
      bst.inorderTraversal(bst.root, true);
      
      System.out.println("\nInorder tree traversal with reverse = false");
      bst.inorderTraversal(bst.root, false);
      
      int search_key = 70;
      Node node = bst.search(bst.root, search_key);    
      if(node != null)
         System.out.println("\nFound " + node.data + ", levels traversed = " + levels);
      else
         System.out.println("\n" + search_key + " not found, levels traversed = " + levels);
      
      levels = 0;   
      search_key = 16;
      node = bst.search(bst.root, search_key);    
      if(node != null)
         System.out.println("\nFound " + node.data + ", levels traversed = " + levels);
      else
         System.out.println("\n" + search_key + " not found, levels traversed = " + levels);
      
      levels = 0;   
      search_key = 110;
      node = bst.search(bst.root, search_key);    
      if(node != null)
         System.out.println("\nFound " + node.data + ", levels traversed = " + levels);
      else
         System.out.println("\n" + search_key + " not found, levels traversed = " + levels);
   }
}
