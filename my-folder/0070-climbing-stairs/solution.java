class Solution {
    //Memoization otimized approach
    public int climbStairs(int n) {
        if(n<=2){
            return n;
        }
        int curr=2;
        int prev=1;

        //Current element depend on the last 2 so no need to store in array

        for(int i=3;i<=n;i++){
            int temp=curr;
            curr=curr+prev;
            prev=temp;
        }

        return curr;
   
    }

   
}
