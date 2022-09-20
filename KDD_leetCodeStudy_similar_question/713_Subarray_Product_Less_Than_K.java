//2022.09.19
//limitation: are those all numbers different? 
//              if nums is null or size is 0, return 0 is OK?

//Input: nums = [10,5,2,6], k = 100 -> Output: 8
// Input: nums = [1,2,3], k = 0  -> Output: 0
// Time Complexity : O(N), Space Complexity: O(1)
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        if(k <= 1){ //입력범위가 1 <= nums[i] <= 1000 임, k가 0또는 1이면 그보다 작은 곱을 만들수가 없으니, return 0
            return 0;
        }
        
        int productSofar = 1;
        int numSubArray = 0;
        int startLeft = 0;
        
        for(int i = 0; i < nums.length; i++){
            productSofar *= nums[i];
            
            //while((productSofar >= k) && (startLeft <= i)){
            while(productSofar >= k){ // 자기자신으로 나누면 1 되어서  (\(startLeft <= i)이걸 체크하지 않아도 되는건가보다.
                productSofar /= nums[startLeft];
                startLeft++;
            }
            
            numSubArray += i - startLeft +1;
            
        }
        
        return numSubArray;
        
    }
}
