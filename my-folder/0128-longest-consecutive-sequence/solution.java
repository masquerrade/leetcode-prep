class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> cs=new HashSet<>();
        for(int n:nums){
            cs.add(n);
        }
        //
        int lonSeq=0;
        for(Integer i:cs){
            //Need to check if it is a part of the previous sequence
            if(cs.contains(i-1)){
                continue;
            }
            else{
                int seq=0;
                //Check the length of the current sequence
                while(cs.contains(i++)){
                    seq++;
                }
                lonSeq=Math.max(seq,lonSeq);
            }
        }

        return lonSeq;
        
    }
}
