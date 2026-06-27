import java.util.*;

class Solution {
    public int maximumLength(int[] nums) {
        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num, freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        if (freq.containsKey(1L)) {
            int ones = freq.get(1L);
            ans = (ones % 2 == 0) ? ones - 1 : ones;
        }

        for (long x : freq.keySet()) {
            if (x == 1) continue;

            long cur = x;
            int len = 0;

            while (freq.getOrDefault(cur, 0) >= 2) {
                len += 2;

                if (cur > 31622) break;

                cur *= cur;
            }
            if (freq.getOrDefault(cur, 0) == 1) {
                len++;
            } else {
                
                len--;
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}