class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {

        //First step is I need to create an adjacency list
        //Adjacency list will contain source node target node and the weight
        //Then using this adjacency list I will form a queue and this queue will  not be on the basis of first come first serve basis 
        //But it will be a priority queue which will be on the weight basis
        //Each node has a weight attached to it and weight defines the distance from the adjacent node

        //I will have to maintain a cost array for each node, which will store the minimum cost for this node from the source , this is the time taken for the signal to reach this node 
        

        //The max of all these values is the time taken to reach the farthest node\


        //Here I don't maintain the visited array as I keep on visiting the same node again and again till the shortest path is found

        //Creating the adjacency matrix/ list

        //Why I can create an array as adjacency list because I don't know how many total elements will be there in the graph

        //I need to create adjacency list as list because we need array of list of interger arrays or map of list of integer arrays
        //Or map of list of integer arrays

        //int [][] adj=new int [n+1];

        List<int []> [] adj=new ArrayList[n+1];

        //I need to initialise each element 
        for(int i=1;i<=n;i++){
            adj[i]=new ArrayList<>();
        }

        for(int [] tm:times){
            int u=tm[0];
            int v=tm[1];
            int w=tm[2];
            //Adding a single dimensional array
            //This won't work
            // if(adj[u]==null){
            //     adj[u]=new ArrayList<>();
            // }
            adj[u].add(new int[]{v,w});
        }

        //Now my adjacency list is ready, I need to add the source element in the queue
        //Pop it and iterate through the adjacent nodes

        //I need a distance array which will store the smallest distance of any node to the source
        
        //I need min heap
        //What min heap will do is keep the nodes with shortest distance on the top
        //So I will always have node with shortest ditance till now
        //If a shorter distance is found add that node in the queue and rprocess its adjacent nodes

        int dis[]=new int[n+1];

        

        for(int i=0;i<=n;i++){
            dis[i]=Integer.MAX_VALUE;
        }

        dis[k]=0;

        // Queue<int[]> djk=new PriorityQueue<>(a[1]-b[1]);//This is wrong declaration .From where this a and b is coming so I need to use lambda expression
        Queue<int[]> djk=new PriorityQueue<>((a,b)->(a[1]-b[1]));//In the queue I will only have the destination node and its distance from the adjacent nodes

        //In priority queue we are adding the current node and total distance of the current node to the origin

        djk.offer(new int[]{k,0});

        while(!djk.isEmpty()){
            int []ent=djk.poll();

            int u=ent[0];
            int t=ent[1];
            //I pop the current node and check if it is a fresh entry or stale entry before checking its neighbours
            if(t>dis[u]){
                continue;
            }

            //If it is a fresh entry check it's neighbours
            //There may be a condition when current node may not have any adjacent nodes
            //Since I am using array no need to check the condition that if the current node doesn't exist in the adj list
            //List is an iterable so I can directly iterate

            for(int[] ls:adj[u]){
                //Now I'm inside the adj list
                int v=ls[0];
                int w=ls[1];

                //This is total w of v
                int tw=w+t;

                if(tw<dis[v]){
                    dis[v]=tw;
                    djk.offer(new int[]{v,tw});
                }
            }


        }

        //Now my dis array will have all the min times .Now I want to fix max of this times
        int maxT=0;


        for(int i=1;i<=n;i++){
            //I missed one condition when any node is not reachble from the source the I need to return -1
            if(dis[i]==Integer.MAX_VALUE){
                return -1;
            }
            if(dis[i]>maxT){
                maxT=dis[i];
            }

        }

        return maxT;        

        
    }
}
