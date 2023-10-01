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