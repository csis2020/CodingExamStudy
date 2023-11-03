

//2023-11-03
//Idea : using HashMap<key, value> : key is nums[i], value is frequency
//Time Complexity: O(N)
//Space Complexity: O(N)
class Solution {
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length <= 0){
            return Integer.MIN_VALUE;
        }
        
        int maxCount = 0;
        int maxNumber = nums[0];
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            
            int count = countMap.getOrDefault(nums[i], 0) + 1;
            if(count > maxCount){
                maxNumber = nums[i];
                maxCount = count;
            }
            countMap.put(nums[i], count);
        }
        
        return maxNumber;
    }
}