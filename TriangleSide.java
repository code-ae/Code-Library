
import java.util.Scanner;


public class TriangleSide {


    public static void main(String args[]) {
    
     
    Scanner scan = new Scanner(System.in);
    
      double testA;
      double testB;
      
    
    System.out.println("What is the value of the a side?");
    Triangle.aSide = scan.nextDouble();
        
    
    System.out.println("What is the value of the b side?");
    Triangle.bSide = scan.nextDouble();
    
    Triangle.aSide = Triangle.aSide * Triangle.aSide;
    Triangle.bSide = Triangle.bSide * Triangle.bSide;
    
    Triangle.cSide = Triangle.aSide + Triangle.bSide;
    Triangle.cSide = Math.sqrt(Triangle.cSide);
    
    System.out.print("The size of the hypotenuse is ") ;
    System.out.print(Triangle.getCSide());
    
}
public static class Triangle {

       
    public static double aSide;
    public static double bSide;
    public static double cSide;
   
    public void setASide(double aSide) {
       
        this.aSide = aSide;
        
        }
        
    public void setBSide(double bSide) {
    
        this.bSide = bSide;
        
        }
        
     
    public static double getCSide() {
    
      return Triangle.cSide ; 
      
      }
    
}
}  