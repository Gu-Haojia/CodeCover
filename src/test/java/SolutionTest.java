import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void Test1() {
        Solution testsolution=new Solution();
        int answer;
        answer= testsolution.findMinStep("WRRBBW","RB");
        assertEquals(-1,answer);
    }

    @Test
    public void Test2() {
        Solution testsolution=new Solution();
        int answer;
        answer= testsolution.findMinStep("WWRRBBWW","WRBRW");
        assertEquals(2,answer);
    }

    @Test
    public void Test3() {
        Solution testsolution=new Solution();
        int answer;
        answer= testsolution.findMinStep("G","GGGGG");
        assertEquals(2,answer);
    }

    @Test
    public void Test4() {
        Solution testsolution=new Solution();
        int answer;
        answer= testsolution.findMinStep("RBYYBBRRB","YRBGB");
        assertEquals(3,answer);
    }


}