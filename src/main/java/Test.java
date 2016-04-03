import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;
import java.util.Iterator;
public class Test{
	public static void main(String []args){
		List<String> list = new ArrayList<String>();
		list.add("one.jpg");
		list.add("two.jpg");
		list.add("three.jpg");
		list.add("four.jpg");
		list.add("five.jpg");
		Down.downImage("http://localhost/", list);
	}
}
