import java.util.EmptyStackException;

public class Stack {
	private String[] elements;
	private int size;

	public Stack() {
		this.elements = new String[10];
		this.size = 0;
	}

	public void push(String element) {
		if (size == elements.length) { // jeśli tablica jest pełna, zwiększa się jej rozmiar
			resize();
		}
		elements[size++] = element;
	}

	public String pop() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elements[--size];
	}

	public String peek() {
		if (size == 0) {
			throw new EmptyStackException();
		}
		return elements[size - 1];
	}

	public int size() {  // rozmiar stosu, używany w testach
		return size;
	}

	private void resize() {
		String[] newElements = new String[elements.length * 2]; // zwiększanie rozmiaru tablicy
		System.arraycopy(elements, 0, newElements, 0, size);
		elements = newElements;
	}
}
