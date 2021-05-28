package org.example.leetcode;

public class _307_NumArray {

    private final int[] cache;
    private final int[] total;
    private final int len;

    public _307_NumArray(int[] nums) {
        this.cache = nums;
        this.len = nums.length;
        int[] temp = new int[len];
        int t = 0;
        for (int i = 0; i < len; i++) {
            t += nums[i];
            temp[i] = t;
        }
        this.total = temp;
    }
    
    public void update(int index, int val) {
        int tem = cache[index] - val;
        for (int i = index; i < len; i++) {
            total[i] -= tem;
        }
        cache[index] = val;
    }
    
    public int sumRange(int left, int right) {
        int leftTotal = left == 0 ? 0 : total[left - 1];
        return total[right] - leftTotal;
    }

    public static void main(String[] args) {
        int[] ints = {1, 3, 5};
        _307_NumArray numArray = new _307_NumArray(ints);
        // 返回 9 ，sum([1,3,5]) = 9
        System.out.println(numArray.sumRange(0, 2));
        // nums = [1,2,5]
        numArray.update(1, 2);
        // 返回 8 ，sum([1,2,5]) = 8
        System.out.println(numArray.sumRange(0, 2));
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */
