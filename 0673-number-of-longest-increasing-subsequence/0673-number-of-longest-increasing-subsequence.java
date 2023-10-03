//2023-10-01
//Solution : 300. Longest Increasing Subsequence 이 풀이법 에다가 longest 인 것을 count 하면 되려나??? -> 그러기 위해 배열하나 더 필요. => count 하는 배열값을 어떻게 update 해야할지가 헷갈렸었음. 이부분을 다시 잘 상기해야 할듯. 
//Time Complexity: O(N^2)
//Space Complexity: O(N)

class Solution {
    public int findNumberOfLIS(int[] nums) {
        if(nums == null || nums.length <= 0){
            return 0;
        }
        
        int[] length = new int[nums.length];
        int[] countMap = new int[nums.length];
        int countLongest = 0;
        int longest = 0;
        Arrays.fill(length, 1);
        Arrays.fill(countMap, 1);
        for(int i = 0; i < nums.length; i++){ // i 를 1부터 체크하면 안됨. [2,2,2,2,2] 가 4가 나오는 문제가 있음. 
            //save max length and count of max length until current nums 
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i]){
                    if(length[j] + 1 == length[i]){
                        countMap[i] += countMap[j];
                    }else if(length[j] + 1 > length[i]){ 
                        countMap[i] = countMap[j];
                        length[i] = length[j] + 1;
                    }
                }
            }
            //아래 체크 항목이 for( j = 0 ~ ) 의 바깥에 있어야 함. 
            if(longest < length[i]){
                longest = length[i];
                countLongest = countMap[i];
            }else if(longest == length[i]){
                countLongest += countMap[i];
            }
        }
        
        return countLongest;
    }
}

/*
class Solution {
public int findNumberOfLIS(int[] nums) {
        int n = nums.length, res = 0, max_len = 0;
        int[] len =  new int[n], cnt = new int[n];
        for(int i = 0; i<n; i++){
            len[i] = cnt[i] = 1;
            for(int j = 0; j <i ; j++){
                if(nums[i] > nums[j]){
                    if(len[i] == len[j] + 1)cnt[i] += cnt[j];
                    if(len[i] < len[j] + 1){
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    }
                }
            }
            if(max_len == len[i])res += cnt[i];
            if(max_len < len[i]){
                max_len = len[i];
                res = cnt[i];
            }
        }
        return res;
    }
}
*/

//2022.10.05

////300. Longest Increasing Subsequence 이 풀이법에다가 longest 인 것을 count 하면 되려나???

//limitation : if nums is null or size is zero, return 0 ??
//Input: nums = [1,3,5,4,7] -> Output: 2
//Input: nums = [2,2,2,2,2] -> Output: 5
//Input: nums = [1,2,4,3,5,4,7,2] -> Output: 3

// Time Complexity: O(n^2), Space Complexity(n)
/*
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
*/