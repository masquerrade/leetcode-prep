class Solution {
    public int majorityElement(int[] nums) {
        //Maintain the count of the candidate majority element 
        //When count become 0 update the Majority element
        //When elm same as count increment count
        //When elm diff from count decrement count

        int candidate=nums[0];
        int count=0;

        for(int n:nums){
            if(count==0){
                candidate=n;
            }
            if(n==candidate){
                count++;
            }
            else{
                count--;
            }
        }
        return candidate;
        
    }
}
