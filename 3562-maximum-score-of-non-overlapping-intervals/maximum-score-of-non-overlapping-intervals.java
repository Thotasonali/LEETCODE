import java.util.*;

class Solution {
    static class Interval {
        int l, r, weight, id;

        Interval(int l, int r, int weight, int id) {
            this.l = l;
            this.r = r;
            this.weight = weight;
            this.id = id;
        }
    }

    static class State {
        long weight;
        List<Integer> indices;

        State(long weight, List<Integer> indices) {
            this.weight = weight;
            this.indices = indices;
        }

        // Prefers higher weight, then lexicographically smaller indices.
        static boolean isBetter(State candidate, State current) {
            if (candidate == null) return false;
            if (current == null) return true;
            if (candidate.weight != current.weight) {
                return candidate.weight > current.weight;
            }
            int size = Math.min(candidate.indices.size(), current.indices.size());
            for (int i = 0; i < size; i++) {
                if (!candidate.indices.get(i).equals(current.indices.get(i))) {
                    return candidate.indices.get(i) < current.indices.get(i);
                }
            }
            return candidate.indices.size() < current.indices.size();
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        Interval[] sorted = new Interval[n];
        for (int i = 0; i < n; i++) {
            List<Integer> interval = intervals.get(i);
            sorted[i] = new Interval(interval.get(0), interval.get(1), interval.get(2), i);
        }

        // Sort primarily by right endpoint, then left endpoint, then original index
        Arrays.sort(sorted, (a, b) -> {
            if (a.r != b.r) return Integer.compare(a.r, b.r);
            if (a.l != b.l) return Integer.compare(a.l, b.l);
            return Integer.compare(a.id, b.id);
        });

        // dp[k][i] stores the best State selecting k intervals from the prefix ending at index i
        State[][] dp = new State[5][n + 1];

        for (int i = 1; i <= n; i++) {
            Interval curr = sorted[i - 1];

            // Binary search for the rightmost non-overlapping interval ending strictly before curr.l
            int low = 0, high = i - 2, prevIdx = 0;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (sorted[mid].r < curr.l) {
                    prevIdx = mid + 1;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            for (int k = 1; k <= 4; k++) {
                // Option 1: Skip current interval
                State bestState = dp[k][i - 1];

                // Option 2: Choose current interval alone (k == 1)
                if (k == 1) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr.id);
                    State candidate = new State(curr.weight, list);
                    if (State.isBetter(candidate, bestState)) {
                        bestState = candidate;
                    }
                }

                // Option 3: Combine current interval with best choice of (k-1) intervals from non-overlapping prefix
                if (dp[k - 1][prevIdx] != null) {
                    State prev = dp[k - 1][prevIdx];
                    List<Integer> list = new ArrayList<>(prev.indices);
                    list.add(curr.id);
                    Collections.sort(list); // Maintain sorted index list for lexicographical comparison

                    State candidate = new State(prev.weight + curr.weight, list);
                    if (State.isBetter(candidate, bestState)) {
                        bestState = candidate;
                    }
                }

                dp[k][i] = bestState;
            }
        }

        // Find best choice across up to 4 chosen intervals
        State best = null;
        for (int k = 1; k <= 4; k++) {
            if (State.isBetter(dp[k][n], best)) {
                best = dp[k][n];
            }
        }

        if (best == null || best.indices.isEmpty()) {
            return new int[0];
        }

        int[] result = new int[best.indices.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = best.indices.get(i);
        }
        return result;
    }
}