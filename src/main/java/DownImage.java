import java.io.FileOutputStream;  
import java.io.InputStream;  
import java.io.OutputStream;  
import java.io.RandomAccessFile;
import java.net.URL;  
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;
import java.util.ArrayList;
import java.util.List;

public class DownImage{
	public static void main(String []args){
		/*
		String fileName = "1.img";
		String addr = "http://127.0.0.1/img/";
		String url = new URL(addr + fileName);	
		HttpURLConnection con = HttpURLConnection(url.openConnection());
		con.setRequestMethod("GET");
		int length = con.getContentLength();
		RadomAccessFile file = new RadomAccessFile("/root/img/" + fileName, "rw");
		file.setLength(length);
		file.close();
		*/
		String srcUrl = "http://127.0.0.1/";
		List<String> list = new ArrayList<String>();
		list.add("one.jpg");
		list.add("two.jpg");
		list.add("three.jpg");
		list.add("four.jpg");
		list.add("five.jpg");
		ExecutorService pool = Executors.newFixedThreadPool(2); 

		for(int i = 0; i < list.size(); i++){
			Thread t = new DownThread(srcUrl, (String)list.get(i));
			pool.execute(t);
		}
		pool.shutdown();
		
		//new DownThread(fileName, url).start();
	}

	static class DownThread extends Thread{
		private String fileName;
		private String url;
		
		public DownThread(String url, String fileName)
		{
			this.fileName = fileName;
			this.url = url;	
		}

		public void run(){
			/** download image**/
			try{
				URL srcFile = new URL(this.url + this.fileName);
				HttpURLConnection con = (HttpURLConnection) srcFile.openConnection();
				con.setRequestMethod("GET");
				InputStream is = con.getInputStream();
				
				byte [] bs = new byte[1024];
				int len;
				RandomAccessFile file = new RandomAccessFile("/Users/img/" + fileName, "rw");
				//OutputStream os = new FileOutputStream(fileName);
				while((len = is.read(bs)) != -1){
					file.write(bs, 0, len);
				}
				file.close();
				is.close();
			}catch(Exception e){
				throw new RuntimeException(e);
			}		

			/** for test **/
			/*
			System.out.println("process " + fileName);
			try {  
			    Thread.sleep(5000);  
			} catch (InterruptedException e) {  
			    e.printStackTrace();  
			}
			System.out.println("processed " + fileName);
			*/
		}
	}
}
