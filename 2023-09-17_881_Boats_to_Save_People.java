//2023-09-17
//limitation: 각 people 의 weight < limit ? yes
//              people 배열은 sorted array? no
//idea : 2-pointer 방식으로 풀어보자
// 2-pointer 방식은 맞는데... 아래처럼 기존 2-pointer 방식으로 하면 풀어지지 않음. 
//      아래아래 기존 방식대로 해서 문제가 생긴경우의 코드참조 
//     ex1) Input: people = [3,2,2,1], limit = 3
//          boat: 0
//          sortedArray[1,2,2,3] : [1,3]3보다 큼 boat++ 하고 end -> [1,2]:boat++ 하고 start++, end-- -> [2] 3보다 작음 boat++ 하고 start++ -> (start> end)이므로 종료

// 리트코드 idea: while(start <=end) 로 진행하긴 하되, '가벼운 사람은 무거운사람과 무게합이 limit 보다 작거나 같으면 같이 탈수 있다 (start++, end--)' '무거운사람은 가벼운 사람을 태울수 없으면 혼자 탄다 (end--)' 가 기본아이디어 => 즉 start++ 만 하는 경우는 생기지 않음. 이게 중요
//   
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        if(people == null || people.length <=0 ){
            return 0;
        }

        Arrays.sort(people);

        int start = 0;
        int end = people.length -1;
        int boats = 0;
        //Input: people = [3,2,2,1]->[1,2,2,3], limit = 3
        //start:1(0) ->2(1)
        //end:3(3) ->2(2) -> 2(1)
        //boats:1 ->2 ->3

        //Input: people = [2,2], limit = 6
        //start:2
        //end:2
        //boats: 1
        //input: people =[3,2,2,1] -> 1,2,2,3, limit = 3
        //start: 1 (0)->2(1)
        //end:3(3)->2(2) ->2(1) -> 1(0)
        //boats:1 -> 2 -> 3
        while(start <= end){
            int weight = people[start] + people[end];
            if(weight <= limit){
                start++; // boat carries light people together with heavy people.
            }
            end--; 
            boats++;
        }
        return boats;
    }
}

//아래풀이는 Input: people = [2,2] , limit = 6 의 경우 1이 아니라 2가 답으로 나옴 
// 그렇다고 while 문을 while(start < end) 로 바꾸면 //Input: people = [3,5,3,4], limit =5 의 경우가 4가 아니라 3이 나옴. 
/*
class Solution {
    public int numRescueBoats(int[] people, int limit) {
        if(people == null || people.length <= 0){
            return 0;
        }

        Arrays.sort(people); //ascending order
        int start = 0;
        int end = people.length -1;
        int boats = 0;
        //Input: people = [3,5,3,4] -> [3,3,4,5], limit = 5
        //start:3(0) 
        //end:5(3) ->4(2) ->3(1) ->3(0)-> ?(-1)
        //boats: 1 ->2 ->3 ->4
        //Input: people = [2,2] , limit = 6
        //start:2(0)
        //end:2(1)
        //boats:
        //아래 while 문을 (start <= end) 로 하면  ex) Input:people=[2,2], limit=6 인 경우에 boats 의 수가 2개가 되는 문제가 생긴다. ==> 어떻게 해결해야하지?? 위에 leetcode solution 참조한 해법 보기 
        // while(start < end) 로 하면 //Input: people = [3,5,3,4], limit =5 인경우가 해결이 안됨. 
        while(start <= end){
            int weight = people[start] + people[end];
            if(weight == limit){
                start++;
                end--;
            }else if(weight < limit){
                start++;
            }else{
                end--;
            }
            boats++;
        }

        return boats;
    }
}
*/