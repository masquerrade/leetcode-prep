//BFS approach
//I made the adjacency list
//If any courses have zero prerequisites add it to the queue and 
//Reduce in degree for the courses for which
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
      
      //First thing I need to do is using the prerequisites array create an adjacency matrix which will be a list of list.Index will represent the vertex and the list will represent all the courses for which the current index is the prerequsite.
      
      //Iterate through the prerequisite list and for each prerquusite assign the courses that is add the edge.
      
      List<List<Integer>> lst=new ArrayList<>();
      
      //First I need to initialise all the List
      
      int n=prerequisites.length;
      int [] indegree=new int[numCourses];

      
      for(int i=0;i<numCourses;i++){
        lst.add(i,new ArrayList<>());
      }
      
      //Here my lst is created
      for(int [] l:prerequisites){
        lst.get(l[1]).add(l[0]);
        indegree[l[0]]++;
      }

      
      
      //Now I need to add to the queue the courses for which there are no prereq
      //First pass I need to add courses with no prerequisite.
      
      //First define a queue
      
      Queue<Integer> bfs=new LinkedList<>();
      
      for(int i=0;i<numCourses;i++){
        if(indegree[i]==0){
          bfs.offer(i);
        }
      }
      int takenCourses=0;
      while(!bfs.isEmpty()){
        int e=bfs.poll();
        takenCourses++;
        
        //Go through all the elm in the adj list and reduce the incourse
        
        for(int i:lst.get(e)){
          indegree[i]--;
          if(indegree[i]==0){
            bfs.offer(i);
          }
          
        }
        
      }
      
      return takenCourses==numCourses;
      
      //For each course I need to maintain how many prereq are remaining.I need this for topological sorting
      
      
      
    }
}

