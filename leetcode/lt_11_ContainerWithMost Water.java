
class Solution {

    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = 0;

        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            maxArea = Math.max(maxArea, h * width);

            // 移動比較矮的那一邊
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

/*
解題思路：
1. 題目要求找到能裝最多水的容器，容器由兩條線和 x 軸組成。
2. 使用雙指標法：一個指向最左，一個指向最右。
3. 每次計算面積 = min(左高, 右高) × 寬度，更新最大值。
4. 移動較矮的指標，因為高度是限制瓶頸，移動較高的線只會讓寬度變小而高度不變。
5. 時間複雜度 O(n)，只需遍歷一次陣列。
 */
