// class Solution {
//     public int trap(int[] height) {


//         //The trick is that I need to calculate the water that can be trapped over each bar
//         //Min of highest on the left side and right side minus current height
//         int n=height.length;
//         int [] leftH=new int[n];
//         int [] rightH=new int[n];
//         leftH[0]=0;
//         rightH[n-1]=0;
//         int currMax=0;
//         int sm=0;
//         for(int i=1;i<n;i++){
//             // int currMax=Math.max(currMax,leftH[i-1])
//             leftH[i]=Math.max( leftH[i-1], height[i-1]);
//         }

//         for(int i=n-2;i>=0;i--){
//             // int currMax=Math.max(currMax,leftH[i-1])
//             rightH[i]=Math.max(rightH[i+1], height[i+1]);
//         }
//         // System.out.println(Arrays.toString(height));
//         // System.out.println(Arrays.toString(rightH));
//         // System.out.println(Arrays.toString(leftH));

//         for(int i=0;i<n;i++){
//             if((Math.min(leftH[i],rightH[i])-height[i])>0){
//                 sm+=(Math.min(leftH[i],rightH[i])-height[i]);
//             }
            
//         }

//         return sm;
        
//     }
// }
// Dynamic programming approach
// class Solution {
//     public int trap(int[] height) {
//         int trap = 0;
//         int n = height.length;
        
//         int[] left = new int[n];
//         int[] right = new int[n];
        
//         //We have to find maximum to the left and right including one self
//         left[0] = height[0];
//         for(int i = 1; i < n; i++) {
//             left[i] = Math.max(left[i-1], height[i]);
//         }
        
//         right[n-1] = height[n-1];
//         for(int i = n - 2; i >=0; i--) {
//             right[i] = Math.max(right[i+1], height[i]);
//         }

//         // System.out.println(Arrays.toString(height));
//         // System.out.println(Arrays.toString(right));
//         // System.out.println(Arrays.toString(left));
        
//         for(int i = 0; i < n; i++) {
//             trap += Math.min(left[i], right[i]) - height[i];
//         }
        
//         return trap;
//     }
// }

//2 Pointer approach O(1) space soln
//The logic is that we come from the two ends and decide for which end I can calculate the water .
//If we know that one end(right) is taller or equal to other end that water on left will not be decided by the right side bars 
//Then we check whether the current bar is the maximum left .If so no water is stored on its top else water equal to difference in height is stored



class Solution {
    public int trap(int[] height) {
        int l=0;
        int r=height.length-1;
        int maxLeft=0;
        int wt=0;
        int maxRight=0;
        while(l<r){
            if(height[l]<=height[r]){
                if(height[l]>maxLeft){
                    maxLeft=height[l];
                }
                else{
                    wt+=(maxLeft-height[l]);
                }
                l++;

            }
            else{
                if(height[r]>maxRight){
                    maxRight=height[r];
                }
                else{
                    wt+=(maxRight-height[r]);
                }
                r--;
            }

        }
        return wt;
    }
}

