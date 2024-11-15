import org.junit.*;
import static org.junit.Assert.*;

public class RPNTest {

    @Test
    public void testCalculateAdd() {
        RPN rpn = new RPN();
        String[] elements = {"3", "4", "+"};
        assertEquals(7, rpn.calculate(elements));
    }

    @Test
    public void testCalculateSub() {
        RPN rpn = new RPN();
        String[] elements = {"10", "5", "-"};
        assertEquals(5, rpn.calculate(elements));
    }

    @Test
    public void testCalculateMult() {
        RPN rpn = new RPN();
        String[] elements = {"6", "7", "*"};
        assertEquals(42, rpn.calculate(elements));
    }

    @Test
    public void testCalculateDiv() {
        RPN rpn = new RPN();
        String[] elements = {"20", "4", "/"};
        assertEquals(5, rpn.calculate(elements));
    }

    @Test
    public void testCalculatePow() {
        RPN rpn = new RPN();
        String[] elements = {"2", "3", "^"};
        assertEquals(8, rpn.calculate(elements));
    }

    @Test
    public void testCalculateModulo() {
        RPN rpn = new RPN();
        String[] elements = {"10", "3", "%"};
        assertEquals(1, rpn.calculate(elements));
    }

    @Test
    public void testCalculateComplexExpression() {
        RPN rpn = new RPN();
        String[] elements = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        assertEquals(22, rpn.calculate(elements));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooManyElements() {
        RPN rpn = new RPN();
        String[] elements = {"3", "4", "+", "5"};
        rpn.calculate(elements);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsufficientElements() {
        RPN rpn = new RPN();
        String[] elements = {"3", "+"};
        rpn.calculate(elements);
    }


    @Test(expected = ArithmeticException.class)
    public void testDivByZero() {
        RPN rpn = new RPN();
        String[] elements = {"4", "0", "/"};
        rpn.calculate(elements);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOperator() {
        RPN rpn = new RPN();
        String[] elements = {"1", "2", "@"};
        rpn.calculate(elements);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyExpression() {
        RPN rpn = new RPN();
        String[] elements = {};
        rpn.calculate(elements);
    }

}
