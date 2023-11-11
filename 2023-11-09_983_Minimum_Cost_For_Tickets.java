//2023-11-08
//Dynamic programming
//내가 느끼기에 dynamic programming 과 backtracking 의 차이는 backtracking 은 
// 다시 돌아와서 체크해야하기 때문에 1)set  2) bt(..) 함수 call 3)reset 와 같이 set/reset 하는 단계가 있다는 것이다. 
//idea: dp[days의 마지막날 +1] 으로 선언하고 
//      dp[i] = Min(dp[i-1] + costs[0], dp[i-7] + costs[1], dp[i-30] + costs[2]) 의 식을 이용
//Time Complexity:O(K) , k is the end day. ( the size of dp array)
//Space Complexity: O(K)
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        if(days == null || days.length <= 0 || costs == null || costs.length <= 0){
            return 0;
        }
        
        int endDay = days[days.length -1];
        int[] dp = new int[endDay +1]; //default : 0
        
        int count = 0; 
        for(int day=1 ; day <= endDay; day++){
            
            if(day < days[count]){
                dp[day] = dp[day-1];
            }else{
                dp[day] = Math.min(dp[day-1] + costs[0], 
                                   Math.min(dp[Math.max(0, day-7)]+costs[1], dp[Math.max(0, day-30)]+costs[2]));
                count++;
            }
        }
        
        return dp[endDay];
    }
}



//2023-11-08
//이건 Backtracking 이 아니라 dynamic programming. 뒤로 되돌아가서 다시 체크하는게 없음. 
//Time Complexity: ??? , 
//Space Complexity: O(N) <-- recursive call stack
//아래 솔루션으로는 다음 example 에서 wrong answer
//input : [1,4,6,9,10,11,12,13,14,15,16,17,18,20,21,22,23,27,28], costs = [3,13,45]
//expected : 44,   output:45 <-- wrong answer. 
/*
class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        if(days == null || days.length <= 0 || costs == null || costs.length <= 0){
            return 0;
        }
        
        int minCost = Integer.MAX_VALUE;
        for(int i = 0; i < days.length; i++){
            int cost = backTracking(days, costs, i) + costs[0]*i;
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }
    
    int backTracking(int[] days, int[] costs, int index){
        
        if(index >= days.length){
            return 0;
        }       
        
        
        int minCost = Integer.MAX_VALUE; 
        int endDate = 0;
        int count = 0;
        
        endDate = days[index] + 29;
        count = index;
        while(count < days.length && days[count] <= endDate){
            count++;
        } 
        int thirtyDays = backTracking(days, costs, count) + costs[2];

        int oneDay = backTracking(days, costs, index+1) + costs[0];
        
        endDate = days[index] + 6; 
        count = index;
        while(count < days.length && days[count] < endDate){
            count++;
        } 
        int twoDays = backTracking(days, costs, count) + costs[1];
        
           
        int currMin = Math.min(Math.min(oneDay, twoDays), thirtyDays);
        minCost = Math.min(minCost, currMin);
        
        return minCost;
    }
}
*/