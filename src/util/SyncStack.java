package util;

import java.util.Stack;

/**
 * Created by David on 11.04.2017.
 */
public class SyncStack <T> {

    private Stack<T> stack;

    public SyncStack(){
        stack = new Stack<T>();
    }

    public SyncStack(T... startValues){
        this();
        for(T t:startValues){
            stack.push(t);
        }
    }

    public synchronized void push(T t){
        stack.push(t);
        notify();
    }

    public synchronized T pop(){
        while(stack.isEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return stack.pop();
    }

}
