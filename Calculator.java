// Cianee Sumowalt
// March 3 2023
// CalculatorApp

// Cianee Sumowalt

// March 3 2023

// CalculatorApp



import java.util.Scanner;

import java.util.Stack;



public class Calculator<E> {

    private Stack<Integer> operandStack; // the numbers in the equation
    private Stack<Character> operatorStack; // + - / *

    public Calculator() {
        this.operandStack = new Stack<Integer>();
        this.operatorStack = new Stack<Character>();
    }

    private int pemdas(char operator) {

        // determines the order of operations

        switch (operator) {

            case '+':

            case '-':

                return 1;

            case '*':

            case '/':

                return 2;

            default:

                return -1;

        }

    }



    private void performOperation() {

        // evaluates the equation

        char operator = operatorStack.pop();

        int operand2 = operandStack.pop();

        int operand1 = operandStack.pop();



        switch(operator) {

            case '+':

                operandStack.push(operand1 + operand2);

                break;

            case '-':

                operandStack.push(operand1 - operand2);

                break;

            case '*':

                operandStack.push(operand1 * operand2);

                break;

            case '/':

                if (operand2 == 0) {

                    operandStack.push(Integer.MIN_VALUE);

                    break;

                } else {

                    operandStack.push(operand1 / operand2);

                }

                break;

        }

    }



    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        Calculator<Number> calculator = new Calculator<>();

        String equation = in.nextLine();

        in.close();

        for (int i = 0; i < equation.length(); i++) { // separates the operands and operators
            char ch = equation.charAt(i);

            if (ch >= '0' && ch <= '9') {

                int operand = ch - '0';

                while (i + 1 < equation.length() && equation.charAt(i + 1) >= '0' && equation.charAt(i + 1) <= '9') {

                    operand = operand * 10 + (equation.charAt(i + 1) - '0');

                    i++;

                }

                if (i > 0 && (equation.charAt(i - 1) == '-' || equation.charAt(i - 1) == '+')) {

                    operand *= equation.charAt(i - 1) == '-' ? -1 : 1;

                    calculator.operandStack.push(Integer.valueOf(operand));

                } else {

                    calculator.operandStack.push(Integer.valueOf(operand));

                }

            } else if (ch == '-') {

    

                if (i + 1 < equation.length() && equation.charAt(i + 1) >= '0' && equation.charAt(i + 1) <= '9') {

                    // negative numbers

                    int operand = -(equation.charAt(i + 1) - '0');

                    i++;

                    while (i + 1 < equation.length() && equation.charAt(i + 1) >= '0' && equation.charAt(i + 1) <= '9') {

                        operand = operand * 10 - (equation.charAt(i + 1) - '0');

                        i++;

                    }

                    calculator.operandStack.push(Integer.valueOf(operand));

                } else {

                    // treat as an operator

                    while (!calculator.operatorStack.empty() && calculator.pemdas(ch) <= calculator.pemdas(calculator.operatorStack.peek())) {

                        calculator.performOperation();

                    }

                    calculator.operatorStack.push(ch);

                }

            } else if (ch == '+' || ch == '*' || ch == '/') {

                while (!calculator.operatorStack.empty() && calculator.pemdas(ch) <= calculator.pemdas(calculator.operatorStack.peek())) {

                    calculator.performOperation();

                }

                calculator.operatorStack.push(ch);

            }

        }



        while (!calculator.operatorStack.empty()) {

            calculator.performOperation();

        }



        int result = calculator.operandStack.pop().intValue();

         if (result <= -9999 || result >= 9999){ // if the second operand == 0, it pushes the lowest integer value. If the result is equal to that, return undef

            System.out.println("undef");

        } else{System.out.println(result);

        }

    }

}



