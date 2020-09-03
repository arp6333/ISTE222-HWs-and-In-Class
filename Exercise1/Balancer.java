import java.util.*;

/**
* Ellie Parobek
* Exercise 1
*/
public class Balancer {
   
   public static void main(String[] args) {
      // Expression 1
      String expression = "-{ [ b * b - (4 * a * c) ] / (2 * a) }";
      if (balancer(expression)) {
         System.out.println("1. " + expression + " is balanced.");
      }
      else {
         System.out.println("1. " + expression + " is not balanced.");
      }
      
      // Expression 2
      expression = "-[ { [ b * b - (4 * a * c) ] / (2 * a) }";
      if (balancer(expression)) {
         System.out.println("2. " + expression + " is balanced.");
      }
      else {
         System.out.println("2. " + expression + " is not balanced.");
      }
      
      // Expression 3
      expression = "-{ [ b * b - (4 * a * c) ] / (2 * a) } ]";
      if (balancer(expression)) {
         System.out.println("3. " + expression + " is balanced.");
      }
      else {
         System.out.println("3. " + expression + " is not balanced.");
      }
      
      // Expression 4
      expression = "-{ [ b * b - [(4 * a * c) ] / (2 * a) }";
      if (balancer(expression)) {
         System.out.println("4. " + expression + " is balanced.");
      }
      else {
         System.out.println("4. " + expression + " is not balanced.");
      }
   } 
   
   public static boolean balancer(String exp) {
      Stack<Character> stack = new Stack<Character>();
      // Loop through each character in the expression
      for (int i = 0; i < exp.length(); i++) {
         Character c = exp.charAt(i);
         // Check for opening character
         if (c.equals('{') || c.equals('(') || c.equals('[')) {
            // Add to stack
            stack.push(c);
         }
         // Check for closing character
         else if (c.equals('}') || c.equals(')') || c.equals(']')) {
            // If stack is empty, expression is unbalanced
            if (stack.size() == 0) {
               return false;
            }
            // Remove top character from stack
            Character popped = (Character)stack.pop();         
            // If character removed does not match the closing character, the expression is unbalanced
            if (!(popped.equals('{') && c.equals('}')) &&
                !(popped.equals('(') && c.equals(')')) &&
                !(popped.equals('[') && c.equals(']')))
            {
               return false;
            }
         }
      }
      
      // If stack is empty, expression is balanced
      if (stack.size() == 0) {
         return true;
      }
      // Otherwise, expression is unbalanced
      else {
         return false;
      }
   }
}