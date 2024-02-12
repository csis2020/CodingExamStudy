//2024-02-11
//2024-02-11
//이건 해법결과를 찾는 것이 아님. 주어진 판에 오류가 없는지만 확인하는 것임
// 물론 공식대로 오류는 없어도 solvable 하지 않을수도 있으나 그건 이문제 영역밖
//푸는 방법은 row, col, sub-box 를 각각 체크하는 것인데 그것을 위해 
// 1. hashset 배열을 사용하는 방법 (배열의 요소가 HashSet 인 방식이친근하지는 않네. )
// 2. bit 자리수를 체크용도로 사용하는 방법 2가지로 풀예정
// 특히 여기서 sub-box 를 체크하기위해서 리트코드의 풀이를 보면 
//  row/3 *3 + col/3 을 이용했는데, 만약 이것이 실제 문제 풀이시 바로 생각이 나지 않는 경우에는 
//  그냥 +3으로 점프하는 식으로 풀이를 할수도 있다고 생각함. 
// 사실 for 문을 하나로 작성하고 그안에서 3가지를 체크하는것과 
//  for문을 3번하고 각 for 문 안에서 1가지찍 체크하나 실제 이론상 시간은 차이 없음. 

//1번 방식 : Hashset 사용: 총 HashSet 배열이 3종류 필요
//Time Complexity: O(N^2) <-- N*N 
//Space Complexity: O(N^2) <-- N*N*3
/*
class Solution {
    final int LENGTH = 9; 
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9){
            return false;
        }

        HashSet<Character>[] rowSets = new HashSet[LENGTH];//HashSet 배열은 이런식으로 선언하는구나...
        HashSet<Character>[] colSets = new HashSet[LENGTH];//HashSet 배열은 이런식으로 선언하는구나...
        HashSet<Character>[] subBoxSets = new HashSet[LENGTH];//HashSet 배열은 이런식으로 선언하는구나...

        //Initialize
        for(int i = 0; i < LENGTH; i++){
            rowSets[i] = new HashSet<Character>();
            colSets[i] = new HashSet<Character>();
            subBoxSets[i] = new HashSet<Character>();
        }

        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < LENGTH; j++){
                char ch = board[i][j];
                if(ch != '.'){
                   int digit = ch - '0';
                   if(digit < 1 || digit > 9){
                       return false;
                   }
                   if(rowSets[i].contains(ch)){
                       return false;
                   }else{
                       rowSets[i].add(ch);
                   }
                   if(colSets[j].contains(ch)){
                       return false;
                   }else{
                       colSets[j].add(ch);
                   }

                   int subBoxIndex = i/3 *3 + j/3;
                   if(subBoxSets[subBoxIndex].contains(ch)){
                       return false;
                   }else{
                       subBoxSets[subBoxIndex].add(ch);
                   }
                }
            }
        }

        return true; 
    }
}
*/

//2번방식- 일반배열이용
// row/ col/ subbox 각각 배열에 index 와 같은 숫자를 넣고, 이미 있는것이 또나오면 return false
//Time Complexity : O(N^2) <-- N*N
//Space Complexity: O(N^2) <-- N*N*3
/*
class Solution {
    final int LENGTH = 9; 
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9){
            return false;
        }

        //default initial values are '0'
        int[][] rowChecks = new int[LENGTH][LENGTH];
        int[][] colChecks = new int[LENGTH][LENGTH];
        int[][] subBoxesChecks = new int[LENGTH][LENGTH];

        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < LENGTH; j++){
                char ch = board[i][j];
                if(ch == '.'){
                    continue;
                }

                int digit = ch - '0';
                if(digit < 1 || digit > 9){
                    return false;
                }  

                if(rowChecks[i][digit-1] == 1){
                    return false;
                }
                rowChecks[i][digit-1] = 1;
                if(colChecks[j][digit-1] == 1){
                    return false;
                }
                colChecks[j][digit-1] = 1;

                int subBoxesIndex = i/3 * 3 + j/3;
                if(subBoxesChecks[subBoxesIndex][digit-1] == 1){
                    return false;
                }
                subBoxesChecks[subBoxesIndex][digit-1] = 1;
            }
        }
        return true;
    }
}
*/

// 3번째 방식:  bit masking 를 이용하여 space complexity 를 O(N)으로 줄임. 
//Time Complexity: O(N^2)
//Space Complexity: O(N)
class Solution {
    final int LENGTH = 9; 
    public boolean isValidSudoku(char[][] board) {
        if(board == null || board.length != 9 || board[0].length != 9){
            return false;
        } 

        //default value is '0'
        int[] rowChecks = new int[LENGTH];
        int[] colChecks = new int[LENGTH];
        int[] subBoxesChecks = new int[LENGTH];

        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < LENGTH; j++){
                char ch = board[i][j];
                if(ch == '.'){
                    continue;
                }
                int digit = ch - '0';
                if(digit < 1 || digit > 9){
                    return false;
                }
                int bitm = 1 << digit;
                if((rowChecks[i] & bitm) > 0){
                    return false;
                }
                rowChecks[i] = rowChecks[i] | bitm;

                if((colChecks[j] & bitm) > 0){
                    return false;
                }
                colChecks[j] = colChecks[j] | bitm;

                int subBoxesIndex = i/3 * 3 + j/3;
                if((subBoxesChecks[subBoxesIndex ] & bitm) > 0){
                    return false;
                }
                subBoxesChecks[subBoxesIndex] = subBoxesChecks[subBoxesIndex ] | bitm;
            }
        }
        return true;
    }
}


