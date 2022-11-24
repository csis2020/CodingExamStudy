//2022-11-24
//Time Complexity: O(N)
//Space COmplexity: O(N)
char * reverseWords(char * s){
    int length = strlen(s);
    int start = 0; 
    int end = length -1;
    int ws = 0;
    int we = 0;
    char * revS = malloc(length+1); // need one more 'char' for ' ' 
    int i = 0;

    while(end >= 0)
    {
        //remove space at end position
        while(end >= 0 && s[end] == ' ')
        {
            end--;
        }
        we = end;
        while(end >= 0 && s[end] != ' '){
            end--;
        }
        ws = end + 1;
        //word copy
        for(i = ws; i <= we; i++)
        {
            revS[start++] = s[i];
        }
        revS[start++] = ' ';
    }

    start--;// 'start-1' is end of array.
    while(revS[start] == ' ')
    {
        start--;
    }
    revS[start+1] = '\0';
    return revS;
}