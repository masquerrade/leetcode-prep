class Solution {
    public int findLHS(int[] nums) {
        Map<Integer,Long> freqMap=Arrays.stream(nums).boxed().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        // Map<Integer,Integer> freqMap=new HashMap<>();

        // for(int i:nums){
        //     freqMap.put(i,freqMap.getOrDefault(i,0)+1);
        // }

        //System.out.println(freqMap);

        //Map<Integer,Integer> sortedMap=freqMap.stream().sorted(Map.Entry.comparingByKey()).map(e->e.getValue()).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e1,LinkedHashMap::new));

        Long maxSub=0l;
        for(Map.Entry<Integer,Long> e:freqMap.entrySet()){
            if(freqMap.containsKey(e.getKey()+1)){
                maxSub=Math.max(maxSub,(e.getValue()+freqMap.get(e.getKey()+1)));               
            }          
        }
        return maxSub.intValue();

        
    }
}
