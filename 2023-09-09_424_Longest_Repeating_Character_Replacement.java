//Input: s = "ABAB", k = 2 -> output: 4
//Input: s = "AABABBA", k = 1 -> output: 4

//2023-09-28
//Sliding window 방법가능 
//  1) Sliding window + HashMap (<Key, value> = <Character, Count)
//  2)만약 input 이 alpahbet(only lowercase, 또는 uppercase) 이면 Sliding window + int[26] 배열이용 가능 
//- key point: 속도 개선을 위해, 매번 현재 window 내에 있는 최대반복되는 char 의 숫자를 매번 재계산하지 않고 그대로 쓰다가 (count 가 줄어드는 경우는 새로 계산하지 않고, 이전 max 값 그대로 쓰고 for문으로 계속 전진) 더 큰 count 가 나올때만 update 한다. 
//   ==>     그 이유는 longest 를 찾는 것이기 때문에 더 작은 것은 count 해 봤자, longest 가 당연히 아니기 때문. => 즉 할 필요가 없고 시간낭비인 셈.

//Time Complexity: O(N)
//Space Complexity: O(1) - 최대 26으로 size fix

//1)배열 이용하는 경우 
class Solution{
    public int characterReplacement(String s, int k) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        
        int[] repeatingCh = new int[26];
        int start = 0;
        int maxFrequency = 0;
        int longest = 0;
        for(int end = 0; end < s.length(); end++){
            char ch = s.charAt(end);
            repeatingCh[ch-'A']++;
            maxFrequency = Math.max(maxFrequency, repeatingCh[ch-'A']);
            int difference = end - start + 1 - maxFrequency;
            if(difference > k){
                ch = s.charAt(start);
                repeatingCh[ch -'A']--;
                start++;
            }else{
                longest = Math.max(longest, end - start + 1);
            }
        }
        return longest;
    }
}
        
//2)HashMap 이용하는 경우 
/*
class Solution{
    public int characterReplacement(String s, int k) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        
        int longest = 0;
        int maxFrequency = 0;
        Map<Character, Integer> repeatingMap = new HashMap<>(); //<key,value> = <character, number of count>
        int start = 0;
        //input: a->b->a->b  / k=2  ,//input: c,a,a,a, b->c->b->a->b->b->a  / k:2
        //start:0                   start:4
        //end:0,1,2,3               end: 10
        //maxFreq:0, 1,2            maxFreq:4
        //Map: <a,2><b,2>           Map:<c,1><a,2><b,4>
        //longest:0,1,2,3,4         longest:0,1,2,3,4,5,6
        for(int end = 0; end < s.length(); end++){
            char ch = s.charAt(end);
            repeatingMap.put(ch, repeatingMap.getOrDefault(ch,0)+1);
            maxFrequency = Math.max(maxFrequency, repeatingMap.get(ch));
            int difference = end - start + 1 - maxFrequency;
            if(difference > k){
                ch = s.charAt(start);
                repeatingMap.put(ch, repeatingMap.get(ch) - 1);
                start++;
            }else{
                longest = Math.max(longest, end - start +1);
            }            
        }
        return longest;
    }
}
*/

//2023-01-28
//[idea] : alphabet 각 char 의 개수를 count 하기위한 int 배열을 선언하고
// string 의 각 character 마다 count 를 하면서 동시에 (substring.length - maxCharLength) > k 인지 체크하는 것 
//Time Complexity: O(n)
//Space Complexity: O(1)  <-- alphabet size 26.
/*
class Solution{
    public int characterReplacement(String s, int k) {
        if(s == null || s.length() == 0 || k < 0){
            return 0;
        }

        int[] numOfCh = new int[26];
        int maxRepeat = 0;
        int maxStrLength = 0;
        int start = 0;

        //k=2, s = "ACBABBA" -> "-CBABBA" -> "--BABBA"
        //[A]:2, [B]:3  ,[C]:1
        //index:0  1  2  3  4  5  6
        //start:0  0  0  0  1  1  2
        //maxR: 1  1  1  2  2  3  3
        //curL: 1  2  3  4  4  5  5
        //maxL: 1  2  3  4  4  5  5
        for(int i = 0; i < s.length(); i++){
            int index = s.charAt(i) - 'A';
            numOfCh[index]++;

            maxRepeat = Math.max(numOfCh[index], maxRepeat);
            int curLength = i - start + 1; 
            if((curLength - maxRepeat) > k ){
                numOfCh[s.charAt(start) - 'A']--;
                start++;
                curLength--;
            }

            maxStrLength = Math.max(maxStrLength, curLength);
        }

        return maxStrLength;
    }
}
*/


