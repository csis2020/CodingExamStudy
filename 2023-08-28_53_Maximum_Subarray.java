//2023.August.28
//limitation: 

//example: 
// nums=[-2,1,-3,4,-1,2,1,-5,4]
// subSum (include current nums[i])
//          (-2) -> (1) -> (1, -3) ->  (4) -> (4, -1) -> (4, -1, 2) -> (4, -1, 2, 1) -> (4, -1, 2, 1, -5) -> (4, -1, 2, 1, -5, 4)
// maxSum : (-2) -> (1) -> (1)     ->  (4) -> (4)     -> (4, -1, 2) -> (4, -1, 2, 1) -> (4, -1, 2, 1) -> (4, -1, 2, 1)
//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution{
    public int maxSubArray(int[] nums){
        if(nums == null || nums.length == 0){
            return -1001;
        }

        int subSum = nums[0];
        int maxSum = nums[0];

        for(int i = 1; i < nums.length; i++){
            subSum = Math.max(subSum + nums[i], nums[i]);
            maxSum = Math.max(subSum, maxSum);
        }
        return maxSum;
    }
}


//limitation : if nums is null or nums' length is zero, return ????
//intput: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]  -> output : 6
//intput: nums = [-2, -1] -> output: -1
//2022-12-02
//Time Complexity : O(N)
//Space Complexity: O(1)
/*
class Solution{
    public int maxSubArray(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        int maxSum = nums[0];
        int subSum = nums[0];
        for(int i = 1; i < nums.length; i++){
            int tempSum = nums[i] + subSum;
            subSum = Math.max(nums[i], tempSum);
            maxSum = Math.max(subSum, maxSum);
        }
        return maxSum; 
    }
}
*/
//2022-09-12
//Time complexity: O(n) , Space complexity: O(1)
//Dynamic Proframming, Kadane's Algorithm  ????? 
/*
class Solution {
    
    public int maxSubArray(int[] nums) {
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int largestSum = nums[0];
        int currentSum = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            
            currentSum += nums[i];
            
            if(currentSum < nums[i]){ // reset sum
                currentSum = nums[i];
            }
            
            if(largestSum < currentSum){
                largestSum = currentSum;
            }
        }
        
        return largestSum;
        
    }
    
}
*/
//using Divide & Cdonquer
// Time Complexity: O(nlogn), Space complexity:O(logn)
/*
class Solution {
    
    public int maxSubArray(int[] nums) {
        
        if(nums == null || nums.length < 1){
            return 0;
        }
        
        return recursiveMaxSubArray(nums, 0, nums.length-1);
    }
    
    int recursiveMaxSubArray(int[] nums, int start, int end){
        
        if(nums == null || nums.length < 1){
            return 0;
        }
        
       // System.out.println("["+start+","+end+"] ");
        
        if(start > end){
            return Integer.MIN_VALUE;
        }else if(start == end){
            return nums[start];
        }
        
        int mid = (start + end)/2;
        int leftMaxSum = 0;
        int rightMaxSum = 0;
        int currentSum = 0;
        
        //leftMax
        for(int i = (mid-1); i >= start; i--){
            currentSum += nums[i];
            leftMaxSum = Math.max(leftMaxSum, currentSum);
        }
        
        currentSum = 0;
        //Right maxSum
        for(int i = (mid+1); i <= end; i++){
            currentSum += nums[i];
            rightMaxSum = Math.max(rightMaxSum, currentSum);
        }
        
        int totalMaxSum = leftMaxSum + nums[mid] + rightMaxSum;
        
        //find max sum of left array
        int leftMaxSubArray = recursiveMaxSubArray(nums, start, mid-1);
        //find max sum of right array
        int rightMaxSubArray = recursiveMaxSubArray(nums, mid+1, end);
        
       // System.out.println("["+start+","+end+"] left:"+ leftMaxSubArray + ", right:"+rightMaxSubArray +", total:" + totalMaxSum);
        
        return Math.max(totalMaxSum, Math.max(leftMaxSubArray, rightMaxSubArray));        
        
    }
}
*/


/*
//2022-06-22
//intput: nums = [-2, 1, -3, 4, -1, 2, 1, -5, 4]  -> output : 6
//Time complexity: O(NlogN) , Space complexity: O(logN)
class Solution {
    
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0 ){
            return 0; // return 0 is OK???
        }

        return recursiveMaxSubArray(nums, 0, nums.length-1);

    }

    private int recursiveMaxSubArray(int[] nums, int left, int right){

        if(left > right){
            return Integer.MIN_VALUE;
        }

        int maxSum = 0;

        int leftMax = 0;
        int rightMax = 0;

        int mid = Math.floorDiv(left + right, 2);

        int curMax = 0;
        for(int i = mid+1; i <= right; i++){
            curMax += nums[i];
            rightMax = Math.max(rightMax, curMax);
        }

        curMax = 0;
        for(int i=mid-1; i >= left; i--){
            curMax += nums[i];
            leftMax = Math.max(leftMax, curMax);
        }

        maxSum = leftMax + nums[mid] + rightMax;

        leftMax = recursiveMaxSubArray(nums, left, mid-1);
        rightMax = recursiveMaxSubArray(nums, mid+1, right);

        return Math.max(maxSum, Math.max(leftMax, rightMax));

    }
}
*/
//2022-06-11
//limitation : nums is null?? 
//
// Time complexity: O(N),  space complexity: O(1)
/*
class Solution {
    
    public int maxSubArray(int[] nums) {

        if(nums == null || nums.length == 0){
            return 0;  //which value shoud return? Integer.MIN_VALUE?
        }
     
        int maxSum = nums[0];
        int curSum= nums[0];

        for(int i = 0; i < nums.length; i++){
            curSum = Math.max(curSum + nums[i], nums[i]);

            maxSum = Math.max(curSum, maxSum);
        }

        return maxSum;
    }
}
*/
/*
class Solution {
    
     public int maxSubArray(int[] nums) {
         int maxSum = nums[0];
         int curSum = nums[0];
         
         for(int i = 1; i < nums.length; i++){
             curSum = Math.max(nums[i], curSum + nums[i]);
             maxSum = Math.max(curSum, maxSum);
         }
         
         return maxSum;
     }         


}
*/


/* 2022.03.24 my solution
class Solution {

    public int maxSubArray(int[] nums) {
        
        int maxSum = nums[0];
        int preSum = nums[0];
        
        int pos = 1;
        int startIndex = 0;
        
        if(nums.length == 1){
            return maxSum;
        }
        
        while(pos < nums.length){
            
            int tempSum = preSum + nums[pos];
            if(tempSum <= 0){
                startIndex = pos;
                preSum = nums[pos];
            }else{
                preSum = tempSum;
                if(nums[startIndex] <= 0){
                    preSum -= nums[startIndex];
                    startIndex++;
                }
            }
            
            maxSum = Math.max(maxSum, preSum);
            //if(maxSum < preSum){
            //    maxSum = preSum;
            //}
            
            pos++;
        }
        
        return maxSum;
    }

}
*/