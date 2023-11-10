
//2023-11-10
//Dynamic Programming
class Solution{
    public int coinChange(int[] coins, int amount){
        if(coins == null || coins.length <= 0 || amount <= 0){
            return 0;
        }
        
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int i = 0; i < coins.length; i++){
            if(coins[i] <= amount){
                dp[coins[i]] = 1;  
            }
        }
        
        for(int i = 1; i <=amount; i++){
            for(int j = 0; j < coins.length; j++){
                int money = i - coins[j];
                if(money >= 0 && dp[money] != -1){
                    if(dp[i] < 0){
                        dp[i] = dp[money] +1;
                    }else{
                        dp[i] = Math.min(dp[i], dp[money] + 1);
                    }
                }
            }
        }
        
        return dp[amount];
    }
}

//2023-11-10
//Dynamic programming
//아래처럼 하니까 Time Limit Exceeded 가 남. 왜일까???
/*
class Solution{
    public int coinChange(int[] coins, int amount){
        if(coins == null || coins.length <= 0 || amount <= 0){
            return 0;
        }
        
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;   
        return recursiveCheck(coins, amount, dp);
    }
    
    int recursiveCheck(int[] coins, int amount, int[] dp){
        if(amount < 0){
            return -1;
        }
        //if(amount == 0){
        //    return 1;
        //}
        
        if(dp[amount] >= 0){
            return dp[amount];
        }
        
        int minCount = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++){
            int count = recursiveCheck(coins, amount - coins[i], dp);
            if(count >= 0){
                minCount = Math.min(minCount, count + 1);
            }
        }
        
        if(minCount != Integer.MAX_VALUE){
            dp[amount] = minCount;
            return dp[amount];
        }
        
        return -1;        
    }
}
*/


























//2023.Septemmber.1
//limitation : if clins array is ascending order? => maybe not, 
// coins = [1, 2, 5], amount = 6

// amount[1] = 1
// amount[2] = min(amount[2-1] + 1, amount[0] + 1)  => min(2,1) => 1
// amount[3] = min(amont[3-1] +1, amount[3-2] +1) =>min(2, 2) => 2
// ...
// Time Complexity: O(n*m) n:amount , m: coins.length
// Space Complexity : O(n) n: amount
/*
class Solution{
    public int coinChange(int[] coins, int amount){
        if(coins == null || coins.length <= 0 || amount <= 0){
            return 0;
        }

        //Arrays.sort(coins);//ascending order //don't need to sort

        int[] fewest = new int[amount+1]; 
        // default value is '-1'
        //Arrays.fill(fewest, Integer.MAX_VALUE);
        Arrays.fill(fewest, amount+1);
        fewest[0] = 0;
        int current = 1;
        //Input: coins = [2], amount = 3
        //amount[0]=0, amount[1] = -1, amount[2] = 1, amount[3] = -1 => return -1
        //Input: coins = [1], amount = 0 -> return '0'
        //coins = [1,2,5], amount = 11
        //a[0] =0
        //a[1] = a[0] + 1 -> 1
        //a[2] = a[0] + 1 -> 1 , min(1, a[1] +1) -> 1
        //a[3] = a[1] + 1 -> 2, min(2, a[2]+1) -> 2
        //a[4] = a[2] + 1 -> 2, min(2, a[3] +1) -> 2
        //a[5] = a[0] +1 ->1 , min(1,a[3] +1) -> 1, min(1, a[4] +1) -> 1
        //a[6] = a[1] + 1 -> 2, min(2, a[4] +1) -> 2, min(2, a[5] +1) -> 2
        //a[7] = a[2] + 1 -> 2, min(2, a[5] +1) ->2, min(2, a[6]+1) -> 2
        //a[8] = a[3] +1 -> 3, min(3, a[6] +1) -> 3, min(3, a[7]) -> 3
        //a[9] = a[4] +1 -> 3, min(3, a[7] +1)-> 3, min(3, a[8] +1) -> 3
        //a[10] = a[5] +1 -> 2, min(2, a[8] +1) -> 2, min(2, a[9]+1) -> 2
        //a[11] = a[6] +1 -> 3, min(3, a[9] +1) ->3, min(3, a[10]+1) -> 3
        while(current <= amount){
            //for(int i = coins.length -1; i >= 0; i--){ //don't think about order
            for(int i =0; i < coins.length ; i++){
                int tempAmount = current - coins[i];
                if(tempAmount >= 0 && fewest[tempAmount] != (amount+1)){
                    fewest[current] = Math.min(fewest[current], fewest[tempAmount] +1);
                }
            }
            current++;
        }
        
        return (fewest[amount] == amount+1) ? -1 : fewest[amount];
    }
}
*/

//Limitation: if coins is null or size is zero, return -1;
//Input: coins = [1,2,5], amount = 11 --> Output: 3
//Input: coins = [2], amount = 3  ---> Output: -1
//Input: coins = [2], amount = 1  ---> Output: -1
//Input: coins = [186,419,83,408], amount = 6249 ---> Output: 20
//Input: coins = [470,35,120,81,121], amount = 9825 ---> Output:30

