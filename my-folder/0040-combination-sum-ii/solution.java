class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if(candidates==null || candidates.length==0){
            return Collections.emptyList();
        }

        int[] copyCan=candidates.clone();
        //Here sorting is not optional
        Arrays.sort(copyCan);
        List<List<Integer>> result= new ArrayList<>();
        backTrack(copyCan,0,target,new ArrayList<>(),result);

        return result;
    }

    private void backTrack(int[] copyCan,int startIndex,int target,List<Integer> currentList,List<List<Integer>> result){

        if(target==0){
            //Don't miss this
            result.add(new ArrayList<>(currentList));
            return;
        }

        for(int i=startIndex;i<copyCan.length;i++){
            if(target<copyCan[i]){
                return;
            }

            if(i!=startIndex && copyCan[i]==copyCan[i-1]){
                continue;
            }

            currentList.add(copyCan[i]);
            backTrack(copyCan,i+1,target-copyCan[i],currentList,result);

            currentList.remove(currentList.size()-1);

        }
    }
}
