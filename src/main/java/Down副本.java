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
import java.util.Map;
import java.util.Hashtable;

class Down{
	public static String desPath = "/Users/liuheng/img/";
	public static int THREAD_POOL_SIZE = 5;
	public static void downImage(String srcUrl, List<String> list){
		ExecutorService pool = Executors.newFixedThreadPool(THREAD_POOL_SIZE); 
		for(int i = 0; i < list.size(); i++){
			Thread t = new DownThread(srcUrl, (String)list.get(i));
			pool.execute(t);
		}
		pool.shutdown();
	}		

	static class DownThread extends Thread{
		private String fileName;
		private String url;
		private int length;
		
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
				byte [] bs = new byte[1024];
				int len;
				RandomAccessFile file = new RandomAccessFile(desPath + fileName, "rw");
				while((len = is.read(bs)) != -1){
					file.write(bs, 0, len);
					this.length += len;
				}
				file.close();
				is.close();
			}catch(Exception e){
				throw new RuntimeException(e);
			}		
		}
	}
}
