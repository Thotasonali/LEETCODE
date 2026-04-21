class Solution {
    public int maxDistance(int[] colors) {
        int max = 0;
        int n = colors.length;
        
        // Compare every color with the first color
        for (int i = 1; i < n; i++) {
            if (colors[i] != colors[0]) {
                max = Math.max(max, i);
            }
        }
        
        // Compare every color with the last color
        for (int i = 0; i < n - 1; i++) {
            if (colors[i] != colors[n - 1]) {
                max = Math.max(max, (n - 1) - i);
            }
        }
        
        return max;
    }
}
