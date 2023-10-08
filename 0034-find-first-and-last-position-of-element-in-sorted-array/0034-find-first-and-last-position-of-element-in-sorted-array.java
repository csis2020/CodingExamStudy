
//Input: nums = [5,7,7,8,8,10], target = 8 -> Output: [3,4]
//Input: nums = [], target = 0 -> Output: [-1,-1]
//Input: nums = [2,2], target = 3 -> Output: [-1,-1]

//2023-10-08 
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length <= 0){
            return new int[]{-1, -1};
        }
        
        //find left boundary
        int leftBoundary = -1;
        int rightBoundary = -1;
        int start = 0; 
        int end = nums.length -1;
        int mid = 0;
        //nums = [5,7,7,8,8,10], target = 8
        //(start,end):(0,5),(3,5),(3,3)
        //mid: 2, 4, 3 -> leftBoundary = 3
        while(start <= end){
            mid = (start + end) /2;
            if(nums[mid] == target){
                if(mid > 0 && nums[mid -1] == target){
                    end = mid -1;
                }else{
                   leftBoundary = mid;
                    break;
                }
            }else if(nums[mid] > target){
                end = mid -1;
            }else{
                start = mid + 1;
            }            
        }
        
        //find right boundary

        start = (leftBoundary > 0) ? leftBoundary : 0;
        end = nums.length -1;
        //nums = [5,7,7,8,8,10], target = 8
        //(start,end):(3,5)
        //mid: 4 -> rightBounday : 4
        while(start <= end){
            mid = (start + end) /2;
            if(nums[mid] == target){
                if(mid < (nums.length -1) && nums[mid+1] == target){
                    start = mid + 1;
                }else{
                    rightBoundary = mid;
                    break;
                }
            }else if(nums[mid] > target){
                end = mid -1;
            }else{
                start = mid + 1;
            }
        }
        
        return new int[]{leftBoundary, rightBoundary};
    }
}

//2023-10-08 
//idea: First, find mid position using binary search. The, find the boundary of start and end
//Time Complexity: O(N) <-- 시작은 binary search 로 O(logN) 이였지만 양쪽 boundary 를 찾는 과정에서 최악의 경우 O(N)임 예를들어  nums = [8,8,8,8,8,8,8,8], target = 8
//Space Complexity: O(1)
/*
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

*/