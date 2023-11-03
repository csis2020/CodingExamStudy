

//2023-11-03
//Idea1 : using HashMap<key, value> : key is nums[i], value is frequency
//Time Complexity: O(N)
//Space Complexity: O(N)
/*
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
*/

//Idea2 : Sorting 
//Time Complexity: O(NlogN)
//Space Complexity: O(1)
/*
class Solution {
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length <= 0){
            return Integer.MIN_VALUE;
        }
        
        Arrays.sort(nums); // ascending order
        return nums[nums.length/2];
    }
}
*/

//Idea3: Moore voting algorithm 
// 이 방법은 Majority element 가 n/2 보다 많은 경우 성립함. ( the number of majority > n/2)  , the  number of majority >= n/2 이면 성립 안됨. 
//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution {
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length <= 0){
            return Integer.MIN_VALUE;
        }
        
        int majorityNum = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(count == 0){
                majorityNum = nums[i];
                count++;
            }else if(majorityNum == nums[i]){
                count++;
            }else {
                count--;
            }
        }
        return majorityNum;
    }
}