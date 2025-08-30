class Solution {
    public int evalRPN(String[] tokens) {
        
        Deque<Integer> st=new ArrayDeque<>();

        for(String c:tokens){
            int op1;
            int op2;
            if(c.equals("+")){
                //Operators are popped in reverse order
                op2=st.pop();
                op1=st.pop();
                st.push(op1+op2);
            }
            else if(c.equals("-")){
                op2=st.pop();
                op1=st.pop();
                st.push(op1-op2);
            }
            else if(c.equals("*")){
                op2=st.pop();
                op1=st.pop();
                st.push(op1*op2);
            }
            else if(c.equals("/")){
                op2=st.pop();
                op1=st.pop();
                st.push(op1/op2);
            }
            else{
                st.push(Integer.parseInt(c));
            }
        }

        

        return st.pop();
        
    }
}
