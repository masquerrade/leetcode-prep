//50 min analysis 

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {

        //At the same level same element should not be used twice
        //Null check
        if(nums==null){
            return Collections.emptyList();
        }

        if(nums.length==0){
            return List.of(Collections.emptyList());
        }

        //First empty listr
        List<Integer> currentList=new ArrayList<>();

        //Final result
        List<List<Integer>> result=new ArrayList<>();

        //To remove the duplicates it is important to sort
        Arrays.sort(nums);

        backTrackDuplicate(nums,0,currentList,result);

        return result;        
        
    }

    private void backTrackDuplicate(int[] nums,int startIndex,List<Integer> currentList,List<List<Integer>> result){

        //The current prefix with which this function is called is a valid subset so take a snap
        result.add(new ArrayList<>(currentList));

        //Now start looking for futher subsets with the currentIndex
        for(int i=startIndex;i<nums.length;i++){
            //Check whether the current index is not the start index and it is not same as the prev sibling\
            if(i!=startIndex && nums[i]==nums[i-1]){
                continue;
            }

            //Otherwise keep on going deeper
            currentList.add(nums[i]);
            backTrackDuplicate(nums,i+1,currentList,result);

            //All subsets of this level has been explored now remove the last element to explore the sibling levels
            currentList.remove(currentList.size()-1);
        }
    }
}