//2022-12-11
//Time Complexity: O(N^2) 
//Space Complexity: O(N)  <--- bit masking 을 이용 (9개 자리 bit 를 각각 1~9 숫자 존재여부 체크하기위해 사용)
//leetcode solution 보고 만듬 
/*
class Solution{
    final int LENGTH = 9;
    public boolean isValidSudoku(char[][] board){
        if(board == null || board.length != LENGTH || board[0].length != LENGTH){
            return false;
        }

        int[] rowBM = new int[LENGTH];
        int[] colBM = new int[LENGTH];
        int[] subBoxBM = new int[LENGTH];

        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < LENGTH; j++){
                char ch = board[i][j];
                int value = ch - '0';
                if(ch == '.'){
                    continue;
                }else if(value >= 1 && value <= 9){
                    int bm = 1 << value;
                    if((rowBM[i] & bm) > 0){ // repeat number
                        return false;
                    }
                    rowBM[i] = rowBM[i] | bm;
                    if((colBM[j] & bm) > 0) { // repeat number
                        return false;
                    }
                    colBM[j] = colBM[j] | bm;

                    int index = (i/3) * 3 + (j/3);
                    if((subBoxBM[index] & bm) > 0){ //repeat number
                        return false;
                    }
                    subBoxBM[index] = subBoxBM[index] | bm;

                }else{ // not valid value
                    return false;
                }
            }
        }

        return true;
    }
}
*/

//Time Complexity: O(N^2) 
//Space Complexity: O(N^2) 
//leetcode solution 보고 만듬 
/*
class Solution{
    final int LENGTH = 9;
    public boolean isValidSudoku(char[][] board){
        if(board == null || board.length != LENGTH || board[0].length != LENGTH){
            return false;
        }


        HashSet<Character>[] rowSet = new HashSet[LENGTH];
        HashSet<Character>[] colSet = new HashSet[LENGTH];
        HashSet<Character>[] subBoxSet = new HashSet[LENGTH];

        for(int i = 0; i < LENGTH; i++){
            rowSet[i] = new HashSet<>();
            colSet[i] = new HashSet<>();
            subBoxSet[i] = new HashSet<>();
        }

        char ch;
        for(int i = 0; i < LENGTH; i++){
            for(int j = 0; j < LENGTH; j++){
                ch = board[i][j];
                if(ch == '.'){
                    continue;
                }else if((ch - '0') >= 1 && (ch - '0') <= 9){
                    if(rowSet[i].contains(ch)){
                        return false;
                    }
                    rowSet[i].add(ch);

                    if(colSet[j].contains(ch)){
                        return false;
                    }
                    colSet[j].add(ch);

                    int index = (i/3) * 3 + j/3;
                    if(subBoxSet[index].contains(ch)){
                        return false;
                    }
                    subBoxSet[index].add(ch);

                }else{
                    return false;
                }
            }
        }

        return true;
    }
}
*/

//Time Complexity: O(N^2) <--- 3*N^2
//Space Complexity: O(1) <---  HashSet 을 계속 new 를 하긴했음. 
/*
class Solution {
    public boolean isValidSudoku(char[][] board) {

        if(board == null || board.length == 0 || board[0].length == 0){
            return false;
        }

        HashSet<Character> set;
        //HashSet<Character> colSet;
        char ch;
        for(int i = 0; i <board.length; i++){
            set = new HashSet<>();
            for(int j = 0; j < board[i].length; j++){
                ch = board[i][j];
                if(ch == '.'){
                    continue;
                }
                if((ch - '0') >= 1 && (ch - '0') <= 9){
                    if(set.contains(ch)){
                        return false;
                    }else{
                        set.add(ch);
                    }
                }else{
                    return false;
                }
            }
        }

        for(int j = 0; j < board[0].length; j++){
            set = new HashSet<>();
            for(int i = 0; i < board.length; i++){
                ch = board[i][j];
                if(ch == '.'){
                    continue;
                }
                if((ch - '0') >= 1 && (ch - '0') <= 9){
                    if(set.contains(ch)){
                        return false;
                    }else{
                        set.add(ch);
                    }
                }else{
                    return false;
                }
            }
        }

        for(int i = 0; i < board.length; i+=3){
            for(int j = 0; j < board[0].length; j += 3){
                set = new HashSet<>();
                for(int k = i; k < (i+3); k++){
                    for(int l = j ; l < (j+3) ; l++){
                        ch = board[k][l];
                        if(ch == '.'){
                            continue;
                        }
                        if((ch - '0') >= 1 && (ch - '0') <= 9){
                            if(set.contains(ch)){
                                return false;
                            }else{
                                set.add(ch);
                            }
                        }else{
                            return false;
                        }
                    }
                }
            }
        }

        return true;
        
    }
}
*/