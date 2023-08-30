//2023-August-29
//Time Complexity: O(LogN)
//Space Complexity:O(1)

class Solution{
    public int findMin(int[] nums){
        if(nums == null || nums.length <= 0){
            return -5001;
        }

        int startPos = 0;
        int endPos = nums.length -1;

        //[4,5,6,7,0,1,2]
        //s(0):4 > e(6):2 ->s(0):4 < m(3):7 -> s(4):0,e(6):2 => return '0'
        //[4,1,2,3]
        //s(0):4 > e(3):3 -> s(0):4 > m(1):1 -> s(0):4 > e(1):1 -> s(0):4  == m(0):4 -> s(1):1 == e(1):1 ==> return '1'
        while(startPos <= endPos){
            if(nums[startPos] <= nums[endPos]){ //increasing order
                return nums[startPos];
            }
            int midPos = (startPos + endPos)/2;
            if(nums[startPos] <= nums[midPos]){//Need to check other half of array
                startPos = midPos+1;
            }else{
                endPos = midPos;
            }
        }
        return -5001;
    }
}       


//Limitation: nums are unique??
//              if nums is null or nums' size is 0, return -5001 ???? 
// Input: nums = [3,4,5,1,2] -> Output: 1
// Input: nums = [4,5,6,7,0,1,2] -> Output: 0
// Input: nums = [11,13,15,17] -> Output: 11

//2022-12-03
//Time Complexity: O(logN)
//Space Complexity: O(1)
/*
class Solution{
    public int findMin(int[] nums){
        if(nums == null || nums.length == 0){
            return -5001;
        }

        int start = 0;
        int end = nums.length -1;
        int mid = (start + end) /2;

        while(start <= end){
            mid = (start + end) /2;
            if(nums[start] > nums[mid] ){
                end = mid;
            }else if( nums[mid] > nums[end]){
                start = mid +1;
            }else{ // start < mid < end
                return nums[start];
            }
        }

        //Start > end
        //return mid;
        return -5001;
    }
}
*/
//2022-09-19
//Time Complexity: O(logn), Space Complexity: O(1)
/*
class Solution{
    public int findMin(int[] nums){
        
        if(nums == null || nums.length == 0){
            return -5001;
        }
        
        int start = 0;
        int end = nums.length -1;
        
        //    0, 1, 2, 3, 4, 
        //   [3, 4, 5, 1, 2]
        // start : 0 , end : 4 mid: 2
        // start : 3,  end: 4, mid: 3
        //  0,  1, 2,  3, 4,  5, 6
        //  [4, 5, 6,  7, 0,  1, 2]
        // s:0, e:6, m:3
        // s:4, e:6, m:5
        // 0   1   2   3
        //[11  13  15  17]
        //s:0, e:3, m:1
        while(start <= end){
            if(nums[start] <= nums[end]){
                //This part of array is sorted in ascending order.
                return nums[start]; 
            }
            
            int mid = (start + end) /2;
            if(nums[start] <= nums[mid]){
                //The second half of array has the rotated part
                start = mid +1;
            }else{
                //The first half of array has the rotated part
                end = mid;
            }
                
        }
        //start > end
        return -5001;
    }
}
*/
/*
class Solution{
    public int findMin(int[] nums){
        
        if(nums == null || nums.length == 0){
            return -50001;
        }
        
        if(nums.length == 1){
            return nums[0];
        }
        
        int start = 0; 
        int end = nums.length -1;
        
        
        while(start < end ){
            //it is ascending order array from start to end
            if(nums[start] < nums[end]){
                return nums[start];
            }
            
            int mid = (start + end) / 2;
            
            if(nums[start] <= nums[mid]){ 
                // from star to mid 는 오름차순, from mid+1 to end 에서 Rotated 됨.
                //It means nums[mid] > nums[end]
                //There is a minimum number between mid+1 and end
                start = mid+1;
            }else{ //nums[start] > nums[mid]
                //from start to mid 에서 Rotated 됨. 
                //There is a minimum number between start+1 and mid
                start = start+1;
                end = mid;
            }
            
        }
        
        //start == end
        return nums[start];
    }
}
*/
//Time Complexity: O(logn), Space Complexity: O(logn), recursive call stack
/*
class Solution {
    public int findMin(int[] nums) {
        
        if(nums == null || nums.length == 0){
            return -5001;
        }
        
        return binarySearch(nums, 0, nums.length-1);
    }
    
    private int binarySearch(int[] nums, int start, int end){
        
        //it is the minimum number
        if(start >= end){
            return nums[start];
        }
        
        int mid = (start + end) / 2;
        
        
        if(nums[start] <= nums[mid]){
            
            //In case, ascending order from start to end
            if(nums[mid] < nums[end]){
                return nums[start]; 
            }else{
                //There is a minimum number between mid+1 and end
                return binarySearch(nums, mid+1, end); 
            }
        }else{ //nums[start] > nums[mid
            // there is a minimum number between start+1 and mid
            return binarySearch(nums, start+1, mid); 
        }        
        
    }
}
*/