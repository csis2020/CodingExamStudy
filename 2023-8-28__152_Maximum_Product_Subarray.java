//2023.August.28
//example
//nums[2,3,-2,4] 
//subMax:   2, 6, -2, 4
//                -12,-48
//totalMax: 2, 6,   6, 6  ==> 6
//nums[-2, 0, -1]
//subMax:   -2, 0, 0
//totalMax: -2, 0, 0
//nums[2,3,-2,4,-1] 
//subMax:  2, 6, -2, 4, -1
//               -12, -48, 48   //I need to remember its negative number, if it will be positive, I don't need to remember anymore.
//totalMax:2, 6,  6, 6,  48
//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution{
    public int maxProduct(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        int subMaxProduct = nums[0];
        int totalMaxProduct = nums[0];
        int subMinProduct = nums[0];

        //nums: [-2, 3,-4]
        //tempMax   , 3, -4   
        //tempMin   , -6 , 24
        //subMax: -2, (3,3,-6): 3 , (-4, -4,24):24
        //subMin: -2, (3,3, -6): -6, (-4, -4, 24):-4
        //totalMax -2, (-2, 3): 3, (3,24): 24
        for(int i = 1; i < nums.length; i++){
            int tempMaxProduct = subMaxProduct * nums[i];
            int tempMinProduct = subMinProduct * nums[i];
            subMaxProduct = Math.max(nums[i], Math.max(tempMaxProduct, tempMinProduct));
            subMinProduct = Math.min(nums[i], Math.min(tempMaxProduct, tempMinProduct));
            totalMaxProduct = Math.max(totalMaxProduct, subMaxProduct);
        }

        return totalMaxProduct;
    }
}

// limitation : if nums is null or size is zero return what?????
// Input: nums = [2,3,-2,4]  -> Output: 6
// Input: nums = [-2,0,-1] -> Output: 0
// Input: [-2,3,-4] -> Output: 24
//2022.12.02
//Time Complexity: O(N)
//Space Complexity: O(1)
/*
class Solution{
    public int maxProduct(int[] nums){
        if(nums == null || nums.length == 0){
            return 0;
        }

        int curMax = nums[0];
        int curMin = nums[0];
        int max = nums[0];
        for(int i = 1; i < nums.length ; i++){
            int newMax = curMax * nums[i];
            int newMin = curMin * nums[i];
            curMax = Math.max(nums[i], Math.max(newMax, newMin));
            curMin = Math.min(nums[i], Math.min(newMax, newMin));
            max = Math.max(max, curMax);
        }

        return max;
    }
}
*/
//Time Complexity: O(n),  Space Complexity: O(1)
//2022.09.25
/*
class Solution{
    public int maxProduct(int[] nums){
        
        if(nums == null || nums.length == 0){
            return 0; //???
        }
        
        int totalMax = nums[0];
        int contMax = nums[0];
        int contMin = nums[0];
        
        //2 ->  (3, 6, 6) -> (-2, -12, -6 ) -> (4, -48, -24)
        //Max    6              -2              4, 
        //Min    3              -12             -48
        //TotalM 6               6              6
        
        // -2  -> (3, -6, -6) -> (-4, -12, 24)
        //Max      3,               24
        //Min      -6               -12
        //T-Max    3                24
        for(int i = 1; i < nums.length ; i++){
            int mulContMax = nums[i] * contMax; 
            int mulContMin = nums[i] * contMin;
            contMax = Math.max(nums[i], Math.max(mulContMax, mulContMin));
            contMin = Math.min(nums[i], Math.min(mulContMax, mulContMin)); 
            
            //compare max and new contigous max
            totalMax = Math.max(contMax, totalMax);
        }
        
        return totalMax;
    }
}
*/
// 2022-09-14
/*
class Solution{
    
    public int maxProduct(int[] nums){
        
        if(nums == null || nums.length == 0){
            return 0; 
        }
        
        int max = nums[0]; 
        int curContigMin = nums[0];
        int curContigMax = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            
            int upDatedContigMin = curContigMin * nums[i];
            int upDatedContigMax = curContigMax * nums[i];
            
            //find current contiguous mininum product
            curContigMin = Math.min(upDatedContigMin, Math.min(upDatedContigMax , nums[i]));
            //find current contiguous maximum product
            curContigMax = Math.max(upDatedContigMin, Math.max(upDatedContigMax , nums[i]));
            //maximum product so far
            max = Math.max(curContigMax, max);
        }
        
        return max;
        
    }
}
*/
/*
class Solution {
    public int maxProduct(int[] nums) {
        if(nums == null || nums.length == 0){
            return Integer.MIN_VALUE;
        }
        
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int totalMax = nums[0];
        
        for(int i = 1; i < nums.length; i++){
            
            int productWithMax = maxSoFar * nums[i];
            int productWithMin = minSoFar * nums[i];
            
            //find maximum contiguous product so far
            maxSoFar = Math.max(nums[i], Math.max(productWithMax, productWithMin));
            //find minimum contiguous product so far
            minSoFar = Math.min(nums[i], Math.min(productWithMax, productWithMin));
            
            totalMax = Math.max(maxSoFar, totalMax);
        }
        
        return totalMax;
    }
}
*/