//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表

package com.chen.gu.demo.lee.easyCode.leetcode.editor.cn;

import java.util.HashMap;

//java:两数之和
public class P1_TwoSum {
    public static void main(String[] args){
        Solution solution = new P1_TwoSum().new Solution();
    }
    
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {

        /**
         * 自己的解法
         */
        //HashMap<Integer, int[]> map = new HashMap<>();
        //for (int i = 0; i < nums.length; i++) {
        //    for (int j = i + 1; j < nums.length; j++) {
        //        int sum = nums[i] + nums[j];
        //        int[] arr = {i, j};
        //        map.put(sum, arr);
        //    }
        //
        //}
        //return map.get(target);

        HashMap<Integer, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int next = target - nums[i];
            if (hashMap.containsKey(next)){
                return new int[] {hashMap.get(next), i};
            }
            hashMap.put(nums[i], i);
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}