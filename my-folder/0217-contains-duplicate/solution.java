class Solution {
    public boolean containsDuplicate(int[] nums) {
        //Hashset
        Set<Integer> elm=new HashSet<>();
        for(int i:nums){
            if(elm.add(i)){
                continue ;
            }
            else{
                return true; 
            }
        }
        return false; 
            
    }
}
