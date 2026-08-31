class Solution {
    public List<List<Integer>> subsets(int[] nums) {

        //Common sense tells 
        //0 at a time
        //1 a time looking forward
        //2 at a time 
        //3 at a time 
        
        //Pretty good question
        //If array is null return empty list \
        if(nums==null || nums.length==0){
           return  Collections.emptyList();
        }

        int n=nums.length;

        //Now I know the total number of subsets
        if(n>=31){
            throw new IllegalArgumentException("Total number of elements can't be greater than 30");
        }

        int size=1<<n;

        List<List<Integer>> subsetList=new ArrayList<>(size);

        //There will be a list of current subset
        //Max possible depth of every list is n so use it
        List<Integer> currentSubset=new ArrayList<>(n);

        //I need to pass by reference the curent list and the final list to the backtrack function.
        //Since I need to avoid duplicates and look strictly forward I'll pass the start Index

        backtrack(nums,0,currentSubset,subsetList);

        return subsetList;


    }

    private void backtrack(int[] nums,int startIndex,List<Integer> currentSubset,List<List<Integer>> subsetList){

        //What would be my base case
        if(nums.length<=startIndex){
            //Fucking caught the error> I need to insert the snapshot not the reference to the list
            // subsetList.add(currentSubset);
            subsetList.add(new ArrayList<>(currentSubset));
            return;
        }
        //When do I realize that I need to add currentSubset to the sebsetList


        //The start Index is the index I need to start making my subset
        //In the current iteration I need to include the current element and then find all the subsets which include the current element

        currentSubset.add(nums[startIndex]);
        // I chose the current element and moved forward 
        backtrack(nums,startIndex+1,currentSubset,subsetList);

        //currentSubset.remove(nums[startIndex]); To avoid ambiguity whther to remove by value or index remove the last most element
        //Now I've to unchoose the current element and move forward
        currentSubset.remove(currentSubset.size()-1);
        backtrack(nums,startIndex+1,currentSubset,subsetList);

    }

}
