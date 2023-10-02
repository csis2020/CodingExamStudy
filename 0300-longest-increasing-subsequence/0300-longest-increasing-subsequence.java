//2023-10-01
//Idea : make the array to save the number of increasing sequence until current number.
//  During for loop, compare number from  0 to (i-1) to find smaller number. 
//  if the increasing sequence is bigger, update
//
//      
class Solution{
    public int lengthOfLIS(int[] nums){
        if(nums == null || nums.length <=0){
            return 0;
        }
        
        int[] incres = new int[nums.length];
        int longest = 1;
        Arrays.fill(incres,1);
        //      [10, 9, 2, 5, 3, 7,101,18]
        //incres: 1, 1, 1, 
        //longest:1, 1, 1
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    incres[i] = Math.max(incres[i], incres[j] + 1);
                }
            }
            longest = Math.max(longest, incres[i]);
        }
        return longest;
    }
}


//2023. September.2
//Time Complexity: O(n^2)
//Space Complexity: O(n)
/*
class Solution{
    public int lengthOfLIS(int[] nums){

        if(nums == null || nums.length <= 0){
            return 0;
        }

        int[] maxSub = new int[nums.length];
        Arrays.fill(maxSub, 1);
        int longest = 1;
        //example: [10,9,2,5,3,7,101,18]
        //maxSub:  [1, 1,1,2,2,3,4,4]
        //longest:  1,1,,1,2,2,3,4,4  -> 4
        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    maxSub[i] = Math.max(maxSub[i], maxSub[j] + 1);
                }
            }
            longest = Math.max(longest, maxSub[i]);
        }

        return longest;
    }
}
*/
//limitation : if nums is null and size is zero, return 0 ??
//Input: nums = [10,9,2,5,3,7,101,18] ->  Output: 4
//Input: nums = [10, 9, 2, 5, 3, 7, 101, 18] -> output: 4
//Input: nums = [1] -> output: 1
//Input: nums = [5, 4,3] -> output: 1
//2022-12-02
//Time complexity: O(N^2)
//Space Complexity: O(N)
/*
class Solution{
    public int lengthOfLIS(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] increase = new int[nums.length];
        int longest = 1;
        increase[0] = 1;
        for(int i = 1; i < nums.length; i++){
            increase[i] = 1; //initialize
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    increase[i] = Math.max(increase[i], increase[j]+1);
                }
            }
            longest = Math.max(longest, increase[i]);
        }

        return longest;
    }
}
*/

//Time Complexity: O(n^2), Space Complexity: O(n)
/*
class Solution {
    public int lengthOfLIS(int[] nums) {
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int[] numOfSubseq = new int[nums.length];
        int maxNum = 1; //max size of subsenquence 
        numOfSubseq[0] = 1;
        
        //i:    0  1  2  3   4   5     6   7 
        //in :  10 9  2  5   3   7    101  18
        //max:   1 1  1  2   2   3     4    4
        for(int i = 1; i < nums.length; i++){
            numOfSubseq[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    //update the max Size of subsequence until nums[i]
                    numOfSubseq[i] = Math.max(numOfSubseq[i], numOfSubseq[j] + 1);
                }
            }
            maxNum = Math.max(maxNum, numOfSubseq[i]);
        }
        
        return maxNum;
    }
}
*/