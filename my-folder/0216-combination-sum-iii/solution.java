class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {

        List<List<Integer>> finalCom=new ArrayList<>();
        combSumHelper(k,n,finalCom,new ArrayList<>(),1);

        return finalCom;
        
    }
    public void combSumHelper(int k, int n,List<List<Integer>> finalCom,List<Integer> currCom,int currCan){
        //System.out.println(currCom);
        if(n==0 && currCom.size()==k){
            finalCom.add(new ArrayList<>(currCom));
            return;
        }

        if(n<0 || currCom.size()==k){
            return ;
        }

        for(int i=currCan;i<=9;i++){
            //currCan
            currCom.add(i);
            //[1,2]
            //currCan will be same for all the iteration so can't pass that
            combSumHelper(k,n-i,finalCom,currCom,i+1);

            currCom.remove(currCom.size()-1);


        }
    }
}
