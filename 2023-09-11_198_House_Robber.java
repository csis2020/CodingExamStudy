//2023-September-11
//limitation : array size is bigger than '0' ? -> yes
//             all value are positive? -> yes

//idea: i 번째 집 순서에서 얻을수 있는 최대 money = Math.max(i-1 번째 집을 선택했을때 최대 money, i-2 번째 선택했을때 최대 money + i 번째 집의 money)
// [i] : 2 cases=> [i-1]'s max money ( do not choose [i] house), or [i-2]'s max money + i's money 
//      you can choose max amount of money amone them. 
// example: [1,2,3,1] => 
// 1 -> (1, 2): 2 -> (2, 4):4 -> (4, 3):4 ==> 4
//위 방식은 leetcode 풀이인데, nums[i] 집에서 rob 를 할경우와 안할경우를 따져서 현재까지 가질수 있는 최대 money 를 저장하는 방식이다. 
// 내가 생각했던 방식은 i-2, i-3 을 확인해야하는데 이방식은 i-1, i-2 를 확인하는 방식이라 더 좋음. 

//Time Complexity: O(N) : size of nums
//Space Complexity: O(1)
class Solution{
    public int rob(int[] nums){
        if(nums == null || nums.length <= 0){
            return 0; 
        }

        //Input: nums = [2,7,9,3,1]
        //prePrev: 0   2  7  11  11
        //prev   : 2   7  11 11  12
        //max    : (2, 0+7):7 -> (7, 11):11-> (11, 7+3):11 -> (11, 12):12 =>> 12
        int prePrev = 0;
        int prev = nums[0]; 

        for(int i = 1; i < nums.length; i++){
            int max = Math.max(prev, prePrev +nums[i]);
            prePrev = prev;
            prev = max;
        }

        return prev;
    }
}


//intput: [2, 1,1,2]
//input: [2, 4, 5, 6, 7, 8]
//input: [2, 7, 9, 3, 1]
//2022-12-04
//아래는 leetcode 풀이인데, nums[i] 집에서 rob 를 할경우와 안할경우를 따져서 현재까지 가질수 있는 최대 money 를 저장하는 방식이다. 
// 내가 생각했던 방식은 i-2, i-3 을 확인해야하는데 이방식은 i-1, i-2 를 확인하는 방식이라 더 좋음. 
//Time Complexity:O(N)
//Space Complexity: O(1)
/*
class Solution{
    public int rob(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        int prevPrev = 0;
        int prev = nums[0];
        int max = prev;
        for(int i = 1; i < nums.length; i++){

            max = Math.max(prev, prevPrev+nums[i]);
            prevPrev = prev;
            prev = max;
        }

        return max;
    }
}
*/

//Time Complexity: O(N)
//Space Complexity: O(N)
/*
class Solution{
    public int rob(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        int[] max = new int[nums.length];
        max[0] = nums[0];
        for(int i = 1; i < nums.length; i++){
            int prevPrev = 0;
            if( (i-2) >= 0){
                prevPrev = max[i-2];
            }
            max[i] = Math.max(max[i-1], prevPrev+nums[i]);
        }

        return max[nums.length -1];
    }
}
*/
//2022-12-04
//아래 풀이는 내가 푼 것인데, nums[i] 를 rob 할경우 nums[i] 에 올때까지 가질수 있는 최대의 money 를 저장한 것임. 
//Time Complexity: O(N)
//Space Complexity: O(N)
/*
class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        
        int[] max = new int[nums.length];
        max[0] = nums[0];
        max[1] = nums[1];
        int maxRob = 0;
        for(int i = 0; i < nums.length; i++){
            int prevPrev = 0;
            int prev = 0;
            if((i-3) >=0){
                prevPrev = max[i-3]; 
            }
            if((i-2) >= 0){
                prev = max[i-2];
            }
            max[i] = Math.max(prevPrev, prev) + nums[i];
            maxRob = Math.max(maxRob, max[i]);
        }
        return maxRob;
    }
}
*/