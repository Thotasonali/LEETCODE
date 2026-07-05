class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int MOD = 1_000_000_007;
        int n = board.size();
        int[][] score = new int[n][n];
        int[][] ways = new int[n][n];
        for (int[] row : score) {
            Arrays.fill(row, -1);
        }
        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;
        int[][] dirs = {{1, 0}, {0, 1}, {1, 1}};
        for (int r = n - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                char ch = board.get(r).charAt(c);
                if (ch == 'X' || ch == 'S') continue;
                for (int[] d : dirs) {
                    int nr = r + d[0];
                    int nc = c + d[1];
                    if (nr < n && nc < n && score[nr][nc] != -1) {
                        int val = ch == 'E' ? 0 : ch - '0';
                        int newScore = score[nr][nc] + val;
                        if (newScore > score[r][c]) {
                            score[r][c] = newScore;
                            ways[r][c] = ways[nr][nc];
                        } else if (newScore == score[r][c]) {
                            ways[r][c] = (ways[r][c] + ways[nr][nc]) % MOD;
                        }
                    }
                }
            }
        }
        if (score[0][0] == -1) return new int[]{0, 0};
        return new int[]{score[0][0], ways[0][0]};
    }
}