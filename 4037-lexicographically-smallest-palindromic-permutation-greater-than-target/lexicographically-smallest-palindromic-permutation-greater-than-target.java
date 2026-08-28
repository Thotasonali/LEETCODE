import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;
        int[] count = new int[26];
        
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        char center = 0;
        int oddCount = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                center = (char) ('a' + i);
            }
        }
        
        if (oddCount > 1) {
            return "";
        }
        
        int[] avail = new int[26];
        for (int i = 0; i < 26; i++) {
            avail[i] = count[i] / 2;
        }
        
        // 1. Check exact prefix match
        int[] workAvail = avail.clone();
        boolean canMatchPrefix = true;
        char[] firstHalf = new char[half];
        
        for (int i = 0; i < half; i++) {
            int idx = target.charAt(i) - 'a';
            if (workAvail[idx] > 0) {
                firstHalf[i] = target.charAt(i);
                workAvail[idx]--;
            } else {
                canMatchPrefix = false;
                break;
            }
        }
        
        if (canMatchPrefix) {
            String candidate = buildPalindrome(new String(firstHalf), center);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }
        
        // 2. Backtrack: Find longest valid prefix matching target[0...i-1], 
        // then place a character > target[i] at position i
        for (int i = half - 1; i >= 0; i--) {
            // Count frequency of target[0...i-1]
            int[] prefixCount = new int[26];
            boolean validPrefix = true;
            for (int k = 0; k < i; k++) {
                int idx = target.charAt(k) - 'a';
                prefixCount[idx]++;
                if (prefixCount[idx] > avail[idx]) {
                    validPrefix = false;
                    break;
                }
            }
            
            if (!validPrefix) continue;
            
            // Remaining available pairs after using target[0...i-1]
            int[] rem = avail.clone();
            for (int k = 0; k < 26; k++) {
                rem[k] -= prefixCount[k];
            }
            
            int targetChar = target.charAt(i) - 'a';
            for (int j = targetChar + 1; j < 26; j++) {
                if (rem[j] > 0) {
                    rem[j]--;
                    
                    StringBuilder prefix = new StringBuilder(target.substring(0, i));
                    prefix.append((char) ('a' + j));
                    
                    for (int k = 0; k < 26; k++) {
                        while (rem[k] > 0) {
                            prefix.append((char) ('a' + k));
                            rem[k]--;
                        }
                    }
                    
                    return buildPalindrome(prefix.toString(), center);
                }
            }
        }
        
        // 3. Fallback: If no prefix matching target[0...i-1] works, 
        // try placing the smallest character at index 0 that is strictly greater than target[0]
        int targetFirst = target.charAt(0) - 'a';
        for (int j = targetFirst + 1; j < 26; j++) {
            if (avail[j] > 0) {
                int[] rem = avail.clone();
                rem[j]--;
                StringBuilder prefix = new StringBuilder();
                prefix.append((char) ('a' + j));
                for (int k = 0; k < 26; k++) {
                    while (rem[k] > 0) {
                        prefix.append((char) ('a' + k));
                        rem[k]--;
                    }
                }
                return buildPalindrome(prefix.toString(), center);
            }
        }
        
        return "";
    }
    
    private String buildPalindrome(String firstHalf, char center) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (center != 0) {
            sb.append(center);
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}