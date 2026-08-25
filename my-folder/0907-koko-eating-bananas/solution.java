//Wasted 6hrs for break
//Again 4hrs break
//Wasted 13 hrs came another day after sleeping
//Spent 5hrs again


class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        //n piles
        //i -> piles[i]
        //h hrs
        //Eating speed k/hr max limited to 1 pile/hr
        //min speed k eat all in hrs

        //Common sense
        //h>=piles len
        //At the speed if largest pile
        //Can she eat one pile in 2 hrs
        //if h==piles.length -> k=max(piles[i]) 
        

        //How can I see that it needs binary search
        //What is the max speed at which banana can be eaten max(piles[1])
        //Hours can be greater than the number of piles so it is not mandatory to finish one pile every hour but at max one pile can be eaten every hour

        //Max number of banana in a pile = 10^9
        //The answer which is the number of bananas every hour is monotonically increasing 
        //I know the max possible answer but as the number of hours increase the smaller answers can also be valid .
        // So I need to find from the list of monotonically increasing answers the smallest speed at which I can finish the piles within the given hour

        if(piles==null || piles.length==0){
            return 0;
        }
        // int l=0; Left should not be 0
        int l=1;
        int maxPile=0;
        //Find the search space
        for(int pile:piles){
            if(pile>maxPile){
                maxPile=pile;
            }
        }

        int r=maxPile;
        //End when l==r
        while(l<r){
            int m=l+(r-l)/2;

            //Check if at the speed of m I can finish
            if(canFinish(piles,m,h)){
                //I need to check if any speed less than this is also valid
                r=m;
            }
            else{
                //I need to increase speed
                l=m+1;
            }
        }

        return l;

        
    }

    private boolean canFinish(int []piles, int k,int h){
        //Iterate through all the piles
        //At by speed add up the time taken to finish the full piles
        //Return true if total number of hrs <= h
        int totalHrs=0;
        for(long pile:piles){
            totalHrs+=(pile-1)/k+1;
        }

        return totalHrs<=h;
    }
} 
