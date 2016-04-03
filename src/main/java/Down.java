import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.io.RandomAccessFile;
import java.net.URL;  
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;
import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Hashtable;

class Down{
	public static String desPath = "/Users/liuheng/img/";
	public static int THREAD_POOL_SIZE = 5;
	private static Map<String, byte[]> downResult = new Hashtable<String, byte[]>();
	public static Map<String, byte[]> downImage(String srcUrl, List<String> list){
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE); 

		//ArrayList<Callable<Integer>> callers = new ArrayList<Callable<Integer>>();
		for(int i = 0; i < list.size(); i++){
			/*the first method*/
			Thread t = new DownThread(srcUrl, (String)list.get(i));
			pool.execute(t);
			/*
			*/

			/*the second method*/
			/*
			Callable<Integer> t = new DownThread(srcUrl, (String)list.get(i));
			callers.add(t);
			*/
		}
		/*
		try{
			pool.invokeAll(callers);
		}catch(Exception e){
		System.out.println("hh");}
		*/
		pool.shutdown();
				System.out.println("int down:" + downResult.get("testfile"));
		return downResult;
	}		

	/*
	static class DownThread implements Callable{
		String fileName;
		private String url;
		private int length;
		
		public DownThread(String url, String fileName)
		{
			this.fileName = fileName;
			this.url = url;	
		}

		public Object call() throws Exception{
			try{
				URL srcFile = new URL(this.url + this.fileName);
				HttpURLConnection con = (HttpURLConnection) srcFile.openConnection();
				con.setRequestMethod("GET");
				InputStream is = con.getInputStream();
				byte [] bs = new byte[1024];
				int len;
				RandomAccessFile file = new RandomAccessFile(desPath + fileName, "rw");
				while((len = is.read(bs)) != -1){
					file.write(bs, 0, len);
					this.length += len;
					System.out.println("the length is:" + length);
				}
				downResult.put("testfile", new Integer(5));
				System.out.println("test data:" + downResult.get("testfile"));
				downResult.put(fileName, length); 
				file.close();
				is.close();
			}catch(Exception e){
				throw new RuntimeException(e);
			}
			return null;
		}
	}
	*/

	static class DownThread extends Thread{
		private String fileName;
		private String url;
		private int length;
		private byte [] outArray = new byte[100];
		
		public DownThread(String url, String fileName)
		{
			this.fileName = fileName;
			this.url = url;	
		}

		public void run(){
			try{
				URL srcFile = new URL(this.url + this.fileName);
				HttpURLConnection con = (HttpURLConnection) srcFile.openConnection();
				con.setRequestMethod("GET");
				InputStream is = con.getInputStream();
				//byte [] bs = new byte[1024];
				int len;
				//RandomAccessFile file = new RandomAccessFile(desPath + fileName, "rw");
				/*
				while((len = is.read(bs)) != -1){
					//file.write(bs, 0, len);
					is.read(byteArray)
					this.length += len;
					System.out.println("the length is:" + length);
				}
				*/
				is.read(this.outArray);
				downResult.put("testfile", this.outArray);
				System.out.println("test data:" + downResult.get("testfile"));
				//file.close();
				is.close();
			}catch(Exception e){
				throw new RuntimeException(e);
			}		

		}
	}
}
