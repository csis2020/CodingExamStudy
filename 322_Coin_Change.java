//2022.09.16
//Limitation: if coins is null or size is zero, return -1;
//Input: coins = [1,2,5], amount = 11 --> Output: 3
//Input: coins = [2], amount = 3  ---> Output: -1
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