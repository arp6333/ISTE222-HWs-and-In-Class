public class reverse {

   public static String stringReverse(String in) {
      String newString = "";
      for (int i = in.length() - 1; i >= 0; i--) {
         newString += in.charAt(i);
      }
      return newString;
   }

   public static void main(String[] args) {
      String str = "This is a string";
      System.out.println(str);
      System.out.println(stringReverse(str));
   }
}