//2022-12-03-iteration - DP-bottom up
//Time Complexity: O(amount * coins.length)
//Space Compleixty : O(amount)
/*
class Solution{
    public int coinChange(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount < 0){
            return -1;
        }
        if(amount == 0){
            return 0;
        }

        int[] fewest = new int[amount +1]; // to match amount -index
        Arrays.fill(fewest, amount+1) ; // by default, max value
        fewest[0] = 0;

        for(int i = 1; i <= amount; i++){
            for(int j = 0; j < coins.length; j++){
                int remain = i - coins[j];
                if(remain >= 0){
                    fewest[i] = Math.min(fewest[i], fewest[remain] + 1);
                }
                                
            }
        }
        return fewest[amount] == (amount+1) ? -1 : fewest[amount];
    }
}
*/
//2022-12-03 - recursive - DP-Top down
//Time Complexity: O(amount * coins.length) => 트리를 생각해봤을때 가장 깊이 내려가는것이 $1 가 amount 만큼 내려가는것이라 트리 깊이가 amount 가 되고 $1 일때 각 sub tree 에서 coins 개수만큼 체크함. (amount * coins.length)다른 sub tree 들에서는 memoization 에 의해 더이상 아래로 내려가지 않음. 
//Space Compleixty : O(amount)
/*
class Solution{
    private int[] fewestCoin; //for memoization
    public int coinChange(int[] coins, int amount){
        
        if(coins == null || coins.length == 0 || amount < 0){
            return -1;
        }

        if(amount == 0){
            return 0;
        }

        //default value : 0
        fewestCoin = new int[amount + 1]; // match 'index - amount'' 
        return recursiveCheck(coins,  amount);

    }

    private int recursiveCheck(int[] coins, int amount){

        if(amount < 0){ // cannot make the coin set
            return -1;
        }
        if(amount == 0){ // find the coin set. 
            return 0;
        }
        if(fewestCoin[amount] > 0){ //already know the coin sets
            return fewestCoin[amount];
        }

        int minCount = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++){
            int result = recursiveCheck(coins, amount - coins[i]);
            if(result >= 0){
                minCount = Math.min(minCount, result+1);
            }           
        }
        fewestCoin[amount] = (minCount == Integer.MAX_VALUE) ? -1 : minCount;

        return fewestCoin[amount];

    }

}
*/
//2022.10.03
//recursive 안쓴경우 
//Time complexty:O(size of coins array * amount), Space complexity: O(amount)
/*
class Solution{
    public int coinChange(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount < 0){
            return -1;
        }
        
        if(amount == 0){
            return 0;
        }
        
        int[] minCoinChange = new int[amount+1]; 
        
        for(int i = 1; i <= amount; i++){
            
            int min = amount+1;
            for(int j = 0; j < coins.length; j++){
                int prev = i - coins[j];
                if( prev >= 0){
                    min = Math.min(min, minCoinChange[prev] +1);
                }
            }
            
            minCoinChange[i] = min;
        }
        
        
        if(minCoinChange[amount] == (amount+1)){
            //There is no coin set 
            return -1;
        }
        
        //return the minimum number of Coin set 
        return minCoinChange[amount];
    }
}
*/

//2022.10.03
//Time Complexity: O(size of coin* amount) : coin 이 1 인 경우 (amount -coin) depth 가 amount 와 동일
//Space Complexity: O(amount) //(amount+1) is memoization table size , recursive depth 도 동일하게 최대 amount 이니까 
//                             2*amount 인것 같은데 결국 그래서 amount 인 것일까?
/*
class Solution{
    public int coinChange(int[] coins, int amount){
        if(coins == null || coins.length == 0 || amount < 0){
            return -1;
        }
        
        if(amount == 0){
            return 0;
        }
        
        //This array memorizes the minimum number of coins to make the amount of index.
        int[] numMinCoinChange = new int[amount +1]; // default value => all set '0'
        
        return findMinCoinChange(coins, amount, numMinCoinChange);
    }
    
    private int findMinCoinChange(int[] coins, int amount, int[] numMinCoinChange){
        
        if(amount < 0){
            //cannot find right coin
            return -1;
        }
        
        if(amount == 0){
            //no more coin needed.
            return 0;
        }
        
        //return the memorized result
        if(numMinCoinChange[amount] != 0){
            return numMinCoinChange[amount];
        }
        
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++){
            int result = findMinCoinChange(coins, amount - coins[i], numMinCoinChange);
            if(result >=0){
                min = Math.min(min, result+1);
            }
            //System.out.println("amount:" + amount + ",coins:"+coins[i]+",resultNum:" + result +", min:"+ min);
        }
        
        if(min == Integer.MAX_VALUE){
            //no matched coins' set
            numMinCoinChange[amount] = -1;
        }else{
            //succeed to find coins' set to make the amount
            numMinCoinChange[amount] = min;
        }
        
        return numMinCoinChange[amount];
    }
}
*/

/*
//2022.09.16
//Time Complexity: O(amount * coins.length) , Space Complexity : O(amount)
class Solution {
    public int coinChange(int[] coins, int amount) {
        
        if(coins == null || coins.length == 0){
            return -1;
        }
        
        if(amount == 0){
            return 0;
        }
        
        int[] fewestCoins = new int[amount+1];         
        
        Arrays.fill(fewestCoins, amount+1); // initialize as max value
        
        fewestCoins[0] = 0;
        
        for(int i = 1; i <= amount; i++ ){
            for(int j = 0; j < coins.length; j++){
              
                if( i >= coins[j]){
                    fewestCoins[i] = Math.min(fewestCoins[i], fewestCoins[i - coins[j]] + 1);                   
                }
            }            
        }
        
        return (fewestCoins[amount] < (amount+1)) ? fewestCoins[amount] : -1;
    }
}                                                                                         
*/