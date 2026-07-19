class Solution {
    public String smallestSubsequence(String s) {
        int[] last = new int[26];
        boolean[] seen = new boolean[26];
        StringBuilder st = new StringBuilder();

        for (int i = 0; i < s.length(); i++)
            last[s.charAt(i) - 'a'] = i;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';

            if (seen[idx]) continue;

            while (st.length() > 0 &&
                   st.charAt(st.length() - 1) > c &&
                   last[st.charAt(st.length() - 1) - 'a'] > i) {
                seen[st.charAt(st.length() - 1) - 'a'] = false;
                st.deleteCharAt(st.length() - 1);
            }

            st.append(c);
            seen[idx] = true;
        }

        return st.toString();
    }
}