class Solution {
    public long maxTotalValue(int[] nums, int k) {
        int n = nums.length;

        SegmentTree minTree = new SegmentTree(nums, true);
        SegmentTree maxTree = new SegmentTree(nums, false);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Long.compare(b.value, a.value));

        // For every start index, first use the largest subarray [start..n-1]
        for (int start = 0; start < n; start++) {
            long maxVal = maxTree.query(start, n - 1);
            long minVal = minTree.query(start, n - 1);
            pq.offer(new Node(maxVal - minVal, start, n - 1));
        }

        long answer = 0;

        while (k > 0) {
            Node cur = pq.poll();

            answer += cur.value;
            k--;

            // Shrink the right side and add next candidate for same left index
            if (cur.left < cur.right) {
                int newRight = cur.right - 1;

                long maxVal = maxTree.query(cur.left, newRight);
                long minVal = minTree.query(cur.left, newRight);

                pq.offer(new Node(maxVal - minVal, cur.left, newRight));
            }
        }

        return answer;
    }

    static class Node {
        long value;
        int left;
        int right;

        Node(long value, int left, int right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static class SegmentTree {
        int size;
        int[] tree;
        boolean isMinTree;

        SegmentTree(int[] nums, boolean isMinTree) {
            this.isMinTree = isMinTree;

            int n = nums.length;
            size = 1;

            while (size < n) {
                size *= 2;
            }

            tree = new int[size * 2];

            if (isMinTree) {
                Arrays.fill(tree, Integer.MAX_VALUE);
            } else {
                Arrays.fill(tree, Integer.MIN_VALUE);
            }

            for (int i = 0; i < n; i++) {
                tree[size + i] = nums[i];
            }

            for (int i = size - 1; i >= 1; i--) {
                tree[i] = combine(tree[2 * i], tree[2 * i + 1]);
            }
        }

        int combine(int a, int b) {
            if (isMinTree) {
                return Math.min(a, b);
            } else {
                return Math.max(a, b);
            }
        }

        int query(int left, int right) {
            left += size;
            right += size;

            int result;

            if (isMinTree) {
                result = Integer.MAX_VALUE;
            } else {
                result = Integer.MIN_VALUE;
            }

            while (left <= right) {
                if (left % 2 == 1) {
                    result = combine(result, tree[left]);
                    left++;
                }

                if (right % 2 == 0) {
                    result = combine(result, tree[right]);
                    right--;
                }

                left /= 2;
                right /= 2;
            }

            return result;
        }
    }
}