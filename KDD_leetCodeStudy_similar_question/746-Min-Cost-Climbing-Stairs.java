//2022-10-03
//limitation: cost.length should be bigger than 1
// Input: cost = [1,100,1,1,1,100,1,1,100,1]  -> Output: 6
// Input: cost = [10,15,20] -> Output: 15

//Time complexity: O(n), Space complexity: O(1)
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        
        if(cost == null || cost.length < 2){
            return 0;
        }
        
               
        //n = cost.length;
        //f(0) = 0
        //f(1) = 0   //possible to start step1.
        //f(2) = min (f(1) + cost[1], f(0) + cost[0]);
        //f(n) = min (f(n-1) + cost[n-1], f(n-2) + cost[n-2]);
        //
        
        int targetStep = cost.length;
        
        int prePrevCost = 0;  //f(0), no step
        int prevCost = 0; 
        int minCost = 0;
        
        //cost = [1,100,1,1,1,100,1,1,100,1]
         //i   preCost cost[i-1]   prePrevCost cost[i-1],   minCost
         //2:   0      cost[1]:100    0        cost[0]:1    (100,1)->1
         //3:   1      cost[2]:1      0        cost[1]:100  (1+1, 0+100)->2
         //4:   2      cost[3]:1      1        cost[2]:1    (2+1, 1+1)-> 2
         //5:   2      cost[4]:1      2        cost[3]:1    (2+1, 2+1)-> 3
         //6:   3      cost[5]:100    2        cost[4]:1    (3+100, 2+1) -> 3
         //7:   3      cost[6]:1      3        cost[5]:100  (3+1, 3+100) -> 4
         //8:   4      cost[7]:1      3        cost[6]:1    (4+1, 3+1) -> 4
         //9:   4      cost[8]:100    4        cost[7]:1    (4+100, 4+1) ->5
         //10:  5      cost[9]:1      4        cost[8]:100  (5+1, 4+100) -> 6
        for(int i = 2; i <= targetStep; i++){
            minCost = Math.min(prevCost + cost[i-1], prePrevCost + cost[i-2]);
            prePrevCost = prevCost;
            prevCost = minCost;            
        }
        
        return minCost;
    }
}