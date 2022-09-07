//2022.09.07
// limitation: if nums is null or size is zero, which value should be returned??
//Input: nums = [-1,2,1,-4], target = 1   -> Output: 2
// Input: nums = [0,0,0], target = 1  -> Output: 0

// Time Complexity: O(n^2) ( sorting 에 걸리는 시간은 O(n log n) , O(n^2)가 더 큼.)
// Space Complexity: from O(logN) to O(n), depending on the implementation of the sorting algorithm.

//- two point 
// leetcode 솔루션에서는 while 문에서 closest 를 비교했는데 그럴필요없이, for 문에서만 비교해도 됨. 이것이 더 낫다고 봄 

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        Arrays.sort(nums); // [-1,2,1,-4] -> [-4, -1, 1, 2]
        
        int threeSum = 0;
        int closestNum = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            
            int low = i+1;
            int high = nums.length -1;
            
            while(low < high){
                
                threeSum = nums[i] + nums[low] + nums[high];
                
                if(threeSum == target){
                    return target;
                }else if(threeSum > target){
                    high--;
                }else{ // sum < diff
                    low++;
                }
            }            
            
            int currentDiff = target - threeSum;
            int previousDiff = target - closestNum;
            closestNum = ( Math.abs(currentDiff) < Math.abs(previousDiff) ) ? threeSum : closestNum;
        }
        
        return closestNum;
    }
}

// leetcode 솔루션중 binary search 를 이용한 경우  -- 이해하지는 못했음. 
// Time Complexity: O(n^2 log n )
// Space Complexity: from O(logN) to O(n), depending on the implementation of the sorting algorithm.
/*
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        int sz = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < sz && diff != 0; ++i) {
            for (int j = i + 1; j < sz - 1; ++j) {
                int complement = target - nums[i] - nums[j];
                var idx = Arrays.binarySearch(nums, j + 1, sz - 1, complement);
                int hi = idx >= 0 ? idx : ~idx, lo = hi - 1;
                if (hi < sz && Math.abs(complement - nums[hi]) < Math.abs(diff))
                    diff = complement - nums[hi];
                if (lo > j && Math.abs(complement - nums[lo]) < Math.abs(diff))
                    diff = complement - nums[lo];
            }
        }
        return target - diff;
    }
}
*/

// Time Complexity: O(n^2) ( sorting 에 걸리는 시간은 O(n log n) , O(n^2)가 더 큼.)
// Space Complexity: from O(logN) to O(n), depending on the implementation of the sorting algorithm.
//아래 코드는 while 뿐만아니라 for문에서도 closest 를 저장해서 계속 비교해가면서 했는데... 
// leetcode 솔루션을 보니 for 문에서는 closest 저장하고 비교하는작업이 필요없음. 
// 결국 3Sum 솔루션이 정확히 일치하는 값을 찾아간것을 생각해보면, 마지막에 찾은 값이 가장 closest 한 값인 것임.
/*
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        Arrays.sort(nums);
        
        int closestNum = 100000; 
        
        for(int i = 0; i < nums.length; i++){
            
            int twoSumTarget = target - nums[i];
            int threeSum = nums[i] + closestTwoSum(nums, i+1, nums.length-1, twoSumTarget);
            
            
            closestNum = (Math.abs(target - closestNum) > Math.abs(target - threeSum) ) ? threeSum : closestNum;
        }
        
        return closestNum;
    }
    
    int closestTwoSum(int[] nums, int low, int high, int target){
        
        int closestNum = 100000;
        
        while(low < high){
            int sum = nums[low] + nums[high];
            
            closestNum = (Math.abs(target - closestNum) > Math.abs(target - sum)) ? sum : closestNum;
            if(sum == target){
                return sum;
            }else if(sum > target){
                high--;
            }else{// sum < target
                low++;
            }
        }
        
        return closestNum;
    }
}
*/

//Time Limit Exceeded
//Time Complexity: O(n^3) , Space Complexity: nPr 순열 = n*(n-1)*(n-2) =>  O(n^3)
/*
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        
        if(nums == null || nums.length == 0){
            return 0; // return what?????
        }
        
        
        int length = nums.length;
        List<Integer> threeSum = new ArrayList<Integer>();
        
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                for(int k = 0; k < length; k++ ){
                    if( i != j && i != k && j != k){
                        int diff = nums[i] + nums[j] + nums[k];
                        threeSum.add(diff);
                    }
                }
            }
        }
        
        int listSize = threeSum.size();
        Integer[] threeSumArray = threeSum.toArray(new Integer[listSize]);
        Arrays.sort(threeSumArray, (a,b) -> (Math.abs(a - target) - Math.abs(b - target)));
        
        return threeSumArray[0];
        
    }
}
*/