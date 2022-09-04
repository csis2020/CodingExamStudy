[165. Compare Version Numbers]

// 2022-09-04
// version1 , version2 -->null -> return ????
// input: version1 = 1.0, version2 = 1.0.0  ==> output: 0
// input: version1 = 0.1  version2 = 1.1 ==> output: -1
//Time complexity:  MAX ( MAX(N, M ), timecomplexity of split function) ->  N : length of version1, M: length of version2
// Space complexity: O(N+M), N: the length of strVer1, M: the length of strVer2

// From leetcode => Time complexity : \mathcal{O}(N + M + \max(N, M))O(N+M+max(N,M)), where NN and MM are lengths of input strings. / Space complexity : \mathcal{O}(N + M)O(N+M) to store arrays nums1 and nums2.

class Solution {
    public int compareVersion(String version1, String version2) {
        
        if(version1 == null || version2 == null){
            return -2; // error code??
        }
        
        String[] strVer1 = version1.split("\\.");
        String[] strVer2 = version2.split("\\.");
        
        int count = 0;
        int temp1, temp2;
        while(strVer1.length > count || strVer2.length > count){
            
            temp1 = (strVer1.length > count) ? Integer.parseInt(strVer1[count]) : 0;
            temp2 = (strVer2.length > count) ? Integer.parseInt(strVer2[count]) : 0;
            
            if(temp1 > temp2){
                return 1;
            }else if(temp1 < temp2){
                return -1;
            }
            
            count++;
        }

        return 0;
        
    }
}

//Time complexity:  MAX ( MAX(N, M ), timecomplexity of split function) ->  N : length of version1, M: length of version2
// Space complexity: O(N+M), N: the length of strVer1, M: the length of strVer2
/*
class Solution {
    public int compareVersion(String version1, String version2) {
        
        if(version1 == null || version2 == null){
            return -2; // error code??
        }
        
        String[] strVer1 = version1.split("\\.");
        String[] strVer2 = version2.split("\\.");
        
        int count = 0;
        int temp1, temp2;
        while(strVer1.length > count && strVer2.length > count){
            temp1 = Integer.parseInt(strVer1[count]);
            temp2 = Integer.parseInt(strVer2[count]);
            
            if(temp1 > temp2){
                return 1;
            }else if(temp1 < temp2){
                return -1;
            }
            
            count++;
        }

        if(strVer1.length <= count){
            while(strVer2.length > count){
                temp1 = Integer.parseInt(strVer2[count]);
                if(temp1 > 0){
                    return -1;
                }
                
                count++;
            }
        }else if(strVer2.length <= count) {
            while(strVer1.length > count){
                temp1 = Integer.parseInt(strVer1[count]);
                if(temp1 > 0){
                    return 1;
                }
                
                count++;
            }   
        }
        
        return 0;
        
    }
}
*/