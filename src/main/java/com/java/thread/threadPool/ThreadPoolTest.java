package com.java.thread.threadPool;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 使用线程池的理由：
 *   - 构建一个新的线程的代价都是有些高的，如果你的程序创建了大量生存期很短的线程，那就使用线程池。
 *   - 减少并发线程的数量。如果你的算法会创建许多线程，那就应该使用一个线程数“固定”的线程池来限制并发线程的数量。
 *   
 * java.util.concurrent.Executors 执行器类的一些静态工厂方法介绍：
 *   - newCachedThreadPool				在需要时创建新线程；空闲线程会被保留60秒；线程数量没有限制
 *   - newFixedThreadPool				池中包含固定数量的线程；空闲线程会被一直保留；若提交任务数>空闲线程数则得不到服务的任务将被置于队列中，当其他任务完成后它们就能运行了
 *   - newSingleThreadExecutor			只有1个线程的线程池，这个线程会顺序执行每个递交上来的任务
 *   - newScheduledThreadPool			为预定执行而构建的固定数量线程的线程池
 *   - newSingleThreadScheduledExecutor	为预定执行而构建的单线程的线程池
 *   - newWorkStealingPool				内部构建 ForkJoinPool，利用 work-stealing 算法并行处理任务，不保证顺序。
 */
public class ThreadPoolTest {

	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		System.out.print("Enter base directory(e.g. /usr/local/jdk5.0/src): ");
//		String directory = in.nextLine();
//		System.out.print("Enter keyword(e.g. volatile): ");
//		String keyword = in.nextLine();
		String directory = "/Users/sondywoo/sondy/project/wsJava/JavaStudy/src/main/java/com/java/thread/synch/Test";
		String keyword = "final int";

		// be different with FutureTest.java
		//	1. 获得连接池
		ExecutorService pool = Executors.newCachedThreadPool();
		
		//	2. 向连接池提交任务，并保存好返回的Future对象以备不时之用
//		MatchCounter counter = new MatchCounter(new File(directory), keyword);
//		FutureTask<Integer> task = new FutureTask<Integer>(counter);
		MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
		Future<Integer> result = pool.submit(counter);
		
		try{
			System.out.println(result.get() + " matching files.");
		}catch(ExecutionException e){
			e.printStackTrace();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		
		//	3. 关闭连接池
		pool.shutdown();
		/**
		 * 区别：
		 *   - void shutdown() -- 关闭执行器，执行器不再接受新任务；当所有任务都完成后，池中的线程就死亡。
		 *   - List<Runnable> shutdownNow() -- 关闭执行器，取消所有还没有开始的任务并试图中断正在运行的线程，返回还没有完成的所有任务。
		 */
		int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize(); // 返回该执行器生命周期内的最大线程数。
		System.out.println("largest pool size = " + largestPoolSize);
	}
}

/**
 * This task counts the files in the given directory and its subdirectories that contain a given keyword.
 */
class MatchCounter implements Callable<Integer>{

	private ExecutorService pool;
	
	private File directory;
	private String keyword;
	private int count;
	
	/**
	 * Constructs a MatchCounter
	 * @param directory the directory in which to start the search.
	 * @param keyword the keyword to look for.
	 * @param pool the thread pool for submitting subtasks.
	 */
	public MatchCounter(File directory, String keyword, ExecutorService pool){
		this.directory = directory;
		this.keyword = keyword;
		this.pool = pool;
	}
	
	public Integer call() throws Exception {
		this.count = 0;
		
		try{
			File[] files = this.directory.listFiles();
			ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
			
			for(File file : files){
				if(file.isDirectory()){
					// be different with FutureTest.java
//					MatchCounter counter = new MatchCounter(file, keyword);
//					FutureTask<Integer> task = new FutureTask<Integer>(counter);
					MatchCounter counter = new MatchCounter(file, keyword, pool);
					Future<Integer> result = pool.submit(counter);
					
					results.add(result);

//					Thread t = new Thread(task);
//					t.start();
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
			
			while(!isFound && in.hasNextLine()){
				String line = in.nextLine();
				if(line.contains(keyword)){
					isFound = true;
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