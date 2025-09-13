class Solution {

    //Recurcive approach
    //With memoization also getting time limit exceeded
    
    public int coinChange(int[] coins, int amount) {

        Map<Integer,Integer> mm=new HashMap<>();
         mm.put(0,0);
        return calcCC(coins,amount,mm);            
        
    }

    public int calcCC(int [] coins,int amount,Map<Integer,Integer> mm){
        
        if(amount<0){
            return -1;
        }
        else if(mm.containsKey(amount)){
            return mm.get(amount);
        }

        else{

        int minC=amount+1;

        for(int c:coins){
            int cr=calcCC(coins,amount-c,mm);
            if(cr != -1){
                minC=Math.min(minC,1+cr);
            }
            
        }

        if(minC<amount+1){
             mm.put(amount,minC);
            return minC;
        }
        else{
            mm.put(amount,-1);
            return -1;
        }
        

    }
}
}