//바꿀수 있는 개수가 K 임. substring 내에 바꿀문자가 K 개만 존재하도록 유지하는게 아이디어 
/*
 1) Alphabet 26글자를 저장할 배열 생성 (length: 26)
 2) string 길이만큼 for 문을 돌면서 alphabet 개수를 배열에 update -이때 max 개수 저장해놓기
 3) (substring 개수 - max 개수) > K 이면 바꿀수 있는 개수를 초과했으니 
      3-1) start 위치 증가 ( 증가시키기전, start 위치의 alphabet 을 배열에서 decrease)
      (원래는 현재 윈도우 (substring)안에서 max repeated 값을 찾아야 하는데, 그렇게 하지 않고 그냥 start 포지션만 하나 증가 시킴. 현재 윈도우안에서 max repeated 를 찾으려면 배열을 매번 새로 search 해야한다. 그러나 사실 나중에 더 큰 max repeated 가 나오기 전까지는 처음 찾았던 max repeated 에 의해 발견한 window size 가 최대임. 그래서 더큰 repeated 를 만날때까지 start 를 하나씩 줄여가면서 하는게 성능상 나음. 아래 leetcode 에 영어로 설명된 내용이 있음 참고할것. 
*/
/*
Worth making a note here that maxFrequency doesn't tell us about the maximum frequency of a character in the current window. Rather, it tells us about the maximum frequency of a character seen until now. 

if you observe the maximum frequency in the current window, it is 2(of A or B).
Which actually makes the string invalid, because we can't convert all 5 characters into A or B with at most 2 replacements. But we previously seen a valid window of size 5. We don't want to decrease the size of the window. maxFrequency helps us achieve that.
*/
//Time Complexity: O(N)
//Space Complexity: O(1)  <-- alphabet size 26.
/*
class Solution{
    public int characterReplacement(String s, int k) {
        if(s == null || s.length() == 0 || k < 0){
            return 0;
        }

        int[] alphabetCnt = new int[26];

        int start = 0; 
        int maxRepeat = 0;
        int longest = 0;
        for(int end = 0; end < s.length(); end++){
            char ch = s.charAt(end);
            alphabetCnt[ch-'A']++;
            maxRepeat = Math.max(maxRepeat, alphabetCnt[ch-'A']);

            int nonRepeat = end - start + 1 - maxRepeat;
            if(nonRepeat > k){
                alphabetCnt[s.charAt(start) - 'A']--;
                start++;
            }

            longest = Math.max(longest, end - start + 1);
        }

        return longest;
    }
}
*/
//2022.11.11
/*
class Solution {
    public int characterReplacement(String s, int k) {
        if(s == null || s.length() == 0){
            return 0;
        }

        int longestSize = 0;
        int left = 0;
        //int right = 0;
        int maxRepeatN = 0;
        int[] alphabet = new int[26];
        int currSize, diff;
        
        for(int right = 0; right < s.length(); right++){
            alphabet[s.charAt(right) - 'A']++;
            //maximum repeated number so far
            maxRepeatN = Math.max(maxRepeatN, alphabet[s.charAt(right) - 'A']);
            currSize = right - left + 1;
            diff = currSize - maxRepeatN;
            if(diff > k){
                alphabet[s.charAt(left) - 'A']--;
                left++;
            }
            //longest repeating character replacement
            longestSize = Math.max(longestSize, right - left + 1);
        }

        return longestSize;
    }
}
*/
/*
//leetcode solution
class Solution {
    public int characterReplacement(String s, int k) {
        int start = 0;
        int[] frequencyMap = new int[26];
        int maxFrequency = 0;
        int longestSubstringLength = 0;

        for (int end = 0; end < s.length(); end += 1) {
            // if 'A' is 0, then what is the relative order
            // or offset of the current character entering the window
            // 0 is 'A', 1 is 'B' and so on
            int currentChar = s.charAt(end) - 'A';

            frequencyMap[currentChar] += 1;

            // the maximum frequency we have seen in any window yet
            maxFrequency = Math.max(maxFrequency, frequencyMap[currentChar]);

            // move the start pointer towards right if the current
            // window is invalid
            Boolean isValid = (end + 1 - start - maxFrequency <= k);
            if (!isValid) {
                // offset of the character moving out of the window
                int outgoingChar = s.charAt(start) - 'A';

                // decrease its frequency
                frequencyMap[outgoingChar] -= 1;

                // move the start pointer forward
                start += 1;
            }

            // the window is valid at this point, note down the length
            // size of the window never decreases
            longestSubstringLength = end + 1 - start;
        }

        return longestSubstringLength;
    }
}
*/