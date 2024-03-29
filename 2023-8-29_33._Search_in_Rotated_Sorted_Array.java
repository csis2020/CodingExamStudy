
//2023-August-23
//idea: using 'binary search'
//while ( start <= end){
// mid = (start + end) /2 , 
// if nums[mid] == target, return mid
// else, compare target with start and end
//      => if (nums[start] <= nums[mid]) //first half of array is ascending order 
//                  if(nums[start] <= target < nums[mid]) -> end = mid -1; 
//                  else -> start = mid +1; 
//         else // the other half of array is ascending order
//              if(nums[mid] < target <= nums[end]) -> start = mid +1
//              else -> end = mid -1;

//nums = [4,5,6,7,0,1,2], target = 3
//sp(0):4, ep(6):2, mp(3):7
//Input: nums = [1]

//Time Complexity: O(logN)
//Space Complexity: O(1)
class Solution{
    public int search(int[] nums, int target){
        if(nums == null || nums.length <= 0){
            return -1;
        }

        int sp = 0;
        int ep = nums.length -1;
        
        while(sp <= ep){
            int mp = (sp + ep)/2;
            if(nums[mp] == target){
                return mp;
            }

            if(nums[sp] <= nums[mp]){// first half of array is ascending order
                if(nums[sp] <= target && target < nums[mp]){
                    ep = mp -1;
                }else{
                    sp = mp +1;
                }
            } else{//other half of array is ascending order
                if(nums[mp] < target && target <= nums[ep]){
                    sp = mp +1;
                }else{
                    ep = mp -1;
                }
            }

        }

        //no data
        return -1; 
    }

}


/*
Formula: If a sorted array is shifted, if you take the middle, always one side will be sorted. Take the recursion according to that rule.

1- take the middle and compare with target, if matches return.
2- if middle is bigger than left side, it means left is sorted
2a- if [left] < target < [middle] then do recursion with left, middle - 1 (right)
2b- left side is sorted, but target not in here, search on right side middle + 1 (left), right
3- if middle is less than right side, it means right is sorted
3a- if [middle] < target < [right] then do recursion with middle + 1 (left), right
3b- right side is sorted, but target not in here, search on left side left, middle -1 (right)
*/

// limitation: if nums is null or size is zero, return -1?  yes
//              Is it right that all values of nums are unique? yes
// Input: nums = [4,5,6,7,0,1,2], target = 0  -> Output: 4
// Input: nums = [4,5,6,7,0,1,2], target = 3  -> Output: -1
// Input: nums = [1], target = 0  -> Output: -1
// Input: nums = [3,1], target = 1  -> Output: 1
// Input: nums = [3,1], target = 2  -> Output; -1

//Time Complexity : O(logn), Space Complexity: O(1)
//2022-12-03
/*
class Solution{
    public int search(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }

        int start = 0;
        int end = nums.length -1;
        while(start <= end){
            int mid = (start + end) /2;
            if(nums[mid] == target){
                return mid;
            }

            if(nums[start] <= nums[mid]){//ascending order
                if(nums[start] <= target && target < nums[mid]){
                    end = mid -1;
                }else{ // target is in other part
                    start = mid +1;
                }
            }else{ // from mid to end <-- ascending order
                if(nums[mid] < target && target <= nums[end]){
                    start = mid +1;
                }else{ // target is in other part
                    end = mid -1;
                }
            }
        }

        return -1;
    }
}
*/
//2022-09-25
/*
class Solution{
    public int search(int[] nums, int target){
        if(nums == null || nums.length == 0){
            return -1;
        }
        
        int start = 0;
        int end = nums.length -1;
        // 0 1 2 3 4 5 6 
        //[3 1]
        //s:0, e:1, m:0  target = 2
        //s:1, e:1, m:1
        while(start <= end){
            int mid = (start + end) /2; 
            
            if(nums[mid] == target){
                return mid;
            }
            
            if(nums[start] <= nums[mid]){
                //This part is sorted in the ascendign order 
                if((nums[start] <= target) && (target < nums[mid])){
                    end = mid -1;
                }else{
                    //We should check the other part of array
                    start = mid +1; 
                }
            }else{
                //The second half of array is sorted in the ascending order
                if((nums[mid] < target) && (target <= nums[end])){
                    start = mid + 1;
                }else{
                    end = mid -1;
                }
            }
        }
        
        return -1; //There is no target number in this array.
    }
}
*/
/*
class Solution{
    
    public int search(int[] nums, int target){
        
        if(nums == null || nums.length == 0){
            return -1;
        }  
        
        int start = 0;
        int end = nums.length -1; 

        while(start <= end){

            int mid = (start + end) /2;
            if(nums[mid] == target){
                return mid;
            }
            
            if(nums[start] <= nums[mid]){//first half array is sorted in ascending odrer
                if((nums[start] <= target) && (target < nums[mid])){
                    end = mid - 1;
                }else{
                    start = mid + 1; 
                }           
            }else{ // Second half array is sorted in ascending order
                if((nums[mid] < target)&&( target <= nums[end])){
                    start = mid+1;
                }else{
                    end = mid-1; 
                }
            }
            
        }
        
        return -1; // there is no target in this array.
    }
}
*/

//Time Complexity : O(logn), Space Complexity: O(logn) <- recursive call stack (맞을까?)
/*
class Solution {
    public int search(int[] nums, int target) {
        
        if(nums == null || nums.length == 0){
            return -1;
        }
        
        return recursiveSearch(nums, 0, nums.length -1, target);        
    }
    
    private int recursiveSearch(int[] nums, int start, int end, int target){
     
        if(start >= end){
            if(nums[start] == target){
                return start;
            }else{
                return -1;
            }
        }
        
        int mid = (start + end) / 2;
        
        if(nums[mid] == target){
            return mid;
        }
        
        
        if(nums[start] <= nums[mid]){ //At least,  first half of array is sorted in ascending order
            if( (nums[start] <= target) && (target < nums[mid])){ 
                //Target is in the part of ascending order array.
                return recursiveSearch(nums, start, mid-1, target);
            }else{ 
                return recursiveSearch(nums, mid+1, end, target);
            }
        }else{//Send half of array is sorted in ascending order
            
            if((nums[mid] < target)&&( target <= nums[end])){
                //Target is in the part of ascending order array.
                return recursiveSearch(nums, mid+1, end, target); 
            }else{
                return recursiveSearch(nums, start, mid-1, target);
            }
        }        

    }
}

*/