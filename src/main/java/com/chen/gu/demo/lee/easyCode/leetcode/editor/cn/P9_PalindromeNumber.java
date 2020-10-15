//判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
//
// 示例 1: 
//
// 输入: 121
//输出: true
// 
//
// 示例 2: 
//
// 输入: -121
//输出: false
//解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
// 
//
// 示例 3: 
//
// 输入: 10
//输出: false
//解释: 从右向左读, 为 01 。因此它不是一个回文数。
// 
//
// 进阶: 
//
// 你能不将整数转为字符串来解决这个问题吗？ 
// Related Topics 数学

package com.chen.gu.demo.lee.easyCode.leetcode.editor.cn;

//java:回文数
class P9_PalindromeNumber {
    public static void main(String[] args){
        Solution solution = new P9_PalindromeNumber().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isPalindrome(int x) {

            if (x < 0 || (x % 10 == 0 && x != 0)){
                return false;
            }
            int res = 0;
            while (x > res){
                res = res * 10 + x % 10;
                x = x / 10;
            }
            return res == x || res / 10 == x;
        }

        public boolean ownIsPalindrome(int x) {

            if (x < 0){
                return false;
            }
            String[] split = String.valueOf(x).split("");
            for (int i = 0; i < split.length / 2 + 1; i++) {
                if (!split[i].equals(split[split.length - 1 - i])){
                    return false;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
