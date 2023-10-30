
//2023-10-30


public class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length <= 0){
            return 0;
        }
        
        int total = 0; 
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                if(sum == k){
                    total++;
                }
            }
        }
        
        return total;
    }
}


































//Limitation: nums is null or size is zero, return 0
//              if k is 0, return 0

//Input: nums = [1,1,1], k = 2   ---> Output: 2
//Input: nums = [1,2,3], k = 3   ---> Output: 2

//Leetcode Solution : 
//Time Complexity: O(N), Space Complexity: O(N)
/*
public class Solution {
    public int subarraySum(int[] nums, int k) {

        HashMap < Integer, Integer > sumMap = new HashMap < > ();
        sumMap.put(0, 1); // Sum 이 0 인 경우 (시작이 아무것도 선택안된경우 존재, 즉 [3, 1,2], k=3 일때 첫번째 값 1개가 k 와 같은경우 생김. )
        int prefixSum = 0;
        int answer = 0;
        
        for(int num : nums){
            prefixSum += num;
            answer += sumMap.getOrDefault(prefixSum -k, 0);
            sumMap.put(prefixSum, sumMap.getOrDefault(prefixSum, 0) +1);
        }
        return answer;
    }
}
*/

//Time Complexity: O(N^2), Space Complexity: O(1)
/*
class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int count = 0; 
        
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            //int sum = nums[i];
            //if(sum == k){
            //    count++;
            //}
            //for(int j = i+1; j < nums.length; j++){
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                if(sum == k){
                    count++;
                }
            }
        }
        
        return count;
    }
}
*/