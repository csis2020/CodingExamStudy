

//2023-11-03
//Idea1 : using HashMap<key, value> : key is nums[i], value is frequency
//Time Complexity: O(N)
//Space Complexity: O(N)
/*
class Solution {
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length <= 0){
            return Integer.MIN_VALUE;
        }
        
        int maxCount = 0;
        int maxNumber = nums[0];
        Map<Integer, Integer> countMap = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            
            int count = countMap.getOrDefault(nums[i], 0) + 1;
            if(count > maxCount){
                maxNumber = nums[i];
                maxCount = count;
            }
            countMap.put(nums[i], count);
        }
        
        return maxNumber;
    }
}
*/

//Idea2 : Sorting 
//Time Complexity: O(NlogN)
//Space Complexity: O(1)
/*
class Solution {
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length <= 0){
            return Integer.MIN_VALUE;
        }
        
        Arrays.sort(nums); // ascending order
        return nums[nums.length/2];
    }
}
*/

//Idea3: Boyer–Moore majority vote algorithm (Boyer-Moore 과반수 투표 알고리즘)
// 이 방법은 Majority element 가 n/2 보다 많은 경우 성립함. ( the number of majority > n/2)  , the  number of majority = n/2 이거나 n/2 보다 작으면 성립 안됨. (ex. [2,1,3,1])
//Time Complexity: O(N)
//Space Complexity: O(1)
/*
class Solution {
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length <= 0){
            return Integer.MIN_VALUE;
        }
        
        int majorityNum = nums[0];
        int count = 1;
        for(int i = 1; i < nums.length; i++){
            if(count == 0){
                majorityNum = nums[i];
                count++;
            }else if(majorityNum == nums[i]){
                count++;
            }else {
                count--;
            }
        }
        return majorityNum;
    }
}
*/
//Idea4: Bit manipulation 
//  각 배열의 요소들이 -10^9 ~ 10^9 범위인 것은 32bit integer 이니까 각 요소값들의 1~32bit 에 1 이 n/2 보다 많으면 majority number 의 bit 값임. 그걸 다 더하면 됨. 
// nums=[1,1,-1,-1,-1]  인경우 체크해볼것
//Time Complexity: O(N) <-- 32 * N => O(N)
//Space Complexity: O(1)

class Solution {
    public int majorityElement(int[] nums) {
        if(nums == null || nums.length <= 0){
            return Integer.MIN_VALUE;
        }
        
        int majorityNum = 0;
        
        for(int i = 0; i <32; i++){
            int bit = 1 << i;
            int count = 0;
            for(int j=0; j < nums.length; j++){
            //아래를 if((nums[j] & bit) > 0) 으로하면 [1,1,-1,-1,-1] 인 경우 잘못된 결과 나옴.  expected = -1 인데, 결과값이 2147483647 으로 나오게됨.
            //자바 integer 는 32bit 이고 범위는 -2^31 ~ 2^31 -1 =(-2,147,483,648 ~ 2,147,483,647) , int bit = 1 << 31 을 하면 bit = -2147483648  이 나옴. 
            //자바는 2의 보수법을 사용함. 1<<31 하면 2^31 이 되고 32bit 의 맨왼쪽비트인 most significant bit자리이다 (2^31, 2^30, ..., 2^1, 2^0). 2^31 이 1이면 2의 보수법에 의해 -2^31 = -2147483648 임 , 2의보수: 2진수 값을 모두 반전 시켜준 후, 1을 더해주는 방식이다. 
                if((nums[j] & bit) != 0){ 
                //if((nums[j] & bit) > 0){  // nums[j]가 음수이면 잘못된 결과 나옴. 
                    count++;
                }
            }
            if(count > nums.length/2){
               majorityNum |= bit;  // majorityNum += bit; 해도 똑같음.
            }
        }
        
        return majorityNum;
    }
}





