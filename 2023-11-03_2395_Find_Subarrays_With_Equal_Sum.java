
//2023-11-03
//idea: using HashSet<value> : value is sum of subarrays which size is 2
//Time Complexity: O(N)
//Space Compleixty : O(N)
class Solution {
    public boolean findSubarrays(int[] nums) {
        if(nums == null || nums.length < 2){
            return false;
        }
        
        Set<Integer> sumSet = new HashSet<>();
        
        for(int i = 1; i < nums.length; i++){
            int sum = nums[i] + nums[i-1];
            if(sumSet.contains(sum)){
                return true;
            }else{
                sumSet.add(sum);
            }
        }
        
        return false;
    }
}