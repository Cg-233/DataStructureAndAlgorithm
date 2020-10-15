//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计

package com.chen.gu.demo.lee.easyCode.leetcode.editor.cn;
//java:最小栈
public class P155_MinStack{
    public static void main(String[] args){
        MinStack solution = new P155_MinStack().new MinStack();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class MinStack {

    /** initialize your data structure here. */

    private int[] stack = new int[10];
    private int count = 0;
    private int size = 10;
    private Integer min;
    public MinStack() {

    }
    
    public void push(int x) {
        if (count >= size){
            int[] newStack = new int[size * 2];
            size = size * 2;
            for (int i = 0; i < stack.length; i++) {
                newStack[i] = stack[i];
            }
            stack = newStack;
        }
        if (count == 0){
            min = x;
        }
        stack[count] = x;
        count++;
        if (min > x){
            min = x;
        }
    }
    
    public void pop() {
        count--;
        if (count == 0){
            min = null;
            return;
        }
        if (stack[count] == min){
            min = stack[count - 1];
            for (int i = 0; i < count; i++) {
                if (min > stack[i]){
                    min = stack[i];
                }
            }
        }
    }
    
    public int top() {
        return stack[count - 1];
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)


}