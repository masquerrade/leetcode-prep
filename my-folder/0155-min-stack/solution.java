class MinStack {

    Deque<Integer> s1;
    Deque<Integer> s2;

    public MinStack() {
        //Declare two stack
        s1=new ArrayDeque<>();
        s2=new ArrayDeque<>(); 
    }
    
    public void push(int val) {
        s1.push(val);
        if(s2.isEmpty()){
            s2.push(val);
        }
        else{
            //Here equality is required to handle duplicate values
            if(val<=s2.peek()){
                s2.push(val);
            }
        }
        
    }
    
    public void pop() {
        if(s1.peek().equals(s2.peek())){
            s1.pop();
            s2.pop();
        }
        else{
            s1.pop();
        }
        
    }
    
    public int top() {
        return s1.peek();
    }
    
    public int getMin() {
        return s2.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
