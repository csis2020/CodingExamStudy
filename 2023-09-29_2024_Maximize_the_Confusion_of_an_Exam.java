
//idea : Sliding window + int[2] array
//Longest Repeating Character Replacement 와 같은 case
//- key point: 속도 개선을 위해, 매번 현재 window 내에 있는 최대반복되는 T/F 의 숫자를 매번 재계산하지 않고 그대로 쓰다가 (count 가 줄어드는 경우는 새로 계산하지 않고, 이전 max 값 그대로 쓰고 for문으로 계속 전진) 더 큰 count 가 나올때만 update 한다. 
//   ==>     그 이유는 longest 를 찾는 것이기 때문에 더 작은 것은 count 해 봤자, longest 가 당연히 아니기 때문. => 즉 할 필요가 없고 시간낭비인 셈.

//2023-09-29
//Time Complexity: O(N)
//Space Complexity: O(1) , 사이즈 2 의 고정된 배열 사용
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        if(answerKey == null || answerKey.length() <= 0){
            return 0;
        }
        
        int[] answerFreq = new int[2]; //0: false, 1:true
        int maximize = 0;
        int start = 0;
        int maxFrequency = 0;
        //Input: "T->F->F->T", k = 1          /Input: answerKey = "T->T->F->F", k = 2
        //start:0                               start:0
        //end:3                                 end:3
        //Array: [2,2]                          Array:[2,2]
        //maxFreq:2                             maxFreq:2
        //maxmize:3                             maxmize:4
        
        for(int end = 0; end < answerKey.length(); end++){
            char ch = answerKey.charAt(end);
            
            int idx = (ch == 'T') ? 1 : 0;
            answerFreq[idx]++;
            maxFrequency = Math.max(maxFrequency, answerFreq[idx]);
            int difference = end - start + 1 - maxFrequency;
            if(difference > k){
                ch = answerKey.charAt(start);
                idx = (ch == 'T') ? 1 : 0;
                answerFreq[idx]--;
                start++;
            }else{
                //maximize = Math.max(maximize, end - start +1);
                maximize++; ////else 문으로 오는 경우는 증가하는 경우 뿐임. 
            /*
            start 와 end 의 위치를 생각해보면 왜 이렇게 되는지 이해할 수 있음. 
            - 매loop 돌때마다 end 는 1증가
            - 만약 difference가 k보다 크면 start 1 증가=> window 위치가 sliding 되는 것이고
                WINDOW 사이즈는 그대로
            - difference 가 k보다 작거나같으면 기존대비 end 만 증가한 것이기 때문에 
                WINDOW 사이즈가 1 커짐 
            => 결국 else 로 들어오는 경우는 기존보다 window 사이즈가 1 커질때 일뿐
                */
            }
        }
        return maximize;
    }
}