class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        //Test test testing 
        //In Djikstra we're just tracking the shortest distance found till now but not the number of levels tracked
        //Initialize the cost array
        int [] cost=new int[n];
        int [] costCopy=new int[n];

        Arrays.fill(cost,Integer.MAX_VALUE);

        cost[src]=0;

        //I need to scan all the edges k+1 times. k stops means k edges and k+1 cities
        //Outer loop should run k+1 times
        for(int i=0;i<=k;i++){

            //Inner loop to update the cost array
            //This loop should only see the cost updated by the previous iteration of the outer loop and not of the inner loop 
            costCopy=Arrays.copyOf(cost,n);
            for(int[] flight:flights){

                int from=flight[0];
                int to=flight[1];
                int price=flight[2];

                //Here I need a check to prevent overflow
                if(costCopy[from]==Integer.MAX_VALUE){
                    continue;
                }

                int newCost=costCopy[from]+price;
                //Here we cannot use costCopy for comparison since it is getting refreshed in every iteration.We need to use cost array in comparison to track the correct least price in that whole iteration
                // if(newCost<costCopy[to]){
                if(newCost<cost[to])
                {
                    cost[to]=newCost;
                }
            }

        }

        if(cost[dst]==Integer.MAX_VALUE){
            return -1;
        }

        return cost[dst];
        
    }
}
