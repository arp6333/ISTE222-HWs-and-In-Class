import java.util.Arrays;
import java.util.*;

public class ClassicSearchesA3 {
   static int ops = 0;
   
   static int linearSearchOrdered(int[] arr, int key) {
      int n = arr.length;
      ops = 0;
      
      for(int i = 0; i < n; i++) {
         ops++;
         if(arr[i] == key)
            return i;
         else if(arr[i] > key) {
            return -1;
         }
      }
            
      return -1;
   }

   static int binarySearch(int arr[], int key) {
      int start = 0;
      int end = arr.length - 1;
      ops = 0;
      
      while(start <= end) {
         ops++;
         int mid = (start + end) / 2;
         if(arr[mid] == key)
            return mid;
         else if(arr[mid] < key)
            start = mid + 1;
         else
            end = mid - 1;
      }
      
      return -1;
   }

   static int interpolationSearch(int[] arr, int key) {
      int low = 0;
      int high = arr.length - 1;
      ops = 0;
      
      while(low <= high && key >= arr[low] && key <= arr[high]) {
         ops++;
         int index = low + (((key - arr[low]) * (high - low)) / 
                     (arr[high] - arr[low]));
         
         if(key == arr[index])
            return index;
         
         if(key < arr[index])
            high = index - 1;
         else
            low = index + 1;
      }
      
      return -1;
   }

   static void searchResult(String type, int key, int index) {
      if(index != -1)
         System.out.println(type + ": Found " + key + " at index " + index + 
                            " in " + ops + " operations");
      else
         System.out.println(type + ": Did not find " + key + " in " + ops + 
                            " operations");
   }
   
   static void printArray(int arr[]) { 
      int n = arr.length; 
      
      for(int i = 0; i < n; i++) 
         System.out.print(arr[i] + " "); 
      
      System.out.println(); 
   }
   
   static int[] generateDataSet() {
      int[] list = new int[1000];
      for (int i = 0; i < list.length; i++) {
         int num = (int)((Math.random() * (10000 - 1)) + 1);
         list[i] = num;
      }   
      Arrays.sort(list);
      return list;
   }

   public static boolean arrayContains(final int[] array, final int v) {
      boolean result = false;
      for (int i : array) {
         if (i == v) {
            result = true;
            break;
         }
      }
      return result;
   }

   public static void main(String[] args) {
      LinkedList<Integer> linearList = new LinkedList<Integer>();
      LinkedList<Integer> binaryList = new LinkedList<Integer>();
      LinkedList<Integer> interpolationList = new LinkedList<Integer>();
      LinkedList<Integer> linearListFail = new LinkedList<Integer>();
      LinkedList<Integer> binaryListFail = new LinkedList<Integer>();
      LinkedList<Integer> interpolationListFail = new LinkedList<Integer>();
   
      // Generate 100 data sets with 1,000 random elements between 1 and 10,000
      LinkedList<int[]> list = new LinkedList<int[]>();
      for (int i = 0; i < 100; i++) {
         list.add(generateDataSet());
      }
      
      for (int[] nums : list) {
         // Search for each element in the data set
         for (int key : nums) {
            linearSearchOrdered(nums, key);
            linearList.add(ops);
            binarySearch(nums, key);
            binaryList.add(ops);
            interpolationSearch(nums, key);
            interpolationList.add(ops);
         }
         
         // Search for 1,000 random elements that are not in the data set
         for (int i = 0; i < 1000; i++) {
            int key = (int)((Math.random() * (10000 - 1)) + 1);
            if (!arrayContains(nums, key)){
               linearSearchOrdered(nums, key);
               linearListFail.add(ops);
               binarySearch(nums, key);
               binaryListFail.add(ops);
               interpolationSearch(nums, key);
               interpolationListFail.add(ops);
            }
         }   
      }
         
      // Compute averages for successful searches
      int total = 0;
      double count = 0.0;
      for (int num : linearList) {
         total += num;
         count++;
      }
      System.out.println("Average for successful linear search: " + total / count);
      total = 0;
      count = 0.0;
      for (int num : binaryList) {
         total += num;
         count++;
      }
      System.out.println("Average for successful binary search: " + total / count);
      total = 0;
      count = 0.0;
      for (int num : interpolationList) {
         total += num;
         count++;
      }
      System.out.println("Average for successful interpolation search: " + total / count);
      
      // Compute averages for unsuccessful searches
      total = 0;
      count = 0.0;
      for (int num : linearListFail) {
         total += num;
         count++;
      }
      System.out.println("Average for unsuccessful linear search: " + total / count);
      total = 0;
      count = 0.0;
      for (int num : binaryListFail) {
         total += num;
         count++;
      }
      System.out.println("Average for unsuccessful binary search: " + total / count);
      total = 0;
      count = 0.0;
      for (int num : interpolationListFail) {
         total += num;
         count++;
      }
      System.out.println("Average for unsuccessful interpolation search: " + total / count);
   }
}