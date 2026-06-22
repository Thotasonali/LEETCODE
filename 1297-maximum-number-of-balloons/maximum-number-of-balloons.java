class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] cnt = new int[26];

        for (char c : text.toCharArray()) {
            cnt[c - 'a']++;
        }

        cnt['l' - 'a'] /= 2;
        cnt['o' - 'a'] /= 2;

        return Math.min(
            Math.min(
                Math.min(cnt['b' - 'a'], cnt['a' - 'a']),
                Math.min(cnt['l' - 'a'], cnt['o' - 'a'])
            ),
            cnt['n' - 'a']
        );
    }
}