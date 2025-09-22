class Solution {
    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer,Integer> mp=new HashMap<>();

        for(int n:nums){
            mp.compute(n,(key,v)->(v==null)?1:v+1);
        } 
        
        PriorityQueue<Map.Entry<Integer,Integer>> pq=new PriorityQueue<>((a,b)->Integer.compare(a.getValue(),b.getValue()));

        for(Map.Entry<Integer,Integer> e:mp.entrySet()){
            pq.offer(e);

            if(pq.size()>k){
                pq.poll();
            }
        }

        int [] finalArray=new int[k];

        for(int i=0;i<k;i++){
            finalArray[i]=pq.poll().getKey();
        }

        return finalArray;

    }
}
