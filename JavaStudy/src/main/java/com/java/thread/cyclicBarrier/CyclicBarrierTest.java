package com.java.thread.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier类实现了一个称为障栅(barrier)的集合点。
 * 
 * 适用：
 *     多个线程合作完成某项任务。
 *     当一个线程完成了它的那部分任务后，我们让它运行到障栅处。
 *     一旦所有线程都到达了这个障栅，障栅就撤销，线程就可以继续运行。
 */
public class CyclicBarrierTest {
	public static void main(String[] args) {
		int nThreads = 10;
		Solver solver = new Solver(nThreads);
		solver.mergeWork();
	}
}

class Solver{
	private int nThreads;
	private CyclicBarrier barrier;
	
	public Solver(int nThreads){
		this.nThreads = nThreads;
//		barrier = new CyclicBarrier(nThreads);
		barrier = new CyclicBarrier(nThreads, new BarrierAction());
	}
	
	public void mergeWork(){
		for(int i = 1; i <= nThreads; i++){
			new Thread(new UnitThread(i)).start();
		}
		System.out.println("working in Solver");
	}

	class UnitThread implements Runnable{
		private int threadNo;
		public UnitThread(int threadNo){
			this.threadNo = threadNo;
		}

		public void run() {
			doWork();
			System.out.println("Thread " + threadNo + ": worked.");
			try {
				barrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		public void doWork(){
//			System.out.println("Thread " + threadNo + ": working...");
		}
	}
}

class BarrierAction implements Runnable{

	public void run() {
		System.out.println("working after co-operation through threads.");
	}
}