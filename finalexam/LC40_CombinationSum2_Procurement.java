
import java.util.*;

public class LC40_CombinationSum2_Procurement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        Arrays.sort(candidates); // 必須排序以方便去重

        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);

        for (List<Integer> comb : res) {
            for (int x : comb) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    private static void backtrack(int[] nums, int remain, int start, List<Integer> path, List<List<Integer>> res) {
        if (remain == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 剪枝：剩餘值小於當前數字，後面更大就不用試
            if (nums[i] > remain) {
                break;
            }
            // 去重：同層重複元素跳過
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            path.add(nums[i]);
            backtrack(nums, remain - nums[i], i + 1, path, res); // i+1 保證每個數字僅用一次
            path.remove(path.size() - 1);
        }
    }
}
