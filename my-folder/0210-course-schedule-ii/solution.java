//First practise

//Make the adj list using prereq and inorder


class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        //Loop to create the adjacency list and the indegree array
        //Array of List
        List<Integer> [] adj=new List[numCourses];
        int [] inorder=new int[numCourses];

        //While adding the adjacency list ,I need to initialise. Better to initialize the complete thing before


        for(int i=0;i<numCourses;i++){
            adj[i]=new ArrayList<>();
        }

        //Now iterate prereq array and update adj and inorder
        for(int [] courses:prerequisites){
            int course=courses[0];
            int pre=courses[1];

            adj[pre].add(course);
            inorder[course]++;
        }

        //I need queue to store the courses which can be taken
        Queue<Integer> bfs=new ArrayDeque<>();
        int totalTaken=0;

        //What do I need to return in the end
        //Here I can use array also as the final size of array is fixed
        int [] finalOrder=new int[numCourses];

        for(int i=0;i<numCourses;i++){
            if(inorder[i]==0){
                bfs.offer(i);
            }
        }


        while(!bfs.isEmpty()){
            int current=bfs.poll();
            //We can use total taken here
            finalOrder[totalTaken++]=current;
            //I need to take the current course and go through all its neighbours and decreasing their indegree. If any of their indegree is 0 add them to the queue

            List<Integer> neighbours=adj[current];//My adjacency list is an array

            for(int neighbour:neighbours){
                if(--inorder[neighbour]==0){
                    bfs.offer(neighbour);
                }
            }
        }

        if(totalTaken==numCourses){
            return finalOrder;           
        }
        else{
            return new int[0];
        }

        
    }
}


// //DFS approach
// class Solution {
//     List<Integer> or=new ArrayList<>();

//     public int[] findOrder(int numCourses, int[][] prerequisites) {

        

//         //Make the adjacency map
//         Map<Integer,List<Integer>> adj=new HashMap<>();

//         for(int [] p:prerequisites){
//              int pr=p[1];
//              int cr=p[0];
//              List<Integer> pl=adj.getOrDefault(pr,new ArrayList<>());
//              pl.add(cr);
//              //This is needed as first time I need to add
//              adj.put(pr,pl);
//         }

//         //  Go through all the courses and check if any cycle is there
//         int []v=new int[numCourses];

//         for(int i=0;i<numCourses;i++){
//             if(v[i]!=2){
//                 if(dfs(i,adj,v)){
//                     return new int[0];
//                 }
//             }
//         }

//         //Till now I could have checked all the nodes for the cycle and added elements in the order array in reverse order
//         //System.out.println(or);
//         Collections.reverse(or);
//         //System.out.println(or);

//         return or.stream().mapToInt(Integer::intValue).toArray();
                
//     }

//     public boolean dfs(int i,Map<Integer,List<Integer>>adj,int [] v){
//         v[i]=1;

//         if(adj.containsKey(i)){
//             for(int j:adj.get(i)){
//                 if(v[j]==1){
//                     return true;
//                 }
//                 if(v[j]!=2){
//                     //I need to return the result of the recursive call
//                     //Here the mistake is that I will check only the dfs for one neighbour and not for all
//                     if(dfs(j,adj,v)){
//                         return true;
//                     }
//                 }
                
//             }
//         }

//         v[i]=2;
//         or.add(i);

//         //System.out.println(or);
//         return false;
//     }

// }


// //BFS approach
// class Solution {
//     public int[] findOrder(int numCourses, int[][] prerequisites) {
//         //First build the adjacency list. Map is better so that no need to initialise before hand
//         //While creating the adjacency list only I can initialise also
//         Map<Integer,List<Integer>> adj=new HashMap<>();

//         //Iterate prereq and if the element not there initialise and add
//         //Or use simple getOrDefault 

//         int [] indegree=new int[numCourses];

//         //System.out.println("Prerequisite -> "+Arrays.deepToString(prerequisites));


//         //In this case what will happen is that if there is no prequisite for the course it won't be added in the list
//         for(int [] p:prerequisites){
//             //System.out.println("Inside for loop");
//             int cr=p[0];
//             int pr=p[1];

//             List<Integer> aL=adj.getOrDefault(pr,new ArrayList<Integer>());
//             aL.add(cr);
//             //System.out.println(aL);
//             //I need to update the map after adding the value
//             adj.put(pr,aL);
            
//             indegree[cr]++;
//         }

//         //System.out.println(adj);

//         //My adjacency map is made
//         //Some courses will have indegree 0 I need to add those courses in the queue

//         Queue<Integer> q=new ArrayDeque<>();

//         for(int i=0;i<numCourses;i++){
//             if(indegree[i]==0){
//                 q.offer(i);
//             }
//         }

//         //I have my queue and now I can start BFS
//         int[] finArray=new int[numCourses];
//         int i=0;
//         while(!q.isEmpty()){
//             //If queue is empty there are no other elements without a prereq and there may be a cycle
//             //Take the element from the queue and go through it's adjacency list and reduce the indegree and if indegree becomes 0 then add it to the queue

//             int elm=q.poll();

//             finArray[i++]=elm;
//             if(adj.containsKey(elm)){
//               for(int c:adj.get(elm)){
//                 indegree[c]--;

//                 if(indegree[c]==0){
//                   q.offer(c);
//                 }
//             }
//             }
            

//         }

//         if(i==numCourses){
//             return finArray;
//         }
//         else{
//             return new int [0];
//         }

//     }
// }
