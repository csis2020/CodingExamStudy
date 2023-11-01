
//2023-11-01
//limitation: all numbers in array are different? no. 만약 [1,1,1] 이렇게 있고 k=2 인경우 
//      다 다른것으로 쳐주나?
//      Does the array has '0' element? no , 1 <= nums[i] <= 1000

//idea : two pointer 
//      [10,5,2,6], k = 100 인 경우 
//      [10]->[10,5]->[5,2]->[5,2,6] 순으로 product 이 k보다 작으면 계속 end point 이동, product 이 k보다 커지면, start point 이동
//      subarray개수는 [10]:1개->[10,5]2개->[5,2]2개->[5,2,6]3개 가됨
//      (앞에 이미 count 된 subarray를 빼고 계산을 하기때문에 매번 숫자가 추가될때, 새로 추가된 숫자를 꼭 포함한 subsrray 를 계산하면, subarray 숫자개수만큼이 됨.)
class Solution{
    public int numSubarrayProductLessThanK(int[] nums, int k){
        if(nums == null || nums.length <= 0 || k < 0){
            return 0;
        }
        
        int totalNumber = 0;
        int subProduct = 1;
        int start = 0;
        //nums:[10,5,2,6] , k =100                nums=[1,2,3] , k = 0
        //subProduct:1 , 10,50, 100,10,60         subProduct:1,2,1,3,1
        //start:0,1                               start: 0, 1,2,3
        //totalNumber:0, 1,1+2 = 3, 3+2=5,5+3=8   totalNumber:0
        for(int i = 0; i < nums.length; i++){
            subProduct *= nums[i];
            while(subProduct >= k && start <= i){
                    subProduct /= nums[start];
                    start++;                    
            }
            if(subProduct < k){
                totalNumber += i - start + 1;
            }
        }
        
        return totalNumber;
    }
}


//limitation: are those all numbers different? 
//              if nums is null or size is 0, return 0 is OK?

//Input: nums = [10,5,2,6], k = 100 -> Output: 8
// Input: nums = [1,2,3], k = 0  -> Output: 0
// Time Complexity : O(N), Space Complexity: O(1)

//2022.09.25
/*
class Solution{
    public int numSubarrayProductLessThanK(int[] nums, int k){
        
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        if(k < 1){
            return 0;
        }
        
        int start = 0; 
        int totalNum = 0; 
        int contProduct = 1; 
        
        
        //[10,5,2,6] , k=100
        //s:0, cp:10, total:1  ( [10])
        //s:0, cp:50, total:1+2=3  ([10,5], [5])
        //s:1, c:10,  total: 3+2=5  ([5,2], [2])
        //s:1, c:60,  total: 5+3 =8  ([5,2,6], [2,6], [6])
        //[1, 2, 3], k = 0
        //s:0, cp:1, total: 0
        //s:1, cp:2, total:0
        //s:2, cp:3, total:0
        for(int i = 0; i < nums.length ; i++){
            
            contProduct *= nums[i];
            while((start <= i) && contProduct >= k){
                contProduct /= nums[start];
                start++;
            }
            
            totalNum += i - start + 1;
        }
        
        return totalNum;
    }
}
*/

//2022.09.19
/*
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
*/