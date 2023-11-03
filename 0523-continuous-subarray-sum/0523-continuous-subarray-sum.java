
//2023-11-03
//idea:  560. Subarray Sum Equals K 와 유사 - Using HashMap
//      다른 점은 560 에서는 sum -k 를 key 로 찾았다면, 여기선 sum % k 를 찾음 (sub array 가 multiple of K 이니까 )
// Time Complexity: O(N)
// Space Complexity: O(N)
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        if(nums == null || nums.length <= 0 || k <= 0){
            return false;
        }
        
        Map<Integer, Integer> map = new HashMap<>(); //<key,value>= <sum%k, index>
        map.put(0, -1);
        int sum = 0;
        //nums=[23,2,4,6,7], k=6    nums=[23,2,6,4,7], k=6   nums=[23,2,6,4,7], k=13
        //sum:0, 23,25,29           sum: 23, 25,31, 35       sum:23,25,31,35,42
        //map:[0, -1][5,0] [1,1]    map: [0,-1][5,0][1,1]    map:[0,-1][10,0][12,1][5,2][9,3][3,4]
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
            int remainder = sum % k;
            if(map.containsKey(remainder)){
                if( i - map.get(remainder) >= 2){
                    return true;
                }                
            }else{
                map.put(remainder, i);
            }
        }
        
        return false;
    }
}