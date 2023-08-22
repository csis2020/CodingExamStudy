//2-23-01-23--[2]
//아래 [Java] BOJ 10986 나머지 합 (누적합)  해결법 참조 (백준)
//https://velog.io/@jodawooooon/Java-BOJ-10986-%EB%82%98%EB%A8%B8%EC%A7%80-%ED%95%A9  
//input: nums=[4,5,0,-2,-3,1] , k = 5 -> output: 7
class Solution{
    public int subarraysDivByK(int[] nums, int k){
        if(nums == null || nums.length == 0 || k <= 0){
            return 0; 
        }
        
        int length = nums.length;
        int totalCount = 0;
        //int[] prefixMod = new int[length];
        //int sum = 0;
        int prefixMod = 0;
        int mod[] = new int[k];

        //nums=[4,  5,  0,  -2,  -3,  1] , k = 5
        //mod   4,  4   4    2    4   0 
        // mod[0] = 1, mod[1] = 0, mod[2] = 1, mod[3] = 1, mod[4] = 3
        for(int i = 0; i < length; i++){
            //sum += nums[i];
            prefixMod = (prefixMod + nums[i]%k +k) % k; // to make the negative modular number to positive number
            if(prefixMod == 0){
                totalCount++;  
            }
           mod[prefixMod]++;
        }
        //totalCount = mod[0] + 0 + 0 + 0 + 0 + 4*3/2 =  7
        for(int i = 0; i < k; i++){
            totalCount += (mod[i] * (mod[i]-1))/2;
        }

        return totalCount;

    }
}
//2023-01-23---[1]
//Brute Force 방법 - Time Limit Exceeded
/*
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        
        if(nums == null || nums.length == 0 || k <= 0 ){
            return 0;
        }

        int totalCount = 0;

        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++){
                sum += nums[j];
                if(sum % k == 0){
                    totalCount++;
                }
            }
        }

        return totalCount;
    }
}
*/