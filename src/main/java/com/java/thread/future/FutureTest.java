package com.java.thread.future;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * java.util.concurrent.Callable<V>
 *   与Runnable接口相似，不同的是它的call方法有返回值或抛出异常。
 *   - V call() -- 最终返回V对象的异步计算
 *   
 * java.util.concurrent.Future<V>
 *   - V get() -- 等待异步计算结束并返回计算结果，等待期间get方法的调用被阻塞
 *   - V get(long timeout, TimeUnit unit) -- 等待异步计算结束并返回计算结果，等待期间get方法的调用被阻塞直到计算结束或时间到，若时间到则抛出TimeoutException
 *   - boolean cancel(boolean mayInterrupt) -- 取消计算。若计算还没开始则取消并返回True；若计算已经完成或已经被取消了则返回False；若计算正在进行且mayInterrupt为True则取消并返回True。
 *   - boolean isCancelled() -- 若计算任务已被取消则返回True
 *   - boolean isDone() -- 若计算已经完成，无论正常完成、中途取消、发生异常，都返回True；若计算还在进行中则返回False。
 *   
 * java.util.concurrent.FutureTask<V> implements Future<V>, Runnable
 *   是包装器，它将Callable转换成Future和Runnable，方法：
 *     Callable<V> myComputation = ...;
 *     FutureTask<V> task = new FutureTask<V>(myComputation);
 *     Thread t = new Thread(task);
 *     t.start();
 *     ...
 *     V result = task.get(); //  这样等到异步计算结束get方法就会把计算结果返回。
 */
public class FutureTest {

	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		System.out.print("Enter base directory(e.g. /usr/local/jdk5.0/src): ");
//		String directory = in.nextLine();
//		System.out.print("Enter keyword(e.g. volatile): ");
//		String keyword = in.nextLine();
		String directory = "E:/Project/Workspace/JavaStudy/src/com/java/thread/synch/Test";
		String keyword = "final int";
		
		MatchCounter counter = new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		Thread t = new Thread(task);
		t.start();
		
		try{
			System.out.println(task.get() + " matching files.");
		}catch(ExecutionException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}

/**
 * This task counts the files in the given directory and its subdirectories that contain a given keyword.
 */
class MatchCounter implements Callable<Integer>{
	
	private File directory;
	private String keyword;
	private int count;
	
	/**
	 * Constructs a MatchCounter
	 * @param directory the directory in which to start the search.
	 * @param keyword the keyword to look for.
	 */
	public MatchCounter(File directory, String keyword){
		this.directory = directory;
		this.keyword = keyword;
	}

	public Integer call() throws Exception {
		this.count = 0;
		
		try{
			File[] files = this.directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
			
			for(File file : files){
				if(file.isDirectory()){
					MatchCounter counter = new MatchCounter(file, keyword);
					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					
					results.add(task);
					
					Thread t = new Thread(task);
					t.start();
				}else{
					if(this.search(file)){
						this.count++;
					}
				}
			}
			
			for(Future<Integer> result : results){
				try{
					this.count += result.get();
				}catch(ExecutionException e){
					e.printStackTrace();
				}
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		return this.count;
	}
	
	/**
	 * Searches a file for a given keyword.
	 * @param file the file to search.
	 * @return true if the keyword is contained in the file.
	 */
	public boolean search(File file){
		try{
			Scanner in = new Scanner(new FileInputStream(file));
			boolean isFound = false;
			
			//while(!isFound && in.hasNextLine()){
			while(in.hasNextLine()){
				String line = in.nextLine();
				if(line.contains(keyword)){
					isFound = true;
					System.out.printf("%s:%s%n", file.getPath(), line);
				}
			}
			
			in.close();
			return isFound;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
}