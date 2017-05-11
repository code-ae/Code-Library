public class Instruments {

   public static void main(String args[]) {
   
      String[] inst = new String[4] ;
      
      inst[0] = "Cello" ;
      inst[1] = "Guitar" ;
      inst[2] = "Violin" ;
      inst[3] = "Double Bass" ;
      
      String[] vowels = new String[5];
      vowels[0] = "a";
      vowels[1] = "e";
      vowels[2] = "i";
      vowels[3] = "o";
      vowels[4] = "u";      
           
      for (int i = 0 ; i<=3 ; i++) {
      
      String newName = inst[i] ;
      
      for ( int j = 0 ; j<=4 ; j++ ) {
      
            
      newName  = newName.replace(vowels[j],"");
       }
      System.out.println(newName);
      
       
      
      }
	  }
      }
