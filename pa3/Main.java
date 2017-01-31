import java.awt.*;

class Main
{
public static void main( String [] args ) throws java.lang.Exception
{
Point p1 = new Point( 37, 23 );
Point p2 = new Point( p1 );
Point p3 = p2;
p1.translate(1,1);

System.out.println("" + (p1.equals(p2)) );
System.out.print("" + (p1.equals(p3)) );
System.out.print("" + (p2.equals(p3)) );

p2.translate(1,1);

System.out.println("" + (p1.equals(p2)) );
System.out.print("" + (p1.equals(p3)) );
System.out.print("" + (p2.equals(p3)) );

p3.translate(1,1);

System.out.println("" + (p1.equals(p2)) );
System.out.print("" + (p1.equals(p3)) );
System.out.print("" + (p2.equals(p3)) );
}
}