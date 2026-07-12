class Solution {
    public int maxProfit(int[] prices) {
        //The trick I was forgetting is since there is no possibility of maximizing the profit for multiple days . I'll maintain each state for each day . This is how we can extend this to multiple states and multiple days .

        //B1-> 1st buy
        //S1-> 1st sell
        //B2-> 2nd buy
        //S2-> 2nd sell
        //We've to track these variables for each day

        //For each transaction either buy or sell, we assume that we have the maximized profit for the previous transaction and if we make current transaction on the current day we've to find the max profit

        if(prices==null||prices.length==0){
            return 0;
        }

        //Initialize the state variables 
        int b1=-prices[0];
        int s1=0;
        int b2=-prices[0];
        int s2=0;

        //Iterate through all the all the days maintaining the max profit for each state variable
        for(int price:prices){
            b1=Math.max(b1,-price);
            s1=Math.max(s1,b1+price);
            b2=Math.max(b2,s1-price);
            s2=Math.max(s2,b2+price);
        }

        return s2;
        
        
    }
}


// class Solution {
//     public int maxProfit(int[] prices) {
//         //So I will maintain two variables in this case to maintain the current state one for transaction 1 and the other for transaction 2
//         int b1=Integer.MIN_VALUE;
//         int b2=Integer.MIN_VALUE;
//         int s1=0;
//         int s2=0;

//         for(int p:prices){
//             b1=Math.max(b1,-p);
//             s1=Math.max(s1,b1+p);
//             b2=Math.max(b2,s1-p);
//             s2=Math.max(s2,b2+p);
//         }

//         return s2;
            
//     }
// }
