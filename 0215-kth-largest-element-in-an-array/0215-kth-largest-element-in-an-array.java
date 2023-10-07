

//2023-10-06
//idea1: Array sort
//idea2: Priority Queue

//Idea1: Array Sort
class Solution {
    public int findKthLargest(int[] nums, int k) {
        if(nums == null || nums.length <= 0 || k < 1){
            return -10001;
        }
        
        //Arrays.sort(nums, Collections.reverseOrder());
        Arrays.sort(nums);
        
        return nums[nums.length -k];
    }
}