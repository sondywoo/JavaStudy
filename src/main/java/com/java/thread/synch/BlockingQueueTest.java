package com.java.thread.synch;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 4. 加锁机制3. - 加锁的关键字搜索系统 - 阻塞队列 java.util.concurrent.BlockingQueue
 * 
 * 	   关键字搜索系统 1.
 *     程序在一个指定的目录及它的所有子目录下搜索所有文件，打印出包含指定关键字的文件列表。
 *     生产者线程枚举在所有子目录下的所有文件，并把它们放到一个阻塞队列中。
 *     同时还启动了大量的搜索线程，每个搜索线程从队列中取出一个文件，打开它，打印出包含关键字的所有行，然后取出下一个文件。
 * 这里我们使用了一个小技巧来在工作结束后终止线程。为了发出完成信号，生产者线程在所有文件都枚举完毕的时候再把一个虚拟对象放
 * 入队列。当搜索线程取到这个虚拟对象时，就将其放回并终止。
 * 
 * 3种机制来保护代码块不受并行访问的干扰：
 *     1. 旧版本的Java使用Synchronized关键字
 *     2. JDK 5.0引进了ReentrantLock类
 *     3. 阻塞队列 java.util.concurrent.BlockingQueue，目前最常用
 *     
 * API: 
 * java.util.concurrent包提供了4种 阻塞队列 的实现：
 * 	 - ArrayBlockingQueue(int capacity)
 * 	   ArrayBlockintQueue(int capacity, boolean fair)
 * 	   用循环数组实现，有容量限制
 *     构建一个有指定容量和公平性设置的阻塞队列
 *   - LinkedBlockingQueue() 			 	-- 用链表实现，无容量限制
 *     LinkedBlockingQueue(int capacity) 	-- 用链表实现，有容量限制
 *   - PriorityBlockingQueue() 				-- 用堆实现，有容量限制用默认值 11
 *     PriorityBlockingQueue(int capacity) 	-- 用堆实现，有容量限制
 *     PriorityBlockingQueue(int capacity, Comparator<? super E> comparator) -- 用堆实现，有容量限制
 *     带优先级的队列，元素按优先级顺序被移除
 *     capacity 	- 优先级队列容量的初始值，默认值11.
 *     comparator 	- 用来比较元素的比较器。如果没有指定，则元素必须实现 Comparable 接口。
 *   - DelayQueue()
 *   
 * java.util.concurrent.BlockingQueue 阻塞队列的主要操作：
 *   - boolean add(E o)		添加一个元素并返回True				若队列满，则抛异常IllegalStateException
 *   - E remove()			移除并返回队列头部的元素				若队列空，则抛异常NoSuchElementException
 *   - E element()			返回队列头部的元素					若队列空，则抛异常NoSuchElementException
 *   
 *   - boolean offer(E o)	添加一个元素并返回True				若队列满，则返回False
 *     boolean offer(E o, long timeout, TimeUnit unit)
 *     在timeout结束之前添加一个元素并返回True，否则，到达超时时则返回False
 *   - E poll()				移除并返回队列头部的元素				若队列空，则返回NULL
 *     E poll(long timeout, TimeUnit unit)
 *     在timeout结束之前移除并返回队列头部的元素，否则，到达超时时则返回NULL
 *   - E peek()				返回队列头部的元素					若队列空，则返回NULL
 *   
 *   - void put(E o)		添加一个元素						若队列满，则阻塞
 *   - E take()				移除并返回队列头部的元素				若队列空，则阻塞
 */
public class BlockingQueueTest {
	public static void main(String[] args){
//		Scanner in = new Scanner(System.in);
//		System.out.print("Enter base directory(e.g. /usr/local/jdk5.0/src): ");
//		String directory = in.nextLine();
//		System.out.print("Enter keyword(e.g. volatile): ");
//		String keyword = in.nextLine();
		String directory = "E:/Project/Workspace/JavaStudy/src/com/java/thread/synch/Test";
		String keyword = "final int";
		
		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 100;
		
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
		
		FileEnumerationTask enumerator = new FileEnumerationTask(queue, new File(directory));
		new Thread(enumerator).start();
		for(int i = 1; i <= SEARCH_THREADS; i++){
			new Thread(new SearchTask(queue, keyword)).start();
		}
	}
}

/**
 * 生产者线程
 * This task enumerates all files in a directory and its subdirectories.
 */
class FileEnumerationTask implements Runnable{
	
	//	标志结束的虚拟对象
	public static File DUMMY = new File("");
	
	private BlockingQueue<File> queue;
	private File startingDirectory;
	
	/**
	 * Constructs a FileEnumerationTask.
	 * @param queue the blocking queue to which the enumerated files are added.
	 * @param startingDirectory the directory in which to start the enumeration.
	 */
	public FileEnumerationTask(BlockingQueue<File> queue, File startingDirectory){
		this.queue = queue;
		this.startingDirectory = startingDirectory;
	}
	
	/**
	 * Recursively enumerates all files in a given directory and its subdirectories.
	 * @param directory the directory in which to start
	 */
	public void enumerate(File directory) throws InterruptedException{
		File[] files = directory.listFiles();
		for(File file : files){
			if(file.isDirectory()){
				enumerate(file);
			}else{
				queue.put(file);
			}
		}
	}

	public void run() {
		try {
			enumerate(startingDirectory);
			queue.put(DUMMY);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

/**
 * 搜索线程
 * This task searches files for a given keyword.
 */
class SearchTask implements Runnable{
	
	private BlockingQueue<File> queue;
	private String keyword;
	
	/**
	 * Construct a SearchTask.
	 * @param queue the queue from which to take files.
	 * @param keyword the keyword to look for.
	 */
	public SearchTask(BlockingQueue<File> queue, String keyword){
		this.queue = queue;
		this.keyword = keyword;
	}
	
	/**
	 * Searches a file for a given keyword and prints all matching lines.
	 * @param file the file to search.
	 */
	public void search(File file)throws IOException{
		Scanner in = new Scanner(new FileInputStream(file));
		int lineNumber = 0;
		
		while(in.hasNextLine()){
			lineNumber++;
			String line = in.nextLine();
			if(line.contains(keyword)){
				System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
			}
		}
		in.close();
	}

	public void run() {
		try{
			boolean done = false;
			while(!done){
				File file = queue.take();
				if(file == FileEnumerationTask.DUMMY){
					queue.put(file);
					done = true;
				}else{
					search(file);
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}