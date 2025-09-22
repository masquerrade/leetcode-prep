class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //Only one tine sort
        Arrays.sort(candidates);
 
        List<List<Integer>> finalList=new ArrayList<>();
        combinationSum2Helper(candidates,target,new ArrayList<Integer>(),0,finalList);
        return finalList;
        
    }

    public void combinationSum2Helper(int[] candidates, int target, List<Integer> crrCmb, int canInd,List<List<Integer>> finalList) {

        //Base case
        if(target==0){
            finalList.add(new ArrayList<>(crrCmb));
            return ;
        }

        if(target<0){
            return ;
        }
        
        //No need to sort candidates again and again
        //Arrays.sort(candidates);

        for(int i=canInd;i<candidates.length;i++){

            //We can take duplicates for the first time 
            crrCmb.add(candidates[i]);
            //i+1 is passed as starting index so that each candidate is used only once
            combinationSum2Helper(candidates,target-candidates[i],crrCmb,i+1,finalList);

            //This check is necessary first to avoid index out of bounds exception
            //This is used to skip duplicates
            while((i+1)<candidates.length && candidates[i]==candidates[i+1]){
                i++;
            }

            //Removing the last index to explore other possibilities
            crrCmb.remove(crrCmb.size()-1);

        }

        return ;
        
    }
}
