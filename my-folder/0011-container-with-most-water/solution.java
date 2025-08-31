class Solution {
    public int maxArea(int[] height) {

        int l =0;
        int r=height.length-1;
        int maxAr=0;

        //The trick is once we the area with the smaller bar of the two pointers there will be no other bar with a greater area so 
        //we can ignore the bar

        while(l<r){

            int currAr=(r-l)*Math.min(height[l],height[r]);

            maxAr=Math.max(maxAr,currAr);

            //Even if height are equal we can ignore both or shift one 
            if(height[l]<height[r]){
                l++;
            }
            else{
                r--;
            }

        }

        return maxAr;
        
    }
}
