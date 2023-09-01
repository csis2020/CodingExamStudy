//2023-August-31
//Important: Java does not have a datatype for unsigned integers.
// 1.Get last digit by (input number & 1) -> shift left (reverse) and insert the last digit
// 2. shift right (input number )
// repeat 

//Time Complexity : O(1) , fixed number 32
//Space Complexity : O(1) , only local value
class Solution{
    public int reverseBits(int n){
        
        int count = 32;
        int reverse = 0;
        //input: 0011  -> 0001 -> 0000 -> 0000 -> 0000
        //revese: 0000 -> 0001 -> 0011 -> 0110 -> 1100
        //0000 ^ 0001 , 0010 ^ 0001 , 0110 ^ 0000 , 1100 ^ 0000
        while(count > 0){
            reverse = (reverse << 1) ^ (n & 1);
            n = n >> 1;
            //n = n >>> 1; //fill out the empty position with '0'
            count--;
        }
        return reverse;
    }
}

//Important: Java does not have a datatype for unsigned integers.
//Time complexity : O(1) <-- fixed number : 32
// Space complexity: O(1) <-- only local value

//shift 연산시 빈자리를 '0'으로 채우기:  >>>
/*
public class Solution{
    public int reverseBits(int n){
        
        int mask = 1; // to detect bit on 2^0 position
        int reverse = 0; 
        int position = 31; // bit position from 2^0 to 2^31
        
        while(n != 0){
            reverse += (n & mask) << position;
            n = n >>> 1; // fill out the empty position with '0' 
            position--;
        }
        
        return reverse;
    }
}
*/

/*
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        
        int reverse = 0;
        int mask = 1;
        int count = 0;
        
        while(count < 32){
            reverse = reverse << 1;
            reverse += (n & mask);
            n = n >> 1;
            
            System.out.println("count:"+count+", n:" + n);
            
            count++;
        }
        return reverse;

    }

}
*/