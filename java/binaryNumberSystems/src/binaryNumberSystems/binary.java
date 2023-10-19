package binaryNumberSystems;

import java.io.IOException;
import java.util.Scanner;

public class binary {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println(3009 + " " + 0b101111000001 + " " + 0xBC1);
		System.out.println(3555 + " " + 0b110111100011 + " " + 0xDE3); 
		System.out.println(2996 + " " + 0b101110110100 + " " + 0xBB4); 
		System.out.println(4050 + " " + 0b111111010010 + " " + 0xFD2); 
		System.out.println(3120 + " " + 0b110000110000 + " " + 0xC30); 
		System.out.println(0x2AB + " " + 683 + " " + 0b1010101011); 
		System.out.println(0x4BA + " " + 1210 + " " + 0b10010111010); 
		System.out.println(0x27E + " " + 638 + " " + 0b1001111110); 
		System.out.println(0x35B + " " + 859 + " " + 0b1101011011); 
		System.out.println(0x2B7 + " " + 695 + " " + 0b1010110111); 
		System.out.println(0b1101100000 + " " + 0x360 + " " + 864); 
		System.out.println(0b1111011101 + " " + 0x3DD + " " + 989); 
		System.out.println(0b1111001111 + " " + 0x3CF + " " + 975); 
		System.out.println(0b1110111011 + " " + 0x3BB + " " + 955); 
		System.out.println(0b1001111001 + " " + 0x279 + " " + 633); 
		System.out.println(0b101111000001 + " + " + 0b1010101011 + " = " + (0b101111000001 + 0b1010101011)); 
		System.out.println(0b110111100011 + " + " + 0b10010111010 + " = " + (0b110111100011 + 0b10010111010)); 
		
		
		
		Scanner in = new Scanner(System.in);
        System.out.println("Input a decimal number: ");
        int K = in.nextInt();
        convertorDecimalBinary(K);
        convertorDecimalHex(K);
        in.close();
        //
	}
	public static void convertorDecimalBinary(int number) {    
        int a;     
        String temp = "";    
        while(number !=0)
        {  
        	a = number%2;  
        	temp = a + temp;  
        	number = number/2;     
        }
        System.out.println(temp);  
	}   
	public static void convertorDecimalOctal(int number) {    
        int a;     
        String temp = "";    
        while(number >= 1)
        {  
        	a = number%8;  
        	temp = a + temp;  
        	number = number/8;     
        }
        System.out.println(temp);  
	}  
	public static void convertorDecimalHex(int number) {    
        int a;     
        String temp = "";    
        while(number >= 1)
        {  
        	a = number%16;  
        	if(a<=9)
        		temp = a + temp;  
        	else if(a==10)
        		temp = "A"+temp;
        	else if (a==11)
        		temp = "B"+temp;
        	else if (a==12)
        		temp = "C"+temp;
        	else if (a==13)
        		temp = "D"+temp;
        	else if (a==14)
        		temp = "E"+temp;
        	else if (a==15)
        		temp = "F"+temp;
        	number = number/16;     
        }
        System.out.println(temp);  
	}  
}
//
//  52 С 53 А test
//
//  3009 1    3009 1        3555 1    3555 3      2996 0    2996 4      4050 0    4050 2      3120 0    3120 0                      
//  1504 0    188  12       1777 1    222  14     1498 0    187  11     2025 1    253  13     1560 0    195  3                      
//  752  0    11            888  0    13          749  1    11          1012 0    15          780  0    12     
//  376  0                  444  0                374  0                506  0                390  0  
//  188  0    BC1           222  0    DE3         187  1    BB4         253  1    FD2         195  1    C30  
//  94   0                  111  1                93   1                126  0                97   1
//  47   1                  55   1                46   0                63   1                48   0            
//  23   1                  27   1                23   1                31   1                24   0        
//  11   1                  13   1                11   1                15   1                12   0               
//  5    1                  6    0                5    1                7    1                6    0                  
//  2    0                  3    1                2    0                3    1                3    1              
//  1                       1                     1                     1                     1             
//                                                                                                       
//  101111000001            110111100011          101110110100          111111010010          110000110000                                              
//                                        
//  2AB 2*16**2+10*16**1+11*16**0      683     1010101011                             
//  4BA 4*16**2+11*16**1+10*16**0      1210    10010111010                   
//  27E 2*16**2+7*16*1+14*16**0        638     1001111110
//  35B 3*16**2+5*16**1+11*16**0       859     1101011011
//  2B7 2*16**2+11*16**1+7*16**0       695     1010110111
// 
//
//  0011 0110 0000   360   864   2**5+2**6+2**8+2**9
//  0011 1101 1101   3DD   989
//  0011 1100 1111   3CF   975
//  0011 1011 1011   3BB   955
//  0010 0111 1001   279   633
//
//  101111000001     110111100011                                                                                                   
//    1010101011      10010111010                                                                                 
//  111001101100    1001010011101                                                                        
//    <<<     <<     << < <<   <                                                                  
//                                                                                             
//  -10314   10314  0010100001001010     -22   22    0000000000010110    -12345678   12345678   0000 0000 1011 1100 0110 0001 0100 1110                                                   
//                  1101011110110101                 1111111111101001                           1111 1111 0100 0011 1001 1110 1011 0001                                      
//                  1101011110110110                 1111111111101010                           1111 1111 0100 0011 1001 1110 1011 0010                        
//                                                                              
