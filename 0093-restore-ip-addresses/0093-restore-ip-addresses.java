
//2023-11-13
//Backtracking
//Time Complexity: O(2^N * N) <--- N 은 입력 string의 길이, 매 char 마다 자를지말지 2가지 경우의수 * 매 경우의수 끝에 size N 의 string copy. 
//Space Complexity: O(1) ?? <- recursive call stack 의 max depth 가 4, tempList 의 최대사이즈가 4 이면 상수라서 O(1)일까?
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if(s == null || s.length() < 4){
            return result;
        }
        
        List<String> tempList = new ArrayList<>();
        backTracking(s, 0, tempList, result);
        
        return result;
    }
    
    void backTracking(String s, int start, List<String> tempList, List<String> result){
        //return condition
        if(start == s.length() && tempList.size() == 4){
            result.add(String.join(".", tempList));
            return;
        }
        
        if(start < s.length() && tempList.size() >= 4){
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        
        for(int end = start+1; end <= s.length(); end++){
            if(end - start > 3){
                break;
            }
            String substring = s.substring(start, end);
            
            if(substring.length() == 3 && substring.compareTo("255") > 0){
                break;
            }
            tempList.add(substring);
            backTracking(s, end, tempList, result);
            tempList.remove(tempList.size() -1);

            if(substring.compareTo("0")==0){
                break;
            }
        }
    }
}