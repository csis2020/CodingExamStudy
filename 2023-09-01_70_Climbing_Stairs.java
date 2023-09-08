//2023-August-01
//limitation: n > 0 ?
//to reach n steps : 2 ways: (n-2) + 2 steps , (n-1) + 1 steps 
// n:1 -> 1
// n:2 -> (2 steps), ( <stair 1> + 1 steps) => 2
// n:3 -> (<stair1> + 2 steps), (<stair2> + 1steps) => <stairs1> + <stairs2>

//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution{
    public int climbStairs(int n){

        if(n <= 0){
            return 0;
        }else if(n <= 2){
            return n;
        }

        int prePrev = 1;
        int prev = 2;
        int current = 0;
        int count = 3;
        //example n= 4
        //n:3 -> current: 1 + 2 = 3, prePrev:2, prev: 3
        //n:4 -> current: 2 + 3 = 5
        while(count <= n){
            current = prePrev + prev;
            prePrev = prev;
            prev = current;
            count++;
        }
        
        return current;
    }
}


//n > = 1

//Input: n = 2 ===> Output: 2
//Input: n = 3 ===> Output: 3
//2022-12-03
/*
class Solution{
    public int climbStairs(int n){
        if(n <= 0){
            return 0;
        }else if(n <=2){
            return n;
        }


        int first = 1;
        int second = 2;
        int third = 3;
        for(int i = 3; i <=n ; i++){
            third = first + second;
            first = second;
            second = third;
        }

        return third;
    }
}
*/
//2022-10-03
//Time Complexity: O(N) , Space complexity: O(1)
/*
class Solution{
    public int climbStairs(int n){
                
        if(n <=0){
            return 0;
        }

        //f(0) =0, f(1) = 1, f(2) = 2
        //f(n) = f(n-1) + f(n-2)
        
        if(n <=2){
            return n;
        }
        
        int prePrev = 1;  // start with f(1)
        int prev = 2; // start with f(2)
        int numWays = 0;
        
        for(int i = 3;  i <= n; i++){
            numWays = prev + prePrev;
            prePrev = prev;
            prev = numWays;
        }
            
        return numWays;
    }
}
*/
/*
class Solution {
    public int climbStairs(int n) {
        //f(1) = 1
        //f(2) = 2
        //f(3) = f(1) + f(2)
        //f(n) = f(n-1) + f(n-2)
        
        
        if(n == 1){ //f(1)
            return 1;
        }else if(n == 2){ //f(2)
            return 2;
        }else{
                    
            int pre2 = 1; //f(1)
            int pre1 = 2; //f(2)
            int count = 3;
            int output = 0;
            while(count <=n ){
                output = pre2 + pre1; //f(n) = f(n-2) + f(n-1)
                pre2 = pre1;
                pre1 = output;
                
                count++;
            }
            
            return output;
        }

    }
}
*/
//time limit exceeded
/*
class Solution {
    public int climbStairs(int n) {
        //f(1) = 1
        //f(2) = 2
        //f(3) = f(1) + f(2)
        //f(n) = f(n-1) + f(n-2)
        
        return recursiveCount(n);
        
    }
    
    private int recursiveCount(int n){
        if(n == 1){
            return 1;
        }else if(n == 2){
            return 2;
        }else{
            return recursiveCount(n-1) + recursiveCount(n-2);
        }
    }
}
*/