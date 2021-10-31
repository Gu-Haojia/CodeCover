import java.util.HashSet;
import java.util.Set;

class Solution {
    private int res = Integer.MAX_VALUE;
    private int[] hands = new int[5];  // 手里每种球的个数（该变量是为了防止dfs得传5个参并方便状态序列化）
    private Set<String> memo = new HashSet<>();  // 保存曾经遇到的hands的状态（再次遇到包含的状态就可以跳过）

    public int findMinStep(String board, String hand) {  /* main */
        for (char h : hand.toCharArray())
            ++hands[getId(h)];
        dfs(board, 0);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void dfs(String b, int step) {
        b = eliminate(b);
        if (b.length() == 0) {  // 如果全部消除了
            res = Math.min(res, step);
            return;
        }
        if (step == hands.length )  // 没全部消除但手里没球了，或当前步数已经太多了
            return;
        if (step >= res)
            return;

        String cur = b + hands;  // 随便找一种方式保留状态
        if (memo.contains(cur))
            return;
        for (int i = 0; i < hands.length; ++i) {
            if (hands[i] == 0)
                continue;
            --hands[i];
            for (int j = 0; j < b.length(); ++j) {
                String bb=b.substring(0, j) + getChar(i) + b.substring(j);
                dfs(bb, step + 1);
            }
            ++hands[i];
        }
        memo.add(cur);
    }

    private String eliminate(String board) {
        int start = 0;  // 连续相同字符串的打头位置
        int len = board.length();
        for (int i = 1; i <= len; ++i)
            if (i == len || board.charAt(i) != board.charAt(i - 1)) {  // 后缀由相同字母构成或连续串断开
                int temp= i - start;
                if (temp >= 3)
                    return eliminate(board.substring(0, start) + board.substring(i));
                start = i;
            }
        return board;
    }

    private int getId(char c) {
        return switch (c) {
            case 'W' -> 0;
            case 'B' -> 1;
            case 'Y' -> 2;
            case 'G' -> 3;
            default -> 4;  // 'R'
        };
    }

    private char getChar(int id) {
        return switch (id) {
            case 0 -> 'W';
            case 1 -> 'B';
            case 2 -> 'Y';
            case 3 -> 'G';
            default -> 'R';  // 4
        };
    }
}