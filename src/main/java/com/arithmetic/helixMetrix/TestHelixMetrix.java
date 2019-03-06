package com.arithmetic.helixMetrix;

public class TestHelixMetrix {

	/**
	 * @input: N
	 * @output: metrix like (such as N = 4)
	 * 		1	2	3	4
	 * 		12	13	14	5
	 * 		11	16	15	6
	 * 		10	9	8	7
	 */
	public static void main(String[] args) {
		int n = 10;
		
		Integer[][] A = getHelixMetrix(n);
		printMetrix(A);
	}
	
	//	print the given metrix.
	public static void printMetrix(Integer[][] a) {
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j < a[i].length; j++){
				System.out.print("\t"+a[i][j]);
			}
			System.out.println();
		}
	}

	// Return a n*n helix metrix.
	public static Integer[][] getHelixMetrix(int n) {
		int flag = 1;
		int a = 0;
		int b = 0;
		int fa = 1;
		int fb = 1;
		
		Integer[][] A = new Integer[n][n];
		
		// Initialize values of the metrix.
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				A[i][j] = 0;
			}
		}
		
		// Calculate all values in the metrix.
		for(int i = 1; i <= n*n; i++){
			A[a][b] = i;
			if(flag == 1){
				if(b+fb >=0 && b+fb < n && A[a][b+fb] == 0){
					b += fb;
				}else{
					// if it arrive at the borderline, change the direction of increment.
					
					flag = 0;
					fb *= -1;
					a += fa;
				}
			}else{
				if(a+fa >= 0 && a+fa < n && A[a+fa][b] == 0){
					a += fa;
				}else{
					// if it arrive at the borderline, change the direction of increment.
					
					flag = 1;
					fa *= -1;
					b += fb;
				}
			}
		}
		return A;
	}
}
