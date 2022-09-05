//2022.09.04
// limitation: if numbers is null or size is zero, return null
//Input: numbers = [2,7,11,15], target = 9 , Output: [1,2]
//Input: numbers = [2,3,4], target = 6 , Output: [1,3]

// time complexity: O(n), Space complexity: O(1)

class Solution {
    public int[] twoSum(int[] numbers, int target) {
        
        if(numbers == null || numbers.length == 0){
            return null;
        }
        
        int low = 0; 
        int high = numbers.length -1;
        
        while(low < high){
            int sum = numbers[low] + numbers[high];
            if(sum == target){
                int[] result = {low+1, high+1};
                return result;
            }else if(sum > target){
                high--;
            }else{ // sum < target
                low++;
            }
        }
        
        return null;
    }
}