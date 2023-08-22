//2023-01-25
//Input: nums = [4,2,4,5,6]

//Time Complexity: O(N)
//Space Complexity: O(N)
//아래는 HashMap 을 사용했는데 HashSet 만 쓰고도 할수 있음 

class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        HashMap<Integer, Integer> uniqueMap = new HashMap<>();
        int start = 0;
        int sum = 0;  
        int maxScore = 0; 
        //[5,2,1,2,5,2,1,2,5]
        //map:      (5,0),(2,1),(1,2), (2,3)
        //start:      0     0     0     2
        //sum:        5, 5+2=7, 7+1=8, 8-5-2=1
        //maxScore:
        for(int i = 0; i < nums.length; i++){
            
            //if(!uniqueMap.containsKey(nums[i])){
            //    uniqueMap.put(nums[i], i); //add new data
            //    sum += nums[i];
           // }else{
            //    int duplicatePos =  uniqueMap.get(nums[i]);
            //    while(start <= duplicatePos){
            //        sum -= nums[start];
            //        start++;
            //    }
            //    uniqueMap.put(nums[i], i); //update data
            //}
            
            if(uniqueMap.containsKey(nums[i])){
                int duplicatePos = uniqueMap.get(nums[i]);
                while(start <= duplicatePos){
                    sum -= nums[start]; //remove data 
                    start++;
                }
            }
            uniqueMap.put(nums[i],i); //if nums[i] is unique, it is added for the first time, if it is not unique, the map data will be updated.
            sum += nums[i];
            maxScore = Math.max(maxScore, sum);
        }
        return maxScore;
    }
}


//HashSet 을 사용해서 푼 경우 
/*
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }

        HashSet<Integer> uniqueSet = new HashSet<>();
        int start = 0;
        int sum = 0;  
        int maxScore = 0; 
        //[5,2,1,2,5,2,1,2,5]
        //map:      (5,0),(2,1),(1,2), (2,3)
        //start:      0     0     0     2
        //sum:        5, 5+2=7, 7+1=8, 8-5-2=1
        //maxScore:
        for(int i = 0; i < nums.length; i++){
            
            while(uniqueSet.contains(nums[i])){
                uniqueSet.remove(nums[start]);
                sum -= nums[start];
                start++;
            }
            uniqueSet.add(nums[i]); //if nums[i] is unique, it is added for the first time, if it is not unique, the map data will be updated.
            sum += nums[i];
            maxScore = Math.max(maxScore, sum);
        }
        return maxScore;

    }
}
*/