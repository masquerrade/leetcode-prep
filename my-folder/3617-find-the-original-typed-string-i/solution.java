class Solution {
    public int possibleStringCount(String word) {

        //Map<Integer,Long> freqMap=word.chars().mapToObj(e->e).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        int sum=1;

        for(int i=0;i<word.length()-1;i++){
            if(word.charAt(i)==word.charAt(i+1)){
                sum+=1;
            }
            
        }

        
        // System.out.println(freqMap);
        // for(Map.Entry<Integer,Long> e:freqMap.entrySet()){
        //     if(e.getValue().intValue()>1){
        //         sum+=(e.getValue().intValue()-1);
        //     }
        // }
        
         return sum;
    }
}
