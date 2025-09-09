class Solution {
    public int maxProfit(int k, int[] prices) {

        //Mainitain the state and 
        //Two events can happen on daily basis either buy or sell And this can happen k times
        //So we need to make a record of ith buy and ith sell
        //So we are maintaining this in two arrays
        //And we will update the whole array on daily basis

        //Unlimited transaction
        if(k>prices.length/2){

            int maxP=0;

            for(int i=1;i<prices.length;i++){
                if(prices[i-1]<prices[i]){
                    maxP+=(prices[i]-prices[i-1]);
                }
            }

            return maxP;

        }
        else{

            
        //k+1 as the first index is only used as a placeholder
        int [] bdp=new int [k+1];
        Arrays.fill(bdp,Integer.MIN_VALUE);
        int [] sdp=new int [k+1];

        bdp[0]=-prices[0];

        for(int price:prices){

            for(int i=1;i<=k;i++){//k times
            //In one iteration I'm still in the same day
            //1st buy profit is stored in the 1st index
            bdp[i]=Math.max(bdp[i],sdp[i-1]-price);
            //1st sell profit is stored in the 1st index
            sdp[i]=Math.max(sdp[i],bdp[i]+price);
        //     System.out.println(Arrays.toString(bdp));
        // System.out.println(Arrays.toString(sdp));
        }


        }
        
        // System.out.println(Arrays.toString(prices));

        return sdp[k];       

            
        }


        
    }
}
