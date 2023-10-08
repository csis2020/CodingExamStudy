
//Input: nums = [5,7,7,8,8,10], target = 8 -> Output: [3,4]
//Input: nums = [], target = 0 -> Output: [-1,-1]
//Input: nums = [2,2], target = 3 -> Output: [-1,-1]
class Solution {
    public int[] searchRange(int[] nums, int target) {
        
        if(nums == null || nums.length <= 0){
            return new int[]{-1,-1};
        }
        
        int targetPosition = binarySearch(nums, 0, nums.length-1, target);
        if(targetPosition == -1){
            return new int[]{-1,-1};
        }
        
        int start = targetPosition;
        int end = targetPosition;
        while(start > 0){
            if(nums[start -1] == target){
                start--;
            }else{
                break;
            }
        }
        while(end < (nums.length-1)){
            if(nums[end+1] == target){
                end++;
            }else{
                break;
            }
        }
              
        return new int[]{start, end};
    }
    
    int binarySearch(int[] nums, int start, int end, int target){
      
        while(start <= end){
            int mid = (start + end) /2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                start = mid+1;
            }else{
                end = mid -1;
            }
        }
        
        return -1;
    }
}