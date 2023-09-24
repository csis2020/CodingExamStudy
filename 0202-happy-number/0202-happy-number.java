//2023-09-23
//2-pointers

//Idea: //According to the description, if it does not include 1, it loops endlessly in a cycle. 
//      -> to detect cycle,
//      1) First solution, use HashSet. 
//          if the new sum is already in HashSet, it means there is cycle. 
//      2) Second Solution, use 2-pointers. 
//          (fast pointer - move 2 steps at a time, slow pointer - move 1 step at a time)

// Second Solution - use 2-pointers
// Time Complexity: O(logN) <-- 10으로 계속 나누니까 
//  (만약 9999 라면 =>대충 10000 => log10000=4 (나누는 수가 10인 log) => log4 (< log10) = 대충 1,
//  (즉, logN + log(logN) + log(log(logN))) ... 이렇게 됨. 
// Space Complexity: O(1)
/*
class Solution {
    public boolean isHappy(int n) {
        if(n <= 0){
            return false;
        }
        
       
        //int fast = n;
        //int slow = n;
        //while(fast != 1){
        //    fast = getSum(getSum(fast));
        //    slow = getSum(slow);
        //    if(fast == slow){ //이렇게 하면 fast 와 slow 가 둘이 1로 같은경우 true 가 아닌 false 를 리턴하게 됨. 
        //        return false;
        //    }
       // }
      
        int fast = getSum(n); //to make that fast is different from slow.
        int slow = n;
        while(fast != 1 && fast != slow){
            fast = getSum(getSum(fast));
            slow = getSum(slow);
        }
                
        return fast==1 ; //이 연산자의 결과 boolean 값이 return 됨 
    }
    
    //n= 19->1
    //digiNum: 9 -> 1
    //sum: 81-> 82
    int getSum(int n){
        int sumSquaresOfDigits = 0;
        
        while(n > 0){
            int digitNum = n % 10;
            sumSquaresOfDigits += digitNum * digitNum;
            n = n/10;
        }
        
        return sumSquaresOfDigits;
    }
}
*/
//first Solution -using HashSet . (계산결과값이 1 이 아니고, HashSet 에 이미 값이 있다면, cycle)
class Solution {
    public boolean isHappy(int n) {
        if(n <=0){
            return false;
        }
        
        Set<Integer> sumResult = new HashSet<>();

        while(n != 1){
            n = getSum(n);
            if(sumResult.contains(n)){
                return false;
            }else{
                sumResult.add(n);
            }
        }
        return true;
    }
    
    int getSum(int n){
        int sumSquareDigits = 0;
        while(n > 0){
            int digit = n % 10;
            sumSquareDigits += digit * digit;
            n = n / 10;
        }
        return sumSquareDigits;
    }
}