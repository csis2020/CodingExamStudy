//2022-12-11
//Time Complexity: O(N^2) 
//Space Complexity: O(N)  <--- bit masking 을 이용 
//leetcode solution 보고 만듬 
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