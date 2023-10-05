
//2023-10-04
//idea : window size = k, keep moving and find max average 
// [example] Input: nums = [1,12,-5,-6,50,3], k = 4
// <1, 12, -5, -6> -> <12, -5, -6, 50> -> <-5, -6, 50, 3>
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        
        if(nums == null || nums.length < k|| k <= 0){
            return 0;
        }
        
        double curSum = 0;

        for(int i = 0; i < k; i++){
            curSum += nums[i];
        }
        
        double maxSum = curSum;
      
        for(int i = k; i < nums.length; i++){
            curSum += nums[i];
            curSum -= nums[i - k];
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum / k;
        
    }
}