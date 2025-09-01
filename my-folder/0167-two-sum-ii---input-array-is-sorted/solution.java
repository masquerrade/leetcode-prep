class Solution {
    public int[] twoSum(int[] numbers, int target) {

        //numbers = [2,7,11,15], target = 9
        int l=0,r=numbers.length-1;
        //Two pointer method we can use to reduce the space complexity
        while(l<r){
            int curSum=numbers[l]+numbers[r];
            if(curSum==target){
                return new int[] {l+1,r+1};
            }
            else if(curSum<target){
                l++;
            }
            else{
                r--;
            }
            
        }
        return new int[] {-1,-1};
    }
}
