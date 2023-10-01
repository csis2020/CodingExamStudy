

class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums == null || nums.length <= 0){
            return 0;
        }
        
        int start = 0;
        int longest = 1;
        for(int i = 1; i < nums.length; i++){
            if(nums[i-1] < nums[i]){
                longest = Math.max(longest, i - start + 1);
            }else{
                start = i;
            }
        }
        return longest;
    }
}