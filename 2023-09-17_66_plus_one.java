// 2023-09-17
//leetcode solution: 기본 idea 는  999...9 와 같이 모두 1인 경우만 배열사이즈가 커진다. ( 이때  배열은 100...0 이 됨. ) 
//                  9879 -> 1증가 -> 9880 과 같이 숫자중간에 9보다 작은수가 있으면 그 숫자가 1커지고 나면 그다음은 더이상 계산할 필요가 없다. 

class Solution {
    public int[] plusOne(int[] digits) {
        if(digits == null || digits.length <= 0){
            return null;
        }

        for(int i = digits.length -1 ; i >= 0; i--){
            if(digits[i] == 9){
                digits[i] = 0;
            }else{
                digits[i]++;
                return digits;
            }
        }

        digits = new int[digits.length +1];
        digits[0] = 1; // '1000...0'
        return digits;
    }
}

//내가 푼 솔루션 : 무조건 n-1 ~ 0 까지 다 carry 계산하고, 최종 carry = 1 인경우면 그때 배열사이즈를 하다더큰걸 만든다. 
//Time Complexity :O(N)
//Space Complexity : O(1)~ O(N)
/*
class Solution {
    public int[] plusOne(int[] digits) {

        int carry = 0;
        digits[digits.length -1]++;
        for(int i = digits.length - 1; i >= 0; i--){
            digits[i] += carry;
            if(digits[i] >= 10){
                carry = 1;
                digits[i] %= 10;
            }else{
                carry = 0;
            }
            
        }
        if(carry > 0){
            int[] newDigits = new int[digits.length +1];
            newDigits[0] = 1;
            for(int i = 0 ; i < digits.length ; i++){
                newDigits[i+1] = digits[i];
            }
            return newDigits;
        }

        return digits;
    }
}
*/