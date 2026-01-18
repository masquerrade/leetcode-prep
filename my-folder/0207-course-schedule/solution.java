
//Practise 1 BFS approach
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        //Initially Scan indegree and add all the 0 to the queue
        //Do we need visited array in this case. No we don't need as we are using indegree array to maintain the correct order
        int[] indegree=new int[numCourses];

        List<Integer>[] adj=new List[numCourses];
        //Initialise all the nodes 

        for(int i=0;i<numCourses;i++){
            adj[i]=new ArrayList<>();
        }

        for(int[] courses:prerequisites){
            int course=courses[0];
            int prerequisite=courses[1];

            //Don't forget to use add
            adj[prerequisite].add(course);
            indegree[course]++;

        }

        //Now I need to scan indgree and add all the 0 courses to the queue

        Queue<Integer> bfs=new ArrayDeque<>();

        for(int i=0;i<numCourses;i++){
            if(indegree[i]==0){
                bfs.offer(i);
            }
        }

        int numTaken=0;

        while(!bfs.isEmpty()){
            int current=bfs.poll();
            numTaken++;

            for(int neighbour:adj[current]){
                if(--indegree[neighbour]==0){
                    bfs.offer(neighbour);
                }
            }
        }

        if(numTaken==numCourses){
            return true;
        }

        return false;
    }
}




// //Practise 1
// //When we cannot finish any course . When there is cycle in the graph
// //Detecting cycle in a directed graph 
// //Do bfs and keep count of number of courses completed .How will bfs and dfs detect cycle

// //Using the edges I need to make an adjacency list and then create a graph to check  
// //DFS approach
// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
        
//         //Array of Lists will be best
//         List<Integer> [] adj=new ArrayList[numCourses];

//         //Initialize all the elements of the array


//         for(int i=0;i<numCourses;i++){
//             adj[i]=new ArrayList<>();
//         }

//         for(int[] edge:prerequisites){
//             int course = edge[0];
//             int prereq=edge[1];

//             adj[prereq].add(course);
//         }

//         //I'll check all the nodes in numcourses and if it is not already visited I will call dfs on it        
//         int[] visited=new int[numCourses];

//         for(int i=0;i<numCourses;i++){
//            if(visited[i]==0){
//                 if(hasCycle(i,adj,visited)){
//                     return false;
//                 }
//            }
//         }

//         return true;
        
        
//     }

//     public boolean hasCycle(int node, List<Integer> [] adj, int[] visited){

//         //Any one will call has cycle on this node only if it is not visited .
//         //So mark it as visiting 
//         visited[node]=1;

//         for(int neighbour:adj[node]){
//             if(visited[neighbour]==1){
//                 return true;
//             }
//             //We cannot instantly return the result of the current neighbour
//             if(visited[neighbour]==0){
//                 //So I need to put in if condition so that false if not retured from here .Only in case of cycle I need to return from here
//                 if(hasCycle(neighbour,adj,visited)){
//                     return true;
//                 }
//             }
//         }

//         visited[node]=2;
//         return false;
        
//     }
// }



// //DFS approach
// class Solution {
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
      
//       //I need to do by DFS way
//       //In DFS I will create adjacency list
//       //Then iterate through the courses and call dfs on that
      
//       //Mark the current node being processed as visiting
      
//       //The  go through it's afjacency list and detect cycle on that
      
//       //After processing mark the current node as visited

//       //Step 1 : create adjacency list

//       //Iterate through the courses and for each prerequisite add the courses which belongs to it 
      
//       //Adjacency list -> List of list

//       List<List<Integer>> adj=new ArrayList<>();

//       //Initialise the adjacency list
//       //I will mark each course whether it is a prerequisite for any course
//       for(int i=0;i<numCourses;i++){
//         adj.add(new ArrayList<>());
//       }

//       for(int [] p:prerequisites){
//         adj.get(p[1]).add(p[0]);
//       }

//     //   System.out.println(adj);

//       //Now I have my adjacency list and I need to call dfs for all the elements in the graph if they are not visited
//       //I need the visited and visiting array
//       boolean [] visited=new boolean[numCourses];
//       boolean [] visiting=new boolean[numCourses];

//       for(int i=0;i<numCourses;i++){
//         // System.out.println("Current elm in visited-> "+i);
//         // System.out.println(Arrays.toString(visited));
//         if(visited[i]!=true){
//             //I need to call the dfs method with the current value of the visited and visiting array
//             //Adjacency list also need to be passed
//             if(hasCycle(i,adj,visited,visiting)){
//                 return false;
//             }
//         }
//       }

//       return true;
        
//     }

//     public boolean hasCycle(int e,List<List<Integer>> adj,boolean []visiting ,boolean []visited){
//         //
//         visiting[e]=true;

//         //Go to all the adjacent nodes of this element and mark call dfs for them recursively

//         for(int elm:adj.get(e)){
//             // System.out.println("Current elm in visiting-> "+elm);
//             // System.out.println(Arrays.toString(visiting));
//             if(visiting[elm]==true){
//                 return true;
//             }
//             //One more step is required before checking whether the adjacent element has cycle . We need to check first that if the node is visited.
//             if(visited[elm]!=true){
//                 if(hasCycle(elm,adj,visiting,visited)){
//                 return true;
//             }
//             }
            
//         }
//         //At the end we need to mark visiting as false
//         visiting[e]=false;
//         visited[e]=true;
//         return false;
//         //[[], [3], [3], [], [1, 2]]
//     }
// }


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

