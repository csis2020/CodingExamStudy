
//input: nums = [1,2,3,1], k = 3  -> output: true
//input: nums = [1,0,1,1], k = 1  -> output: true;
//2022-01-14

//Time Complexity: O(N)
//Space Complexity: O(N)
/*
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 0){
            return false;
        }

        HashMap<Integer, Integer> duplMap = new HashMap<>();
        //(1, 0), (2, 1), (3, 2), 
        // 3 - get(1)  = 3 >= k(=3) -> true

        // (1, 0), (0, 1), => (0, 1) , (1, 2)
        // 2 - get(1) = 2  < k(1) -> (1,0)을 (1,2) 로 update
        // 3 - get(1) => 1 >= k(1) -> return true
        for(int i = 0; i < nums.length; i++){
            if(duplMap.containsKey(nums[i])){
                int gap = i - duplMap.get(nums[i]);
                if(gap <= k){
                    return true;
                }else{
                    duplMap.put(nums[i], i); //update index
                }
            }else{
                duplMap.put(nums[i], i);
            }

            //위코드를 아래처럼 고쳐도 됨. 
            //if(duplMap.containsKey(nums[i])){
            //    int gap = i - duplMap.get(nums[i]);
            //    if(gap <= k){
            //        return true;
            //    }
            //}
            //duplMap.put(nums[i], i);
            
        }

        return false;
    }
}
*/

//HashSet 만 이용해서 하는 경우 
//Time Complexity: O(N)
//Space Complexity: O(min(N, K))
class Solution{
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums == null || nums.length == 0 || k < 0){
            return false;
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                return true;
            }

            set.add(nums[i]);
            if(set.size() > k){
                set.remove(nums[i-k]);
            }
            
        }
        return false;
    }
}

