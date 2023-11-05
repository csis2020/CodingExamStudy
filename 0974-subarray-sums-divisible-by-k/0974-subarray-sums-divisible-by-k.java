


//2023-11-03
//누적합을 이용하여 푸는 문제 + alpah
//idea:  560. Subarray Sum Equals K , 523. Continuous Subarray Sum 과 유사하나 divisible 이라는 점에서 다르게 접근해야함. modular 연산의 결과를 저장.
//   

class Solution{
    public int subarraysDivByK(int[] nums, int k){
        if(nums == null || nums.length <= 0 || k <= 1){
            return 0;
        }
        
        int sum = 0;
        int[] mod = new int[k]; //save the number of modular
        mod[0] = 1; //save for the following example cases: nums=[5],k=5 or nums=[1,4],k=5
        int totalCount = 0;
        
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            int remainder = sum % k;
            //만약 nums[i] 가 음수값인 경우, sum이 음수가되어, remainder 가 음수가 될수도 있다. 
            //아래처럼 처리하거나, 
            //만약 음수/양수 상관없이 늘 같은 수식을 쓰고싶으면 remainder = (sum %k +k)%k 로 해도 됨. 
            if(remainder < 0){
                remainder += k;  
            }
            totalCount += mod[remainder]; //만약 해당 remainder 가 존재하지 않는다면 '0' 이 더해질 것임.
            mod[remainder]++;
        }
        
        return totalCount;
    }
}


//누적합 이용 - leetcode 의 솔루션
//Time Complexity: O(N)
//Space Complexity: O(K)
/*
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int prefixMod = 0, result = 0;

        // There are k mod groups 0...k-1.
        int[] modGroups = new int[k];
        modGroups[0] = 1;

        for (int num: nums) {
            // Take modulo twice to avoid negative remainders.
            prefixMod = (prefixMod + num % k + k) % k;
            // Add the count of subarrays that have the same remainder as the current
            // one to cancel out the remainders.
            result += modGroups[prefixMod];
            modGroups[prefixMod]++;
        }

        return result;
    }
}
*/
















//누적합을 이용하여 푸는 문제
//2-23-01-23--[2]
//아래 [Java] BOJ 10986 나머지 합 (누적합)  해결법 참조 (백준)
//https://velog.io/@jodawooooon/Java-BOJ-10986-%EB%82%98%EB%A8%B8%EC%A7%80-%ED%95%A9  
//input: nums=[4,5,0,-2,-3,1] , k = 5 -> output: 7
/*
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
*/
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