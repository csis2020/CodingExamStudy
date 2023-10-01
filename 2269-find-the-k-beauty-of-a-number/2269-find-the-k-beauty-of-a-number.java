
//2023-10-01
//Discuss 에서 풀이한 간단한 방법 -> 코드는 간단해 졌고, 이해하는데는 시간을 좀 써야 했음
//      여기서는 숫자를 하나하나 list 에 옮길 필요없이 숫자 그대로 풀이가 가능함. /
//Time Complexity: O(N) , N is number of digits of 'num'. Max is 9
class Solution {
    public int divisorSubstrings(int num, int k) {
        if(num == 0 || k == 0){
            return 0;
        }
        
        int kBeauty = 0;
        int divisor = 0;
        int power = 1; //power of 10
        
        for(int value = num; value > 0; value = value/10 ){
            divisor += value % 10 * power;
            k--;
            if(k > 0){
                power *= 10;
            }else{
                if( divisor != 0 &&  num%divisor == 0 ){
                    kBeauty++;
                }
                divisor = divisor / 10;
            }
        }
        return kBeauty;
    }
}

//내가 풀이한 방법 - num 의 숫자들을 하나하나 list 에 담음 
//Time Complexity:O(N) , N is number of digits of 'num', Max is 9
//Space Complexity: O(N)
/*
class Solution {
    public int divisorSubstrings(int num, int k) {
        if(num == 0 || k == 0){
            return 0;
        }
        
        List<Integer> numbers = new ArrayList<>();
        int value = num;
        while(value > 0){
            int temp = value % 10;
            numbers.add(temp);
            value = value / 10;
        }
        
        int kBeauty = 0;
        //Input: num = 240, k = 2,  list_numbers:0,4,2
        //count: 0, 1,2/ 0,1,2
        //tens: 1, 10/ 1,10
        //sum: 0, 40/ 4, 24
        //kBeauty: 1,2
        for(int i = 0; i < (numbers.size() - k + 1) ; i++){
            int count = 0;
            int tens = 1;
            int sum = 0;
            while(count < k){
                sum += numbers.get(i + count) * tens;
                tens *= 10;
                count++;
            }
            if(sum != 0 && (num % sum) == 0){
                kBeauty++;
            }
        }
        return kBeauty;
    }
}
*/