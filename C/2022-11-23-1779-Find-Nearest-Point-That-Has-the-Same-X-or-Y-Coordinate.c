//2022-11-23
//Time Complexity: O(N)
//Space Complexity: O(1)
int nearestValidPoint(int x, int y, int** points, int pointsSize, int* pointsColSize){

    int minDist = 100000;
    int manhattanDist = 0;
    int point = -1;
    int absX = 0;
    int absY = 0;
    for(int i = 0; i < pointsSize; i++)
    {
        absX = abs(points[i][0] - x);
        absY = abs(points[i][1] - y);
        if((absX != 0) && (absY != 0))
        {
            continue; // skip this turn
        } 
        manhattanDist = absX + absY; 
        if(minDist > manhattanDist){
            minDist = manhattanDist;
            point = i;
        }
    }

    return point;
}