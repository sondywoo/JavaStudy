package com.arithmetic.practice;

class Solution {
    public String convert(String s, int numRows) {
        if(numRows <= 1) return s;
        int l = s.length();
        StringBuffer sb = new StringBuffer("");
        int elemPerGroup = 2 * (numRows - 1);
        int pos = -1;

        for(int j=1; j<=numRows; j++){
            for(int i=1; i<=(l-1)/elemPerGroup+1; i++){
                pos = elemPerGroup * (i-1) + j;
                if(pos <= l) sb.append(s.charAt(pos-1));
                if(j!=1 && j!=numRows){
                    pos = elemPerGroup * i - (j-2);
                    if(pos <= l) sb.append(s.charAt(pos-1));
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "1234";
        System.out.println(s.charAt(1));
    }
}
