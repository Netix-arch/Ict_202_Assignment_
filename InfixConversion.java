import java.util.Stack;

public class InfixConversion {

    // Function to define operator precedence
    static int precedence(char ch) {
        switch (ch) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // Function to check operand
    static boolean isOperand(char ch) {
        return Character.isLetterOrDigit(ch);
    }

    // Convert Infix to Postfix
    static String infixToPostfix(String exp) {
        String result = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if (isOperand(ch)) {
                result += ch;
            }
            else if (ch == '(') {
                stack.push(ch);
            }
            else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result += stack.pop();
                }
                stack.pop();
            }
            else {
                while (!stack.isEmpty() &&
                       precedence(stack.peek()) >= precedence(ch)) {
                    result += stack.pop();
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // Reverse string
    static String reverse(String exp) {
        return new StringBuilder(exp).reverse().toString();
    }

    // Convert Infix to Prefix
    static String infixToPrefix(String exp) {
        String reversed = reverse(exp);

        String modified = "";
        for (int i = 0; i < reversed.length(); i++) {
            char ch = reversed.charAt(i);

            if (ch == '(')
                modified += ')';
            else if (ch == ')')
                modified += '(';
            else
                modified += ch;
        }

        String postfix = infixToPostfix(modified);

        return reverse(postfix);
    }

    public static void main(String[] args) {
        String infix = "(A+B)*C-D";

        System.out.println("Infix Expression: " + infix);
        System.out.println("Postfix Expression: " + infixToPostfix(infix));
        System.out.println("Prefix Expression: " + infixToPrefix(infix));
    }
}
