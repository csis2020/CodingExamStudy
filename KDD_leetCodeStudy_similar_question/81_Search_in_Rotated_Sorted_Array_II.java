//2022-09-22
// limitation: if nums is null or size is zero, return false
//              nums is sorted in ascending order at first, but it is rotated before pased to this function 
//              nums values are not distinct.
// Input: nums = [2,5,6,0,0,1,2], target = 0  -> Output: true
// Input: nums = [2,5,6,0,0,1,2], target = 3  -> Output: false
// Input: nums = [1,0,1,1,1], target 0 -> Output: true
// Input: nums = [1,1,1,1,1], target 0 -> Output: false

// Time Complexity: O(n), Space Complexity: O(1)
class Solution {
    public boolean search(int[] nums, int target) {
        
        if(nums == null || nums.length == 0){
            return false;
        }
        
        int start = 0; 
        int end = nums.length -1;        
        
        //index: 0  1  2  3  4   5  6
        //nums:  2  5  6  0  0   1  2

        while(start <= end){            
           
            if(nums[start] == nums[end]){ //To avoid duplicate values

                while(start < end && nums[start] == nums[start+1]){
                    start++;
                }
                while(start < end && nums[end-1] == nums[end]){
                    end--;
                }
            }
            
            int mid = (start + end) /2;
            if(nums[mid] == target){
                return true;
            }            

            if(nums[start] <= nums[mid]){//first half of array is sorted in ascending order
                if((nums[start] <= target) && (target < nums[mid])){
                    end = mid -1;
                }else{
                    start = mid +1;
                }
            }else{// second half of array is sorted in ascending oder

                if((nums[mid] < target) && (target <= nums[end])){
                    start = mid +1;
                }else{
                    end = mid -1;
                }
            }   
            
        }
        
        return false; // Target value is not existed in this array
    }
}

