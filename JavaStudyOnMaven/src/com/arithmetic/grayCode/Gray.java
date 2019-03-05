package com.arithmetic.grayCode;

/**
 * Gray Code(格雷码):
 * 
 * 	格雷码(Gray Code, Grey Code), 又叫循环二进制码或反射二进制码, 葛莱码.
 * 
 * 	    典型格雷码是一种具有反射特性和循环特性的单步自补码，它的循环、单步特性消除了随机取数时出现重大误差的可能，它的
 * 	反射、自补特性使得求反非常方便。格雷码属于可靠性编码，是一种错误最小化的编码方式，因为，虽然自然二进制码可以直接由
 * 	数/模转换器转换成模拟信号，但在某些情况，例如从十进制的7转换为8时二进制码的每一位都要变，能使数字电路产生很大的尖峰
 * 	电流脉冲。而格雷码则没有这一缺点，它在相邻位间转换时，只有一位产生变化。它大大地减少了由一个状态到下一个状态时逻辑
 * 	的混淆。由于这种编码相邻的两个码组之间只有一位不同，因而在用于风向的转角位移量 - 数字量的转换中，当风向的转角位移量
 * 	发生微小变化(而可能引起数字量发生变化时，格雷码仅改变一位，这样与其它编码同时改变两位或多位的情况相比更为可靠，即可
 * 	减少出错的可能性。另外由于最大数与最小数之间也仅一个数不同，故通常又叫循环二进制码或反射二进制码。
 * 	    但格雷码不是权重码，每一位码没有确定的大小，不能直接进行比较大小和算术运算，也不能直接转换成液位信号，要经过一
 * 	次码变换，变成自然二进制码,再由上位机读取。解码的方法是用‘0’和采集来的4位格雷码的最高位（第4位）异或，结果保留到4位
 * 	，再将异或的值和下一位（第3位）相异或，结果保留到3位，再将相异或的值和下一位（第2位）异或，结果保留到2位，依次异或，
 * 	直到最低位，依次异或转换后的值（二进制数）就是格雷码转换后自然码的值。
 * 
 *  数学(计算机)描述：
 *  	原码：p[0~n]；格雷码：c[0~n](n∈N)；编码：c=G(p)；解码：p=F(c)；书写时从左向右标号依次减小.
 *  	编码：c=p XOR p[i+1](i∈N,0≤i≤n-1)，c[n]=p[n]；
 *  	解码：p[n]=c[n]，p=c XOR p[i+1](i∈N,0≤i≤n-1).
 * 
 * 	Gray Code是由贝尔实验室的Frank Gray在20世纪40年代提出的并于1880年由法国工程师Jean-Maurice-Emlle Baudot发明的，
 * 	用来在使用PCM（Pulse Code Modulation 脉冲编码调制）方法传送讯号时避免出错，并于1953年3月17日取得美国专利。
 * 	由定义可知，Gray Code的编码方式不是唯一的，这里讨论的是最常用的一种。
 * 
 * 		十进制数 	二进制数 	格雷码
 * 		0 			0000 		0000
 * 		1 			0001 		0001
 * 		2 			0010 		0011
 * 		3 			0011 		0010
 * 		4 			0100 		0110
 * 		5 			0101 		0111
 * 		6 			0110 		0101
 * 		7 			0111 		0100
 * 		8 			1000 		1100
 * 		9 			1001 		1101
 * 		10 			1010 		1111
 * 		11 			1011 		1110
 * 		12 			1100 		1010
 * 		13 			1101 		1011
 * 		14 			1110 		1001
 * 		15 			1111 		1000
 * 
 * 	异或:
 * 		0 ^ 0 = 0
 * 		0 ^ 1 = 1
 * 		1 ^ 0 = 1
 * 		1 ^ 1 = 0
 */
public class Gray {
	
	/**
	 * 编码 (二进制码 -> 格雷码):
	 * 		从最右边一位起，依次将每一位与左边一位异或(XOR)，作为对应格雷码该位的值，最左边一位不变(相当于左边是0).
	 * 
	 * @param binaryCode
	 * @return
	 */
	public static int grayCoding(int binaryCode){
		int grayCode = 0;
		grayCode = binaryCode >>> 1;
		grayCode ^= binaryCode;
		return grayCode;
	}
	
	/**
	 * 解码 (格雷码 -> 二进制码):
	 * 		从左边第二位起，将每位与左边一位解码后的值异或，作为该位解码后的值(最左边一位依然不变).
	 * 
	 * @param grayCode
	 * @return
	 */
	public static int grayUncoding(int grayCode){
		int binaryCode = 0;
		int temp = 0;
		for(int i=32; i>0; i--){
			temp = (grayCode >> (i-1)) & 1;
			binaryCode = (binaryCode << 1) | ((binaryCode & 1) ^ temp);
		}
		return binaryCode;
	}
	
	/**
	 * 生成n位编码的Gray Code的编码串：
	 * 比如 2位的二进制编码生成的编码串：00 01 11 10
	 * 比如 3位的二进制编码生成的编码串：000 001 011 010 110 111 101 100
	 * 
	 * @param length
	 * @return
	 */
	public static String getGrayBinaryStrings(int length){
		if(length <= 0){
			return "";
		}
		String separate = " ";
		StringBuffer str = new StringBuffer("");
		int maxNum = 1;
		for(int i=0; i<length; i++){
			maxNum *= 2;
		}
		//System.out.println(maxNum);
		for(int i = maxNum-1; i >= 0; i--){
			str.append(separate);
			int code = Gray.grayCoding(i);
			for(int j=0; j<length; j++){
				str.append(code & 1);
				code >>>= 1; 
			}
		}
		String strings = str.reverse().substring(0, str.length()-1);
		//System.out.println(strings);
		return strings;
	}
	
	/**
	 * 十进制数 -> 二进制字符串：
	 * 
	 * 		0	->	"0000 0000 0000 0000 0000 0000 0000 0000"
	 * 		1	->	"0000 0000 0000 0000 0000 0000 0000 0001"
	 * 		2	->	"0000 0000 0000 0000 0000 0000 0000 0010"
	 * 		3	->	"0000 0000 0000 0000 0000 0000 0000 0011"
	 * 		4	->	"0000 0000 0000 0000 0000 0000 0000 0100"
	 * 		5	->	"0000 0000 0000 0000 0000 0000 0000 0101"
	 * 		6	->	"0000 0000 0000 0000 0000 0000 0000 0110"
	 * 		7	->	"0000 0000 0000 0000 0000 0000 0000 0111"
	 * 		8	->	"0000 0000 0000 0000 0000 0000 0000 1000"
	 * 		9	->	"0000 0000 0000 0000 0000 0000 0000 1001"
	 * 
	 * @param code
	 * @return
	 */
	public static String toBinaryString(int code){
		StringBuffer str = new StringBuffer("");
		for(int i=0; i<32; i++){
			if(i%4 == 0){
				str.append(" ");
			}
			str.append(code & 1);
			code >>>= 1;
		}
		return str.reverse().substring(0, str.length()-1);
	}
	public static void printBinaryStr(int code){
		System.out.print(toBinaryString(code));
	}
}
