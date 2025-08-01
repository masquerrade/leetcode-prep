//HashSet approach
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        //[-1,0,1,2,-1,-4]
        //Sort
        Arrays.sort(nums);
        
        //List<List<Integer>> lst= new ArrayList<>();

        //To avaoid duplicate I need to store in set and return
        Set<List<Integer>> fin=new HashSet<>();

        for(int i=0;i<nums.length;i++){
            //For first go duplicates should also be considered
            int j=i+1;
            //Two Sum approach
            Set<Integer> set= new HashSet<>();
            while(j<nums.length){
                int target=0-nums[i]-nums[j];
                // System.out.println("i="+i+" j="+j+" target="+target);
                // System.out.println("Set="+set);
                if(set.contains(target)){
                    //Add the new list
                    List<Integer> triplet=Arrays.asList(nums[i],nums[j],target);
                    //Sorting list
                    triplet.sort(Comparator.naturalOrder());
                    fin.add(triplet);
                }
                else{
                    //Add the value in the set
                    set.add(nums[j]);
                }

                j++;
            }
            while(i<(nums.length-1) && nums[i]==nums[i+1]){
                i++;
            }
        }

        return new ArrayList<>(fin);
        
        
    }
}
