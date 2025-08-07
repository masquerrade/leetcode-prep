class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {

        //Immutable map
        //  Map<String, Integer> map = Map.of(
        //     "apple", 1,
        //     "banana", 2,
        //     "cherry", 3
        // );
        //Map<String, Integer> immutableMap = Collections.unmodifiableMap(modifiableMap);
//         Map<String, Integer> map = Map.of(
//     "Apple", 10,
//     "Banana", 20,
//     "Orange", 30
// );

// List<Integer> immutableList = List.copyOf(map.values());
// List<Integer> immutableList = Collections.unmodifiableList(valueList);


        // Immutable List



        Map<String,List<String>> mp=new HashMap<>();
        for(String s:strs){
            char[] c=s.toCharArray();
            Arrays.sort(c);
            //Convert charater array to string in Java
            String key=String.valueOf(c);
            //System.out.println(key);
            // List<String> grp=mp.getOrDefault(key,new ArrayList<>());
            // grp.add(s);
            // mp.put(key,grp);
            mp.computeIfAbsent(key,k->new ArrayList<>()).add(s);

            // Automatically creates and inserts a new list if the key doesn't exist
            //map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
            
        }

        //Creating list of values
        return new ArrayList<>(mp.values());
    }
}
