//2024.02.21
//Time Complexity : O(N)
//Space Complexity : O(N)
class Solution {
    public boolean containsDuplicate(int[] nums) {

        if(nums == null || nums.length <= 0){
            return false;
        }

        HashSet<Integer> set = new HashSet<>();
        //
        for(int num : nums){
            if(set.contains(num)){
                return true;
            }

            set.add(num);
        }

        return false;
    }
}

//2023.August.22
//limitation: nums is null
//Idea: hashSet
//example: 
//input: nums = [1,2,3,1] , ():1->(1):2-> (1,2):3-> (1,2,3):1 => true

//Time Complexity: O(N)
//Space Complexity: O(N)
/*
class Solution{
    public boolean containsDuplicate(int[] nums){
        HashSet<Integer> uniqueSet = new HashSet<>();

        for(int i = 0; i < nums.length; i++){
            if(uniqueSet.contains(nums[i])){
                return true;
            }else{
                uniqueSet.add(nums[i]);
            }
        }
        return false;
    }
}
*/

// input : nums = [1, 2, 3, 1] , output : true
// input: nums = [1, 2, 3, 4], output: false

//2023-01-14
//Time Complexity: O(n)
//Space Complexity: O(n)
/*
class Solution{
    public boolean containsDuplicate(int[] nums){
        if(nums == null || nums.length == 0){
            return false;
        }

        HashSet<Integer> integerSet = new HashSet<>();

        for(int num : nums){
            if(integerSet.contains(num)){
                return true;
            }else{
                integerSet.add(num);
            }
        }

        return false;
    }
}
*/

//2022-12-02
//Time Complexity:O(N)
//Space Complexity: O(n)
/*
class Solution{
    public boolean containsDuplicate(int[] nums){
        if(nums == null || nums.length == 0){
            return false;
        }

        HashSet<Integer> numSet = new HashSet<>();
        for(int i = 0; i <nums.length; i++){
            if(numSet.contains(nums[i])){
                return true;
            }else{
                numSet.add(nums[i]);
            }
        }
        return false;
    }
}
*/
//2022.09.12
/*
// Time complexity : O(n),   Space complexity : O(n)
class Solution {
    

    public boolean containsDuplicate(int[] nums) { 
        
        if(nums == null || nums.length == 0){
            return false; //error
        }
        
        HashSet<Integer> existedNum = new HashSet<Integer>();
        
        for(int i = 0; i < nums.length; i++){
            if(existedNum.contains(nums[i])){ //nums[i] is already existed.
                return true;
            }
            
            existedNum.add(nums[i]);  // nums[i] comes up for the first time.
        }
        
        return false; // There is no duplicated number
        
    }
    
}
*/

//2022.07.22
/*
class Solution {
    
    //I think below Leetcode solution is better, I just can use HashSet. (2022.07.22)
    // <--- 아닌듯 leetcode 에서 HashSet 을 안쓰는 방법은 for 문 2번, 또는 sorting 한뒤 비교인데 이건 각각 O(n^2), 
    // 또는 O(nLogn) 임.
    public boolean containsDuplicate(int[] nums) { 
        
        if(nums == null || nums.length == 0){
            return false;
        }
        
        HashSet<Integer> set = new HashSet<>();
        
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])){
                return true;
            }else{
                set.add(nums[i]);
            }
        }
        
        return false;

    }
}
*/
        
 
        
/*
class Solution {
    
    //I think below Leetcode solution is better, I just can use HashSet. 
    public boolean containsDuplicate(int[] nums) { 
    
        Set<Integer> set = new HashSet<>(nums.length);
        
        for(int value : nums){
            if(set.contains(value)){
                return true;
            }
            set.add(value);
        }
        
        return false;
    
    }
    
    // I used HashMap

   // public boolean containsDuplicate(int[] nums) {
        
   //     Map<Integer, Integer> map = new HashMap<>();
        
    //    for(int i = 0; i < nums.length; i++){
    //        if(map.containsKey(nums[i]) && map.get(nums[i]) != i){
    //            return true;
    //        }
    //        map.put(nums[i], i);
    //    }
    //    return false;        
            
   // }
    
}
*/