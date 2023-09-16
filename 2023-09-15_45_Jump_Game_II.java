//2023-09-15
// idea : 0 을 시작으로 해서 현재부터 가장 멀리 갈수 있는 곳을 current farthest 로 설정하고 
//   현재부터 current farthest 사이에서 가장 멀리 갈수 있는 값을 찾아서 그것을 next farthest 로 정함. 
//   index 가 current farthest 를 만나면, jump 수를 1개 더 count 하고, current farthest = next farthest 로 update. 다시 현재부터 current farthest 사이에서 next farthest 를 찾음. 
//  이과정을 index '0' 부터 n-2 까지 하면 됨.  (마지막 index 인 n-1 은 마지막 도착지 이므로 제외)

//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution{
    public int jump(int[] nums){
        if(nums == null || nums.length <= 0){
            return 0;
        }

        int jumps = 0;
        int currentFar = 0;
        int nextFar = 0;

        //Input: nums = [2,3,1,1,4]
        //jumps:  0, 1, 2   ==> 2
        //currentFar: 0, 2, 4
        //nextFar: 0, 2, 4, 4
        for(int i = 0; i < nums.length-1; i++){
            nextFar = Math.max(nextFar, i + nums[i]);
            if( i == currentFar){
                jumps++;
                currentFar = nextFar;
            }
        }

        return jumps;
    }
}

//2022.10.23 
/*
class Solution {
    public int jump(int[] nums) {
        
        int jumps = 0; 
        int currentJumpEnd = 0; 
        int farthest = 0; 
        
        for(int i = 0; i < nums.length -1 ; i++){
            
            farthest = Math.max(farthest, i + nums[i]);
            
            if( i == currentJumpEnd){
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        
        return jumps;
    }
}
*/