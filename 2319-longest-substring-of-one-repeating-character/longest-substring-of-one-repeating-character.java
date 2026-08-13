class Solution {
    static class Node {
        char firstChar, lastChar;
        int max;
        int prefix;
        int suffix;
        int len;

        Node(char c) {
            this.firstChar = c;
            this.lastChar = c;
            this.max = 1;
            this.prefix = 1;
            this.suffix = 1;
            this.len = 1;
        }

        Node() {}
    }

    private Node[] tree;
    private char[] chars;

    private Node merge(Node left, Node right) {
        Node parent = new Node();
        parent.len = left.len + right.len;
        parent.firstChar = left.firstChar;
        parent.lastChar = right.lastChar;

        parent.max = Math.max(left.max, right.max);
        
        // Merge prefix
        if (left.prefix == left.len && left.lastChar == right.firstChar) {
            parent.prefix = left.len + right.prefix;
        } else {
            parent.prefix = left.prefix;
        }

        // Merge suffix
        if (right.suffix == right.len && right.firstChar == left.lastChar) {
            parent.suffix = right.len + left.suffix;
        } else {
            parent.suffix = right.suffix;
        }

        // Check seamless boundary transition
        if (left.lastChar == right.firstChar) {
            parent.max = Math.max(parent.max, left.suffix + right.prefix);
        }

        return parent;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(chars[start]);
            return;
        }
        int mid = start + (end - start) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    private void update(int node, int start, int end, int idx, char ch) {
        if (start == end) {
            tree[node] = new Node(ch);
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, ch);
        } else {
            update(2 * node + 1, mid + 1, end, idx, ch);
        }
        tree[node] = merge(tree[2 * node], tree[2 * node + 1]);
    }

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        chars = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char ch = queryCharacters.charAt(i);
            update(1, 0, n - 1, idx, ch);
            ans[i] = tree[1].max;
        }

        return ans;
    }
}