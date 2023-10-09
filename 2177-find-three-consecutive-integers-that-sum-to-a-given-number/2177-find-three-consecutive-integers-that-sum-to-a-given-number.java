class Solution {
    public long[] sumOfThree(long num) {
        
        long remainder = num % 3; 
        if(remainder > 0){
            return new long[0];
        }
        
        long value = num / 3; 
        
        return new long[]{value -1, value, value +1};
    }
}