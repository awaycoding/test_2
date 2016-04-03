package hello;
import org.joda.time.LocalTime;
 
public class HelloWorld 
{
  public static void main(String[] args) 
  {
    LocalTime currentTime = new LocalTime();
    System.out.println("The current local time is: " + currentTime);
    Greeter greeter = new Greeter();
    System.out.println(greeter.sayHello());
	int aa = t();
	System.out.println("the value of aa is:" + aa);
  }
  int t(){
	  int a = 1;
	  return a;
  }
}
