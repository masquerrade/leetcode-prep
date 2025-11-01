//DFS approach
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
      
      //I need to do by DFS way
      //In DFS I will create adjacency list
      //Then iterate through the courses and call dfs on that
      
      //Mark the current node being processed as visiting
      
      //The  go through it's afjacency list and detect cycle on that
      
      //After processing mark the current node as visited

      //Step 1 : create adjacency list

      //Iterate through the courses and for each prerequisite add the courses which belongs to it 
      
      //Adjacency list -> List of list

      List<List<Integer>> adj=new ArrayList<>();

      //Initialise the adjacency list
      //I will mark each course whether it is a prerequisite for any course
      for(int i=0;i<numCourses;i++){
        adj.add(new ArrayList<>());
      }

      for(int [] p:prerequisites){
        adj.get(p[1]).add(p[0]);
      }

    //   System.out.println(adj);

      //Now I have my adjacency list and I need to call dfs for all the elements in the graph if they are not visited
      //I need the visited and visiting array
      boolean [] visited=new boolean[numCourses];
      boolean [] visiting=new boolean[numCourses];

      for(int i=0;i<numCourses;i++){
        // System.out.println("Current elm in visited-> "+i);
        // System.out.println(Arrays.toString(visited));
        if(visited[i]!=true){
            //I need to call the dfs method with the current value of the visited and visiting array
            //Adjacency list also need to be passed
            if(hasCycle(i,adj,visited,visiting)){
                return false;
            }
        }
      }

      return true;
        
    }

    public boolean hasCycle(int e,List<List<Integer>> adj,boolean []visiting ,boolean []visited){
        //
        visiting[e]=true;

        //Go to all the adjacent nodes of this element and mark call dfs for them recursively

        for(int elm:adj.get(e)){
            // System.out.println("Current elm in visiting-> "+elm);
            // System.out.println(Arrays.toString(visiting));
            if(visiting[elm]==true){
                return true;
            }
            //One more step is required before checking whether the adjacent element has cycle . We need to check first that if the node is visited.
            if(visited[elm]!=true){
                if(hasCycle(elm,adj,visiting,visited)){
                return true;
            }
            }
            
        }
        //At the end we need to mark visiting as false
        visiting[e]=false;
        visited[e]=true;
        return false;
        //[[], [3], [3], [], [1, 2]]
    }
}


// //BFS approach
// //I made the adjacency list
// //If any courses have zero prerequisites add it to the queue and 
// //Reduce in degree for the courses for which the prerequisites have been satisfied.
// //In each pass there will be atleast one course with 0 prerequisites or else there is a cycle .
// //So keep on adding to the queue and reducing the in degree
// //Adjacency list is always used to reduce the indegree of the adjacent courses
// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
      
//       //First thing I need to do is using the prerequisites array create an adjacency matrix which will be a list of list.Index will represent the vertex and the list will represent all the courses for which the current index is the prerequsite.
      
//       //Iterate through the prerequisite list and for each prerquusite assign the courses that is add the edge.
      
//       List<List<Integer>> lst=new ArrayList<>();
      
//       //First I need to initialise all the List
      
//       int n=prerequisites.length;
//       int [] indegree=new int[numCourses];

      
//       for(int i=0;i<numCourses;i++){
//         lst.add(i,new ArrayList<>());
//       }
      
//       //Here my lst is created
//       for(int [] l:prerequisites){
//         lst.get(l[1]).add(l[0]);
//         indegree[l[0]]++;
//       }

      
      
//       //Now I need to add to the queue the courses for which there are no prereq
//       //First pass I need to add courses with no prerequisite.
      
//       //First define a queue
      
//       Queue<Integer> bfs=new LinkedList<>();
      
//       for(int i=0;i<numCourses;i++){
//         if(indegree[i]==0){
//           bfs.offer(i);
//         }
//       }
//       int takenCourses=0;
//       while(!bfs.isEmpty()){
//         int e=bfs.poll();
//         takenCourses++;
        
//         //Go through all the elm in the adj list and reduce the incourse
        
//         for(int i:lst.get(e)){
//           indegree[i]--;
//           if(indegree[i]==0){
//             bfs.offer(i);
//           }
          
//         }
        
//       }
      
//       return takenCourses==numCourses;
      
//       //For each course I need to maintain how many prereq are remaining.I need this for topological sorting
      
      
      
//     }
// }

