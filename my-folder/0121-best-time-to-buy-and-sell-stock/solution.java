class Solution {
    public int maxProfit(int[] price) {
        int min = price[0];
        int k = 0;
        int l = 0;
        int ans = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < price.length; i++) {

            if (min > price[i]) {
                min = price[i];

                max = min;

            }
            if (max < price[i]) {
                max = price[i];

        }
        
        ans=Math.max(ans,((max+1)-(min+1)));
        
        }

    return ans;
    }
}
