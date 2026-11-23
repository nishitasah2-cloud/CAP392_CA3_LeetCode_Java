import java.util.Scanner;
class Solution {
    public int[] twoSum(int[] nums, int target) {
         try{
            if (nums == null){
                throw new IllegalArgumentException("Array cannot be null");
            }
            HashMap<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++){
                int remaining = target - nums[i];

                if (map.containsKey(remaining)){
                    return new int[]{map.get(remaining), i};
                }
                map.put(nums[i], i);
            }
            throw new IllegalArgumentException("No valid pair found");

        }catch (IllegalArgumentException e){
            throw e;
        }
    }
}
