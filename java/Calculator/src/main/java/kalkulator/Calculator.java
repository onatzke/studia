package kalkulator;
import java.util.Scanner;

public class Calculator {

    private int state = 0;
    private Integer memory = null;  // Wartość przechowywana w pamięci kalkulatora, początkowo pusta

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Integer getMemory() {
        return memory;
    }



    // operacje na pamięci
    public void saveToMemory() {
        memory = state;
        System.out.println("Zapisano w pamięci: " + memory);
    }

    public void clearMemory() {
        memory = null;
        System.out.println("Wyczyszczono pamięć");
    }

    // Wykonuje operację na bieżącym wyniku z wartością z pamięci
    public void operateMemory(String operation) {
        if (memory != null) {
            switch (operation) {
                case "+":
                    add(memory);
                    break;
                case "-":
                    sub(memory);
                    break;
                case "*":
                    mult(memory);
                    break;
                case "/":
                    div(memory);
                    break;
            }
        } else {
            System.out.println("Pamięć jest pusta.");
        }
    }




    public void add(int value) {
        try {
            state = Math.addExact(state, value);
        } catch (ArithmeticException e) {
            System.out.println("Wynik przekracza zakres typu int.");
        }
    }
    // Jeśli wynik przekroczy zakres wartości typu int, Math.addExact zgłasza wyjątek ArithmeticException,
    // wtedy program przechodzi do bloku catch, wyświetla komunikat o błędzie,
    // a bieżaca wartość state nie zmienia się


    public void sub(int value) {
        try {
            state = Math.subtractExact(state, value);
        } catch (ArithmeticException e) {
            System.out.println("Wynik przekracza zakres typu int.");
        }
    }

    public void mult(int value) {
        try {
            state = Math.multiplyExact(state, value);
        } catch (ArithmeticException e) {
            System.out.println("Wynik przekracza zakres typu int.");
        }
    }

    public void div(int value) {
        if (value == 0) {
            System.out.println("Nie można dzielić przez 0.");
            return;
        }
        state /= value;
    }


    // Konwersja na int: Integer.parseInt(...) próbuje przekonwertować wprowadzony
    // ciąg znaków na liczbę całkowitą typu int, jeśli mogą zostać
    // przekonwertowane na int, metoda zwraca tę liczbę, przerywając pętlę
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowa wartość. Podaj liczbę całkowitą/mniejszą.");
            }
        }
    }


    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nWybierz operację:");
        System.out.println("1. Dodawanie (+)");
        System.out.println("2. Odejmowanie (-)");
        System.out.println("3. Mnożenie (*)");
        System.out.println("4. Dzielenie (/)");
        System.out.println("5. Zmień wynik");
        System.out.println("6. Zapisz do pamięci");
        System.out.println("7. Operacja z liczbą zapisaną w pamięci");
        System.out.println("8. Wyczyść pamięć");
        System.out.println("9. Wyjście");

        while (true) {
            System.out.println("\nAktualny wynik: " + calc.getState());
            if (calc.getMemory() != null) {
                System.out.println("Wartość w pamięci: " + calc.getMemory());
            }

            String wybor = scanner.nextLine();

            if (wybor.equals("9")) {
                break;
            }

            if (wybor.equals("7")) {  // Operacje na wartości zapisanej w pamięci
                System.out.println("Wybierz operację z liczbą zapisaną w pamięci:");
                System.out.println("+ : Dodawanie");
                System.out.println("- : Odejmowanie");
                System.out.println("* : Mnożenie");
                System.out.println("/ : Dzielenie");
                String memoryOperation = scanner.nextLine();
                if ("+-*/".contains(memoryOperation)) {
                    calc.operateMemory(memoryOperation);
                } else {
                    System.out.println("Nieprawidłowa operacja");
                }
                continue;
            }

            switch (wybor) {
                case "1":
                    calc.add(getIntInput(scanner, "Podaj liczbę do dodania:"));
                    break;
                case "2":
                    calc.sub(getIntInput(scanner, "Podaj liczbę do odjęcia:"));
                    break;
                case "3":
                    calc.mult(getIntInput(scanner, "Podaj liczbę do pomnożenia:"));
                    break;
                case "4":
                    calc.div(getIntInput(scanner, "Podaj liczbę do podzielenia:"));
                    break;
                case "5":
                    calc.setState(getIntInput(scanner, "Podaj nową wartość:"));
                    break;
                case "6":
                    calc.saveToMemory();
                    break;
                case "8":
                    calc.clearMemory();
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór, wybierz liczbę od 1 do 9");
            }
        }

        scanner.close();
    }
}
