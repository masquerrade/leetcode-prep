class Solution {
    public boolean isValid(String s) {

        Deque<Character> st=new LinkedList<>();
        // char [] car=s.toCharArray();
        // System.out.println(Arrays.toString(car));
        // return false;
        //If opening bracket then push
        //If closing bracket then pop and it should match with the closing bracket
        //At the end there should be no element in the stack
        

        for(char c: s.toCharArray()){
            if(c=='('||c=='{'||c=='['){
                st.push(c);
            }
            else{
                if(st.size()==0){
                    //System.out.println("Stack Empty "+st);
                    return false;
                }
                else{
                    //Keep in mind that opening bracket will always be in stack
                    
                    if(!isMatching(c,st.pop())){
                        //System.out.println("Character Mismatch ch= "+ch+" c="+c);
                        return false;
                    }                    
                }
               
            }

        }

        if(st.size()==0){
            return true;
        }
        else{
            //System.out.println(st);
            //System.out.println("Still remaining"+st);
            return false;
        }
    }

    public static boolean isMatching(Character ch,Character c){
        if(c.equals('(') && ch.equals(')') || c.equals('{') && ch.equals('}') || c.equals('[') && ch.equals(']')){
            return true;
        }
        else{
            return false;
        }
    }
}
