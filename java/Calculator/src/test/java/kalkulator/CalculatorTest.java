package kalkulator;

import org.junit.*;
import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private Calculator sut;

    @Before
    public void setUp() {
        sut = new Calculator();
    }



    @Test
    public void testAddOne() {
        sut.add(1);
        assertEquals("0+1 = 1", 1, sut.getState());
    }


    @Test
    public void testMultOneByTwo() {
        sut.setState(1);
        sut.mult(2);
        assertEquals("1*2 = 2", 2, sut.getState());
    }

    @Test
    public void testDivFourByTwo() {
        sut.setState(4);
        sut.div(2);
        assertEquals("4/2 = 2", 2, sut.getState());
    }



    // Testy na granicach dziedziny
    @Test
    public void testAddMaxInteger() {
        sut.setState(Integer.MAX_VALUE - 1);
        sut.add(1);
        assertEquals(Integer.MAX_VALUE, sut.getState());
    }

    @Test
    public void testAddMinInteger() {
        sut.setState(Integer.MIN_VALUE + 1);
        sut.add(-1);
        assertEquals(Integer.MIN_VALUE, sut.getState());
    }


    @Test
    public void testMultiplyOverflow() {
        Calculator sut = new Calculator();
        sut.setState(Integer.MAX_VALUE);
        sut.mult(2);
        assertEquals("Wynik przekracza zakres typu int.", Integer.MAX_VALUE, sut.getState());
    }



    // Test dzielenia przez zero
    @Test
    public void testDivideByZero() {
        Calculator sut = new Calculator();
        sut.setState(10);
        sut.div(0);
        assertEquals("Nie mozna dzielić przez 0.", 10, sut.getState());
    }
    @Test
    public void testMemoryDivideByZero() {
        Calculator sut = new Calculator();
        sut.setState(0);
        sut.saveToMemory();
        sut.setState(10);
        sut.operateMemory("/");
        assertEquals("Nie można dzielić przez 0", 10, sut.getState());
    }



    @Test
    public void testMultiplyByZero() {
        sut.setState(5);
        sut.mult(0);
        assertEquals(0, sut.getState());
    }

    @Test
    public void testDivideZeroByNumber() {
        sut.setState(0);
        sut.div(5);
        assertEquals(0, sut.getState());
    }


    // Test dzielenia przez liczby ujemne
    @Test
    public void testDivideByNegativeOne() {
        sut.setState(10);
        sut.div(-1);
        assertEquals(-10, sut.getState());
    }


    // Test ustawienia stanu kalkulatora na określoną wartość
    @Test
    public void testSetState() {
        sut.setState(10);
        assertEquals(10, sut.getState());
    }

    // Test czyszczenia pamięci kalkulatora
    // Oczekujemy, że operacja z pustą pamięcią nie zmieni stanu
    @Test
    public void testClearMemory() {
        Calculator sut = new Calculator();
        sut.setState(5);
        sut.saveToMemory();
        sut.clearMemory();
        sut.operateMemory("+");
        assertEquals("Pamięć jest pusta.", 5, sut.getState());

    }


    // Test operacji dodawania bieżącej wartości do wartości przechowywanej w pamięci
    @Test
    public void testMemoryAddOperation() {
        Calculator sut = new Calculator();
        sut.setState(10);
        sut.saveToMemory();
        sut.setState(5);
        sut.operateMemory("+");
        assertEquals(15, sut.getState());
    }


}

