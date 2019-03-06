package com.java.io.stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**************************************************************
 * PipedInputStream/PipedOutputStream 特点： 
 * 1. PipedInputStream/PipedOutputStream 主要用于网络传输
 * 2. 同一根管道中可以有N个写入线程，可以有M个读取线程
 * 3. 如果把关闭资源的语句写在线程里，无论是哪个写入线程还是读取线程关闭了管口，管道中其他所有Input线程就读不了，所有
 * 		Output线程也写不了
 ************************************************************** 
 */
public class TestPiped {
	public static void main(String[] args) throws Exception {
		//	建立管道方法
		//	方法1.
//		PipedInputStream pis = new PipedInputStream();
//		PipedOutputStream pos = new PipedOutputStream(pis);
		//	方法2.
//		PipedInputStream pis = new PipedInputStream();
//		PipedOutputStream pos = new PipedOutputStream();
//		pos.connect(pis);
		//	方法3.
		PipedInputStream pis = new PipedInputStream();
		PipedOutputStream pos = new PipedOutputStream();
		pis.connect(pos);
		
		OutputThread ot = new OutputThread(pos);
		OutputThread2 ot2 = new OutputThread2(pos);
		InputThread it = new InputThread(pis);
		InputThread it2 = new InputThread(pis);
		ot.start();
		ot2.start();
		it.start();
		it2.start();
		Thread.sleep(3000);
		System.out.println("------- CLOSE main pis");
		pis.close();
		System.out.println("------- CLOSE main pos");
		pos.close();
		
//		//	创建第 1 根管道
//		PipedInputStream pis1 = new PipedInputStream();			//	管道的数据出口
//		PipedOutputStream pos1 = new PipedOutputStream(pis1);	//	管道的数据入口
//		//	创建第 2 根管道
//		PipedInputStream pis2 = new PipedInputStream();			//	管道的数据出口
//		PipedOutputStream pos2 = new PipedOutputStream(pis2);	//	管道的数据入口
//		OutputThread ot1 = new OutputThread(pos1);
//		OutputThread2 ot2 = new OutputThread2(pos2);
//		InputThread it1 = new InputThread(pis1);
//		InputThread it2 = new InputThread(pis2);
//		ot1.start();
//		ot2.start();
//		it1.start();
//		it2.start();
	}
}

class OutputThread extends Thread{	//	管道写入线程1, 向管道中写入数字
	PipedOutputStream pos;
	public OutputThread(PipedOutputStream pos){
		this.pos = pos;
	}
	public void run(){
		try{
//			pos.write(63);			//	向管道中写入数据
			
			//	包装PipedOutputStream流，当然也可以直接操作PipedOutputStream，只是效率较低
			BufferedOutputStream bos = new BufferedOutputStream(pos);
			DataOutputStream dos = new DataOutputStream(bos);
			for(int i = 0; i < 90; i++){
				dos.writeInt(i);
				dos.flush();
				sleep(10);
			}
//			System.out.println("CLOSE OutputThread-1\n");
//			dos.close();	//	关闭资源的语句不能写在线程里
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
class OutputThread2 extends Thread{	//	管道写入线程2, 向管道中写入大数字
	PipedOutputStream pos;
	public OutputThread2(PipedOutputStream pos){
		this.pos = pos;
	}
	public void run(){
		try{
			//	包装PipedOutputStream流，当然也可以直接操作PipedOutputStream，只是效率较低
			BufferedOutputStream bos = new BufferedOutputStream(pos);
			DataOutputStream dos = new DataOutputStream(bos);
			for(int i = 0; i < 110; i++){
				dos.writeInt(i+10000);
				dos.flush();
				sleep(10);
			}
//			System.out.println("CLOSE OutputThread-2\n");
//			dos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
class InputThread extends Thread{	//	管道读取线程
	PipedInputStream pis;
	private int count;
	public InputThread(PipedInputStream pis){
		this.pis = pis;
		count = 0;
	}
	public void run(){
		try{
//			System.out.println(pis.read());		//	读取管道中的数据

			//	包装PipedInputStream流，当然也可以直接操作PipedInputStream，只是效率较低
			BufferedInputStream bis = new BufferedInputStream(pis);
			DataInputStream dis = new DataInputStream(bis);
			for(int i = 0; i < 100; i++){
				count++;
				System.out.println(this.getName() + "\t--> " + dis.readInt());
				sleep(10);
			}
			System.out.println(this.getName() + " --> " + count);
//			sleep(10000);
//			System.out.println("CLOSE Input" + this.getName());
//			dis.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}