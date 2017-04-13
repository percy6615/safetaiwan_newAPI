package safetaiwan_CallBack;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import safetaiwan_CommTools.CommonTools;
import safetaiwan_messageObject.DisasterNotification;

public class DownloadImageFile implements Runnable {
	// CallBackParser call;
	String fileName;
	int downloaded;
	List<DisasterNotification> image;
	int size;
	static int MAX_BUFFER_SIZE = 1024;

	DownloadImageFile(List<DisasterNotification> image) {
		// this.call = call;
		this.image = image;
	}

	public static void main(String[] args) {
		
		String[] urlString = {
				"http://link.safetaiwan.tw/mobile_app/report/pictures/20170413121213_357220077438888.jpg",
				"http://link.safetaiwan.tw/mobile_app/report/pictures/20170413104315_352738080071540.jpg" };
		URL u = null;
		
		int i = 0;
		while (i < urlString.length) {
			try {
				int size = -1;
				int downloaded = 0;
				u = new URL(urlString[i]);
				HttpURLConnection connection = (HttpURLConnection) u.openConnection();
				connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
				connection.connect();
				// Make sure response code is in the 200 range.
				if (connection.getResponseCode() / 100 != 2) {
					System.out.println(connection.getResponseCode());
					// error();
				} // Check for valid content length.
				int contentLength = connection.getContentLength();
				if (contentLength < 1) {
					// error();
				}
				if (size == -1) {
					size = contentLength;
				}
				RandomAccessFile file = new RandomAccessFile(getFileNameSavePath(u), "rw");
				file.seek(downloaded);
				InputStream stream = connection.getInputStream();
				while (true) {
					/*
					 * Size buffer according to how much of the file is left to
					 * download.
					 */
					byte buffer[];
					if (size - downloaded > MAX_BUFFER_SIZE) {
						buffer = new byte[MAX_BUFFER_SIZE];
					} else {
						buffer = new byte[size - downloaded];
					}

					// Read from server into buffer.
					int read = stream.read(buffer);
					if (read == -1)
						break;

					// Write buffer to file.
					file.write(buffer, 0, read);
					downloaded += read;
				}
				file.close();
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}

	@Override
	public void run() {
		int i = 0;
		while (i < image.size()) {
			downloaded = 0;
			size = -1;
			String url = image.get(i).getImgURL();
			if(url==null || url ==""){
				i++;
				continue;
			}
			URL u = null;
			try {
				u = new URL(url);
				HttpURLConnection connection = (HttpURLConnection) u.openConnection();
				connection.setRequestProperty("Range", "bytes=" + downloaded + "-");
				connection.connect();
				// Make sure response code is in the 200 range.
				if (connection.getResponseCode() / 100 != 2) {
					System.out.println(connection.getResponseCode());
					// error();
				}

				// Check for valid content length.
				int contentLength = connection.getContentLength();
				if (contentLength < 1) {
					// error();
				}
				if (size == -1) {
					size = contentLength;
				}
				RandomAccessFile file = new RandomAccessFile(getFileNameSavePath(u), "rw");
				file.seek(downloaded);
				InputStream stream = connection.getInputStream();
				while (true) {
					/*
					 * Size buffer according to how much of the file is left to
					 * download.
					 */
					byte buffer[];
					if (size - downloaded > MAX_BUFFER_SIZE) {
						buffer = new byte[MAX_BUFFER_SIZE];
					} else {
						buffer = new byte[size - downloaded];
					}

					// Read from server into buffer.
					int read = stream.read(buffer);
					if (read == -1)
						break;

					// Write buffer to file.
					file.write(buffer, 0, read);
					downloaded += read;
				}
				file.close();
				stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}
	}

	private static String getFileNameSavePath(URL url) {
		String fileName = url.getFile();
		String path = CommonTools.APPLocation()+ "/resources/img/" +fileName.substring(fileName.lastIndexOf('/') + 1);
		return path;
	}
}
