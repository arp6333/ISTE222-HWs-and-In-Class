/**
* Ellie Parobek
* ISTE222.01S2 Comp Prob Solving Info Domain III
* Assignment 1 - Linear Data Structures
*/
import java.util.*; 

public class BASIC {
   private static Stack<String> stack;
   
   // Main method that instantiates the stack and calls all other methods.
   public static void main(String args[]) {
      // Instantiate the stack
      stack = new Stack<String>();
      stack.push("10 INPUT \"What is your name: \"; U$");
      stack.push("20 PRINT \"Hello \"; U$");
      stack.push("30 INPUT \"How many stars do you want: \"; N");
      stack.push("40 S$ = \"\"");
      stack.push("50 FOR I = 1 TO N");
      stack.push("60 S$ = S$ + \"*\"");
      stack.push("70 NEXT I");
      stack.push("80 PRINT S$");
      stack.push("90 INPUT \"Do you want more stars? \"; A$");
      stack.push("100 IF LEN(A$) = 0 THEN GOTO 90");
      stack.push("110 A$ = LEFT$(A$, 1)");
      stack.push("120 IF A$ = \"Y\" OR A$ = \"y\" THEN GOTO 30");
      stack.push("130 PRINT \"Goodbye \"; U$");
      stack.push("140 END");
      
      // List all lines of the program
      System.out.println("1. List all lines of the program: ");
      listAll();
      
      // List lines 40-80 of the program
      System.out.println("\n2. List lines 40-80 of the program: ");
      listRange(40, 80);
      
      // Insert line 17 into the program
      System.out.println("\n3. Insert line 17 into the program: ");
      insert(17);
      
      // Insert line 34 into the program
      System.out.println("\n4. Insert line 34 into the program: ");
      insert(34);
      
      // Insert line 88 into the program
      System.out.println("\n5. Insert line 88 into the program: ");
      insert(88);
      
      // List all lines of the program
      System.out.println("\n6. List all lines of the program: ");
      listAll();
      
      // Renumber the program
      System.out.println("\n7. Renumber the program: ");
      renumber();
      
      // List all lines of the program
      System.out.println("\n8. List all lines of the program: ");
      listAll();
      
      // Insert line 80 into the program (should not be allowed)
      System.out.println("\n9. Insert line 80 into the program (should not be allowed): ");
      insert(80);
      
      // Modify line 9 of the program (should not be allowed)
      System.out.println("\n10. Modify line 9 of the program (should not be allowed): ");
      modify(9, 111);
      
      // Modify line 10 of the program to line 110 (should not be allowed)
      System.out.println("\n11. Modify line 10 of the program to line 110 (should not be allowed): ");
      modify(10, 110);
      
      // Modify line 10 of the program to line 111
      System.out.println("\n12. Modify line 10 of the program to line 111: ");
      modify(10, 111);
      
      // List all lines of the program
      System.out.println("\n13. List all lines of the program: ");
      listAll();
      
      // Renumber the program
      System.out.println("\n14. Renumber the program: ");
      renumber();
      
      // List all lines of the program
      System.out.println("\n15. List all lines of the program: ");
      listAll();
   }
   
   // List all elements in the stack
   public static void listAll() {
      stack.forEach(System.out::println);
   }
   
   // List a range of elements in the stack between first and last
   public static void listRange(int first, int last) {
      for (int i = (first / 10) - 1; i < (last / 10); i++) {
         System.out.println(stack.get(i));
      }
   }
   
   // Insert a line of code into the program at the specified lineNumber
   // If there is already a line of code at lineNumber, print an error message and do not add the line
   public static void insert(int lineNumber) {
      for (int i = 0; i < stack.size(); i++) {
         String str = stack.get(i);
         // Get the line number (beginning of line to the first space)
         int existingLineNumber = Integer.parseInt(str.substring(0, str.indexOf(" ")));
         // If our line number already exists, don't add it
         if (existingLineNumber == lineNumber){
            System.out.println("Line number " + lineNumber + " already exists in stack and will not be added");
            return;
         }
         // If line number is greater than our line number, add it to the stack at this location
         if (existingLineNumber > lineNumber){
            System.out.println("Line number " + lineNumber + " successfully added to stack");
            stack.add(i, lineNumber + " ");
            return;
         }
      }
   }
   
   // Renumbers all lines of code into increments of 10
   // The first line should always be line 10 after performing a renumber
   public static void renumber() {
      int newLineNumber = 0;
      for (int i = 0; i < stack.size(); i++) {
         newLineNumber += 10;
         String str = stack.get(i);
         // Get the line number (beginning of line to the first space)
         int existingLineNumber = Integer.parseInt(str.substring(0, str.indexOf(" ")));
         // Replace line number with new line number if necessary
         if (existingLineNumber != newLineNumber){
            String newStr = newLineNumber + str.substring(str.indexOf(" "));
            stack.set(i, newStr);
         }
      }
      System.out.println("Stack successfully renumbered to line " + newLineNumber);
   }
   
   // Changes the current lineNumber to newLineNumber, then moves it to the correct location in the program
   // If lineNumber does not exist, print an error message and do not modify the program
   // If newLineNumber is already being used, print an error message and do not modify the program
   public static void modify(int lineNumber, int newLineNumber) {
      String newStr = "";
      String oldStr = "";
      int lineToReplace = 0;
      for (int i = 0; i < stack.size(); i++) {
         String str = stack.get(i);
         // Get the line number (beginning of line to the first space)
         int existingLineNumber = Integer.parseInt(str.substring(0, str.indexOf(" ")));
         // If our new line number already exists, don't modify it
         if (existingLineNumber == newLineNumber){
            System.out.println("Line number " + newLineNumber + " already exists in stack and will not be modified");
            return;
         }
         // Get the line number string that we want to modify
         if (existingLineNumber == lineNumber){
            newStr = newLineNumber + str.substring(str.indexOf(" "));
            oldStr = str;
         }
         // If line number is greater than our line number, this is the line we want to modify at
         if (existingLineNumber > lineNumber){
            lineToReplace = i;
         }
      }
      
      // If lineNumber does not exist, print an error message and do not modify the program
      if (oldStr.equals("")) {
         System.out.println("Line number " + lineNumber + " does not exist in stack and cannot not be modified");
      }
      // If lineNumber was found, remove it from the old location and add it to the new location
      else {   
         stack.remove(oldStr);
         stack.add(lineToReplace, newStr + " ");
         System.out.println("Line number " + lineNumber + " successfully modified to line " + newLineNumber);
      }
   }
}