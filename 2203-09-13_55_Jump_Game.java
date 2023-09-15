//2023-09-13
//dynamic programming 방식 - 맨끝에서 부터 체크 
//  'checked position = 갈수 있다고 확인된 위치' 라고 할때 
//  (현재 위치 i + nums[i]) >= checked position 이면 checked position = 현재위치 i 로 update 
// I will check this from last index. 
// Let's set the checked position. checked positon is reachable position.
// If I reach out to the reachable poisiton from current position, current positon will be new reachable position. 
// I can move from current positon(i) to 'current positon + nums[i]'
//  I means, (current position i + nums[i]) >= reachable position , then new reachable poisiton becomes i. 

//  Time Complexity: O(N)
//  Space Complexity: O(1)
class Solution{
    public boolean canJump(int[] nums){
        if(nums == null || nums.length <= 0){
            return false;
        }

        //Input: nums = [3,2,1,0,4]
        //position : 4 <- 4 <- 4 <- 4 <-4 <- 4 ==> false
        //reachable: 8 <- 3 <- 3 <- 3 <- 3

        int position = nums[nums.length -1];
        for(int i = nums.length -1; i >= 0; i--){
            int reachable = i + nums[i];
            if(reachable >= position){
                position = i;
            }
        }

        return position == 0;
    }
}


//2022.12.04 - leetcode 의 최적솔루션
//input: [9, 4, 2, 1, 0] -> output: true;
//input: [2, 3, 1,1,4] -> output: true;
//Time Complexity: O(N)
//Space Complexity: O(1)
/*
public class Solution {
    public boolean canJump(int[] nums) {
        if(nums == null || nums.length == 0){
            return false;
        }

        int pos = nums.length -1; // checked pos
        for(int i = nums.length -1; i >=0; i--){
            int possibleJump = i + nums[i];
            if(possibleJump >= pos){
                pos = i;
            }
        }

        return pos == 0;
    }
}
*/
//2022.10.17
//Limitation : if nums is null or nums size is zero, return false
//Time Complexity: O(n^2) <-- 최악의 경우 for loop 을 2번 도는 효과가 남. (4, 3, 2, 1, 0) 이런경우...
//Space Complexity: O(n) <- O(2n) :  First n originates from recursion. Second n comes from the usage of the memo table.
/*
class Solution{                  
    public boolean canJump(int[] nums){
        if(nums == null || nums.length == 0){
            return false;
        }
        
        Boolean[] isChecked = new Boolean[nums.length]; //default value is null
        
        return recursiveJump(nums, isChecked, 0);
    }
    
    private boolean recursiveJump(int[] nums, Boolean[] isChecked, int pos){
        
        if(pos < 0 || pos >= nums.length){ // cannot reach to end. 
            return false;
        }
        
        if(pos == nums.length -1){ //reach to end. 
            return true;
        }
        
        if(isChecked[pos] != null){ //already Checked. 
            return isChecked[pos];
        }
        
        
        //check each jumps
        for(int i = 1; i <= nums[pos]; i++){
            if(recursiveJump(nums, isChecked, pos+i) == true){
                isChecked[pos] = true;
                return true;
            }
        }
        
        isChecked[pos] = false;
        return false;
    }
}
*/
/*
class Solution {
    
    
    public boolean canJump(int[] nums) {
        
        if(nums == null || nums.length == 0){
            return false;
        }
        
        Boolean[] isChecked = new Boolean[nums.length]; //default value is null
        
        return recursiveJump(nums, isChecked, 0);
        
    }
    
    private boolean recursiveJump(int[] nums, Boolean[] isChecked,  int pos){
        if(pos == (nums.length-1)){
            return true;
        }
        
        if(pos >= nums.length ){
            return false;
        }
           
        if(isChecked[pos] != null){ //already checked
           return isChecked[pos]; 
        }
           
        if(nums[pos] == 0){ //no more move
            isChecked[pos] = false;
            return false;
        }
        
        for(int i = 1; i <= nums[pos]; i++){
            if(recursiveJump(nums, isChecked, pos+i) == true){
                isChecked[pos] = true;
                return true;   
            }            
        }
        
        isChecked[pos] = false;
        return false;
    }
}
*/