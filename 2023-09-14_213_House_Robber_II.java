
//2023-09-14
//기본아이디어:  circle 로 인접하지 않게 2가지 case 로 나누어 계산 -> there are 2 cases
// => 2가지 경우를 각각 따져서 더 큰 max 를 선택
//   1. 마지막집 제외하고 ( 첫번째 집 [i]부터 [n-2]까지의 집)을 robbing 하는 경우 의 max 값 
//   2. 첫번째집 빼고 ([i+1]부터 [n-1]까지 집을 robbing 하는 경우 ) 의 max 값 
//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution{
    public int rob(int[] nums){
        if(nums == null || nums.length <= 0){
            return 0;
        }

        if(nums.length == 1){
            return nums[0];
        }else if(nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }

        int max1 = robII(nums, 0, nums.length-2); //from [i] to [n-2]
        int max2 = robII(nums, 1, nums.length-1); //from [1] to [n-1]
        return Math.max(max1, max2);

    }

    //Input: nums = [2,3,2]
    //[2,3]=> 0:2, 1:3 ->return 2
    //[3,2]=> 1:3, 2:3 -> return 3
    //Input: nums = [1,2,3,1]
    //[1,2,3]=> 0:1, 1:2, 2:4 -> return 4
    //[2,3,1]=> 1:2, 2:3, 3:3 -> return 3
    int robII(int[] nums, int start, int end){
        int prePrev = 0;
        int prev = 0;
        int max = 0;
        for(int i = start; i <= end; i++){
            max = Math.max(prePrev + nums[i], prev);
            prePrev = prev;
            prev = max;
        }
        return max;
    }

}


//2022-12-04
//Time Complexity :O(N)
//Space Complexity: O(1)
/*
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        if(nums.length == 1){
            return nums[0];
        }else if(nums.length == 2){
            return Math.max(nums[0], nums[1]);
        }
        
        int max1 = rob(nums, 0, nums.length-2);
        int max2 = rob(nums, 1, nums.length-1);
        return Math.max(max1, max2);
    }

    private int rob(int[] nums, int start, int end){
        int prevPrev = 0;
        int prev = 0;
        int max = 0;
        for(int i = start; i <= end; i++){
            max = Math.max(prev, prevPrev+nums[i] ); // i번째 집을 rob 안할경우와, 할 경우에서 최대값 선택
            prevPrev = prev;
            prev = max;
        }

        return max;
    }
}
*/