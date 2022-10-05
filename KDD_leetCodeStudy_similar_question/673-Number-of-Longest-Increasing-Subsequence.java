//2022.10.05

////300. Longest Increasing Subsequence 이 풀이법에다가 longest 인 것을 count 하면 되려나???

//limitation : if nums is null or size is zero, return 0 ??
//Input: nums = [1,3,5,4,7] -> Output: 2
//Input: nums = [2,2,2,2,2] -> Output: 5
//Input: nums = [1,2,4,3,5,4,7,2] -> Output: 3

// Time Complexity: O(n^2), Space Complexity(n)
class Solution {
    public int findNumberOfLIS(int[] nums) {
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int maxLIS = 1;  // the size of longest increasing subsequence
        int resultNumber = 1; // the number of longest increasing subsequences
        
        int[] valueMaxLIS = new int[nums.length]; 
        int[] numberMaxLIS = new int[nums.length];
        
        valueMaxLIS[0] = 1;
        numberMaxLIS[0] = 1;
        
        //          [1, 3, 5, 4, 7]
        //eachNum    1  2  3  3  4
        //maxLIS     1  2  3  3  4
        //numMaxLIS  1  1  1  1  1
        
        //               [1,2,4,3,5,4,7,2]  
        //eachMaxNum      1 2 3 3 4 4 5 2    
        //the numof Max   1 1 1 1 2 3 2 1
        for(int i = 1; i < nums.length; i++){
            valueMaxLIS[i] = 1;
            int eachNum = 1;
            int eachMax = 0;
            for(int j = 0; j < i ; j++){
                
                if(nums[j] < nums[i]){
                    eachMax = valueMaxLIS[j] + 1;
                    if(eachMax == valueMaxLIS[i]){
                        eachNum += numberMaxLIS[j];
                    }else if(eachMax > valueMaxLIS[i]){
                        valueMaxLIS[i] = eachMax;
                        eachNum = numberMaxLIS[j];
                    }
                    //System.out.println("i:"+i+",j:"+j+", eachMax:" + eachMax+", eachNum:"+eachNum);
                }
            }
            
            numberMaxLIS[i] = eachNum;
            
            //System.out.println("i:"+i+", maxLIS:"+maxLIS+", eachNumMax:" + valueMaxLIS[i]+", eachNum:"+eachNum + ", numMaxLIS:" + numberMaxLIS[i]);
            
            if(valueMaxLIS[i] == maxLIS){
                resultNumber += numberMaxLIS[i];
            }
            if(valueMaxLIS[i] > maxLIS){
                resultNumber = numberMaxLIS[i];
                maxLIS = valueMaxLIS[i];
            }
        }
        return resultNumber;
    }
}