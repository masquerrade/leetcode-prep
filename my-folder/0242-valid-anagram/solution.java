class Solution {
    public boolean isAnagram(String s, String t) {

        //No need to make 2 hashmaps
        //Make one hashmap
        //If count==0 return false .
        //If count!=0 decrement and return true at the end
        if(s.length()!=t.length()){
            return false;
        }
        Map<Character,Integer> mp= new HashMap<>();

        for(char c:s.toCharArray())
        {
            mp.put(c,mp.getOrDefault(c,0)+1);
        }

        for(char c:t.toCharArray()){

            //We can combine these two if conditions
            if(mp.containsKey(c)){

                if(mp.get(c)==0){
                    return false;
                }
                else{
                    mp.put(c,mp.get(c)-1);
                }
        
            }
            else{
                return false;
            }
        }

        //System.out.println(mp);



        return true;
        
    }
}
