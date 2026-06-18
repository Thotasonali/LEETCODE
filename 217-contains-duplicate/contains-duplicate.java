class Solution {
    public boolean containsDuplicate(int[] nums) {
        int size = 1;

        while (size < nums.length * 4) {
            size <<= 1;
        }

        int[] keys = new int[size];
        boolean[] used = new boolean[size];
        int mask = size - 1;

        for (int num : nums) {
            int idx = hash(num) & mask;

            while (used[idx]) {
                if (keys[idx] == num) {
                    return true;
                }

                idx = (idx + 1) & mask;
            }

            used[idx] = true;
            keys[idx] = num;
        }

        return false;
    }

    private int hash(int x) {
        x ^= x >>> 16;
        x *= 0x7feb352d;
        x ^= x >>> 15;
        x *= 0x846ca68b;
        x ^= x >>> 16;
        return x;
    }
}
/*class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> check=new HashSet<>();
        for(int num:nums ){
            if(!check.add(num)){
                return true;
            }
            else{
                check.add(num);
            }
        }
        return false;


        
    }
}
*/