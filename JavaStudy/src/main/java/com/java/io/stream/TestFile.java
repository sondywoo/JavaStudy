package com.java.io.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/******************************************************************
 * 	java.io包	- 高级读写，速度较慢
 * 	java.nio包	- 低级读写，速度较快
 * ****************************************************************
 * 	java.io包中	- 以 ...Stream 结尾 - 以“字节”为单位读写 - 图片，音乐等大文件	- 不适合中文, 能Copy文件但是不能在屏幕上打印中文
 * 				- 以 ...Reader 结尾 - 以“字符”为单位读写 - 文本				- 适合读中文, 既能Copy文件又能在屏幕上打印中文
 * 					 ...Writer
 * ****************************************************************
 * 
 * 重点掌握的IO类
 * 
 * InputStream	:	FileInputStream		BufferedInputStream		PipedInputStream	ObjectInputStream
 * OutputStream	:	FileOutputStream	BufferedOutputStream	PipedOutputStream	ObjectOutputStream
 * ----------------------------------------------------------------
 * Reader		:	FileReader			BufferedReader			PipedReader			InputStreamReader
 * Writer		:	FileWriter			BufferedWriter			PipedWriter			OutputStreamWriter
 * ----------------------------------------------------------------
 * RandomAccessFile
 * 		setLength();
 * 		seek();
 * ----------------------------------------------------------------
 * File
 * ----------------------------------------------------------------
 * Filter
 ******************************************************************
 */
public class TestFile {
	public static void main(String[] args) throws IOException {
		
		//	1. 创建IO流资源
		FileInputStream fis = new FileInputStream("E:\\Project\\Workspace\\JavaStudy\\src\\com\\java\\io\\test.txt");
		FileOutputStream fos = new FileOutputStream("E:/Project/Workspace/JavaStudy/src/com/java/io/testCopied.txt");
		
		//	2. IO操作
		//	ex.	文件间拷贝内容
		//		1. 低效方法
//		int data = fis.read();
//		for(int i=1; data != -1;){
//			System.out.print((char)data);			//	能Copy文件中的中文字但是不能在屏幕上打印中文
//			fos.write(data);
//			data = fis.read();
////			System.out.println("--> " + i++);	//	对比次数
//		}
		//		2. 高效方法
		byte[] data2 = new byte[1024];
		int size = fis.read(data2);
		for(int i=1; size != -1;){
			for(int j=0; j<size; j++){
				System.out.print((char)data2[j]);
			}
			fos.write(data2, 0, size);
			size = fis.read(data2);
//			System.out.println("--> " + i++);	//	对比次数
		}
		
		//	3. 关闭IO流资源
		fis.close();
		fos.close();
		System.out.println("------------> completed copy.");
	}
}
