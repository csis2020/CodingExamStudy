//2023-11-07
//Backtracking ( 백트래킹은 모든 경우의 수를 고려하여 해를 찾는 방식입니다. 이름에서 유추할 수 있듯이, 퇴각 검색(추적)이라는 의미를 갖습니다. 즉, 해를 찾기 위해서 후보군을 나열하고, 제약 조건을 점진적으로 체크합니다. 만약 조건에 맞지않다면, 뒤로 돌아와서(backtrack), 바로 다음 후보군으로 넘어갑니다. 백트래킹에서 검색할 후보들을 상태 공간 트리(State Space Tree)로 표현합니다.)

//example: 문제 예제뿐아니라 nums=[2,2,2,2,3,4,5], k=4  에 대해서도 해볼것

class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length <= 0 || k <= 0){
            return false;
        }
        
        boolean[] checked = new boolean[nums.length];
        int totalSum = 0; 
        for(int num: nums){
            totalSum += num;
        }
        if(totalSum % k != 0){
            return false;
        }
        int targetSum = totalSum / k;
        //descending order 로 정렬하면 backtracking 의 time 을 줄일수 있다. 
        //if(currentSum > targetSum)인경우를 더 빨리 찾게됨.
        Arrays.sort(nums); //int[] 의 개별요소들은 int 의 primitive 타입이라 Arrays.sort(arr, (a,b)->(b-a));를 쓸수 없음. a,b 가 Object 이어야함.
        int start = 0;
        int end = nums.length -1;
        while(start < end){
            int temp = nums[end];
            nums[end] = nums[start];
            nums[start] = temp;
            start++;
            end--;
        }
        
        return backtracking(nums, k, 0, 0, targetSum, checked);
    }
    
    boolean backtracking(int[] nums, int k, int index, int currentSum, int targetSum, boolean[] checked){
        
        if(currentSum > targetSum){
            return false;
        }
        if(index >= nums.length){
            return false;        
        }
        
        if(k == 1){ // k개의 subset 만드는것 가능. 이미 k개로 나눌수 있는지 체크했기때문에 k==1 이 나온것이면 성공임. 
            return true;
        }
        
        if(currentSum == targetSum){ // subset 하나 찾는데 성공! 총 k 개를 찾아야 하니, k-1 하고 다시 backtracking
            return backtracking(nums, k-1, 0, 0, targetSum, checked );
        }
        
        for(int i = index; i < nums.length; i++){
            if(checked[i]){
                continue;
            }
            checked[i] = true;
            //여기서는 현재 subset 에 들어갈 nums[i] 를 찾는 것이라서 k 를 그대로 사용.
            if(backtracking(nums, k, i+1, currentSum +nums[i], targetSum, checked )){
                return true;
            }
            checked[i] = false;
        }
        
        return false;
    }
}


//Back tracking : leetcode 의 솔루션 
//Time complexity: O(k⋅2^N).
//Space complexity: O(N).
/*
class Solution {
    private boolean backtrack(int[] arr, int index, int count, int currSum, int k, 
                              int targetSum, boolean[] taken) {

        int n = arr.length;
      
        // We made k - 1 subsets with target sum and last subset will also have target sum.
        if (count == k - 1) { 
            return true;
        }
        
        // No need to proceed further.
        if (currSum > targetSum) { 
            return false;
        }
      
        // When curr sum reaches target then one subset is made.
        // Increment count and reset current sum.
        if (currSum == targetSum) {
            return backtrack(arr, 0, count + 1, 0, k, targetSum, taken);
        }
        
        // Try not picked elements to make some combinations.
        for (int j = index; j < n; ++j) {
            if (!taken[j]) {
                // Include this element in current subset.
                taken[j] = true;
                
                // If using current jth element in this subset leads to make all valid subsets.
                if (backtrack(arr, j + 1, count, currSum + arr[j], k, targetSum, taken)) {
                    return true;
                }
                
                // Backtrack step.
                taken[j] = false;
            }
        } 
      
        // We were not able to make a valid combination after picking each element from the array,
        // hence we can't make k subsets.
        return false;
    }
    
    void reverse(int[] arr) {
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) { 
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
  
    public boolean canPartitionKSubsets(int[] arr, int k) {
        int totalArraySum = 0;
        int n = arr.length;
        
        for (int i = 0; i < n; ++i) {
             totalArraySum += arr[i];
        }
      
        // If total sum not divisible by k, we can't make subsets.
        if (totalArraySum % k != 0) { 
            return false;
        }
      
        // Sort in decreasing order. 
        Arrays.sort(arr);
        reverse(arr);
        
        int targetSum = totalArraySum / k;
        boolean[] taken = new boolean[n];
      
        return backtrack(arr, 0, 0, 0, k, targetSum, taken);
    }
}
*/