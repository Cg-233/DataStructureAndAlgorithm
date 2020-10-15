//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 注意空字符串可被认为是有效字符串。 
//
// 示例 1: 
//
// 输入: "()"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "()[]{}"
//输出: true
// 
//
// 示例 3: 
//
// 输入: "(]"
//输出: false
// 
//
// 示例 4: 
//
// 输入: "([)]"
//输出: false
// 
//
// 示例 5: 
//
// 输入: "{[]}"
//输出: true 
// Related Topics 栈 字符串

package com.chen.gu.demo.lee.easyCode.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 java:有效的括号
 */
public class P20_ValidParentheses{
    public static void main(String[] args){
        Solution solution = new P20_ValidParentheses().new Solution();
        //boolean valid = solution.isValid("()[]{}");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
    public boolean isValidOwn(String s) {

        Map<String, String> rule = new HashMap<>();
        rule.put(")","(");
        rule.put("]","[");
        rule.put("}","{");
        List<String> keys = new ArrayList<>(rule.keySet());
        List<String> values = new ArrayList<>(rule.values());

        String[] stack = new String[s.length() * 2];
        int count = 0;
        String[] chars = s.split("");
        for (int i = 0; i < chars.length; i++) {

            if (values.contains(chars[i])) {
                stack[count] = chars[i];
                count++;
                continue;
            }
            if (keys.contains(chars[i])){
                if (count <= 0){
                    return false;
                }
                if (stack[count - 1].equals(rule.get(chars[i]))){
                    count--;
                    continue;
                }


            }
            return false;
        }
        return count == 0;
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(char c: s.toCharArray()){
            if(c=='('){
                stack.push(')');
            }
            else if(c=='['){
                stack.push(']');
            }
            else if(c=='{'){
                stack.push('}');
            }
            else if(stack.isEmpty()||c!=stack.pop()){
                return false;
            }
        }
        return stack.isEmpty();
    }

}

    //leetcode submit region end(Prohibit modification and deletion)

}