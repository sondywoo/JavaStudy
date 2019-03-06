package com.java.io.stream;

import java.io.FileInputStream;

public class TestAvailable {
	public static void main(String[] args) throws Exception{
		FileInputStream fis = new FileInputStream("E:/Project/Workspace/JavaStudy/src/com/java/io/test.txt");
		
		////////////////////////////////////////////////////////////////////////
		//	永远不可能被阻塞的代码片段
		int bytesAvailable = fis.available();
		if(bytesAvailable > 0){
			byte[] datas = new byte[bytesAvailable];
			fis.read(datas);
			System.out.println("------------> " + bytesAvailable);
			for(int i = 0; i < datas.length; i++){
				System.out.print((char)datas[i]);
			}
		}
		////////////////////////////////////////////////////////////////////////
		
		fis.close();
	}
}
