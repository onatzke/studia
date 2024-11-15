import org.junit.*;
import java.util.EmptyStackException;
import static org.junit.Assert.assertEquals;


public class StackTest {

	@Test
	public void testPopAfterPush() {
		Stack stack = new Stack();
		stack.push("napis");
		String result = stack.pop();

		assertEquals("pop after push", "napis", result);
	}

	@Test
	public void testPeekAfterPush() {
		Stack stack = new Stack();
		stack.push("napis");
		String result = stack.peek();

		assertEquals("peek after push", "napis", result);
		assertEquals("size after peek", 1, stack.size());
	}

	@Test
	public void testPushAndPop() {
		Stack stack = new Stack();
		stack.push("napis1");
		stack.push("napis2");

		assertEquals("napis2", stack.pop());
		assertEquals("napis1", stack.pop());

		assertEquals("rozmiar stosu powinien wynosić 0", 0, stack.size());

	}

	@Test
	public void testPushMultipleElements() {
		Stack stack = new Stack();
		stack.push("napis1");
		stack.push("napis2");
		stack.push("napis3");

		assertEquals("rozmiar po wstawieniu kilku elementów", 3, stack.size());
		assertEquals("peek po wstawieniu kilku elementów", "napis3", stack.peek());
	}

	@Test(expected = EmptyStackException.class)
	public void testPeekOnEmptyStack() {
		Stack stack = new Stack();
		stack.peek();
	}

	@Test(expected = EmptyStackException.class)
	public void testPopOnEmptyStack() {
		Stack stack = new Stack();
		stack.pop();
	}

	@Test
	public void testResize() {
		Stack stack = new Stack();
		for (int i = 0; i < 20; i++) {
			stack.push("napis" + i);
		}

			assertEquals("zmiana rozmiaru", 20, stack.size());
	}

}
