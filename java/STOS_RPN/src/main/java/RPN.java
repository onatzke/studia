public class RPN {

    public int calculate(String[] elements) {
        if (elements == null || elements.length == 0) {
            throw new IllegalArgumentException("Wyrażenie nie może być null/puste");
        }

        Stack stack = new Stack();

        for (String element : elements) {
            if (isOperator(element)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Niewystarczająca liczba elementów do wykonania operacji");
                }
                int y = Integer.parseInt(stack.pop());
                int x = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(performOperation(element, x, y)));
            } else {
                try {
                    stack.push(element);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Niepoprawny element: " + element);
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Za dużo elementów w wyrażeniu");
        }
        return Integer.parseInt(stack.pop());  // jeśli po obliczeniu został dokładnie 1 element czyli wynik,
                                              // to jest on zwracany
    }

    private boolean isOperator(String element) {
        return "+-*/^%".contains(element);
    }

    private int performOperation(String operator, int x, int y) {
        switch (operator) {
            case "+":
                return x + y;
            case "-":
                return x - y;
            case "*":
                return x * y;
            case "/":
                if (y == 0) throw new ArithmeticException("Nie można dzielić przez 0");
                return x / y;
            case "^":
                return (int) Math.pow(x, y);
            case "%":
                if (y == 0) throw new ArithmeticException("Nie można dzielić przez 0");
                return x % y;
            default:
                throw new IllegalArgumentException("Niewłaściwy operator: " + operator);
        }
    }
}
