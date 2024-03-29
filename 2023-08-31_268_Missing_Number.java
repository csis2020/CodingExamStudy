
//2023.August.31
//limitation: nums is null or length is 0 ???
//idea: Using bit manipulation
// using XOR -> clear same number :  ex) 0101 ^ 0101 -> 0000
// ex) nums = [3,0,1] => n:3 => (0 ^ 1 ^ 2 ^ 3) ^ ( 3 ^ 0 ^ 1) => clear all existed numbers, and missing number is the result. 

//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution{
    public int missingNumber(int[] nums){
        if(nums == null || nums.length <= 0){
            return 0;
        }

        int remainNumber = nums.length;

        //example:[9,6,4,2,3,5,7,0,1]
        // 9 ^ 0 ^ 9 ^ 1 ^ 6 ^ 2 ^ 4 ^ 3 ^ 2 ^ 4 ^ 3 ^5 ^ 5 ^ 6 ^ 7 ^ 7 ^ 0 ^ 8 ^ 1
        // =>   8
        for(int i = 0; i < nums.length ; i++){
            remainNumber ^= i ^ nums[i];
        }

        return remainNumber;
    }
}

//2022.08.21

// limitation : nums is null?? size is 0?
// Input: nums = [3,0,1]  -> Output: 2
// Input: nums = [0,1] -> Output: 2
// Input: nums = [9,6,4,2,3,5,7,0,1] -> Output: 8

//Time Complexity: O(n) , Space complexity: O(1)
/*
class Solution{
    public int missingNumber(int[] nums){
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int remainNumber = nums.length; // the number of nums.length should be checked. 
        for(int i = 0; i< nums.length; i++){
            remainNumber ^= i ^ nums[i]; //to cancel each number from '0' and 'nums.length'
        }
        
        //3:       011
        //i=0:     000
        //nums[0]: 011
        //i=1:     001
        //nums[1]: 000
        //i=2:     010
        //nums[2]: 001
        // => 모두 canceling 되고  010 만 남음.
        
        return remainNumber;
    }
}
*/

/*
// Time complexity : O(n), Space complexity : O(1)
class Solution {
    public int missingNumber(int[] nums) {
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int sum = (nums.length + 1) * nums.length / 2; // sum = (n+1)n/2  , n is length
        
        for(int i = 0 ; i < nums.length; i++){
            sum -= nums[i];
        }
        
        return sum; 
        
    }
}
*/