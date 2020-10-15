//给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。 
//
// 示例 1: 
//
// 输入: 123
//输出: 321
// 
//
// 示例 2: 
//
// 输入: -123
//输出: -321
// 
//
// 示例 3: 
//
// 输入: 120
//输出: 21
// 
//
// 注意: 
//
// 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231, 231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。 
// Related Topics 数学

package com.chen.gu.demo.lee.easyCode.leetcode.editor.cn;
//java:整数反转
class P7_ReverseInteger {
    public static void main(String[] args){
        Solution solution = new P7_ReverseInteger().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int reverse(int x) {
        //    String value = String.valueOf(x);
        //    boolean startsWith = value.startsWith("-");
        //    value = value.replace("-", "");
        //    char[] array = value.toCharArray();
        //    StringBuffer buffer = new StringBuffer();
        //    for (int i = array.length - 1; i >= 0 ; i--) {
        //        buffer.append(String.valueOf(array[i]));
        //    }
        //    String str = buffer.toString();
        //    Integer integer = 0;
        //    if (str.length() >= 10 ){
        //        String substring = str.substring(0, str.length() - 5);
        //        String substring2 = str.substring(str.length() - 5, str.length() );
        //        if (str.length() > 10 || Integer.valueOf(substring) > 21474 || (str.length() == 10 && Integer.valueOf(substring2) > 83647)){
        //            integer = 0;
        //        } else {
        //            String string = buffer.toString();
        //            integer = Integer.valueOf(string);
        //            if (startsWith){
        //                integer = Integer.valueOf("-".concat(string));
        //            }
        //            if ((!startsWith && integer > 2147483647) || (startsWith && integer < -2147483647)){
        //                integer = 0;
        //            }
        //        }
        //    } else {
        //        String string = buffer.toString();
        //        integer = Integer.valueOf(string);
        //        if (startsWith){
        //            integer = Integer.valueOf("-".concat(string));
        //        }
        //        if ((!startsWith && integer > 2147483647) || (startsWith && integer < -2147483647)){
        //            integer = 0;
        //        }
        //    }
        //    return integer;
            int res = 0;
            int num = 0;
            while (x != 0){
                num = x % 10;
                x = x / 10;
                if (res > 214748364 || (res == 214748364 && num > 7)){
                    return 0;
                }
                if (res < -214748364 || (res == -214748364 && num < -8)){
                    return 0;
                }
                res = res * 10 + num;
            }
            return res;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}