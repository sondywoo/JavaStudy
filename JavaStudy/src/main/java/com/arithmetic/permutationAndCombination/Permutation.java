package com.arithmetic.permutationAndCombination;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * M个字母，每个字母出现N次，求全排列数 = ((M * N)!) / ((N!)的M次方) 
 * 
 * @author Sondy Woo
 */
public class Permutation {
	private static Set<String> permutateSets;
	private static int times;
	private static int[] flagArray;
	
	public static int getCountOfPermutation(String data){
		int count = 0;
		countPermutateNums(data);
		count = permutateSets.size();
		return count;
	}
	public static void print(){
		System.out.println("+++++++++++++++++++++++++++++++");
		Iterator iterator = permutateSets.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		System.out.println("+++++++++++++++++++++++++++++++");
	}
	private static void countPermutateNums(String data){
		permutateSets = new HashSet<String>();
		char[] dataArray = data.toCharArray();
		
		for(int i=0; i<dataArray.length; i++)
			System.out.print(dataArray[i]);
		System.out.println();
		
		times = dataArray.length;
		int dataSize = times;
		flagArray = new int[dataSize];
		char[] tempDataArray = new char[dataSize];
		
		circleInvoke(dataSize, dataArray, tempDataArray);
	}
	private static void circleInvoke(int dataSize, char[] dataArray, char[] tempDataArray){
//		System.out.println("--------------"+times);
		if(times > 0){
			 for(int i = 0; i < dataSize; i++){
				 if(flagArray[i] == 1){
					 continue;
				 }
				 flagArray[i] = 1;
				 tempDataArray[dataSize - times] = dataArray[i];
				 times--;
				 circleInvoke(dataSize, dataArray, tempDataArray);
				 flagArray[i] = 0;
				 times++;
			 }
		}else{
			String targetString = "";
			for(int i = 0; i < dataSize; i++){
				targetString += tempDataArray[i];
			}
			permutateSets.add(targetString);
		}
	}
	
	public static void main(String[] args){
		int count = getCountOfPermutation("112233344");
		System.out.println("Total: " + count);
		print();
		System.out.println("Total: " + count);
	}
}
