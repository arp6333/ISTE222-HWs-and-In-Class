import java.util.LinkedList;

public class test {

   public static void main(String[] args) {
      LinkedList<String> list = new LinkedList<String>();
   
      list.add("Head");
      list.add("Element 10");
      list.add("Element 20");
      list.add("Element 30");
      list.add("Element 40");
      
      System.out.println("ListTest list elements");
      for (int i = 0; i< list.size(); i++){
         System.out.println("Node value = " + list.get(i));
      }
      
      list.add(2, "Element 11");
      System.out.println("List after insert");
      for (int i = 0; i< list.size(); i++){
         System.out.println("Node value = " + list.get(i));
      }
      
      list.remove();
      list.set(0, "Head");
      System.out.println("List after delete and update");
      for (int i = 0; i< list.size(); i++){
         System.out.println("Node value = " + list.get(i));
      }
   }
}


