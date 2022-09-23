//2022.09.23
//limitation : if numCOurses is less than 1, return false
//              if prerequisites is null or size is zero, return empty
// Input: numCourses = 2, prerequisites = [[1,0]]  -> Output: true
// Input: numCourses = 2, prerequisites = [[1,0],[0,1]]  -> Output: false

// Time Complexity 랑 Space Complexity 가 헷갈린다...
// 아래는 leetcode 설명. 
// Time Complexity: O(E + V) , where 'V' is the number of courses, and 'E'  is the number of dependencies.
//                  - As in the previous algorithm, it would take us 'E' time complexity to build a graph in the first step. 
//                  - Since we perform a postorder DFS traversal in the graph, we visit each vertex and each edge once and only once in the worst case, 'E' + 'V'
// Space Complexity: O(E + V) , with the same denotation as in the above time complexity.
//                  - We built a graph data structure in the algorithm, which would consume 'E' + 'V' space
//                  - n addition, during the backtracking process, we employed two bitmaps (path and visited) to keep track of the visited path and the status of check respectively, which consumes 2 * V space
//                  - Finally, since we implement the function in recursion, which would incur additional memory consumption on call stack. In the worst case where all nodes chained up in a line, the recursion would pile up |V| times
//                  - Hence the overall space complexity of the algorithm would be O(E + 4*V) = O(E + V)

class Solution {
    
    HashMap<Integer, List<Integer>> prereqLists ;
    boolean[] visited;
    boolean[] checked;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        if(numCourses < 1 || prerequisites == null || prerequisites.length == 0){
            return true;
        }
        
        prereqLists = new HashMap<Integer, List<Integer>>();
        visited = new boolean[numCourses];
        checked = new boolean[numCourses];
        
        //make prerequisets list => key: course, values: prerequisite list
        for(int i = 0; i < prerequisites.length; i++){
            int course = prerequisites[i][0];
            int prereqCourse = prerequisites[i][1];
            if(prereqLists.containsKey(course)){
                prereqLists.get(course).add(prereqCourse);
            }else{
                List<Integer> prereqList = new ArrayList<Integer>();
                prereqList.add(prereqCourse);
                prereqLists.put(course, prereqList);
            }
        }
        
        //Check if there is cycle 
        for(int i = 0; i < numCourses; i++){
            if(isCycle(i)){ //if it is true, there is cycle
                return false;
            }
        }
        return true; // there is no cycle. You can finish all courses.
    }
    
    private boolean isCycle(int course){
        if(course < 0 || course >= checked.length){
            return false;
        }
        
        if(checked[course]){ //already checked course. We don't need to check it again.
            return false;
        }
        
        if(visited[course]){//it means, there is a cycle.
            return true;
        }
        
       
        if(prereqLists.containsKey(course)){ //This course has at least one prerequisites.
            List<Integer> tempList = prereqLists.get(course);
            visited[course] = true;
            for(int i = 0; i < tempList.size(); i++){

                if(isCycle(tempList.get(i))){
                    return true;
                }
            }
            //사실 여기에서는 cycle 이 생긴것을 발견하자 마자 return true 를 하고,  이미 checked 된 값에 대해서는 더이상 check 를 하지 않기 때문에 visited[course] = false; 를 하지 않아도 문제가 발생하지 않음. 
            visited[course] = false; 
        }
        
        checked[course] = true; 
        return false; //there is no cycle.
    }
}