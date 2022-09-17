//2022-09-22
//Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"] -> Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5 1","dig2 3 6"]
//Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"] -> Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7"]

//Time Complexity: O(m*nlogn) , nlogn by sort , m: maximum length of a single log
//Space complxity: O(m*logn) -> O(m*logn), m: maximun length of a single log 
//  overiding 함수 안에서 each log 를 위한 String[] 를 할당 => m 
//  sort 함수 (quick sort)의 space complexity: O(logn) --> 따라서 m * log n

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        
        if(logs == null || logs.length == 0){
            return null;
        }
        
        Comparator<String> myComparator = new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                String[] strArr1 = s1.split(" ", 2);
                String[] strArr2 = s2.split(" ", 2);
                
                boolean isDigitContent1 = Character.isDigit(strArr1[1].charAt(0)); 
                boolean isDigitContent2 = Character.isDigit(strArr2[1].charAt(0));
                
                if(!isDigitContent1 && !isDigitContent2){//both are dletter-logs
                    int compareResult = strArr1[1].compareTo(strArr2[1]); //sort by their contents                    
                    if(compareResult == 0){ //if contents are the same
                        compareResult = strArr1[0].compareTo(strArr2[0]);    // sort by their identifiers
                    }
                    return compareResult;
                    
                    
                }else if(isDigitContent1 && !isDigitContent2){ // s1: digit-log , s2: letter-log 
                    return 1;  // s1 > s2 (digit-log > letter-log )
                }else if(!isDigitContent1 && isDigitContent2){ //s1: letter-log, s2: digit-log
                    return -1;// s1 < s2  
                }else{ //both are digital-logs
                    return 0;  //maintain their relative ordering
                }
                
            }
        };
        
        Arrays.sort(logs, myComparator);
        
        return logs;
    }
}


//time complexity: O(NlogN ) <-- compare time??  , Space complexity: O(1) <--- 틀렸네.
//time complexity: O(MxNlogN) , Space complexity: (MlogN) 이라고 하네
/*
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        
        if(logs == null || logs.length == 0){
            return null;
        }
        
        Arrays.sort(logs, new Comparator<String>(){
           
            @Override
            public int compare(String a, String b){
                String[] arrA = a.split(" ", 2); //identifier , content
                String[] arrB = b.split(" ", 2); //identifier, content
                
                //arrA is digit-logs
               boolean isDigitA = Character.isDigit(arrA[1].charAt(0));
                boolean isDigitB = Character.isDigit(arrB[1].charAt(0));
                
                //System.out.println("A:"+ arrA[1] + "_isDigit:"+ isDigitA + ", B:"+ arrB[1] + "_isDigit:" + isDigitB);
                
                
                if(isDigitA && isDigitB) { //both are digit-logs
                    return 0; //keep same order   //난 여기가 return -1 일 꺼라고 생각했는데 그러면 error 가 나네... 아무것도 안한다는 의미로 0 이여야 하는가 보다. 
                }else if(!isDigitA && isDigitB){
                    return -1; //the letter-lg comes before digit-logs
                }else if(isDigitA && !isDigitB){ //digit-logs comes after letter-log
                    return 1; //reverse order
                }else{ //both are letter
                    
                    int result = arrA[1].compareTo(arrB[1]);
                    if(result == 0){
                        return arrA[0].compareTo(arrB[0]);
                    }else{
                        return result;
                    }
                }
                
            }
        });
        
        return logs;
    }
    
   
   // private boolean isDigit(String str){
   //     if(str == null || str == "" ){
   //         return false;
   //     }
        
        //check if it is digit
   //     if(str.length() == 1){
   //         char ch = str.charAt(0);
   //         if(Character.isDigit(ch)){
   //             return true;
   //         }
   //     }
        
   //     return false;
   //}
    
}
*/