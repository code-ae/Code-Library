import java.io.* ;
import java.util.*;

public class Contact {

   public static int count ;
   public String name ;
   public int phoneNumber ;
   
   
   public Contact(String name , int phoneNumber) {
   
      count++ ;
      this.name = name ;
      this.phoneNumber = phoneNumber ;
      
      }
      
      public String getName() {
      
         return this.name;
      }
      
      public int getNumber() {
         
         return this.phoneNumber;
      
      }
      
      
      public static void main (String args[]) {
      
      Scanner input = new Scanner(System.in);
      Contact[] list = new Contact[4] ;
      
      list[0] = new Contact("Philly",5550) ;
      list[1] = new Contact("Becky",6330) ;
      list[2] = new Contact("Rufio",4456) ;
      
      System.out.println("There are " + count + " contacts");
      
      for( int i = 0 ; i<3 ; i++ ) {
      System.out.println(list[i].getName()) ;
      System.out.println(list[i].getNumber()) ;
      System.out.println("---") ;
      
      System.out.println("Would you like to add another? Yes/No");
      String answer = input.next() ;
      
         if( answer = "No" ) {
            System.out.println("Goodbye.") ;
            }
         else {
            System.out.println(" Sure, what is the new contacts name?");
            String newName = input.next();
            newContact.name = newName ;
            
            System.out.println("and the number?");
            int newNumber = input.nextInt();
            newContact.phoneNumber = newNumber ;
            }
            
         Contact newContact = new Contact() ;
         
         
         
         list[3] = newContact ;
         
           
         for( int j = 0 ; j<=3 ; j++ ) {
      System.out.println(list[j].getName()) ;
      System.out.println(list[j].getNumber()) ;
      System.out.println("---") ;
      
      
      }
      
}
}
}
      
     
